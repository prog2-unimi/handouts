package it.unimi.di.prog2.l17.digraph;

import java.util.Objects;

public class Arc<T> {
  public final T source, destination;

  public Arc(final T source, final T destination) {
    this.source = source;
    this.destination = destination;
  }

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
