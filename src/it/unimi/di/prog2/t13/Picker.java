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

/** Una interfaccia utile a scegliere dei candidati per delle interrogazioni. */
public interface Picker {
  /**
   * Restituisce uno dei candidati possibili.
   *
   * @return un candidato.
   * @throws NoSuchElementException se non ci sono più candidati.
   */
  public String pick() throws NoSuchElementException;

  /**
   * Restituisce il numero richiesto di candidati.
   *
   * @param k il numero di candidati da restituire.
   * @return i candidati.
   * @throws IllegalArgumentException se k è minore di 1.
   * @throws NoSuchElementException se k eccede il numero di candidati rimanenti.
   */
  public default String[] pick(int k) throws IllegalArgumentException, NoSuchElementException {
    if (k < 1) throw new IllegalArgumentException("Il numero di candidati dev'essere positivo.");
    if (k > remaining()) throw new NoSuchElementException("Non ci sono abbastanza candidati.");
    final String[] retval = new String[k];
    for (int i = 0; i < k; i++) retval[i] = pick();
    return retval;
  }

  /** Restituisce il numero dei candidati che sono rimasti da chiamare. */
  public int remaining();
}
