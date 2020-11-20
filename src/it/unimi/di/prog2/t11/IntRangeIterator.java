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

public class IntRangeIterator implements Iterable<Integer> {
  private final int start, stop, step;

  public IntRangeIterator(final int start, final int stop, final int step) {
    this.start = start;
    this.stop = stop;
    this.step = step;
  }

  public IntRangeIterator(final int start, final int stop) {
    this(start, stop, 1);
  }

  public IntRangeIterator(final int stop) {
    this(0, stop, 1);
  }

  @Override
  public Iterator<Integer> iterator() {
    return new Iterator<Integer>() {
      private int n = start;

      @Override
      public boolean hasNext() {
        return n < stop;
      }

      @Override
      public Integer next() {
        if (!hasNext()) throw new NoSuchElementException();
        final int r = n;
        n += step;
        return r;
      }
    };
  }
}
