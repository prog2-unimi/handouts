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

public class NonZeroDigitsGenerator implements Iterator<Integer> {

  private long remaining;

  protected NonZeroDigitsGenerator(final long remaining) {
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
