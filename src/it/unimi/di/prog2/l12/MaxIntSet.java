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

import it.unimi.di.prog2.l07.EmptyException;
import java.util.Iterator;

/**
 * {@code MaxIntSet} is a subtype of {@link IntSet} with an additional method, {@link
 * MaxIntSet#max}, to determine the maximum element of the set.
 */
public class MaxIntSet extends IntSet {

  /** The biggest element, if set is not empty */
  private int biggest;

  /** Construct an empty {@code MaxIntSet}. */
  public MaxIntSet() {
    super();
  }

  @Override
  public void insert(final int x) {
    if (size() == 0 || x > biggest) biggest = x;
    super.insert(x);
  }

  @Override
  public void remove(final int x) {
    super.remove(x);
    if (size() == 0 || x < biggest) return;
    Iterator<Integer> g = elements();
    biggest = g.next();
    while (g.hasNext()) {
      final int z = g.next();
      if (z > biggest) biggest = z;
    }
  }

  /**
   * Returns the maximum value in the set, or rises {@link EmptyException} otherwise.
   *
   * @return the maximum value in the set.
   * @throws EmptyException if the set is empty.
   */
  public int max() throws EmptyException {
    if (size() == 0) throw new EmptyException();
    return biggest;
  }

  public boolean repOk() {
    if (!super.repok()) return false;
    if (size() == 0) return true;
    boolean found = false;
    final Iterator<Integer> g = elements();
    while (g.hasNext()) {
      final int z = g.next();
      if (z > biggest) return false;
      if (z == biggest) found = true;
    }
    return found;
  }

  @Override
  public String toString() {
    if (size() == 0) return "Max" + super.toString();
    else return "Max" + super.toString() + ", max = " + biggest;
  }
}
