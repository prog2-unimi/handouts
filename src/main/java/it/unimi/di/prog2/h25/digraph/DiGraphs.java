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

package it.unimi.di.prog2.h25.digraph;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Collection;
import java.util.Map;

/**
 * A collection of utilities related to {@link DiGraph}s.
 *
 * <p>This class provides a few utility methods, mainly related to <a
 * href="https://www.graphviz.org/">Graphviz</a>.
 */
public class DiGraphs {

  private DiGraphs() {}

  public static <T> void fromAdjacencyMap(
      final Map<T, Collection<T>> adjacency, final DiGraph<T> graph) {
    for (Map.Entry<T, Collection<T>> e : adjacency.entrySet()) {
      T src = e.getKey();
      for (T dst : e.getValue()) graph.addArc(src, dst);
    }
  }

  /**
   * Returns the <em>dot</em> representation of the given graph.
   *
   * @param <T> the type of the graph nodes.
   * @param g the graph.
   * @return the <em>dot</em> representation of the given graph.
   */
  public static <T> String toDot(final DiGraph<T> g) {
    return toDot(g, null);
  }

  /**
   * Returns the <em>dot</em> representation of the given graph.
   *
   * @param <T> the type of the graph nodes.
   * @param g the graph.
   * @param extra extra configuration to be added to the <em>dot</em> source.
   * @return the <em>dot</em> representation of the given graph.
   */
  public static <T> String toDot(final DiGraph<T> g, final String extra) {
    StringBuilder b = new StringBuilder();
    b.append("digraph G {\n");
    if (extra != null) b.append(extra + "\n");
    for (Arc<T> arc : g.arcs())
      b.append(String.format("\t%s -> %s;\n", arc.source.toString(), arc.destination.toString()));
    b.append("}\n");
    return b.toString();
  }

  /**
   * Renders a <em>dot</em> source to a PDF file (invoking an external command).
   *
   * @param dot the <em>dot</em> source
   * @param path path of the PDF to create.
   */
  public static void dotToPdf(final String dot, final String path) {
    final ProcessBuilder pb = new ProcessBuilder("sfdp", "-T", "pdf");
    pb.redirectOutput(new File(path));
    try {
      final Process p = pb.start();
      final OutputStream os = p.getOutputStream();
      os.write(dot.getBytes());
      os.close();
      p.waitFor();
    } catch (IOException | InterruptedException e) {
      System.err.println("Something went wrong");
    }
  }
}
