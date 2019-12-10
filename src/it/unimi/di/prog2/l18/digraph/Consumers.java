package it.unimi.di.prog2.l17.digraph;

import java.util.Collection;
import java.util.function.Consumer;

public class Consumers {

  private Consumers() {}

  static <T> Consumer<T> printConsumer() {
    return new Consumer<>() {
      @Override
      public void accept(T node) {
        System.out.println(node);
      }
    };
  }

  static <T> Consumer<T> collectConsumer(final Collection<T> result) {
    return new Consumer<>() {
      @Override
      public void accept(T node) {
        result.add(node);
      }
    };
  }
}
