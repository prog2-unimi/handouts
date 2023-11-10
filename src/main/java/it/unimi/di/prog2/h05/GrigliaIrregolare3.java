/*

Copyright 2021 Luca Prigioniero, Massimo Santini
Copyright 2023 Massimo Santini

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

public class GrigliaIrregolare3 {

  public static boolean appartiene(int[] ascisse, int[] ordinate, int x, int y) {
    int[] lista;
    int i;

    lista = ascisse;
    for (i = 0; i < lista.length; i++) if (lista[i] == x) break;
    if (i < lista.length) {
      lista = ordinate;
      for (i = 0; i < lista.length; i++) if (lista[i] == y) break;
      if (i < lista.length) return true;
      else return false;
    } else return false;
  }

  public static void main(String[] args) {

    // I punti di una griglia irregolare

    int[] ascisse = {10, 6, 4, 8, 2};
    int[] ordinate = {1, 5, 3, 9, 7, 11};

    // Domanda: Il punto (5, 7) appartiene alla griglia?

    if (appartiene(ascisse, ordinate, 5, 7))
      System.out.println("Il punto (5, 7) appartiene alla griglia");
    else System.out.println("Il punto (5, 7) non appartiene alla griglia");

    // e il punto (4, 9)?

    if (appartiene(ascisse, ordinate, 4, 9))
      System.out.println("Il punto (4, 9) appartiene alla griglia");
    else System.out.println("Il punto (4, 9) non appartiene alla griglia");
  }
}
