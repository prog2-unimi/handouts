package it.unimi.di.prog2.l14.ei.lsp;

public class S extends T {
  private final int b;

  public S(int a, int b) {
    super(a);
    this.b = b;
  }

  public boolean equals(Object o) {
    if (getClass() == o.getClass()) {
      final S s = (S) o;
      return super.equals(o) && b == s.b;
    }
    return false;
  }
}
