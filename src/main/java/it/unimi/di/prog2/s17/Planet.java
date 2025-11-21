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
 * A planet.
 *
 * <p>A planet is a {@link CelestialBody} with variable position and velocity.
 */
public class Planet extends CelestialBody {

  /** The velocity of this planet. */
  private Point velocity;

  /*-
   * RI: - velocity must not be null;
   *     - the attributes of the superclass are private so they do not concern this RI;
   *
   * AF: - the velocity of the planet is contained in the field with the same name, for the rest the AF of the supertype applies.
   */

  /**
   * Constructs a planet.
   *
   * <p>Constructs a planet initially at rest (i.e., with a velocity of zero) given its name and
   * initial position.
   *
   * @param name the name.
   * @param x the x coordinate of the initial position.
   * @param y the y coordinate of the initial position.
   * @param z the z coordinate of the initial position.
   * @throws NullPointerException if the name is {@code null}.
   * @throws IllegalArgumentException if the name is composed of only spaces, or is empty.
   */
  public Planet(final String name, final int x, final int y, final int z) {
    super(name, x, y, z);
    velocity = Point.ZERO;
  }

  @Override
  public Point velocity() {
    return velocity;
  }

  @Override
  public void updatePosition() {
    position(position().sum(velocity));
  }

  @Override
  public void updateVelocity(final CelestialBody c) {
    Objects.requireNonNull(c);
    final Point dv = c.position().subtract(position()).signum();
    velocity = velocity.sum(dv);
  }

  @Override
  public String toString() {
    return String.format("Planet, name: %s, pos: %s, vel: %s", name(), position(), velocity());
  }
}
