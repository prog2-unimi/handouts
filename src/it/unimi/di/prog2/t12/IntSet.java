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

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Esempio di {@code IntSet} concreto non ordinato, tratto dalla sezione 7.6 del libro di testo di
 * Liskov <em>et al.</em>.
 */
public class IntSet extends AbstractIntSet {

  private final List<Integer> elements = new ArrayList<>();

  @Override
  public Iterator<Integer> iterator() {
    return elements.iterator();
  }

  @Override
  public void insert(int x) {
    if (elements.indexOf(x) != -1) return;
    elements.add(x);
    size++;
  }

  @Override
  public void remove(int x) {
    final int idx = elements.indexOf(x);
    if (idx == -1) return;
    elements.remove(idx);
    size--;
  }

  @Override
  public boolean repOk() {
    if (elements == null) return false;
    if (elements.size() != size) return false;
    for (Integer x : this) if (x == null) return false;
    for (int i = 0; i < elements.size(); i++)
      for (int j = 0; j < elements.size(); j++)
        if (i != j && elements.get(i).equals(elements.get(j))) return false;
    return true;
  }

  public static void main(String[] args) {
    final IntSet us = new IntSet();
    us.insert(3);
    us.insert(2);
    us.insert(1);
    us.insert(2);
    us.insert(3);
    System.out.println(us);
  }
}
