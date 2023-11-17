/*

Copyright 2021 Luca Prigioniero, Massimo Santini
Copyright 2023 Massimo Santini

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

package it.unimi.di.prog2.h15;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class IntRange {

  /**
   * Returns an {@link IntRange.Builder} with the assigned from value.
   *
   * @param from the from value.
   * @return the {@link IntRange.Builder}.
   */
  public static Builder from(int from) {
    return new Builder().from(from);
  }

  /**
   * Returns an {@link IntRange.Builder} with the assigned end value.
   *
   * @param to the end value.
   * @return the {@link IntRange.Builder}.
   */
  public static Builder to(int to) {
    return new Builder().to(to);
  }

  /**
   * Returns an {@link IntRange.Builder} with the assigned step value.
   *
   * @param step the step value.
   * @return the {@link IntRange.Builder}.
   */
  public static Builder step(int step) {
    return new Builder().step(step);
  }

  /** Iterable of integers with configurable initial from, to, and step values. */
  static class Builder implements Iterable<Integer> {

    private int start, stop, step;

    /** Constructs the iterable from 0 to {@link Integer#MAX_VALUE} (exclusive) with step 1. */
    private Builder() {
      this.start = 0;
      this.stop = Integer.MAX_VALUE;
      this.step = 1;
    }

    /**
     * Returns an instance of this iterable, with the assigned initial value.
     *
     * @param from the initial value.
     * @return the iterable.
     */
    private Builder from(int from) {
      this.start = from;
      return this;
    }

    /**
     * Returns an instance of this iterable, with the assigned end value.
     *
     * @param to the end value.
     * @return the iterable.
     */
    private Builder to(int to) {
      this.stop = to;
      return this;
    }

    /**
     * Returns an instance of this iterable, with the assigned step.
     *
     * @param step the step value.
     * @return the iterable.
     * @throws IllegalArgumentException if the step is 0.
     */
    private Builder step(int step) {
      if (step == 0) throw new IllegalArgumentException();
      this.step = step;
      return this;
    }

    /**
     * Returns the iterator for this iterable.
     *
     * @return the iterator.
     * @throws IllegalArgumentException if the initial value is less than the final, but the step is
     *     negative, or conversely if the initial value is greater than the final, but the step is
     *     positive.
     */
    @Override
    public Iterator<Integer> iterator() {
      if (step > 0 && start > stop || step < 0 && start < stop)
        throw new IllegalArgumentException();
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
