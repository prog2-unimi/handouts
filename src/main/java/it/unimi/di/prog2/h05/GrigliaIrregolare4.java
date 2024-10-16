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

/** Quarto esempio di astrazione per parametrizzazione. */
public class GrigliaIrregolare4 {

  /** . */
  private GrigliaIrregolare4() {}

  /**
   * Funzione che stabilisce se un dato valore appartiene ad una data lista.
   *
   * @param lista i valori in cui effettuare la ricerca.
   * @param v il valore da cercare.
   * @return {@code true} sse il valore appartiene alla lista.
   */
  public static boolean appartiene(int[] lista, int v) {
    for (int i = 0; i < lista.length; i++) if (lista[i] == v) return true;
    return false;
  }

  /**
   * Funzione che stabilisce se il punto (x, y) appartiene ad una data griglia irregolare.
   *
   * @param ascisse l'elenco di ascisse.
   * @param ordinate l'elenco di ordinate.
   * @param x l'ascissa del punto.
   * @param y l'ordinata del punto.
   * @return {@code true} sse (x,y) appartiene alla griglia.
   */
  public static boolean appartiene(int[] ascisse, int[] ordinate, int x, int y) {
    return appartiene(ascisse, x) && appartiene(ordinate, y);
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
