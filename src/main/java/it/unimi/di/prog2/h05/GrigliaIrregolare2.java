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

/** Secondo esempio di astrazione per parametrizzazione. */
public class GrigliaIrregolare2 {

  /** . */
  private GrigliaIrregolare2() {}

  /**
   * Metodo principale.
   *
   * @param args non utilizzato.
   */
  public static void main(String[] args) {

    // I punti di una griglia irregolare

    int[] primo = {10, 6, 9, 8, 2};
    int[] secondo = {1, 5, 3, 4, 7, 11};

    int[] v1, v2;
    int i, x, y;

    // Domanda: Il punto (5, 7) appartiene alla griglia?

    x = 5;
    y = 7;
    v1 = primo;
    v2 = secondo;

    for (i = 0; i < v1.length; i++) if (v1[i] == x) break;
    if (i < v1.length) {
      for (i = 0; i < v2.length; i++) if (v2[i] == y) break;
      if (i < v2.length) System.out.printf("Il punto (%d, %d) appartiene alla griglia\n", x, y);
      else System.out.printf("Il punto (%d, %d) non appartiene alla griglia\n", x, y);
    } else System.out.printf("Il punto (%d, %d) non appartiene alla griglia\n", x, y);

    // e il punto (4, 9)?

    x = 4;
    y = 9;
    v1 = secondo;
    v2 = primo;

    for (i = 0; i < v1.length; i++) if (v1[i] == x) break;
    if (i < v1.length) {
      for (i = 0; i < v2.length; i++) if (v2[i] == y) break;
      if (i < v2.length) System.out.printf("Il punto (%d, %d) appartiene alla griglia\n", x, y);
      else System.out.printf("Il punto (%d, %d) non appartiene alla griglia\n", x, y);
    } else System.out.printf("Il punto (%d, %d) non appartiene alla griglia\n", x, y);
  }
}
