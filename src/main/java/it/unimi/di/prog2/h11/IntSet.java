/*

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

package it.unimi.di.prog2.h11;

import it.unimi.di.prog2.h08.impl.EmptyException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * {@code IntSet}s are mutable, unbounded sets of integers.
 *
 * <p>A typical IntSet is \( S = \{x_1, \ldots, x_n \} \).
 */
public class IntSet {

  /** The {@link List} containing this set elements. */
  private final List<Integer> els;

  /*
   * AF: gli elementi dell'IntSet sono tutti e soli gli interi
   *     contenuti nella lista; detto altrimenti,
   *     S = { els.get(i) per 0 <= i < els.size() }
   *
   * RI: els != null
   *     els non deve contenere null
   *     els non deve contenere duplicati, detto altrimenti
   *     se 0 <= i, j < els.size() e i != j allora els.get(i) != els.get(j)
   *
   */

  private boolean repOk() {
    for (int i = 0; i < els.size(); i++)
      for (int j = 0; j < els.size(); j++)
        if (i != j && els.get(i).equals(els.get(j))) return false;
    return true;
  }

  /**
   * Initializes this set to be empty.
   *
   * <p>Builds the set \( S = \varnothing \).
   */
  public IntSet() {
    els = new ArrayList<>();
    assert repOk();
  }

  /**
   * A *copy constructor*.
   *
   * @param other the {@code IntSet} to copy from.
   */
  public IntSet(IntSet other) {
    els = new ArrayList<Integer>(other.els);
    assert repOk();
  }

  // Methods

  /**
   * Looks for a given element in this set.
   *
   * @param x the element to look for.
   * @return the index where {@code x} appears in {@code els} if the element belongs to this set, or
   *     -1
   */
  private int getIndex(int x) {
    return els.indexOf(x);
  }

  /**
   * Adds the given element to this set.
   *
   * <p>This method modifies the object, that is: \( S' = S \cup \{ x \} \).
   *
   * @param x the element to be added.
   */
  public void insert(int x) {
    if (getIndex(x) < 0) els.add(x);
    assert repOk();
  }

  /**
   * Removes the given element from this set.
   *
   * <p>This method modifies the object, that is: \( S' = S \setminus \{ x \} \).
   *
   * @param x the element to be removed.
   */
  public void remove(int x) {
    int i = getIndex(x);
    if (i < 0) return;
    int last = els.size() - 1;
    els.set(i, els.get(last));
    els.remove(last);
    assert repOk();
  }

  /**
   * Tells if the given element is in this set.
   *
   * <p>Answers the question \( x\in S \).
   *
   * @param x the element to look for.
   * @return whether the given element belongs to this set, or not.
   */
  public boolean isIn(int x) {
    return getIndex(x) != -1;
  }

  /**
   * Returns the cardinality of this set.
   *
   * <p>Responds with \( |S| \).
   *
   * @return the size of this set.
   */
  public int size() {
    return els.size();
  }

  /**
   * Returns an element from this set.
   *
   * @return an arbitrary element from this set.
   * @throws EmptyException if this set is empty.
   */
  public int choose() throws EmptyException {
    if (els.isEmpty()) throw new EmptyException("Can't choose from an empty set");
    return els.get(els.size() - 1);
  }

  public List<Integer> elements() {
    return els;
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj) return true;
    if (!(obj instanceof IntSet)) return false;
    IntSet other = (IntSet) obj;
    if (els.size() != other.els.size()) return false;
    for (int e : els) if (!other.isIn(e)) return false;
    return true;
  }

  @Override
  public int hashCode() {
    els.sort(null); // benevolent side effect
    return Objects.hash(els.size(), els);
  }

  @Override
  public String toString() {
    String lst = els.toString();
    return "IntSet: {" + lst.substring(1, lst.length() - 1) + "}";
  }
}
