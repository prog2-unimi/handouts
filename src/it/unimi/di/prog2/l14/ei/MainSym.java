package it.unimi.di.prog2.l14.ei;

import it.unimi.di.prog2.l14.ei.sym.S;
import it.unimi.di.prog2.l14.ei.sym.T;

/*
 * Per una discussione di questo codice, si veda
 * https://prog2.di.unimi.it/guides/equalityandinheritance
 */

public class MainSym {
  public static void main(String[] args) {
    S s = new S(1, 2);
    T t = new T(1);
    System.out.println(t.equals(s));
    System.out.println(s.equals(t));
  }
}


