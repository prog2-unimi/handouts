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

import it.unimi.di.prog2.h25.digraph.ArcListDiGraph;
import it.unimi.di.prog2.h25.digraph.Consumers;
import it.unimi.di.prog2.h25.digraph.DiGraph;
import java.util.Queue;
import java.util.Scanner;
import java.util.function.Supplier;

/** Tests <em>directed graph</em> package with visits. */
public class DiGraphVisitClient {

  /**
   * Reads a graph from the standard input and performs a visit.
   *
   * <p>More precisely, reads a sequence of lines, each containing two strings separated by a space
   * corresponding to the source and destination of an arc. The first source is the starting node of
   * the visit. Then it performs a visit using a {@link Queue} supplier that is either a {@link
   * Queues#FIFOSupplier()} or a {@link Queues#LIFOSupplier()}) according to the first letter of the
   * first argument.
   *
   * @param args the first argument is either {@code F} for a FIFO visit, or some other value for a
   *     LIFO visit.
   */
  public static void main(String[] args) {
    final DiGraph<String> G = new ArcListDiGraph<>();
    final Supplier<Queue<String>> supplier =
        args[0].charAt(0) == 'F' ? Queues.FIFOSupplier() : Queues.LIFOSupplier();
    String start = null;
    try (Scanner s = new Scanner(System.in)) {
      while (s.hasNextLine()) {
        final String[] srcDst = s.nextLine().trim().split("\\s+");
        G.addArc(srcDst[0], srcDst[1]);
        if (start == null) start = srcDst[0];
      }
    }
    G.visit(start, Consumers.printConsumer(), supplier);
  }
}
