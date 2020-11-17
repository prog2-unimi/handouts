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

package it.unimi.di.prog2.t10;

public class IntSetClient {

  public static int sum(IntSet s) {
    int sum = 0;
    for (int x : s) sum += x;
    return sum;
  }

  public static int max(IntSet s) {
    int max = Integer.MIN_VALUE;
    for (int x : s) if (x > max) max = x;
    return max;
  }

  public static void main(String[] args) {
    IntSet s = new IntSet();

    s.insert(1);
    s.insert(3);
    s.insert(5);

    System.out.println(sum(s) + ", " + max(s));
  }
}
