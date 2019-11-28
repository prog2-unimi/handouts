package it.unimi.di.prog2.l14;

import java.util.Set;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;

public class CountingSet<E> extends ForwardingSet<E> {

  private int count = 0;

  public CountingSet(Set<E> set) {
    super(set);
  }

  @Override
  public boolean add(E e) {
    count++;
    return super.add(e);
  }

  @Override
  public boolean addAll(Collection<? extends E> c) {
    count += c.size();
    return super.addAll(c);
  }

  public int count() {
    return count;
  }

  public static void main(String[] args) {
    CountingSet<String> cs = new CountingSet<>(new HashSet<String>());
    cs.addAll(Arrays.asList(args));
    System.out.println("Added elements: " + cs.count());
  }

}
