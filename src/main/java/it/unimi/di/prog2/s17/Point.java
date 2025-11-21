/*

Copyright 2025 Massimo Santini

This file is part of "Programmazione 2 @ UniMI" teaching material.

This is free software: you can redistribute it and/or modify
it under the terms of the GNU General Public License as published by
the Free Software Foundation, either version 3 of the License, or
(at your option) any later version.

This material is distributed in the hope that it will be useful,
but WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
GNU General Public License for more details.

You should have received a copy of the GNU General Public License
along with this file.  If not, see <https://www.gnu.org/licenses/>.

*/

package it.unimi.di.prog2.s17;

import java.util.Objects;

/**
 * Immutable three-dimensional point with integer coordinates.
 *
 * @param x the x coordinate.
 * @param y the y coordinate.
 * @param z the z coordinate.
 */
public record Point(int x, int y, int z) {

  /** The point corresponding to the origin. */
  public static final Point ZERO = new Point(0, 0, 0);

  /**
   * Returns the sum of this point and the given point.
   *
   * @param q the point to be added to this point.
   * @return the sum.
   * @throws NullPointerException if q is null.
   */
  public Point sum(final Point q) {
    Objects.requireNonNull(q);
    final Point result = new Point(x + q.x, y + q.y, z + q.z);
    return result.equals(ZERO) ? ZERO : result;
  }

  /**
   * Returns the difference between this point and the given point.
   *
   * @param q the point to be subtracted from this point.
   * @return the difference.
   * @throws NullPointerException if q is null.
   */
  public Point subtract(final Point q) {
    Objects.requireNonNull(q);
    final Point result = new Point(x - q.x, y - q.y, z - q.z);
    return result.equals(ZERO) ? ZERO : result;
  }

  /**
   * Returns the l1 norm of this point.
   *
   * @return the norm.
   */
  public long norm() {
    return (x > 0 ? x : -x) + (y > 0 ? y : -y) + (z > 0 ? z : -z);
  }

  /**
   * Returns a point with coordinates -1, 0, or 1 depending on the sign of the coordinates of this
   * point.
   *
   * @return a point with coordinates representing the sign of this point's coordinates.
   */
  public Point signum() {
    return new Point((int) Math.signum(x), (int) Math.signum(y), (int) Math.signum(z));
  }

  @Override
  public String toString() {
    return String.format("(%d, %d, %d)", x, y, z);
  }
}
