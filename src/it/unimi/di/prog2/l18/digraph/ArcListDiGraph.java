package it.unimi.di.prog2.l18.digraph;

import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class ArcListDiGraph<T> implements DiGraph<T> {

  private final Set<T> nodes = new HashSet<>();
  private final Set<Arc<T>> arcs = new HashSet<>();

  @Override
  public void addArc(T source, T destination) {
    arcs.add(new Arc<>(source, destination));
    addNode(source);
    addNode(destination);
  }

  @Override
  public void addNode(T node) {
    nodes.add(node);
  }

  @Override
  public Collection<T> nodes() {
    return Collections.unmodifiableCollection(nodes);
  }

  @Override
  public Collection<Arc<T>> arcs() {
    return Collections.unmodifiableCollection(arcs);
  }

}
