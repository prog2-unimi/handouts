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

package it.unimi.di.prog2.l05;

/**
 * This class provides a number of standalone procedures that are useful for manipulating arrays of
 * ints.
 *
 * <p>This version contains just the implementaion of Figure 3.5.
 */
public class Arrays1 {

  /**
   * Searches for an element in a sorted array.
   *
   * @param a the array of elements to be searched, sorted in ascending order.
   * @param x the element to search in <code>a</code>.
   * @return an index where <code>x</code> is stored <code>x</code> is in <code>a</code>, otherwise
   *     returns -1.
   */
  public static int searchSorted(int[] a, int x) {
    if (a == null) return -1;
    for (int i = 0; i < a.length; i++)
      if (a[i] == x) return i;
      else if (a[i] > x) return -1;
    return -1;
  }
}
