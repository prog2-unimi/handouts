package it.unimi.di.prog2.l17.digraph;

import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class AdjacencyMapDiGraph<T> implements DiGraph<T> {

  private final Map<T, Set<T>> adjacency = new HashMap<>();

  @Override
  public void addArc(final T src, final T dst) {
    Set<T> outgoing = adjacency.get(src);
    if (outgoing == null)
      adjacency.put(src, outgoing = new HashSet<>());
    outgoing.add(dst);
    addNode(dst);
  }

  @Override
  public void addNode(T node) {
    if (!adjacency.containsKey(node))
      adjacency.put(node, new HashSet<>());
  }

  @Override
  public boolean hasArc(T src, T dst) {
    final Set<T> outgoing = adjacency.get(src);
    if (outgoing == null)
      return false;
    return outgoing.contains(dst);
  }

  @Override
  public Collection<T> outgoing(T src) {
    final Set<T> outgoing = adjacency.get(src);
    if (outgoing == null)
      return Collections.emptyList();
    return Collections.unmodifiableCollection(outgoing);
  }

  @Override
  public Collection<T> nodes() {
    return Collections.unmodifiableCollection(adjacency.keySet());
  }

}
