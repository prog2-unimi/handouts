package it.unimi.di.prog2.l14.oi.sr;

public class Square extends Rectangle {

  public Square(int base) {
    super(base, base);
  }

  public void base(int base) {
    super.base(base);
    super.height(base);
  }

  public void height(int height) {
    super.base(height);
    super.height(height);
  }

  @Override
  public String toString() {
    return String.format("Square, base = %d", base());
  }
}

