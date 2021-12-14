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

/**
 * Una implementazione parziale di {@code Picker} che seleziona i candidati tra i numeri minori di
 * un numero dato.
 */
public abstract class AbstractNumberPicker implements Picker {

  /** Il numero totale di candidati. */
  protected final int tot;

  // RI: tot >= 0
  // Si osservi che l'attributo è protetto, ma finale, quindi le sottoclassi non
  // possono falsificare il RI.

  /** Costruire una istanza a partire dal numero massimo di candidati. */
  public AbstractNumberPicker(final int tot) {
    if (tot < 0)
      throw new IllegalArgumentException("Il numero di candidati non può essere negativo.");
    this.tot = tot;
  }
}
