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

package it.unimi.di.prog2.h15.dd;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * A class representing decimal digits of a long, endowed with a non zero digits iterator, based on
 * the <strong>nested static</strong> {@link DecimalDigitsNSG.NonZeroDigitsGeneratorNS
 * NonZeroDigitsGeneratorNS} class.
 */
public class DecimalDigitsNSG {

  private final long number;

  public DecimalDigitsNSG(final long number) {
    this.number = number;
  }

  /** A nested static class implementing the genrator. */
  private static class NonZeroDigitsGeneratorNS implements Iterator<Integer> {

    private long remaining;

    private NonZeroDigitsGeneratorNS(final long remaining) {
      this.remaining = remaining;
    }

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
  }

  /**
   * Returns a <em>generator</em> on from the least significant to the most significant non zero
   * digits.
   *
   * @return the generator.
   */
  public Iterator<Integer> nonZeroDigits() {
    // we "expose" the representation, albeit just to a nested static class
    return new NonZeroDigitsGeneratorNS(number);
  }

  /**
   * This method serves the only purpose to show an usage example of this class.
   *
   * <p>It prints the digits of the given number.
   *
   * @param args the first parametr is the number.
   */
  public static void main(String[] args) {
    final DecimalDigitsNSG dd = new DecimalDigitsNSG(Long.parseLong(args[0]));
    final Iterator<Integer> it = dd.nonZeroDigits();
    while (it.hasNext()) System.out.println(it.next());
  }
}
