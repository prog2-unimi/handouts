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

package it.unimi.di.prog2.h13;

import java.util.Scanner;

/** Calsse utilizzata per verificare il comportamento di {@link IntQueue}. */
public class IntQueueClient {

  /**
   * Legge dal flusso di ingresso i seguenti dati:
   *
   * <ul>
   *   <li>un numero {@code n}, seguito da
   *   <li>una sequenza (non limitata) di {@code +1} e {@code -1}.
   * </ul>
   *
   * <p>Dopo aver instanziato una coda di dimensione {@code n}, esegue le seguenti operazioni fino
   * al termine del flusso d'ingresso, o fino al raggiungimento di uno stato non valido:
   *
   * <ul>
   *   <li>ogni volta che legge {@code +1}, esegue {@link IntQueue#enqueue(int)}, con argomento
   *       {@code numEnqueue} pari al numero di {@code +1} letti fino al momento dell'invocazione,
   *       mentre
   *   <li>ogni volta che legge {@code -1} esegue {@link IntQueue#dequeue()}, e stampa il numero
   *       estratto dalla coda.
   * </ul>
   *
   * <p>Al termine del flusso d'ingresso, qualora il numero di {@code -1} ecceda quello di {@code
   * +1}, oppure se si è tentato di inserire più elementi della <i>capienza</i> della coda, stampa
   * il contenuto della coda (utilizzando il metodo {@link #toString()}, seguito dal numero degli
   * elementi presenti nella coda
   *
   * @param args non usato.
   */
  public static void main(String[] args) {
    IntQueue queue;
    int nEnc = 0, nDec = 0;

    try (Scanner s = new Scanner(System.in)) {
      int n = s.nextInt();
      queue = new IntQueue(n);
      while (s.hasNextByte()) {
        byte op = s.nextByte();
        if (op == +1) {
          if (queue.size() == n) break;
          queue.enqueue(++nEnc);
        } else {
          if (nDec == nEnc) break;
          System.out.println(queue.dequeue());
          nDec++;
        }
      }
    }
    System.out.println(queue);
    System.out.println(queue.size());
  }
}
