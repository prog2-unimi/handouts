package it.unimi.di.prog2.l17.digraph;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Collection;
import java.util.Map;

public class DiGraphs {

  private DiGraphs() {}

  public static <T> void fromAdjacencyMap(final Map<T, Collection<T>> adjacency,
      final DiGraph<T> graph) {
    for (Map.Entry<T, Collection<T>> e : adjacency.entrySet()) {
      T src = e.getKey();
      for (T dst : e.getValue())
        graph.addArc(src, dst);
    }
  }

  public static <T> String toDot(final DiGraph<T> g) {
    return toDot(g, null);
  }

  public static <T> String toDot(final DiGraph<T> g, final String extra) {
    StringBuilder b = new StringBuilder();
    b.append("digraph G {\n");
    if (extra != null)
      b.append(extra + "\n");
    for (Arc<T> arc : g.arcs())
      b.append(String.format("\t%s -> %s;\n", arc.source.toString(), arc.destination.toString()));
    b.append("}\n");
    return b.toString();
  }

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
