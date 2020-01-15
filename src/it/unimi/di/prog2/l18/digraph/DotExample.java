/*

Copyright 2019 Massimo Santini

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

package it.unimi.di.prog2.l18.digraph;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.function.Function;

/** An example classe creating a {@link ImplicitDiGraph} and rendering it to a PDF file. */
public class DotExample {

  public static void main(String[] args) throws IOException {

    if (args.length != 3) {
      System.out.println("DotExample: start stop path");
      System.exit(-1);
    }

    int start = -1, stop = -1;
    try {
      start = Integer.parseInt(args[0]);
      stop = Integer.parseInt(args[1]);
    } catch (NumberFormatException e) {
      System.out.println("DotExample: start stop path");
      System.exit(-1);
    }

    List<Integer> nodes = new ArrayList<>();
    for (int i = start; i < stop; i++) nodes.add(i);

    Function<Integer, Collection<Integer>> delta =
        new Function<Integer, Collection<Integer>>() {
          @Override
          public Collection<Integer> apply(Integer t) {
            return List.of(t + 1, 2 * t);
          }
        };

    ImplicitDiGraph<Integer> g = new ImplicitDiGraph<Integer>(nodes, delta);
    DiGraphs.dotToPdf(
        DiGraphs.toDot(
            g, "node [shape=point];\nedge [arrowhead=none];\nratio = expand\nsize = \"10,10!\""),
        args[2]);
  }
}
