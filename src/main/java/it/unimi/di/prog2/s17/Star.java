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

/**
 * A fixed star.
 *
 * <p>A fixed star is a {@link CelestialBody} with fixed position and velocity (and energy equal to
 * 0).
 */
public class Star extends CelestialBody {

  /*-
   * RI: - always true: it has no state and the attributes of the superclass are private
   *       so they do not concern this RI;
   *
   * AF: - the AF of the supertype applies.
   */

  /**
   * Constructs a fixed star.
   *
   * <p>Constructs a fixed star given its name and position.
   *
   * @param nome the name.
   * @param x the x coordinate of the initial position.
   * @param y the y coordinate of the initial position.
   * @param z the z coordinate of the initial position.
   * @throws NullPointerException if the name is {@code null}.
   * @throws IllegalArgumentException if the name is composed of only spaces, or empty.
   */
  public Star(final String nome, final int x, final int y, final int z) {
    super(nome, x, y, z);
  }

  @Override
  public Point velocity() {
    return Point.ZERO;
  }

  @Override
  public long energy() {
    return 0;
  }

  @Override
  public void updatePosition() {}

  @Override
  public void updateVelocity(final CelestialBody c) {}

  @Override
  public String toString() {
    return String.format("Star, name: %s, pos: %s", name(), position());
  }
}
