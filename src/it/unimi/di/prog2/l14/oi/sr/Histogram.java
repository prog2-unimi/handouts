package it.unimi.di.prog2.l14.oi.sr;

import java.util.LinkedList;
import java.util.List;
import java.util.NoSuchElementException;

public class Histogram {

  List<Rectangle> rectangles = new LinkedList<>();

  public void add(Rectangle r) {
    int i;
    for (i = 0; i < rectangles.size(); i++)
      if (rectangles.get(i).height() > r.height())
        break;
    rectangles.add(i, r);
  }

  public void changeBase(Rectangle o, int base) throws NoSuchElementException {
    final int idx = rectangles.indexOf(o);
    if (idx != -1)
      rectangles.get(idx).base(base);
    else
      throw new NoSuchElementException();
  }

  @Override
  public String toString() {
    return rectangles.toString();
  }
}
