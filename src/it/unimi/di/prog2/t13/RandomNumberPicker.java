/*

Copyright 2021 Massimo Santini

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

package it.unimi.di.prog2.t13;

import java.util.NoSuchElementException;
import java.util.Random;
import java.util.Set;
import java.util.TreeSet;

/** Una implementazione di {@code AbstractNumberPicker} che sceglie i candidati in modo casuale. */
public class RandomNumberPicker extends AbstractNumberPicker {

  /** Il generatore di numeri casuale usato per scegliere i candidati. */
  private final Random rng = new Random();
  /** L'insieme dei candidati già chiamati. */
  private final Set<Integer> used = new TreeSet<>();

  public RandomNumberPicker(final int tot) {
    super(tot);
  }

  @Override
  public String pick() throws NoSuchElementException {
    if (used.size() == tot) throw new NoSuchElementException("Non sono rimasti candidati.");
    for (; ; ) {
      final int i = rng.nextInt(tot);
      if (!used.contains(i)) {
        used.add(i);
        return "" + i;
      }
    }
  }

  @Override
  public int remaining() {
    return tot - used.size();
  }
}
