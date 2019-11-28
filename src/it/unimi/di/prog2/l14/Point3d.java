package it.unimi.di.prog2.l14;

public class Point3d extends Point2d {
  final int z;

  public Point3d(int x, int y, int z) {
    super(x, y);
    this.z = z;
  }

  @Override
  public String toString() {
    return String.format("Point3d(%d, %d, %d)", x, y, z);
  }

  public boolean equals(Point3d o) {
    if (o == null || z != o.z)
      return false;
    return super.equals(o);
  }

  // @Override // the one in Point2d
  public boolean equals(Point2d o) {
    if (o instanceof Point3d)
      equals((Point3d) o);
    return super.equals(o);
  }

  @Override // the one in Object
  public boolean equals(Object o) {
    if (o instanceof Point3d)
      equals((Point3d) o);
    return super.equals(o);
  }
}
