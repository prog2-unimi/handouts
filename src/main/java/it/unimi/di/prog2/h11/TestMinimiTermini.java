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

package it.unimi.di.prog2.h11;

import java.util.Scanner;

/** Test della riduzione ai minimi termini di una frazione. */
public class TestMinimiTermini {

  /**
   * Legge dal flusso di ingresso una sequenza di coppie di interi (che rappresentano denominatore e
   * numeratore di una frazione), costruisce le relative frazioni e quindi emette nel flusso di
   * uscita le frazioni ridotte ai mininmi termini.
   *
   * <p>Ad esempio, se nel flusso d'ingresso ci fossero le coppie
   *
   * <pre>
   * 2 4
   * 4 -2
   * -2 3
   * </pre>
   *
   * il main deve emettere nel flusso d'uscita
   *
   * <pre>
   * 1/2
   * -2
   * -2/3
   * </pre>
   *
   * @param args non usato.
   */
  public static void main(String[] args) {
    try (Scanner s = new Scanner(System.in)) {
      while (s.hasNextInt()) System.out.println(Frazione.numDen(s.nextInt(), s.nextInt()));
    }
  }
}
