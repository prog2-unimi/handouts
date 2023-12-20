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

package it.unimi.di.prog2.h22;

import java.util.List;
import java.util.Objects;
import java.util.Random;

public class ListUtils {
  private ListUtils() {}

  private static final Random RND = new Random();

  /**
   * Restituisce il massimo elemento di una lista.
   *
   * @param <T> il tipo di elemento della lista.
   * @param lst la lista di elementi.
   * @return il massimo valore della lista.
   * @throws IllegalArgumentException se la lista è vuota.
   * @throws NullPointerException se la lista è {@code null}.
   */
  public static <T extends Comparable<T>> T max(List<T> lst) {
    if (Objects.requireNonNull(lst).isEmpty()) throw new IllegalArgumentException();
    T max = lst.get(0);
    for (int i = 1; i < lst.size(); i++) {
      T v = lst.get(i);
      if (v.compareTo(max) > 0) max = v;
    }
    return max;
  }

  /**
   * Permuta gli elementi della lista in modo causale.
   *
   * @param <T> il tipo di elemento della lista.
   * @param lst la lista di elementi.
   * @throws NullPointerException se la lista è {@code null}.
   */
  public static <T> void shuffle(List<T> lst) {
    Objects.requireNonNull(lst);
    for (int i = 0; i < lst.size(); i++) {
      T temp = lst.get(i);
      int j = RND.nextInt(i, lst.size());
      lst.set(i, lst.get(j));
      lst.set(j, temp);
    }
  }
}
