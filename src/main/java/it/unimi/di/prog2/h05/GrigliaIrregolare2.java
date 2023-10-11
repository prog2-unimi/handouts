/*

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

public class GrigliaIrregolare2 {

  public static void main(String[] args) {

    // I punti di una griglia irregolare

    int[] ascisse = {10, 6, 4, 8, 2};
    int[] ordinate = {1, 5, 3, 9, 7, 11};
    int[] lista;
    int i, x, y;

    // Domanda: Il punto (5, 7) appartiene alla griglia?

    x = 5;
    y = 7;

    lista = ascisse;
    for (i = 0; i < lista.length; i++) if (lista[i] == x) break;
    if (i < lista.length) {
      lista = ordinate;
      for (i = 0; i < lista.length; i++) if (lista[i] == y) break;
      if (i < lista.length) System.out.printf("Il punto (%d, %d) appartiene alla griglia\n", x, y);
      else System.out.printf("Il punto (%d, %d) non appartiene alla griglia\n", x, y);
    } else System.out.printf("Il punto (%d, %d) non appartiene alla griglia\n", x, y);

    // e il punto (4, 9)?

    x = 4;
    y = 9;

    lista = ascisse;
    for (i = 0; i < lista.length; i++) if (lista[i] == x) break;
    if (i < lista.length) {
      lista = ordinate;
      for (i = 0; i < lista.length; i++) if (lista[i] == y) break;
      if (i < lista.length) System.out.printf("Il punto (%d, %d) appartiene alla griglia\n", x, y);
      else System.out.printf("Il punto (%d, %d) non appartiene alla griglia\n", x, y);
    } else System.out.printf("Il punto (%d, %d) non appartiene alla griglia\n", x, y);
  }
}
