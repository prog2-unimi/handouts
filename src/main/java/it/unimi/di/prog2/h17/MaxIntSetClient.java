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

package it.unimi.di.prog2.h17;

import java.util.Scanner;

/** Classe di test per {@link MaxIntSet}. */
public class MaxIntSetClient {

  /**
   * Legge una serie di istruzioni e stampa l'insieme risultante dalla loro esecuzione.
   *
   * <p>Dopo aver istsanziato un insieme vuoto legge dal flusso di ingresso una serie di interi, se
   * positivi li aggiunge all'insieme, se negaivi rimuove il corridpondente valore assoluto. Se
   * l'intero è pari a 0 stampa l'insieme.
   *
   * @param args non usato.
   */
  public static void main(String[] args) {
    MaxIntSet M = new MaxIntSet();
    try (Scanner s = new Scanner(System.in)) {
      while (s.hasNextInt()) {
        int x = s.nextInt();
        if (x > 0) M.insert(x);
        else if (x < 0) M.remove(-x);
        else System.out.println(M);
      }
    }
  }
}
