package it.unimi.di.prog2.l14.ei.lsp;

/*
 * Per una discussione di questo codice, si veda
 * https://prog2.di.unimi.it/guides/equalityandinheritance
 */

public class R extends T {
  public R(int a) {
    super(a);
  }

  public void sayHi() {
    System.out.println("Hi!");
  }
}
