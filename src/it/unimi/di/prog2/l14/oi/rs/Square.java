package it.unimi.di.prog2.l14.oi.rs;

public class Square {
  private int base;

  public Square(int base) {
    this.base = base;
  }

  public int base() {
    return this.base;
  }

  public void base(int base) {
    this.base = base;
  }

  @Override
  public String toString() {
    return String.format("Square, base = %d", base);
  }
}

