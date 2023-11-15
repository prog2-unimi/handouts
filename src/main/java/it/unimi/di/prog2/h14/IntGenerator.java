/*

Copyright 2021 Luca Prigioniero, Massimo Santini
Copyright 2023 Massimo Santini

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

package it.unimi.di.prog2.h14;

import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

/** Generatore (nel senso della Liskov) degli interi contenuti in una {@link List}. */
public class IntGenerator implements Iterator<Integer> {

  /** Gli elementi della lista. */
  private final List<Integer> els;

  /** La posizione del prossimo elemento da iterare (se < els.size()). */
  private int idx;

  /**
   * Costruisce un generatore a partire da una lista (parziale, usato solo in {@link IntSet}).
   *
   * @param els la lista degli elementi, non deve essere o contenere {@code null}.
   */
  public IntGenerator(List<Integer> els) {
    this.els = els;
    this.idx = 0;
  }

  @Override
  public boolean hasNext() {
    return idx < els.size();
  }

  @Override
  public Integer next() {
    if (!hasNext()) throw new NoSuchElementException();
    return els.get(idx++);
  }
}
