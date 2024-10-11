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

package it.unimi.di.prog2.h04;

/**
 * Vedi <a
 * href="https://github.com/mapio/labprog/blob/master/esercizi/ultima_ripetizione/Testo.md">testo</a>.
 */
public class UltimaRipetizione {

  /** . */
  private UltimaRipetizione() {}

  /**
   * Stampa l'ultima parola ripetuta tra quelle passate come argomenti.
   *
   * @param args le parole da esaminare.
   */
  public static void main(String[] args) {
    for (int i = args.length - 1; i >= 0; i--)
      for (int j = i - 1; j >= 0; j--)
        if (args[i].equals(args[j])) {
          System.out.println(args[i]);
          return;
        }
  }
}
