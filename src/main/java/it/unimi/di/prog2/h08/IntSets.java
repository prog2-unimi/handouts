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

package it.unimi.di.prog2.h08;

import java.util.Objects;

/** A collection of methods for {@link IntSet}s. */
public class IntSets {

  // See EJ 2.4
  private IntSets() {}

  /**
   * Builds a set from an array of elements.
   *
   * @param a and array of integer elements.
   * @return the set containing an entry for every distinct element of {@code a}.
   * @throws NullPointerException if {@code a} is {@code null}.
   */
  public static IntSet getElements(int[] a) throws NullPointerException {
    Objects.requireNonNull(a, "L'array non può essere null");
    IntSet s = new IntSet();
    for (int i = 0; i < a.length; i++) s.insert(a[i]);
    return s;
  }
}
