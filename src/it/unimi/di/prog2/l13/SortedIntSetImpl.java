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

import java.util.LinkedList;

public class SortedIntSetImpl extends IntSetImpl implements SortedIntSet {

  public SortedIntSetImpl() {
    elements = new LinkedList<>();
  }

  @Override
  public void insert(int x) {
    int i;
    for (i = 0; i < size; i++) {
      int el = elements.get(i);
      if (el == x) return;
      else if (el > x) break;
    }
    elements.add(i, x);
    size++;
  }

  @Override
  public boolean repok() {
    // HOMEWORK: describe and implement the representation invariant
    return false;
  }

  @Override
  public int max() throws EmptyException {
    return elements.get(elements.size() - 1);
  }
}
