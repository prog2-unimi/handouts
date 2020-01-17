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

/** An immutable (vertical, or horizontal) segment. */
public class Segment implements Figure {

  // The representation invariant is that coord is not null and length is
  // positive; since the class is immutable, such invariant is always preserved
  // given that it is ensured by the only constructor of this class
  private final Coord start;
  private final int length;
  private final boolean isVertical;

  public Segment(final Coord start, final int length, final boolean isVertcal) {
    if (start == null) throw new IllegalArgumentException("start must not be null");
    this.start = start;
    if (length <= 0) throw new IllegalArgumentException("length must be positive");
    this.length = length;
    this.isVertical = isVertcal;
  }

  @Override
  public void draw(final Bitmap bitmap) {
    int r = start.r, c = start.c;
    if (isVertical) for (int i = 0; i < length; i++) bitmap.turnOn(new Coord(r + i, c));
    else for (int i = 0; i < length; i++) bitmap.turnOn(new Coord(r, c + i));
  }
}
