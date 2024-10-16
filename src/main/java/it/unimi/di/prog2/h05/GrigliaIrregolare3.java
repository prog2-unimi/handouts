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

/** Terzo esempio di astrazione per parametrizzazione. */
public class GrigliaIrregolare3 {

  /** . */
  private GrigliaIrregolare3() {}

  /**
   * Funzione che stabilisce se il punto (x, y) appartiene ad una data griglia irregolare.
   *
   * @param v1 l'elenco di ascisse.
   * @param v2 l'elenco di ordinate.
   * @param x l'ascissa del punto.
   * @param y l'ordinata del punto.
   * @return {@code true} sse (x,y) appartiene alla griglia.
   */
  public static boolean appartiene(int[] v1, int[] v2, int x, int y) {
    int i;

    for (i = 0; i < v1.length; i++) if (v1[i] == x) break;
    if (i < v1.length) {
      for (i = 0; i < v2.length; i++) if (v2[i] == y) break;
      if (i < v2.length) return true;
      else return false;
    } else return false;
  }

  /**
   * Metodo principale.
   *
   * @param args non utilizzato.
   */
  public static void main(String[] args) {

    // I punti di una griglia irregolare

    int[] ascisse = {10, 6, 9, 8, 2};
    int[] ordinate = {1, 5, 3, 4, 7, 11};

    // Domanda: Il punto (5, 7) appartiene alla griglia?

    if (appartiene(ascisse, ordinate, 5, 7))
      System.out.println("Il punto (5, 7) appartiene alla griglia");
    else System.out.println("Il punto (5, 7) non appartiene alla griglia");

    // e il punto (4, 9)?

    if (appartiene(ordinate, ascisse, 4, 9))
      System.out.println("Il punto (4, 9) appartiene alla griglia");
    else System.out.println("Il punto (4, 9) non appartiene alla griglia");
  }
}
