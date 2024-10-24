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

package it.unimi.di.prog2.s06;

import java.util.Scanner;

/** Classe client per il calcolo delle radici quadrate. */
public class RadiciClient {

  /** . */
  private RadiciClient() {}

  /**
   * Metodo che esercita il metodo di calcolo delle radici.
   *
   * <p>Legge una sequenza di {@code double} dal flusso di ingresso e ne emette la radice quadrata
   * nel flusso d'uscita.
   *
   * @param args non usato.
   */
  public static void main(String[] args) {
    try (Scanner s = new Scanner(System.in)) {
      while (s.hasNextDouble()) {
        double x = s.nextDouble();
        double y = Radici.radiceTotale(x);
        System.out.println(Math.abs(y * y - x) < 0.001);
      }
    }
  }
}
