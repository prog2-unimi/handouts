/*

Copyright 2019 Massimo Santini

This file is part of "Programmazione 2 @ UniMI" teaching material.

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

import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.function.Function;

/**
 * An <em>implicit</em> directed graph implementation.
 *
 * <p>Graphs of this class are defined just by a collection of nodes and a "delta" function that,
 * for every node, returns a collection giving the outgoing set. The arcs are not memorized, but
 * computed using the "delta" function.
 *
 * @param <T> the type of the graph nodes.
 */
public class ImplicitDiGraph<T> implements DiGraph<T> {

  private final Set<T> nodes;
  private final Function<T, Collection<T>> delta;

  /**
   * Creates a directed graph given nodes and a function to obtain the outgoing sets.
   *
   * <p>Observe that if {@code nodes} is not required to be a set, duplicate elements will be not be
   * considered at creation time; similarly, the {@code delta} function can return duplicate values,
   * the {@link #outgoing(Object)} method will take care to remove them.
   *
   * @param nodes a collection of nodes.
   * @param delta the function that will be used to compute the outgoing sets.
   */
  public ImplicitDiGraph(final Collection<T> nodes, final Function<T, Collection<T>> delta) {
    this.nodes = Collections.unmodifiableSet(new HashSet<>(nodes));
    this.delta = delta;
  }

  @Override
  public Set<T> nodes() {
    return nodes;
  }

  @Override
  public Set<T> outgoing(T source) {
    return new HashSet<>(delta.apply(source));
  }
}
