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
 * A celestial body.
 *
 * <p>A celestial body is characterized by
 *
 * <ul>
 *   <li>a <em>name</em> (not empty or composed of only spaces);
 *   <li>a <em>position</em> and a <em>velocity</em> given by a {@link Point};
 *   <li>an <em>energy</em> given by the product of the norm of the position and the norm of the
 *       velocity.
 * </ul>
 *
 * <p>A celestial body can update its velocity based on the interaction with another celestial body;
 * once its velocity is modified, it can update its position. For details, refer to the
 * <em>overview</em> of this package.
 *
 * <p>Equality and ordering between celestial bodies are based solely on the name.
 */
public abstract class CelestialBody implements Comparable<CelestialBody> {

  /** The name of the celestial body. */
  private final String name;

  /** The position of the celestial body. */
  private Point position;

  /*-
   * RI: - name must not be null and must not be empty or composed of only spaces;
   *     - position must not be null;
   *
   * AF: - the name and position of the celestial body are contained in the fields with the same name.
   */

  /**
   * Constructs a celestial body with the given name.
   *
   * @param name the name.
   * @param x the x coordinate of the initial position.
   * @param y the y coordinate of the initial position.
   * @param z the z coordinate of the initial position.
   * @throws NullPointerException if the name is {@code null}.
   * @throws IllegalArgumentException if the name is composed of only spaces, or empty.
   */
  protected CelestialBody(final String name, int x, int y, int z) {
    if (Objects.requireNonNull(name).isBlank()) throw new IllegalArgumentException();
    this.name = name;
    position = new Point(x, y, z);
  }

  /**
   * Returns the name of this celestial body.
   *
   * @return the name.
   */
  public String name() {
    return name;
  }

  /**
   * Returns the position of this celestial body.
   *
   * @return the position (not {@code null}}).
   */
  public Point position() {
    return position;
  }

  /**
   * Sets the position of this celestial body.
   *
   * @param position the new position.
   * @throws NullPointerException if the position is {@code null}.
   */
  protected void position(Point position) {
    this.position = Objects.requireNonNull(position);
  }

  /**
   * Returns the velocity of this celestial body.
   *
   * @return the velocity (not {@code null}}).
   */
  public abstract Point velocity();

  /**
   * Returns the total energy of this celestial body.
   *
   * @return the energy.
   */
  public long energy() {
    return position().norm() * velocity().norm();
  }

  /** Updates the position of this celestial body (usually following a change in its velocity). */
  public abstract void updatePosition();

  /**
   * Updates the velocity of this celestial body.
   *
   * <p>The velocity must be updated based on the interaction with the celestial body given as an
   * argument according to what is illustrated in the <em>overview</em> of this package; this method
   * <em>must not modify</em> the celestial body given as an argument.
   *
   * @param c the celestial body to interact with.
   * @throws NullPointerException if c is {@code null}.
   */
  public abstract void updateVelocity(final CelestialBody c);

  @Override
  public int compareTo(CelestialBody o) {
    return name.compareTo(o.name);
  }

  @Override
  public boolean equals(Object obj) {
    if (!(obj instanceof CelestialBody)) return false;
    return name.equals(((CelestialBody) obj).name);
  }

  @Override
  public int hashCode() {
    return name.hashCode();
  }
}
