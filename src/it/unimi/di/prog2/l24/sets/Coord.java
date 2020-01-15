/*

Copyright 2019 Massimo Santini

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

package it.unimi.di.prog2.l24.sets;

import java.util.Objects;

/**
 * An immutable pair of coordinates of a pixel.
 *
 * <p>It is a *value* class: two coordinates are considered equal if their rows and columns are
 * respectively equal.
 */
public class Coord {

  // The representation invariant is always true: any pair of integers is a valid
  // coordinate
  public final int r, c;

  public Coord(final int r, final int c) {
    this.r = r;
    this.c = c;
  }

  @Override
  public boolean equals(Object obj) {
    if (!(obj instanceof Coord)) return false;
    final Coord other = (Coord) obj;
    return c == other.c && r == other.r;
  }

  @Override
  public int hashCode() {
    return Objects.hash(c, r);
  }

  @Override
  public String toString() {
    return String.format("(%d, %d)", r, c);
  }
}
