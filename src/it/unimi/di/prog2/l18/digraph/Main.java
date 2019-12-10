package it.unimi.di.prog2.l18.digraph;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Main {
  public static void main(String[] args) {
    DiGraph<String> g = new ArcListDiGraph<>();

    DiGraph<String> gg = new AdjacencyMapDiGraph<>();
    DiGraphs.fromAdjacencyMap(Map.of("one", Set.of("two", "three"), "two", Set.of("one", "three")),
        gg);
    System.out.println(gg.nodes());

    g.addArc("mio", "tuo");
    g.addArc("mio", "suo");
    g.addArc("tuo", "nostro");
    g.addArc("suo", "nostro");

    System.out.println(g.nodes());
    System.out.println(g.arcs());

    g.visit("mio", Consumers.printConsumer(), Suppliers.FIFOSupplier());
    g.visit("mio", System.out::println, Suppliers.LIFOSupplier());
    List<String> result = new ArrayList<>();
    g.visit("mio", Consumers.collectConsumer(result), Suppliers.LIFOSupplier());
    System.out.println(result);

    System.out.print(DiGraphs.toDot(g));
  }
}
