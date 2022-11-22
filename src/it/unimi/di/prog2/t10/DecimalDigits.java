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

/**
 * A class representing decimal digits of a long.
 *
 * <p>In this package there are several variants of this class endowed in addition with an
 * <em>iterator</em> (that is a method that returns a <em>generator</em> in Lisokv's parlance) on
 * non zero digits; the variants corrspond to various possibilities with regard to the
 * implementation style of the generator:
 *
 * <ul>
 *   <li><code>DecimalDigitsEG</code> based on the <strong>external</strong> class <code>
 *       NonZeroDigitsGenerator</code>,
 *   <li><code>DecimalDigitsNSG</code> based on a <strong>nested static</strong> class,
 *   <li><code>DecimalDigitsIG</code> based on an <strong>inner</strong> class,
 *   <li><code>DecimalDigitsAG</code> based on an <strong>anonymos</strong> class.
 * </ul>
 *
 * Such variants are listed here in increasing order of preferableness (and implementation
 * complexity).
 */
public class DecimalDigits {

  private final long number;

  public DecimalDigits(final long number) {
    this.number = number;
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
    final DecimalDigits dd = new DecimalDigits(Long.parseLong(args[0]));
    final int pos = Integer.parseInt(args[1]);
    System.out.println(dd.digit(pos));
  }
}
