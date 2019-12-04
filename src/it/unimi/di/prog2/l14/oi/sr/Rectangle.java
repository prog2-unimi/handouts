package it.unimi.di.prog2.l14.oi.sr;

public class Rectangle {
  private int base, height;

  public Rectangle(int base, int height) {
    this.base = base;
    this.height = height;
  }

  public int base() {
    return this.base;
  }

  public void base(int base) {
    this.base = base;
  }

  public int height() {
    return this.height;
  }

  public void height(int height) {
    this.height = height;
  }

  @Override
  public String toString() {
    return String.format("WrongFigures.Rectangle: base = %d, height = %d", base(), height);
  }
}
