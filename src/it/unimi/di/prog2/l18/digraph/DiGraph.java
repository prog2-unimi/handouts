/*

Copyright 2019 Massimo Santini

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

package it.unimi.di.prog2.l18.digraph;

import java.util.AbstractSet;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Queue;
import java.util.Set;
import java.util.function.Consumer;
import java.util.function.Supplier;

/**
 * An interface representing an <em>directed graph</em> where nodes are of generic type {@code T}.
 *
 * <p>A directed graph \( G = (N, A) \) is a pair where \( N \) is a finite set of <em>nodes</em>
 * (of type {@code T}) and \(A = \{(x, y) : x, y \in N \} \) is the set of <em>arcs</em> that are
 * ordered pairs of elements of \( N \); the first element of and arc is called <em>source</em>, and
 * the second one is called <em>destination</em>. Given a node \( x \in N \), the set of
 * <em>outgoing</em> nodes of \( x \) is the set \(\{y : (x, y) \in A \} \).
 *
 * <p>Classes implementing this interface can represent both <em>immutable</em> and <em>mutable</em>
 * directed graphs, default implementations of mutation methods {@link #addNode(Object)} and {@link
 * #addArc(Object, Object)} rise {@link UnsupportedOperationException} (while the default
 * implementation of {@link #addArc(Arc)} relies on {@link #addArc(Object, Object)}).
 *
 * <p>Every implementation must at least override {@link #nodes()}; moreover, since {@link #arcs()}
 * has a default implementation in terms of such method and {@link #outgoing(Object)}, and
 * conversely {@link #outgoing(Object)} has a default implementation in terms of {@link #arcs()}, at
 * least one of these two methods must be overridden. All other methods have default implementations
 * based on {@link #outgoing(Object)} and/or {@link #arcs()}.
 *
 * @param <T> the type of the graph nodes.
 */
public interface DiGraph<T> {

  /**
   * Returns the graph nodes.
   *
   * @return the graph nodes.
   */
  Set<T> nodes();

  /**
   * Returns the set of outgoing nodes of a given node.
   *
   * @param source the source node.
   * @return the nodes that have an arc with the given {@code source} in this graph (the collection
   *     is empty if the {@code source} does not belong to this graph).
   */
  default Set<T> outgoing(final T source) {
    return new AbstractSet<>() {
      @Override
      public Iterator<T> iterator() {
        return new Iterator<T>() {

          private final Iterator<Arc<T>> arcs = arcs().iterator();
          private T destination = null;

          @Override
          public boolean hasNext() {
            if (destination == null) {
              while (arcs.hasNext()) {
                Arc<T> arc = arcs.next();
                if (arc.source == source) {
                  destination = arc.destination;
                  return true;
                }
              }
              return false;
            }
            return true;
          }

          @Override
          public T next() {
            if (!hasNext()) throw new NoSuchElementException();
            final T next = destination;
            destination = null;
            return next;
          }
        };
      }

      @Override
      public int size() {
        int size = 0;
        for (Iterator<T> it = this.iterator(); it.hasNext(); ) size++;
        return size;
      }
    };
  }

  /**
   * Adds a node to this graph.
   *
   * @param node the node to be added.
   */
  default void addNode(T node) {
    throw new UnsupportedOperationException();
  }

  /**
   * Adds an arc to this graph.
   *
   * <p>It the source, or destination, node are not present in the graph, they are also added.
   *
   * @param source the source of the arc to be added.
   * @param destination the destination of the arc to be added.
   */
  default void addArc(T source, T destination) {
    throw new UnsupportedOperationException();
  }

  /**
   * Adds an arc to this graph.
   *
   * <p>It the source, or destination, node are not present in the graph, they are also added.
   *
   * @param arc the arc to be added.
   */
  default void addArc(Arc<T> arc) {
    addArc(arc.source, arc.destination);
  }

  /**
   * Tells whether an arc belongs to this graph, or not.
   *
   * @param source the source of the arc to be queried.
   * @param destination the destination of the arc to be queried.
   * @return whether the arc belongs to the graph, or not.
   */
  default boolean hasArc(T source, T destination) {
    return outgoing(source).contains(destination);
  }

  /**
   * Tells whether an arc belongs to this graph, or not.
   *
   * @param arc the arc to be queried.
   * @return whether the arc belongs to the graph, or not.
   */
  default boolean hasArc(Arc<T> arc) {
    return hasArc(arc.source, arc.destination);
  }

  /**
   * Tells whether a node belongs to this graph, or not.
   *
   * @param node the node to be queried.
   * @return whether the node belongs to the graph, or not.
   */
  default boolean hasNode(T node) {
    return nodes().contains(node);
  }

  /**
   * Returns this graph arcs.
   *
   * @return the arcs belonging to this graph.
   */
  default Set<Arc<T>> arcs() {
    return new AbstractSet<>() {

      @Override
      public Iterator<Arc<T>> iterator() {
        return new Iterator<>() {
          private final Iterator<T> src = nodes().iterator();
          private Iterator<T> dst;
          T source = null, destination = null;

          @Override
          public boolean hasNext() {
            while (destination == null) {
              if (source == null) {
                if (!src.hasNext()) return false;
                source = src.next();
                dst = outgoing(source).iterator();
              }
              if (dst.hasNext()) {
                destination = dst.next();
                return true;
              } else source = destination = null;
            }
            return true;
          }

          @Override
          public Arc<T> next() {
            if (!hasNext()) throw new NoSuchElementException();
            Arc<T> arc = new Arc<>(source, destination);
            destination = null;
            return arc;
          }
        };
      }

      @Override
      public int size() {
        int size = 0;
        for (Iterator<Arc<T>> it = this.iterator(); it.hasNext(); ) size++;
        return size;
      }
    };
  }

  /**
   * Performs a visit on the graph.
   *
   * @param start the not where the visit must start.
   * @param consumer a {@link Consumer} that will be called on every visited node.
   * @param supplier a {@link Supplier} providing the {@link Queue} to be used to perform the visit.
   */
  default void visit(T start, Consumer<T> consumer, Supplier<? extends Queue<T>> supplier) {
    Queue<T> bag = Queues.once(supplier.get());
    bag.add(start);
    while (!bag.isEmpty()) {
      T curr = bag.remove();
      consumer.accept(curr);
      for (T t : outgoing(curr)) bag.add(t);
    }
  }
}
