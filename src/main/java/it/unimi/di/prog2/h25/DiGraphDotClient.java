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

package it.unimi.di.prog2.h25;

import it.unimi.di.prog2.h25.digraph.DiGraphs;
import it.unimi.di.prog2.h25.digraph.ImplicitDiGraph;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.function.Function;

/** Tests <em>directed graph</em> package generating a Dot file. */
public class DiGraphDotClient {

  /**
   * Generates an {@link ImplicitDiGraph}.
   *
   * <p>More precisely, generates a graph with nodes in the range {@code [start, stop[]} and arcs
   * {@code (x, x + 1)} and {@code (x, 2 * x)} and prints its Dot serialization on the standard
   * output.
   *
   * @param args the {@code start} and {@code stop} values.
   * @throws IOException
   */
  public static void main(String[] args) throws IOException {

    int start = -1, stop = -1;
    start = Integer.parseInt(args[0]);
    stop = Integer.parseInt(args[1]);

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
    System.out.println(
        DiGraphs.toDot(
            g, "node [shape=point];\nedge [arrowhead=none];\nratio = expand\nsize = \"10,10!\""));
  }
}
