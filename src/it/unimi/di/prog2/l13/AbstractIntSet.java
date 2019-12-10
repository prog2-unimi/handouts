/*

Copyright 2019 Massimo Santini

This file is part of "Programmazione 2 @ UniMI" teaching material.

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

package it.unimi.di.prog2.l13;

import java.util.Iterator;

public abstract class AbstractIntSet implements IntSet {

  /** The size of this set. */
  protected int size;

  // Constructors

  /**
   * Initializes this set to be empty.
   *
   * <p>Builds the set \( S = \varnothing \).
   */
  public AbstractIntSet() {
    size = 0;
  }

  /**
   * A <em>copy constructor</em>.
   *
   * @param other the {@code IntSet} to copy from.
   */
  public AbstractIntSet(IntSet other) {
    for (Integer i : other) insert(i);
  }

  // Methods

  @Override
  public int size() {
    return size;
  }

  @Override
  public String toString() {
    String result = this.getClass().getSimpleName() + ": ";
    if (size == 0) return result + "{}";
    Iterator<Integer> it = iterator();
    result += "{ " + it.next();
    while (it.hasNext()) result += ", " + it.next();
    return result + " }";
  }
}
