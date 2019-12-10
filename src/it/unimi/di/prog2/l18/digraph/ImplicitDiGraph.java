package it.unimi.di.prog2.l18.digraph;

import java.util.Collection;
import java.util.Collections;
import java.util.function.Function;

public class ImplicitDiGraph<T> implements DiGraph<T> {

  private final Collection<T> nodes;
  private final Function<T, Collection<T>> delta;

  public ImplicitDiGraph(final Collection<T> nodes, final Function<T, Collection<T>> delta) {
    this.nodes = Collections.unmodifiableCollection(nodes);
    this.delta = delta;
  }

  @Override
  public Collection<T> nodes() {
    return nodes;
  }

  @Override
  public Collection<T> outgoing(T source) {
    return delta.apply(source);
  }

}
