/*

Copyright 2023 Massimo Santini

This file is part of "Programmazione 2 @ UniMI" teaching material.

This is free software: you can redistribute it and/or modify
it under the terms of the GNU General Public License as published by
the Free Software Foundation, either version 3 of the License, or
(at your option) any later version.

This material is distributed in the hope that it will be useful,
but WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
GNU General Public License for more details.

You should have received a copy of the GNU General Public License
along with this file.  If not, see <https://www.gnu.org/licenses/>.

*/

package it.unimi.di.prog2;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.DynamicTest.dynamicTest;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Stream;
import org.junit.jupiter.api.DynamicTest;
import org.junit.jupiter.api.function.Executable;

public class BlackBoxTestsGenerator {

  public static final Pattern TASK_PATTERN = Pattern.compile("expected-(\\d+).txt");
  public static final String EXPECTED_FORMAT = "expected-%d.txt";
  public static final String ARGS_FORMAT = "args-%d.txt";
  public static final String INPUT_FORMAT = "input-%d.txt";

  public final Path testsDir;

  public class BalckBoxTest {
    public final String name;
    private final Method main;
    private final Path path;
    final List<DynamicTest> cases = new ArrayList<>();

    public class Case implements Executable {
      private final String[] args;
      private final byte[] input, expected;

      public Case(int n) throws IOException {
        Path input = path.resolve(String.format("input-%d.txt", n));
        Path args = path.resolve(String.format("args-%d.txt", n));
        Path expected = path.resolve(String.format("expected-%d.txt", n));
        this.input = input.toFile().exists() ? Files.readAllBytes(input) : new byte[0];
        this.args =
            args.toFile().exists()
                ? Files.readAllLines(args).toArray(new String[0])
                : new String[0];
        this.expected = Files.readAllBytes(expected);
      }

      public void execute() {
        InputStream stdin = System.in;
        PrintStream stdout = System.out;
        System.setIn(new ByteArrayInputStream(input));
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        System.setOut(new PrintStream(baos));
        try {
          main.invoke(null, (Object) args.clone());
        } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
        }
        try {
          baos.close();
        } catch (IOException e) {
        }
        byte[] out = baos.toByteArray();
        System.setIn(stdin);
        System.setOut(stdout);
        String[] ol = new String(out).split("\n");
        String[] el = new String(expected).split("\n");
        assertArrayEquals(el, ol);
      }
    }

    private BalckBoxTest(final String name)
        throws NoSuchMethodException, SecurityException, ClassNotFoundException, IOException {
      this.name = Objects.requireNonNull(name);
      this.path = testsDir.resolve(name.replace(".", File.separator));
      this.main = Class.forName(name).getMethod("main", String[].class);
      try (DirectoryStream<Path> stream = Files.newDirectoryStream(path, "expected-*.txt")) {
        for (Path path : stream) {
          if (!Files.isDirectory(path)) {
            Matcher m = TASK_PATTERN.matcher(path.getFileName().toString());
            if (m.matches())
              cases.add(
                  dynamicTest(name + " - " + m.group(1), new Case(Integer.parseInt(m.group(1)))));
          }
        }
      }
    }
  }

  public BlackBoxTestsGenerator(String testsDir) {
    this.testsDir = Paths.get(testsDir); // TODO: check not null and readable
  }

  public Stream<DynamicTest> test(final String name) {
    BalckBoxTest bbt = null;
    List<DynamicTest> cases = new ArrayList<>();
    try {
      bbt = new BalckBoxTest(name);
      cases = bbt.cases;
    } catch (NoSuchMethodException | SecurityException | ClassNotFoundException e) {
      cases.add(
          dynamicTest(
              "NoSuchMethodException",
              () -> {
                throw e;
              }));
    } catch (IOException e) {
      cases.add(
          dynamicTest(
              "IOException",
              () -> {
                throw e;
              }));
    }
    return cases.stream();
  }
}
