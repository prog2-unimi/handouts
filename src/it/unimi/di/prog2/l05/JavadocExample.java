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

public class JavadocExample {

  /**
   * Finds the maximum value in an array.
   *
   * <p>This function scans the elements of an array of integers and returns the maximum value
   * found.
   *
   * @param x a (possibly empty) array of values
   * @return the maximum value found in <code>x</code>
   */
  public static int max(int[] x) {
    int min = Integer.MAX_VALUE;
    for (int i = 0; i < x.length; i++) if (min < x[i]) min = x[i];
    return min;
  }
}
