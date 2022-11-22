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

/** A class representing decimal digits of a long, endowed with a non zero digits iterator. */
public class DecimalDigitsEG {

  private final long number;

  public DecimalDigitsEG(final long number) {
    this.number = number;
  }

  /**
   * Returns a <em>generator</em> on from the least significant to the most significant non zero
   * digits.
   *
   * @return the generator.
   */
  public Iterator<Integer> nonZeroDigits() {
    // we expose the representation to an external class
    return new NonZeroDigitsGenerator(number);
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
    final DecimalDigitsEG dd = new DecimalDigitsEG(Long.parseLong(args[0]));
    final Iterator<Integer> it = dd.nonZeroDigits();
    while (it.hasNext()) System.out.println(it.next());
  }
}
