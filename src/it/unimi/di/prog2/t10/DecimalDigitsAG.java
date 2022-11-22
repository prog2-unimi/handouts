/*

Copyright 2022 Massimo Santini

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

package it.unimi.di.prog2.t10;

import java.util.Iterator;
import java.util.NoSuchElementException;

/** A class representing decimal digits of a long, endowed with a non zero digits iterator. */
public class DecimalDigitsAG {

  private final long number;

  public DecimalDigitsAG(final long number) {
    this.number = number;
  }

  /**
   * Returns a <em>generator</em> on from the least significant to the most significant non zero
   * digits.
   *
   * @return the generator.
   */
  public Iterator<Integer> nonZeroDigits() {
    // no need to pass any value, nor to define any named class
    return new Iterator<Integer>() {

      private long remaining = number;

      @Override
      public boolean hasNext() {
        while (remaining != 0 && remaining % 10 == 0) remaining /= 10;
        return remaining != 0;
      }

      @Override
      public Integer next() {
        if (!hasNext()) throw new NoSuchElementException();
        int digit = (int) (remaining % 10);
        remaining /= 10;
        return digit;
      }
    };
  }

  /**
   * Returns the digit corresponding to the given power of 10.
   *
   * @param power the power.
   * @return the corresponding digit.
   */
  public int digit(final int power) {
    if (power < 0) throw new IllegalArgumentException("The power must be positive.");
    long digit = number;
    for (int i = 0; i < power; i++) digit /= 10;
    return (int) (digit % 10);
  }

  /*
   * This method serves the only purpose to show an usage example of this class.
   */
  public static void main(String[] args) {
    final DecimalDigitsAG dd = new DecimalDigitsAG(Long.parseLong(args[0]));
    final Iterator<Integer> it = dd.nonZeroDigits();
    while (it.hasNext()) System.out.println(it.next());
  }
}
