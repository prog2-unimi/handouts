/*

Copyright 2024 Massimo Santini

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

package it.unimi.di.prog2.h03;

/**
 * Una classe che mostra alcune caratteristiche legate all'<a
 * href="https://dev.java/learn/oop/#inheritance">ereditarietà</a>.
 */
public class SequenzeDiCaratteri {

  /** . */
  private SequenzeDiCaratteri() {}

  /**
   * Il metodo principale.
   *
   * <p>Questo metodo sfrutta il fatto che {@link CharSequence} (usato per la variabile locale
   * {@code sequenza}) è un supertipo sia di {@link String} (usato per la variabile locale {@code
   * stringa} che di {@link StringBuilder} (usato per la variabile {@code concatenatore}) per
   * mostrare tra l'altro come:
   *
   * <ul>
   *   <li>è possibile invocare i metodi {@link CharSequence#length() length} e {@link
   *       CharSequence#charAt(int) charAt} quale che sia il sottotipo dell'oggetto riferito dalla
   *       variabile {@code sequenza};
   *   <li>è possibile invocare il metodo {@link StringBuilder#append(String) append} al sottotipo
   *       {@link StringBuilder} attraverso la variabile {@code concatenatore}.
   * </ul>
   *
   * @param args gli argomenti della riga di comando
   */
  public static void main(String[] args) {

    CharSequence sequenza;

    String stringa = new String(args[0]);

    sequenza = stringa;
    for (int i = 0; i < sequenza.length(); i++)
      System.out.printf("%02d: %s\n", i, sequenza.charAt(i));

    StringBuilder concatenatore = new StringBuilder();

    for (int i = 0; i < args.length; i++) concatenatore.append(args[i]);

    sequenza = concatenatore;
    for (int i = 0; i < sequenza.length(); i++)
      System.out.printf("%02d: %s\n", i, sequenza.charAt(i));
  }
}
