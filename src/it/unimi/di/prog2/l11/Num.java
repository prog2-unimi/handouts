/*

Copyright 2019 Massimo Santini

This file is part of "Programmazione 2 @ UniMI" teaching material.

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

package it.unimi.di.prog2.l11;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Num {

  private Num() {}

  public static Iterator<Integer> allPrimes() {
    return new PrimesGen();
  }

  // inner class
  private static class PrimesGen implements Iterator<Integer> {
    private List<Integer> ps; // primes yielded
    private int p; // next candidate to try

    PrimesGen() {
      p = 2;
      ps = new ArrayList<>();
    }

    public boolean hasNext() {
      return true;
    }

    public Integer next() {
      if (p == 2) {
        ps.add(2);
        p = 3;
        return 2;
      }
      for (int n = p; true; n = n + 2)
        for (int i = 0; i < ps.size(); i++) {
          int el = ps.get(i);
          if (n % el == 0) break; // not a prime
          if (el * el > n) { // have a prime
            ps.add(n);
            p = n + 2;
            return n;
          }
        }
    }
  } // end PrimesGen
}
