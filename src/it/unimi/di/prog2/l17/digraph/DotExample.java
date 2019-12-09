package it.unimi.di.prog2.l17.digraph;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.function.Function;

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
    for (int i = start; i < stop; i++)
      nodes.add(i);

    Function<Integer, Collection<Integer>> delta = new Function<Integer, Collection<Integer>>() {
      @Override
      public Collection<Integer> apply(Integer t) {
        return List.of(t + 1, 2 * t);
      }
    };

    ImplicitDiGraph<Integer> g = new ImplicitDiGraph<Integer>(nodes, delta);
    DiGraphs.dotToPdf(
        DiGraphs.toDot(g,
            "node [shape=point];\nedge [arrowhead=none];\nratio = expand\nsize = \"10,10!\""),
        args[2]);

  }

}
