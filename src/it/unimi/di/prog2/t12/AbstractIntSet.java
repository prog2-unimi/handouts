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

import java.util.Iterator;

/**
 * Esempio di {@code IntSet} astratto, tratto dalla sezione 7.6 del libro di testo di Liskov <em>et
 * al.</em>.
 */
public abstract class AbstractIntSet implements Iterable<Integer> {

  protected int size;

  public abstract void insert(int x);

  public abstract void remove(int x);

  public abstract boolean repOk();

  public boolean isIn(int x) {
    for (int z : this) if (z == x) return true;
    return false;
  }

  public boolean subset(AbstractIntSet other) {
    for (int x : this) if (!other.isIn(x)) return false;
    return true;
  }

  public int size() {
    return size;
  }

  @Override
  public String toString() {
    if (size == 0) return "IntSet: {}";
    final StringBuilder sb = new StringBuilder();
    final Iterator<Integer> it = iterator();
    sb.append("IntSet: {");
    sb.append(it.next());
    while (it.hasNext()) sb.append(", " + it.next());
    sb.append("}");
    return sb.toString();
  }
}
