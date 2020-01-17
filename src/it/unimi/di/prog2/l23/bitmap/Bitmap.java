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

public class Bitmap {

  // the representation is simply a matrix of booleans, b and h must be equal to
  // the number of rows and columns, this is true at construction time and never
  // changed; then bitmap[i][j] must be true iff the (i, j) pixel is on, it
  // is easy to check that this is true for every method.
  private final int b, h;
  private final boolean[][] bitmap;

  public Bitmap(final int b, final int h) {
    if (b <= 0) throw new IllegalArgumentException("b must be positive");
    this.b = b;
    if (h <= 0) throw new IllegalArgumentException("h must be positive");
    this.h = h;
    bitmap = new boolean[h][b];
  }

  public void turnOn(final Coord p) {
    if (0 <= p.r && p.r < h && 0 <= p.c && p.c < b) bitmap[p.r][p.c] = true;
  }

  public void turnOff(final Coord p) {
    if (0 <= p.r && p.r < h && 0 <= p.c && p.c < b) bitmap[p.r][p.c] = false;
  }

  public void clear() {
    for (int i = 0; i < h; i++) for (int j = 0; j < b; j++) bitmap[i][j] = false;
  }

  public void invert() {
    for (int i = 0; i < h; i++) for (int j = 0; j < b; j++) bitmap[i][j] = !bitmap[i][j];
  }

  public String render() {
    final StringBuilder s = new StringBuilder();
    for (int i = 0; i < h; i++) {
      for (int j = 0; j < b; j++) s.append(bitmap[i][j] ? '*' : '.');
      s.append('\n');
    }
    return s.toString();
  }
}
