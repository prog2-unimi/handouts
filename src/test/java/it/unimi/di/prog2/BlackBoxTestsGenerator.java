/*

Copyright 2021 Luca Prigioniero, Massimo Santini
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
import static org.junit.jupiter.api.Assertions.assertTimeoutPreemptively;
import static org.junit.jupiter.api.Assertions.fail;
import static org.junit.jupiter.api.Assumptions.assumeTrue;
import static org.junit.jupiter.api.DynamicContainer.dynamicContainer;
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
import java.time.Duration;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.junit.jupiter.api.DynamicContainer;
import org.junit.jupiter.api.DynamicNode;
import org.junit.jupiter.api.DynamicTest;
import org.junit.jupiter.api.function.Executable;

public class BlackBoxTestsGenerator {

  public final Path testsDir;
  public final boolean generateActualFiles = System.getenv("GENERATE_ACTUAL_FILES") != null;

  public class BlackBoxTest {

    public static final Duration TIMEOUT = Duration.ofSeconds(10);
    public static final String ARGS_FORMAT = "args-%d.txt";
    public static final String INPUT_FORMAT = "input-%d.txt";
    public static final String EXPECTED_FORMAT = "expected-%d.txt";
    public static final String ACTUAL_FORMAT = "actual-%d.txt";
    public static final Pattern TASK_PATTERN = Pattern.compile("expected-(\\d+).txt");

    private final Method main;
    private final Path path;
    private final List<DynamicTest> cases;

    private class Case implements Executable {
      private final int num;
      private final String[] args;
      private final byte[] input;
      private final List<String> expected;

      private Case(int num) throws IOException {
        this.num = num;
        Path args = path.resolve(String.format(ARGS_FORMAT, num));
        Path input = path.resolve(String.format(INPUT_FORMAT, num));
        Path expected = path.resolve(String.format(EXPECTED_FORMAT, num));
        this.input = input.toFile().exists() ? Files.readAllBytes(input) : new byte[0];
        this.args =
            args.toFile().exists()
                ? trim(Files.readAllLines(args)).toArray(new String[0])
                : new String[0];
        this.expected = trim(Files.readAllLines(expected));
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
          baos.close();
          if (generateActualFiles)
            Files.write(path.resolve(String.format(ACTUAL_FORMAT, num)), baos.toByteArray());
        } catch (IllegalAccessException
            | IllegalArgumentException
            | InvocationTargetException
            | IOException e) {
          fail("Error executing tests", e);
        }
        System.setIn(stdin);
        System.setOut(stdout);
        assertIterableEquals(expected, trim(Arrays.asList(baos.toString().split("\n"))));
      }
    }

    private DynamicContainer container() {
      return dynamicContainer(path.getFileName().toString(), cases);
    }

    private List<DynamicTest> cases() {
      return cases;
    }

    private BlackBoxTest(final Path fClsPath) {
      this.path = testsDir.resolve(Objects.requireNonNull(fClsPath));
      String fqClsName = fClsPath.toString().replace(File.separator, ".");
      Method main = null;
      List<DynamicTest> cases = new ArrayList<>();
      try {
        main = Class.forName(fqClsName).getMethod("main", String[].class);
      } catch (NoSuchMethodException | SecurityException | ClassNotFoundException e) {
        cases.add(
            dynamicTest(
                fqClsName + " [missing main method]",
                () -> {
                  assumeTrue(false, "Main not found");
                }));
      }
      this.main = main;
      if (main != null)
        try (DirectoryStream<Path> stream = Files.newDirectoryStream(path, "expected-*.txt")) {
          for (Path path : stream) {
            Matcher m = TASK_PATTERN.matcher(path.getFileName().toString());
            if (m.matches()) {
              String name = fqClsName + " - " + m.group(1);
              try {
                final Case tc = new Case(Integer.parseInt(m.group(1)));
                cases.add(
                    dynamicTest(
                        name,
                        () -> {
                          assertTimeoutPreemptively(TIMEOUT, tc);
                        }));
              } catch (IOException e) {
                cases.add(
                    dynamicTest(
                        name + " [problem reading testcase]",
                        () -> {
                          fail("Problems reading test case", e);
                        }));
              }
            }
          }
        } catch (IOException e) {
          cases.add(
              dynamicTest(
                  fqClsName + " [missing tests dir]",
                  () -> {
                    fail("Problems reading tests", e);
                  }));
        }
      this.cases = Collections.unmodifiableList(cases);
    }
  }

  public BlackBoxTestsGenerator(String testsDir) {
    this.testsDir = Paths.get(Objects.requireNonNull(testsDir));
  }

  private List<DynamicContainer> wrap(List<BlackBoxTest> tc) {
    return tc.stream().map(t -> t.container()).toList();
  }

  public List<? extends DynamicNode> test(final String pkgName) {
    Objects.requireNonNull(pkgName);
    Path subDir = Paths.get(pkgName.replace(".", File.separator));
    Map<Path, List<BlackBoxTest>> p2t = new HashMap<>();
    try {
      Files.find(
              testsDir.resolve(subDir),
              Integer.MAX_VALUE,
              (p, a) -> a.isDirectory() && p.resolve("expected-1.txt").toFile().exists())
          .forEach(
              p ->
                  p2t.computeIfAbsent(
                          testsDir.resolve(subDir).relativize(p.getParent()),
                          k -> new LinkedList<>())
                      .add(new BlackBoxTest(testsDir.relativize(p))));
    } catch (IOException e) {
      return List.of(
          dynamicTest(
              pkgName + " [missing tests dir]",
              () -> {
                fail("Problems reading tests", e);
              }));
    }
    if (p2t.size() == 0)
      return List.of(
          dynamicTest(
              pkgName + " [missing testcases]",
              () -> {
                fail("No testcases found");
              }));
    else if (p2t.size() == 1) {
      List<BlackBoxTest> lt = p2t.entrySet().iterator().next().getValue();
      if (lt.size() == 1) return lt.get(0).cases();
      else return wrap(lt);
    } else {
      return p2t.entrySet().stream()
          .map(
              e ->
                  dynamicContainer(
                      e.getKey().toString().isEmpty() ? "[pkg]" : e.getKey().toString(),
                      wrap(e.getValue())))
          .toList();
    }
  }
}
