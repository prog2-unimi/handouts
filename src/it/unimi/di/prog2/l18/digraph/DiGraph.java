package it.unimi.di.prog2.l17.digraph;

import java.util.AbstractCollection;
import java.util.Collection;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Queue;
import java.util.function.Consumer;
import java.util.function.Supplier;

public interface DiGraph<T> {

  Collection<T> nodes();

  default Collection<T> outgoing(final T source) {
    return new AbstractCollection<>() {
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
            if (!hasNext())
              throw new NoSuchElementException();
            final T next = destination;
            destination = null;
            return next;
          }
        };
      }

      @Override
      public int size() {
        int size = 0;
        for (Iterator<T> it = this.iterator(); it.hasNext();)
          size++;
        return size;
      }
    };
  }

  default void addNode(T node) {
    throw new UnsupportedOperationException();
  }

  default void addArc(T source, T destination) {
    throw new UnsupportedOperationException();
  }

  default void addArc(Arc<T> arc) {
    addArc(arc.source, arc.destination);
  }

  default boolean hasArc(T source, T destination) {
    return outgoing(source).contains(destination);
  }

  default boolean hasArc(Arc<T> arc) {
    return hasArc(arc.source, arc.destination);
  }

  default boolean hasNode(T node) {
    return nodes().contains(node);
  }

  default Collection<Arc<T>> arcs() {
    return new AbstractCollection<>() {

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
                if (!src.hasNext())
                  return false;
                source = src.next();
                dst = outgoing(source).iterator();
              }
              if (dst.hasNext()) {
                destination = dst.next();
                return true;
              } else
                source = destination = null;
            }
            return true;
          }

          @Override
          public Arc<T> next() {
            if (!hasNext())
              throw new NoSuchElementException();
            Arc<T> arc = new Arc<>(source, destination);
            destination = null;
            return arc;
          }
        };

      }

      @Override
      public int size() {
        int size = 0;
        for (Iterator<Arc<T>> it = this.iterator(); it.hasNext();)
          size++;
        return size;
      }
    };
  }

  default void walk(T start, Consumer<T> consumer, Supplier<? extends Queue<T>> supplier) {
    Queue<T> bag = Suppliers.once(supplier.get());
    bag.add(start);
    while (!bag.isEmpty()) {
      T curr = bag.remove();
      consumer.accept(curr);
      for (T t : outgoing(curr))
        bag.add(t);
    }
  }

}
