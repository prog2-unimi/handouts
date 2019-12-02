package it.unimi.di.prog2.l14.ei;

import it.unimi.di.prog2.l14.ei.trans.S;
import it.unimi.di.prog2.l14.ei.trans.T;
/*
 * Per una discussione di questo codice, si veda
 * https://prog2.di.unimi.it/guides/equalityandinheritance
 */

public class MainTrans {
  public static void main(String[] args) {
    S s = new S(1, 2);
    T t = new T(1);
    S u = new S(1, 3);
    System.out.println(s.equals(t));
    System.out.println(t.equals(u));
    System.out.println(s.equals(u));
  }
}


