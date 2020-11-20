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

package it.unimi.di.prog2.t11;

import java.util.Iterator;

public class NextOrNullIteratorWrapper<E> implements Iterator<E> {

  private final Iterator<E> it;

  /*-
   * Qui non vorrei:
   *                 che spotless
   *                 intervenisse
   */
  public NextOrNullIteratorWrapper(Iterator<E> it) {
    this.it = it;
  }

  public NextOrNullIteratorWrapper(Iterable<E> ite) {
    it = ite.iterator();
  }

  @Override
  public boolean hasNext() {
    return it.hasNext();
  }

  @Override
  public E next() {
    if (it.hasNext()) return it.next();
    return null;
  }
}
