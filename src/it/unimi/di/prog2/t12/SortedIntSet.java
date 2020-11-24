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

package it.unimi.di.prog2.t12;

import it.unimi.di.prog2.t11.OrderedIntList;
import java.util.Iterator;
import java.util.Objects;

/**
 * Esempio di {@code SortedIntSet}, tratto dalla sezione 7.6 del libro di testo di Liskov <em>et
 * al.</em>.
 */
public class SortedIntSet extends AbstractIntSet {

  private final OrderedIntList sortedElements = new OrderedIntList();

  @Override
  public Iterator<Integer> iterator() {
    return sortedElements.smallToBig();
  }

  @Override
  public void insert(int x) {
    if (sortedElements.contains(x)) return;
    sortedElements.add(x);
    size++;
  }

  @Override
  public void remove(int x) {
    if (sortedElements.remove(x)) size--;
  }

  @Override
  public boolean subset(AbstractIntSet other) {
    if (other instanceof SortedIntSet) return subset((SortedIntSet) other);
    return super.subset(other);
  }

  public boolean subset(SortedIntSet other) {
    Objects.requireNonNull(other);
    if (size == 0) return true;
    if (other.size == 0) return false;
    Iterator<Integer> ti = iterator();
    Iterator<Integer> oi = other.iterator();
    int t = ti.next(), o = oi.next();
    for (; ; ) {
      while (o < t && oi.hasNext()) o = oi.next();
      if (t != o) return false;
      if (!ti.hasNext()) return true;
      t = ti.next();
    }
  }

  @Override
  public boolean repOk() {
    return sortedElements.size() == size;
  }
}
