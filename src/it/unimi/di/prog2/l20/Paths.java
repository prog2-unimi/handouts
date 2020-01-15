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

package it.unimi.di.prog2.l20;

public class Paths {

  public static int selection(boolean a, boolean b) {
    int x = -1, y = -1;

    if (a) x = 1;
    else x = 0;

    if (b) y = 2 * x;
    else y = 2 / x;

    return y;
  }

  public static int iteration(int x) {
    int y = 2, z = -1;

    while (x > 0) {
      x -= 1;
      y /= 2;
      z = 1 / y;
    }

    return z;
  }
}
