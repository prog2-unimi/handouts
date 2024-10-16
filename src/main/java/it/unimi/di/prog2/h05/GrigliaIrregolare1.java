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

package it.unimi.di.prog2.h05;

/** Primo esempio di astrazione per parametrizzazione. */
public class GrigliaIrregolare1 {

  /** . */
  private GrigliaIrregolare1() {}

  /**
   * Metodo principale.
   *
   * @param args non utilizzato.
   */
  public static void main(String[] args) {

    // I punti di una griglia irregolare, il primo vettore sono le ascisse, il secondo le ordinate

    int[] primo = {10, 6, 9, 8, 2};
    int[] secondo = {1, 5, 3, 4, 7, 11};
    int i;

    // Domanda: Il punto (5, 7) appartiene alla griglia?

    for (i = 0; i < primo.length; i++) if (primo[i] == 5) break;
    if (i < primo.length) {
      for (i = 0; i < secondo.length; i++) if (secondo[i] == 7) break;
      if (i < secondo.length) System.out.println("Il punto (5, 7) appartiene alla griglia");
      else System.out.println("Il punto (5, 7) non appartiene alla griglia");
    } else System.out.println("Il punto (5, 7) non appartiene alla griglia");

    // e il punto (4, 9), se invertiamo ascisse e ordinate?

    for (i = 0; i < secondo.length; i++) if (secondo[i] == 4) break;
    if (i < secondo.length) {
      for (i = 0; i < primo.length; i++) if (primo[i] == 9) break;
      if (i < primo.length) System.out.println("Il punto (4, 9) appartiene alla griglia");
      else System.out.println("Il punto (4, 9) non appartiene alla griglia");
    } else System.out.println("Il punto (4, 9) non appartiene alla griglia");
  }
}
