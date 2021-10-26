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

package it.unimi.di.prog2.t05;

import java.util.Arrays;

public class Sorting {

  static void sort(int[] v) {
    for (int last = v.length - 1; last > 0; last--) {
      int argmax = last;
      for (int i = 0; i < last; i++) if (v[argmax] < v[i]) argmax = i;
      int tmp = v[argmax];
      v[argmax] = v[last];
      v[last] = tmp;
    }
  }

  static int search(int needle, int[] haystack) {
    for (int i = 0; i < haystack.length; i++) if (haystack[i] == needle) return i;
    return -1;
  }

  public static int searchSorted(int needle, int[] haystack) {
    int low = 0;
    int high = haystack.length - 1;
    while (low <= high) {
      int mid = low + ((high - low) / 2);
      int midVal = haystack[mid];
      if (midVal < needle) low = mid + 1;
      else if (midVal > needle) high = mid - 1;
      else return mid;
    }
    return -(low + 1);
  }

  public static void main(String[] args) {
    int[] v = {5, 1, 3, 2, 0, 4};
    sort(v);
    System.out.println(Arrays.toString(v) + " " + searchSorted(4, v));
  }
}
