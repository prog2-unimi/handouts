package it.unimi.di.prog2.l14;

public class ColoredPoint2d extends Point2d {

  static enum Color {
    RED, GREEN, BLUE
  };

  final Color c;

  public ColoredPoint2d(int x, int y, Color c) {
    super(x, y);
    this.c = c;
  }

  @Override
  public String toString() {
    return String.format("ColoredPoint2d(%d, %d), %s", x, y, c.toString());
  }

  @Override
  public boolean equals(Object o) {
    if (o == null || o.getClass() != getClass())
      return false;
    return super.equals(o);
  }
}
