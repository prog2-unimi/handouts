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

import java.util.ArrayList;
import java.util.List;

/** An immutable figure defined as the union of figures. */
public class FiguresComposition implements Figure {

  // the representation invariant is that figures is a not null and contains no
  // null elements.
  private final List<Figure> figures;

  public FiguresComposition(final List<Figure> figures) {
    if (figures == null) throw new IllegalArgumentException("figures can not be null");
    for (final Figure f : figures)
      if (f == null) throw new IllegalArgumentException("no figure can be null");
    this.figures = new ArrayList<Figure>(figures);
  }

  @Override
  public void draw(final Bitmap bitmap) {
    for (final Figure f : figures) f.draw(bitmap);
  }
}
