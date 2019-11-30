package it.unimi.di.prog2.l14.ei.lsp;

public class T {
  private final int a;

  public T(int a) {
    this.a = a;
  }

  public boolean equals(Object o) {
    if (getClass() == o.getClass()) {
      final T t = (T) o;
      return a == t.a;
    }
    return false;
  }
}
