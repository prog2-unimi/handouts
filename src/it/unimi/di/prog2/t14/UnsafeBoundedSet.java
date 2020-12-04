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

package it.unimi.di.prog2.t14;

import it.unimi.di.prog2.t07.EmptyException;
import java.util.Arrays;

/**
 * An unsafe version of {@link SafeBoundedSet}.
 *
 * @see SafeBoundedSet
 */
public class UnsafeBoundedSet {

  private final Object[] elements;
  private int next;

  public UnsafeBoundedSet(final int n) {
    if (n <= 0) throw new IllegalArgumentException();
    elements = new Object[n];
    next = 0;
  }

  private int indexOf(final Object element) {
    for (int i = 0; i < next; i++) if (elements[i] == element) return i;
    return -1;
  }

  public boolean insert(final Object element) {
    final int idx = indexOf(element);
    if (idx != -1) return false;
    if (next == elements.length) throw new ExceededBoundException();
    elements[next++] = element;
    return true;
  }

  public boolean remove(final Object element) {
    final int idx = indexOf(element);
    if (idx == -1) return false;
    if (--next > 0) elements[idx] = elements[next];
    return true;
  }

  public Object choose() {
    if (next == 0) throw new EmptyException();
    return elements[--next];
  }

  @Override
  public String toString() {
    return Arrays.toString(Arrays.copyOf(elements, next));
  }
}
