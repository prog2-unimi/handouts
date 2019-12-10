package it.unimi.di.prog2.l18.digraph;

import java.util.Objects;

/**
 * An arc of a directed graph with nodes of type {@code T}.
 *
 * <p>
 * This is a <em>value</em> class: arcs with the same sources and destinations are equal.
 *
 * @param <T> the type of the graph nodes.
 */
public class Arc<T> {
  public final T source, destination;

  /**
   * Creates an arc.
   *
   * @param source the arc source.
   * @param destination the arc destination.
   */
  public Arc(final T source, final T destination) {
    this.source = source;
    this.destination = destination;
  }

  /**
   * Tells whether this arc is equal to the given object, or not.
   *
   * <p>
   * This arc is equal to {@code obj} iff it is an {@link Arc} with the same source and destination
   * of this one.
   *
   * @param obj the object to compare to.
   * @return whether this arc is equal to the given object.
   */
  @Override
  public boolean equals(Object obj) {
    if (this == obj)
      return true;
    if (!(obj instanceof Arc<?>))
      return false;
    Arc<?> other = (Arc<?>) obj;
    return other.source.equals(source) && other.destination.equals(destination);
  }

  @Override
  public int hashCode() {
    return Objects.hash(source, destination);
  }

  @Override
  public String toString() {
    return String.format("(%s, %s)", source.toString(), destination.toString());
  }
}
