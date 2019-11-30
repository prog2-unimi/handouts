package it.unimi.di.prog2.l14.ei.trans;

public class T {
  private final int a;

  public T(int a) {
    this.a = a;
  }

  public boolean equals(Object o) {
    if (o instanceof T) {
      final T t = (T) o;
      return a == t.a;
    }
    return false;
  }
}
