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

package it.unimi.di.prog2.h11;

import java.util.Scanner;

/** Test del prodotto tra frazioni e numeri interi. */
public class TestProdottoPerIntero {

  /**
   * Riceva il denominatore e numeratore di una frazione come arogmenti sulla linea di comando e
   * quindi legge dal flusso di ingresso una sequenza di interi e dopo aver moltiplicato la frazione
   * per tali interi emette nel flusso di uscita la frazione risultante, ridotta ai mininmi termini.
   *
   * <p>Ad esempio, se nel flusso d'ingresso ci fossero le coppie
   *
   * <pre>
   * 2 4
   * 4 -2
   * -2 3
   * </pre>
   *
   * il main, avendo
   *
   * <pre>7 11</pre>
   *
   * come parametri sulla linea di comando, deve emettere nel flusso d'uscita
   *
   * <pre>
   * -7
   * </pre>
   *
   * @param args denominatore e numeratore della frazione.
   */
  public static void main(String[] args) {
    Frazione f = Frazione.numDen(Integer.parseInt(args[0]), Integer.parseInt(args[1]));
    try (Scanner s = new Scanner(System.in)) {
      while (s.hasNextInt()) f = f.prodotto(s.nextInt());
      System.out.println(f);
    }
  }
}
