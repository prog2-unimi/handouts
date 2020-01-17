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

package it.unimi.di.prog2.l23.bitmap;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

/** An immutable stamp. */
public class Stamp implements Figure {

  // the representation invariant is that coords is not null and contains the
  // set of this stamp coordinates; since the class is immutable, such invariant
  // is always preserved given that it is ensured by the only constructor of
  // this class
  private final Set<Coord> coords;

  public Stamp(final int[][] mat) {
    if (mat == null) throw new IllegalArgumentException("mat must not be null");
    Set<Coord> temp = new HashSet<Coord>();
    for (int r = 0; r < mat.length; r++)
      for (int c = 0; c < mat[r].length; c++) if (mat[r][c] != 0) temp.add(new Coord(r, c));
    coords = Collections.unmodifiableSet(temp);
  }

  @Override
  public void draw(final Bitmap bitmap) {
    for (Coord p : coords) bitmap.turnOn(p);
  }

  public void draw(Bitmap bitmap, final int r, final int c) {
    for (Coord p : coords) bitmap.turnOn(new Coord(p.r + r, p.c + c));
  }
}
