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

package it.unimi.di.prog2.h03;

import java.util.Scanner;

/**
 * Vedi <a
 * href="https://github.com/mapio/labprog/blob/master/esercizi/quadrato_magico/Testo.md">testo</a>.
 */
public class VerificaQuadratoMagico {

  public static void main(String[] args) {
    try (Scanner s = new Scanner(System.in)) {

      int n = s.nextInt();
      int[][] M = new int[n][n];
      for (int i = 0; i < n; i++) for (int j = 0; j < n; j++) M[i][j] = s.nextInt();

      int somma = 0;
      for (int j = 0; j < n; j++) somma += M[0][j];

      for (int i = 1; i < n; i++) {
        int test = 0;
        for (int j = 0; j < n; j++) test += M[i][j];
        if (test != somma) return;
      }

      for (int j = 0; j < n; j++) {
        int test = 0;
        for (int i = 0; i < n; i++) test += M[i][j];
        if (test != somma) return;
      }

      int test = 0;
      for (int i = 0; i < n; i++) test += M[i][i];
      if (test != somma) return;

      test = 0;
      for (int i = 0; i < n; i++) test += M[i][n - i - 1];
      if (test != somma) return;

      System.out.println("magico");
    }
  }
}
