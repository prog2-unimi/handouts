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

import java.util.Collection;
import java.util.function.Consumer;

/**
 * A collection of {@link Consumer}s to be used in graph visits.
 *
 * <p>This class provides some {@link Consumer}s to be used by the {@link DiGraph#visit(Object,
 * Consumer, java.util.function.Supplier)} method.
 */
public class Consumers {

  private Consumers() {}

  /**
   * A {@link Consumer} printing to the standard output every visited node.
   *
   * @param <T> the type of the graph nodes.
   * @return the consumer.
   */
  static <T> Consumer<T> printConsumer() {
    return new Consumer<>() {
      @Override
      public void accept(T node) {
        System.out.println(node);
      }
    };
  }

  /**
   * A {@link Consumer} that adds the visited nodes in the given {@link Collection}.
   *
   * @param <T> the type of the graph nodes.
   * @param result here will be added the visited nodes.
   * @return the consumer.
   */
  static <T> Consumer<T> collectConsumer(final Collection<T> result) {
    return new Consumer<>() {
      @Override
      public void accept(T node) {
        result.add(node);
      }
    };
  }
}
