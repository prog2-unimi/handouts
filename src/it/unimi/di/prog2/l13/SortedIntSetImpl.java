package it.unimi.di.prog2.l13;

import java.util.LinkedList;

public class SortedIntSetImpl extends IntSetImpl implements SortedIntSet {

  public SortedIntSetImpl() {
    elements = new LinkedList<>();
  }

  @Override
  public void insert(int x) {
    int i;
    for (i = 0; i < size; i++) {
      int el = elements.get(i);
      if (el == x)
        return;
      else if (el > x)
        break;
    }
    elements.add(i, x);
    size++;
  }

  @Override
  public boolean repok() {
    // HOMEWORK: describe and implement the representation invariant
    return false;
  }

  @Override
  public int max() throws EmptyException {
    return elements.get(elements.size() - 1);
  }

}
