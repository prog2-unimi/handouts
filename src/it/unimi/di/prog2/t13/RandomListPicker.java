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

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Random;

/** Una implementazione di {@code AbstractListPicker} che sceglie i candidati in modo casuale. */
public class RandomListPicker extends AbstractListPicker {

  /** Il generatore di numeri casuale usato per scegliere i candidati. */
  private final Random rng = new Random();

  protected RandomListPicker(final List<String> candidates) {
    super(candidates);
  }

  @Override
  public String pick() throws NoSuchElementException {
    if (remaining() == 0) throw new NoSuchElementException("Non sono rimasti candidati.");
    return remove(rng.nextInt(remaining()));
  }
}
