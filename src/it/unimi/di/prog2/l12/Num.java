/*

Copyright 2019 Massimo Santini

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

package it.unimi.di.prog2.l12;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class Num {

  private Num() {}

  /**
   * An {@link Iterator} returning the divisor of the given integer.
   *
   * <p><strong>Implementation details</strong>: this iterator is <em>lazy</em>: given that is
   * impossible to know in advance the number of divisors of a given integer, it possibly caches the
   * next divisor in its state when {@link DivisorIterator#hasNext} is called so that {@link
   * DivisorIterator#next} can, in case, return it.
   */
  private static class DivisorIterator implements Iterator<Integer> {

    /** The integer whose divisors are to be returned. */
    private final int n;

    /**
     * The next divisor to return.
     *
     * <p>The representation invariant is that either {@code d} is a divisor of {@code n} and it has
     * not yet been returned by {@code next}, or it is not a divisor of {@code n}.
     */
    private int d;

    DivisorIterator(int n) {
      if (n <= 0) throw new IllegalArgumentException();
      this.n = n;
      d = 1; // the rep. inv. is true: 1 is a divisor and here {@code next} hasn't been called yet.
    }

    @Override
    public boolean hasNext() {
      // Given the rep. inv. {@code d} can be:
      // - a divisor not yet returned, in this case the loop terminates
      // immediately returning {@code true} (leaving {@code d} unchanged);
      // - not a divisor.
      // In this case, the loop looks for the next divisor:
      // - if {@code d} &lt; {@code n} it will find a new divisor (greater than
      // {@code d}, and possibly equal to {@code n}), set {@code d} to its
      // value and return {@code true};
      // - otherwise {@code d} must be equal to 1 + {@code n}, so that the loop
      // does not even execute, and the method returns {@code false}, leaving
      // {@code d} unchanged. In any case, the rep. inv. is preserved true.
      for (int i = d; i <= n; i++)
        if (n % i == 0) {
          d = i;
          return true;
        }
      return false;
    }

    @Override
    public Integer next() {
      if (!hasNext()) throw new NoSuchElementException();
      int ret = d;
      // Here {@code d} is a divisor, given the contract of {@code hasNext} and
      // given the rep. inv. it has not yet been returned,
      d = d + 1;
      // Before returning, {@code d} is advanced, this makes the rep. inv. true
      // given that, even if the new value is a divisor, it has not yet been
      // returned.
      return ret;
    }
  }

  /**
   * Returns an iterator over the divisor of a given number.
   *
   * @param n the number of which the divisor must be iterated, it must be &gt; 0.
   * @return an iterator returning each divisor of {@code n} once and in increasing order.
   * @throws IllegalArgumentException if the parameter is negative, or 0.
   */
  public static Iterator<Integer> divisorIterator(int n) {
    if (n <= 0) throw new IllegalArgumentException();
    return new DivisorIterator(n);
  }
}
