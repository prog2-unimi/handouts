/*

Copyright 2020 Massimo Santini

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

package it.unimi.di.prog2.t05;

import java.util.List;

/**
 * Provides useful standalone procedures for manipulating lists.
 *
 * <p>This is a "modern" implementation of Figure 3.7 code (using {@link List} instead of vectors).
 */
public class Lists {

  /**
   * Removes the duplicate elements from a list.
   *
   * <p>Remsoves all duplicate elements from <code>v</code>; uses {@link Object#equals(Object)} to
   * determine duplicates. The order of remaining elements may change.
   *
   * @param v a list, its elements must be not <code>null</code>; it will be modified in place.
   */
  @SuppressWarnings({"unchecked", "rawtypes"})
  public static void removeDupls(List v) {
    if (v == null) return;
    for (int i = 0; i < v.size(); i++) {

      Object x = v.get(i);
      int j = i + 1;

      // remove all dups of x from the rest of v
      while (j < v.size())
        if (!x.equals(v.get(j))) j++;
        else {
          int last = v.size() - 1;
          v.set(j, v.get(last));
          v.remove(last);
        }
    }
  }
}
