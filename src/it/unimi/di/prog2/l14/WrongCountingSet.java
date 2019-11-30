package it.unimi.di.prog2.l14;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;

@SuppressWarnings("serial")
public class WrongCountingSet<E> extends HashSet<E> {

  private int count = 0;

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
    WrongCountingSet<String> cs = new WrongCountingSet<>();
    cs.addAll(Arrays.asList(args));
    System.out.println("Elements: " + cs.toString());
    System.out.println("Added elements: " + cs.count());
  }
}
