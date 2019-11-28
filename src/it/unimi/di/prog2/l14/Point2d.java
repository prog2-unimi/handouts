package it.unimi.di.prog2.l14;

public class Point2d {
  final int x, y;

  public Point2d(int x, int y) {
    this.x = x;
    this.y = y;
  }

  @Override
  public String toString() {
    return String.format("Point2d(%d, %d)", x, y);
  }

  public boolean equals(Point2d o) {
    if (o == null)
      return false;
    return x == o.x && y == o.y;
  }

  @Override // the one in Object
  public boolean equals(Object o) {
    if (o instanceof Point2d)
      return equals((Point2d) o);
    return super.equals(o);
  }
}
