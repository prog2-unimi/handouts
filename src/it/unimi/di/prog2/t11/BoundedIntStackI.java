/*

Copyright 2020 Massimo Santini

This file is part of "Programmazione 2 @ UniMI" teaching material.

This is free software: you can redistribute it and/or modify
it under the terms of the GNU General Public License as published by
the Free Software Foundation, either version 3 of the License, or
(at your option) any later version.

This material is distributed in the hope that it will be useful,
but WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
GNU General Public License for more details.

You should have received a copy of the GNU General Public License
along with this file.  If not, see <https://www.gnu.org/licenses/>.

*/

package it.unimi.di.prog2.t11;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Example of an iterable bounded integer stack built using <a
 * href="https://docs.oracle.com/javase/tutorial/java/javaOO/innerclasses.html">Inner Classes</a>.
 */
public class BoundedIntStackI {

  private final int[] numbers;
  private int free;

  public BoundedIntStackI(int n) {
    numbers = new int[n];
    free = 0;
  }

  public boolean isFull() {
    return free == numbers.length;
  }

  public void push(int x) {
    if (isFull()) throw new IllegalStateException("Stack is full");
    numbers[free++] = x;
  }

  public boolean isEmpty() {
    return free == 0;
  }

  public int pop() {
    if (isEmpty()) throw new IllegalStateException("Stack is empty");
    return numbers[--free];
  }

  class InnerSteppedContentIterator implements Iterator<Integer> {

    private int i = 0;
    private final int step;

    InnerSteppedContentIterator(int step) {
      this.step = step;
    }

    @Override
    public boolean hasNext() {
      return i < free;
    }

    @Override
    public Integer next() {
      if (!hasNext()) throw new NoSuchElementException();
      return numbers[(i += step) - step];
    }
  }

  public Iterator<Integer> content() {
    return new InnerSteppedContentIterator(1);
  }

  public Iterator<Integer> evenContent() {
    return new InnerSteppedContentIterator(2);
  }
}
