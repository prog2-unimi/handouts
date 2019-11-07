package it.unimi.di.prog2.l04;

public class Num {

  static int fact(int n) {
    int f = 1;
    for (int i = n; i > 0; i--)
      f *= i;
    return f;
  }
}
