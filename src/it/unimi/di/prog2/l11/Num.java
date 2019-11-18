package it.unimi.di.prog2.l11;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Num {

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
          if (n % el == 0)
            break; // not a prime
          if (el * el > n) { // have a prime
            ps.add(n);
            p = n + 2;
            return n;
          }
        }
    }
  } // end PrimesGen

}
