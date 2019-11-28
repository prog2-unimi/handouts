package it.unimi.di.prog2.l14;

import java.util.Set;
import it.unimi.di.prog2.l14.ColoredPoint2d.Color;

public class PlayWithPoints {

  public static final Set<Point2d> ON_UNIT_CIRCLE_AND_AXES =
      Set.of(new Point2d(1, 0), new Point2d(0, 1), new Point2d(-1, 0), new Point2d(-1, 1));

  public static boolean isonUnitCircleAndAxes(Point2d p) {
    return ON_UNIT_CIRCLE_AND_AXES.contains(p);
  }

  public static void checkImpllies(boolean premise, boolean conclusion) {
    System.out.println(!premise || conclusion ? "ok" : "fail");
  }

  public static void main(String[] args) {

    Point2d p0 = new Point2d(0, 1);
    Point3d p1 = new Point3d(0, 1, 2);
    Point3d p2 = new Point3d(0, 1, 3);
    ColoredPoint2d p4 = new ColoredPoint2d(0, 1, Color.RED);

    // Symmetry
    checkImpllies(p0.equals(p1), p1.equals(p0));

    // Symmetry (violated if Point2d hides its specialized equals)
    checkImpllies(p0.equals(p4), p4.equals(p0));

    // Transitivity (violated)
    checkImpllies(p1.equals(p0) && p0.equals(p2), p1.equals(p2));

    // Liskov Substitution Principle (violated)
    checkImpllies(isonUnitCircleAndAxes(p0), isonUnitCircleAndAxes(p4));
  }

}
