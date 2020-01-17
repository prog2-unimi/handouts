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

import java.util.List;

/** An immutable rectangle. */
public class Rectangle implements Figure {

  // A rectangle is just a {@link FiguresComposition} of its four segments;
  private final Figure rectangle;

  public Rectangle(final Coord start, final int length, final int height) {
    if (start == null) throw new IllegalArgumentException("start must not be null");
    if (length <= 0) throw new IllegalArgumentException("length must be positive");
    if (height <= 0) throw new IllegalArgumentException("height must be positive");
    if (length == 1) rectangle = new Segment(start, height, true);
    else if (height == 1) rectangle = new Segment(start, length, false);
    else
      // in this case we decompose the rectangle as follows
      //
      // 0 3 3
      // 0 . 2
      // 0 . 2
      // 1 1 2
      //
      rectangle =
          new FiguresComposition(
              List.of(
                  new Segment(start, height - 1, true),
                  new Segment(new Coord(start.r + height - 1, start.c), length - 1, false),
                  new Segment(new Coord(start.r + 1, start.c + length - 1), height - 1, true),
                  new Segment(new Coord(start.r, start.c + 1), length - 1, false)));
  }

  @Override
  public void draw(final Bitmap bitmap) {
    rectangle.draw(bitmap);
  }
}
