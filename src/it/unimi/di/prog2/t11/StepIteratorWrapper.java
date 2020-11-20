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
import java.util.NoSuchElementException;

public class StepIteratorWrapper<T> implements Iterator<T> {

  private final Iterator<T> it;
  private final int step;
  private T cache;

  public StepIteratorWrapper(Iterator<T> it, int step) {
    this.it = it;
    this.step = step;
    cache = it.hasNext() ? it.next() : null; // priming the cache
  }

  @Override
  public boolean hasNext() {
    if (cache != null) return true;
    int i = 0;
    for (i = 0; i < step && it.hasNext(); i++) cache = it.next();
    return i == step;
  }

  @Override
  public T next() {
    if (!hasNext()) throw new NoSuchElementException();
    T val = cache;
    cache = null;
    return val;
  }
}
