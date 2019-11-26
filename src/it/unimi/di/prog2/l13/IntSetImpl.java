package it.unimi.di.prog2.l13;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class IntSetImpl extends AbstractIntSet {

  /** The list containing set elements. */
  protected List<Integer> elements;

  public IntSetImpl() {
    // super();
    elements = new ArrayList<>();
  }

  @Override
  public void insert(int x) {
    if (elements.indexOf(x) == -1) {
      elements.add(x);
      size++;
    }
  }

  @Override
  public void remove(int x) {
    final int idx = elements.indexOf(x);
    if (x != -1) {
      elements.remove(idx);
      size--;
    }
  }

  @Override
  public boolean repok() {
    // HOMEWORK: describe and implement the representation invariant
    return false;
  }

  @Override
  public Iterator<Integer> iterator() {
    return elements.iterator();
  }

}
