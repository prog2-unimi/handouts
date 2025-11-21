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
import java.util.SortedSet;
import java.util.StringJoiner;
import java.util.TreeSet;

/**
 * An astronomical system.
 *
 * <p>An astronomical system is an ordered set of celestial bodies; it is capable of simulating the
 * evolution (of position and velocity) of the celestial bodies contained within it, and of
 * calculating its total energy.
 *
 * <p>The intended use of this class is:
 *
 * <ul>
 *   <li>create an astronomical system;
 *   <li>add a list of celestial bodies to it;
 *   <li>perform the simulation;
 *   <li>calculate the total energy.
 * </ul>
 *
 * <p>The method {@link #add(CelestialBody)} does not make a copy of the celestial body, therefore
 * if the celestial body is modified after being added, the behavior of the astronomical system is
 * undefined. The last two steps can be repeated to know the intermediate state of the simulation;
 * conversely, adding celestial bodies after the start of the simulation makes the behavior of the
 * astronomical system undefined.
 */
public class AstronomicalSystem {

  /** The set of celestial bodies. */
  private final SortedSet<CelestialBody> celestialBodies;

  /*-
   * RI: - celestialBodies must not be or contain null,
   * AF: - the celestial bodies of the system are contained in celestialBodies.
   */

  /** Creates an empty system. */
  public AstronomicalSystem() {
    celestialBodies = new TreeSet<>();
  }

  /**
   * Adds a celestial body to the system.
   *
   * <p>If the celestial body is already present in the system (i.e., there is already a celestial
   * body with the same name), this request is silently ignored.
   *
   * @param c the celestial body.
   * @return {@code true} if the celestial body was added, {@code false} otherwise.
   * @throws NullPointerException if c is null.
   */
  public boolean add(final CelestialBody c) {
    return celestialBodies.add(Objects.requireNonNull(c));
  }

  /**
   * Performs a simulation step.
   *
   * <p>For details see the <em>overview</em> of this package.
   */
  private void step() {
    for (final CelestialBody p : celestialBodies)
      for (final CelestialBody c : celestialBodies) {
        if (p == c) continue;
        p.updateVelocity(c);
      }
    for (final CelestialBody c : celestialBodies) c.updatePosition();
  }

  /**
   * Simulates the evolution of the system for a given number of steps.
   *
   * @param num the number of steps to simulate.
   * @throws IllegalArgumentException if num is not positive.
   */
  public void simulateSteps(final int num) {
    if (num <= 0) throw new IllegalArgumentException();
    for (int i = 0; i < num; i++) step();
  }

  /**
   * Returns the total energy of the system.
   *
   * @return the total energy.
   */
  public long totalEnergy() {
    long res = 0;
    for (final CelestialBody c : celestialBodies) res += c.energy();
    return res;
  }

  @Override
  public String toString() {
    final StringJoiner sj = new StringJoiner("\n");
    for (CelestialBody c : celestialBodies) sj.add(c.toString());
    return sj.toString();
  }
}
