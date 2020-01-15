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

import java.util.HashSet;
import java.util.Set;

/** A rendered that is able to translate a set of graphic primitives to a string. */
public class Renderer {

  // the representation is given by a set of coordinates that have to be considered
  // as the turned on (or off) pixels weather coordsAreOn is respectively true or false;
  // the invariant is that coords is not null and the sizes are positive, such invariant
  // is ensured at construction time, since coords and the sizes are final, this means
  // that the invariant is never violated
  private final int b, h;
  private boolean coordsAreOn = true;
  private final Set<Coord> coords = new HashSet<Coord>();

  public Renderer(final int b, final int h) {
    if (b <= 0) throw new IllegalArgumentException("b must be positive");
    this.b = b;
    if (h <= 0) throw new IllegalArgumentException("h must be positive");
    this.h = h;
  }

  // if coordsAreOn is true we add the given coordinate (if it is within the
  // size bounds) otherwise we remove it
  public void turnOn(final Coord p) {
    if (coordsAreOn) {
      if (0 <= p.c && p.c < b && 0 <= p.r && p.r < h) coords.add(p);
    } else coords.remove(p);
  }

  // if coordsAreOn is false we add the given coordinate (if it is within the size bounds)
  // otherwise we remove it
  public void turnOff(final Coord p) {
    if (coordsAreOn) coords.remove(p);
    else if (0 <= p.c && p.c < b && 0 <= p.r && p.r < h) coords.add(p);
  }

  public void invert() {
    coordsAreOn = !coordsAreOn;
  }

  public void clear() {
    coordsAreOn = true;
    coords.clear();
  }

  public void draw(final Figure f) {
    for (final Coord c : f.coords()) turnOn(c);
  }

  // this draws the figure translated by r, c — it is useful for stamps
  public void draw(final Figure f, final int r, final int c) {
    for (final Coord fc : f.coords()) turnOn(new Coord(fc.r + r, fc.c + c));
  }

  public String render() {
    final StringBuilder s = new StringBuilder();
    for (int i = 0; i < h; i++) {
      for (int j = 0; j < b; j++) {
        final boolean inCoords = coords.contains(new Coord(i, j));
        if (coordsAreOn) s.append(inCoords ? '*' : '.');
        else s.append(!inCoords ? '*' : '.');
      }
      s.append('\n');
    }
    return s.toString();
  }
}
