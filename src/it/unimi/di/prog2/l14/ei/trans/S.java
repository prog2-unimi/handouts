package it.unimi.di.prog2.l14.ei.trans;


public class S extends T {
  private final int b;

  public S(int a, int b) {
    super(a);
    this.b = b;
  }

  public boolean equals(Object o) {
    if (o instanceof S) {
      final S s = (S) o;
      return super.equals(o) && b == s.b;
    }
    if (o instanceof T)
      return super.equals(o);
    return false;
  }
}
