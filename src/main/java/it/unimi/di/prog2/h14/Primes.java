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

package it.unimi.di.prog2.h14;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/** Iteratore che genera i numeri primi. */
public class Primes implements Iterator<Integer> {

  /*-
   *  AF: il prossimo numero primo è il primo intero maggiore o uguale a
   *      candidate che non sia divisibile per uno dei primi già restituiti da next,
   *      contenuti in foundPrimes.
   *
   *  RI: - candidate è 2, oppure un numero dispari maggiore del più grande primo in
   *        foundPrimes
   *      - foundPrimes è vuoto, oppure contiene tutti i numeri primi miniori o uguali
   *        a candidate che sono stati restituiti da next.
   */

  /** Il prossimo potenziale numero primo. */
  private int candidate = 2;

  /** L'elenco di primi restiuiti da next. */
  private final List<Integer> foundPrimes = new LinkedList<>();

  @Override
  public boolean hasNext() {
    return true;
  }

  /**
   * Verifica se {@code n} è divisibile per un dei primi in {@link #foundPrimes}.
   *
   * @param n il numero da verificare.
   * @return {@code true} se {@code n} è divisibile per un dei primi già trovati.
   */
  private boolean isDivisibleByAFoundPrime(int n) {
    for (int p : foundPrimes) {
      if (n % p == 0) return true;
      if (p * p > n) return false;
    }
    return false;
  }

  @Override
  public Integer next() {
    if (candidate == 2) {
      foundPrimes.add(candidate);
      candidate = 3;
      return 2;
    } else
      for (int n = candidate; ; n += 2)
        if (!isDivisibleByAFoundPrime(n)) {
          foundPrimes.add(n);
          candidate = n + 2;
          return n;
        }
  }
}
