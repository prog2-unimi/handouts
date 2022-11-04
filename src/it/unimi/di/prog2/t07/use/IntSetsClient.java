/*

Copyright 2022 Massimo Santini

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

package it.unimi.di.prog2.t07.use;

import it.unimi.di.prog2.t07.impl.IntSet;

public class IntSetsClient {

  public static void main(String[] args) {
    int[] a = new int[args.length];
    int i = 0;
    for (String s : args) a[i++] = Integer.parseInt(s);
    IntSet S = IntSets.getElements(a);
    System.out.println(S);
  }
}
