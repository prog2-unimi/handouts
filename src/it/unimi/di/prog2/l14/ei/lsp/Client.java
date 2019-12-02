package it.unimi.di.prog2.l14.ei.lsp;

/*
 * Per una discussione di questo codice, si veda
 * https://prog2.di.unimi.it/guides/equalityandinheritance
 */

import java.util.Set;

public class Client {
  private static final Set<T> SMALLS = Set.of(new T(1), new T(2));

  public static boolean isSmall(T t) {
    return SMALLS.contains(t);
  }
}
