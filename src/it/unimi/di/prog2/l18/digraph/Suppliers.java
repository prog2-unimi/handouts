package it.unimi.di.prog2.l18.digraph;

import java.util.AbstractQueue;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;
import java.util.function.Supplier;

public class Suppliers {

  private Suppliers() {}

  public static <T> Supplier<Queue<T>> LIFOSupplier() {
    return new Supplier<>() {
      @Override
      public Queue<T> get() {
        return Collections.asLifoQueue(new LinkedList<>());
      }
    };
  }

  public static <T> Supplier<Queue<T>> FIFOSupplier() {
    return new Supplier<>() {
      @Override
      public Queue<T> get() {
        return new LinkedList<>();
      }
    };
  }

  public static <T> Queue<T> once(final Queue<T> queue) {
    return new AbstractQueue<>() {

      final Set<T> seen = new HashSet<>();

      @Override
      public boolean offer(T e) {
        if (seen.contains(e))
          return true;
        seen.add(e);
        return queue.offer(e);
      }

      @Override
      public T poll() {
        return queue.poll();
      }

      @Override
      public T peek() {
        return queue.peek();
      }

      @Override
      public Iterator<T> iterator() {
        return queue.iterator();
      }

      @Override
      public int size() {
        return queue.size();
      }
    };
  }
}
