package it.unimi.di.prog2.l14.oi.rs;

public class Rectangle extends Square {
  private int height;

  public Rectangle(int base, int height) {
    super(base);
    this.height = height;
  }

  public int height() {
    return this.height;
  }

  public void height(int height) {
    this.height = height;
  }

  @Override
  public String toString() {
    return String.format("Rectangle: base = %d, height = %d", base(), height);
  }
}

