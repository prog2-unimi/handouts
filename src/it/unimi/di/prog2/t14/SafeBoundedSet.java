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
 * A set of elements of a given type {@code T}.
 *
 * <p>The set cannot contain {@code null} values. The set has a limited <em>capacity</em> (usually
 * defined in construction); inserting in a set that has reached its capacity throws an {@link
 * ExceededBoundException}.
 */
public class SafeBoundedSet<T> {

  private final T[] elements;
  private int next;

  /**
   * Creates a set of given capacity.
   *
   * @param capacity the set capacity.
   */
  @SuppressWarnings("unchecked") // this will be explained in the next lecture
  public SafeBoundedSet(final int capacity) {
    if (capacity <= 0) throw new IllegalArgumentException();
    elements = (T[]) new Object[capacity];
    next = 0;
  }

  /**
   * Inserts an element in the set.
   *
   * <p>Inserts an element in the set, if not present.
   *
   * @param element the element to be inserted.
   * @return wether the element has been inserted or not.
   * @throws ExceededBoundException in case the set has reached its capacity and {@code element} is
   *     not yet in the set.
   */
  public boolean insert(final T element) {
    final int idx = indexOf(element);
    if (idx != -1) return false;
    if (next == elements.length) throw new ExceededBoundException();
    elements[next++] = element;
    return true;
  }

  /**
   * Removes the given element from the set.
   *
   * <p>Removes the given element, if present in the set.
   *
   * @param element the element to remove.
   * @return wether the element has been removed or not.
   */
  public boolean remove(final T element) {
    final int idx = indexOf(element);
    if (idx == -1) return false;
    if (--next > 0) elements[idx] = elements[next];
    return true;
  }

  /**
   * Returns one of the elements in the set.
   *
   * @return an element from the set.
   * @throws EmptyException if the set is empty.
   */
  public T choose() {
    if (next == 0) throw new EmptySetException();
    return elements[--next];
  }

  /**
   * Finds the index of the given element in {@link #elements}.
   *
   * <p>If there is an index {@code idx} such that {@code elements[idx].equals(element)} returns it,
   * otherwise returns -1.
   *
   * @param element the element to look for.
   * @return the index of the found element, or -1.
   */
  private int indexOf(final Object element) {
    for (int i = 0; i < next; i++) if (elements[i] == element) return i;
    return -1;
  }

  @Override
  public String toString() {
    return Arrays.toString(Arrays.copyOf(elements, next));
  }
}
