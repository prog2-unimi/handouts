package it.unimi.di.prog2.l18.digraph;

import java.util.Collection;
import java.util.function.Consumer;

/**
 * A collection of {@link Consumer}s to be used in graph visits.
 *
 * <p>
 * This class provides some {@link Consumer}s to be used by the
 * {@link DiGraph#visit(Object, Consumer, java.util.function.Supplier)} method.
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
