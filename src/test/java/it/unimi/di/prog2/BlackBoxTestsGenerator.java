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

import static org.junit.jupiter.api.Assertions.assertIterableEquals;
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
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Stream;
import org.junit.jupiter.api.Assumptions;
import org.junit.jupiter.api.DynamicTest;
import org.junit.jupiter.api.function.Executable;

public class BlackBoxTestsGenerator {

  public static final Pattern TASK_PATTERN = Pattern.compile("expected-(\\d+).txt");
  public static final String EXPECTED_FORMAT = "expected-%d.txt";
  public static final String ARGS_FORMAT = "args-%d.txt";
  public static final String INPUT_FORMAT = "input-%d.txt";

  public final Path testsDir;
  public final int keep;

  public class BalckBoxTest {
    public final String name;
    private final Method main;
    private final Path path;
    final List<DynamicTest> cases = new ArrayList<>();

    public class Case implements Executable {
      private final String[] args;
      private final byte[] input;
      private List<String> expected;

      public Case(int n) throws IOException {
        Path input = path.resolve(String.format("input-%d.txt", n));
        Path args = path.resolve(String.format("args-%d.txt", n));
        Path expected = path.resolve(String.format("expected-%d.txt", n));
        this.input = input.toFile().exists() ? Files.readAllBytes(input) : new byte[0];
        this.args =
            args.toFile().exists()
                ? trim(Files.readAllLines(args)).toArray(new String[0])
                : new String[0];
        this.expected = trim(Files.readAllLines(expected));
      }

      private static List<String> toLines(ByteArrayOutputStream baos) {
        try {
          baos.close();
        } catch (IOException e) {
        }
        return trim(Arrays.asList(baos.toString().split("\n")));
      }

      private static List<String> trim(List<String> in) {
        List<String> out = new ArrayList<>();
        for (String s : in) {
          String t = s.trim();
          if (s.isEmpty()) continue;
          out.add(t);
        }
        return Collections.unmodifiableList(out);
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
        System.setIn(stdin);
        System.setOut(stdout);
        assertIterableEquals(expected, toLines(baos));
      }
    }

    private static String simplify(String s, int k) {
      if (k <= 0) return s;
      int n = s.length();
      while (k-- > 0) n = s.lastIndexOf(".", n - 1);
      return s.substring(n + 1);
    }

    private BalckBoxTest(final String name) {
      this.name = Objects.requireNonNull(name);
      this.path = testsDir.resolve(name.replace(".", File.separator));
      String simpleName = simplify(name, keep);
      Method main = null;
      try {
        main = Class.forName(name).getMethod("main", String[].class);
      } catch (NoSuchMethodException | SecurityException | ClassNotFoundException e) {
        cases.add(
            dynamicTest(
                simpleName + " [missing main method]",
                () -> {
                  Assumptions.assumeTrue(false, "Main not found");
                }));
      }
      this.main = main;
      if (main == null) return;
      try (DirectoryStream<Path> stream = Files.newDirectoryStream(path, "expected-*.txt")) {
        for (Path path : stream) {
          Matcher m = TASK_PATTERN.matcher(path.getFileName().toString());
          DynamicTest tc = null;
          if (m.matches())
            try {
              tc =
                  dynamicTest(
                      simpleName + " - " + m.group(1), new Case(Integer.parseInt(m.group(1))));
            } catch (IOException e) {
              tc =
                  dynamicTest(
                      simpleName + " - " + m.group(1) + " [problem reading testcase]",
                      () -> {
                        Assumptions.assumeTrue(false, "Problems reading test case");
                      });
            }
          cases.add(tc);
        }
      } catch (IOException e) {
        cases.add(
            dynamicTest(
                simpleName + " [missing tests dir]",
                () -> {
                  Assumptions.assumeTrue(false, "Problems reading tests");
                }));
      }
    }
  }

  public BlackBoxTestsGenerator(String testsDir) {
    this(testsDir, -1);
  }

  public BlackBoxTestsGenerator(String testsDir, int keep) {
    this.testsDir = Paths.get(Objects.requireNonNull(testsDir));
    this.keep = keep;
  }

  public Stream<DynamicTest> test(final String name) {
    return new BalckBoxTest(name).cases.stream();
  }

  public static void main(String[] args) throws IOException {
    Files.find(
            Paths.get("tests/it/unimi/di/prog2/h03"),
            Integer.MAX_VALUE,
            (p, a) -> a.isDirectory() && p.resolve("expected-1.txt").toFile().exists())
        .forEach(System.out::println);
  }
}
