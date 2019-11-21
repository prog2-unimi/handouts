package it.unimi.di.prog2.l11;

import java.util.Iterator;

public class UseAllPrimes {

  public static void printPrimes(int m) {
    // MODIFIES: System.out
    // EFFECTS: Prints all the primes less than or equal to m on System.out
    Iterator<Integer> g = Num.allPrimes();
    while (true) {
      int p = g.next();
      if (p > m)
        return;
      System.out.println("The next prime is: " + p);
    }
  }

  public static void main(String[] args) {
    printPrimes(Integer.parseInt(args[0]));
  }
}
