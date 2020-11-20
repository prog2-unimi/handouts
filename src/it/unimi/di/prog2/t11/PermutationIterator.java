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

import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;

/** https://en.wikipedia.org/wiki/Heap%27s_algorithm */
public class PermutationIterator<T> implements Iterator<T[]> {

  private final T[] P;
  private final int[] C;
  private boolean ready;
  private int i;

  public PermutationIterator(T[] P) {
    this.P = P.clone();
    C = new int[P.length];
    this.i = 0;
    ready = true;
  }

  private void exch(int i, int j) {
    T t = P[i];
    P[i] = P[j];
    P[j] = t;
  }

  @Override
  public boolean hasNext() {
    if (ready) return true;
    while (i < P.length) {
      if (C[i] < i) {
        exch(i % 2 == 0 ? 0 : C[i], i);
        ready = true;
        C[i]++;
        i = 0;
        break;
      } else C[i++] = 0;
    }
    return ready;
  }

  @Override
  public T[] next() {
    if (!hasNext()) throw new NoSuchElementException();
    ready = false;
    return P.clone();
  }

  public static void main(String[] args) {
    String[] p = new String[] {"uno", "due", "tre"};
    Iterator<String[]> it = new PermutationIterator<>(p);

    while (it.hasNext()) System.out.println(Arrays.toString(it.next()));
  }
}
