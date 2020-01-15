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

package it.unimi.di.prog2.l13;

/**
 * {@code SortedIntSet}s are mutable, unbounded, and ordered, sets of integers.
 *
 * <p>A typical IntSet is \( S = \{x_1, \ldots, x_n \} \). The iteration on this set returns its
 * elements in increasing order.
 */
public interface SortedIntSet extends IntSet {

  /**
   * Returns the maximum element from this set.
   *
   * @return the maximum element from this set, if not empty.
   * @throws EmptyException if this set is empty.
   */
  public int max() throws EmptyException;

  public boolean repok();
}
