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

import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

/** Una implementazoine parziale di {@code Picker} che seleziona i candidati da una *lista*. */
public abstract class AbstractListPicker implements Picker {

  /** I candidati da chiamare. */
  private final List<String> candidates;

  // RI: candidates != null e c != null per ogni c in candidates

  /**
   * Costruisce una istanza a partire da un elenco di candidati.
   *
   * @param candidates un elenco di candidati.
   * @throws NullPointerException se l'elenco è null, o contiene null.
   */
  protected AbstractListPicker(final List<String> candidates) throws NullPointerException {
    Objects.requireNonNull(candidates);
    for (final String c : candidates) Objects.requireNonNull(c);
    this.candidates = new LinkedList<>(candidates);
  }

  @Override
  public int remaining() {
    return candidates.size();
  }

  /**
   * Questo metodo restituisce l'i-esimo candidato e trasla di una posizione a sinistra i
   * successivi.
   *
   * @param i l'indice dell'elemento da rimuovere
   * @throws IndexOutOfBoundsException se l'indice non corrisponde a nessun candidato rimanente.
   */
  protected String remove(final int i) throws IndexOutOfBoundsException {
    return candidates.remove(i);
  }
}
