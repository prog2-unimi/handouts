/*

Copyright 2021 Massimo Santini

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

public class IntRangeIteratorBuilder {

  public static Builder from(int from) {
    return new Builder().from(from);
  }

  public static Builder to(int to) {
    return new Builder().to(to);
  }

  public static Builder step(int step) {
    return new Builder().step(step);
  }

  static class Builder implements Iterable<Integer> {

    private int start, stop, step;

    private Builder() {
      this.start = 0;
      this.stop = Integer.MAX_VALUE;
      this.step = 1;
    }

    public Builder from(int from) {
      this.start = from;
      return this;
    }

    public Builder to(int to) {
      this.stop = to;
      return this;
    }

    public Builder step(int step) {
      if (step == 0) throw new IllegalArgumentException();
      this.step = step;
      return this;
    }

    @Override
    public Iterator<Integer> iterator() {
      return new Iterator<Integer>() {
        private int n = start;

        @Override
        public boolean hasNext() {
          return step > 0 ? n < stop : n > stop;
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
}
