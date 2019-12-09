package it.unimi.di.prog2.l17.digraph;

import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;
import java.util.function.Supplier;

public class Bags {

  private Bags() {}

  public static <T> Supplier<Bag<T>> LIFOBagSupplier() {
    return new Supplier<>() {
      @Override
      public Bag<T> get() {
        return asBag(Collections.asLifoQueue(new LinkedList<>()));
      }
    };
  }

  public static <T> Supplier<Bag<T>> FIFOBagSupplier() {
    return new Supplier<>() {
      @Override
      public Bag<T> get() {
        return asBag(new LinkedList<>());
      }
    };
  }

  public static <T> Bag<T> asBag(final Queue<T> queue) {
    return new Bag<T>() {

      @Override
      public boolean isEmpty() {
        return queue.isEmpty();
      }

      @Override
      public void add(T e) {
        queue.add(e);
      }

      @Override
      public T remove() {
        return queue.remove();
      }

    };
  }

  public static <T> Bag<T> once(final Bag<T> bag) {
    return new Bag<T>() {
      private final Set<T> seen = new HashSet<>();

      @Override
      public boolean isEmpty() {
        return bag.isEmpty();
      }

      @Override
      public void add(T e) {
        if (!seen.contains(e)) {
          seen.add(e);
          bag.add(e);
        }
      }

      @Override
      public T remove() {
        return bag.remove();
      }
    };
  }
}
