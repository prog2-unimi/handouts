package it.unimi.di.prog2.l18.digraph;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

/**
 * A directed graph implementation based on a list of {@link Arc}s and one of nodes.
 *
 * @param <T> the type of the graph nodes.
 */
public class ArcListDiGraph<T> implements DiGraph<T> {

  /**
   * The set of graph nodes.
   *
   * <p>
   * Observe that <em>isolated</em> nodes (node not appearing in any arc) belong to this set.
   */
  private final Set<T> nodes = new HashSet<>();

  /**
   * The set of graph arcs.
   */
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
  public Set<T> nodes() {
    return Collections.unmodifiableSet(nodes);
  }

  @Override
  public Set<Arc<T>> arcs() {
    return Collections.unmodifiableSet(arcs);
  }

}
