/*

Copyright 2019 Massimo Santini

This file is part of "Programmazione 2 @ UniMI" teaching material.

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

package it.unimi.di.prog2.l14.oi.sr;

import java.util.LinkedList;
import java.util.List;
import java.util.NoSuchElementException;

public class Histogram {

  List<Rectangle> rectangles = new LinkedList<>();

  public void add(Rectangle r) {
    int i;
    for (i = 0; i < rectangles.size(); i++) if (rectangles.get(i).height() > r.height()) break;
    rectangles.add(i, r);
  }

  public void changeBase(Rectangle o, int base) throws NoSuchElementException {
    final int idx = rectangles.indexOf(o);
    if (idx != -1) rectangles.get(idx).base(base);
    else throw new NoSuchElementException();
  }

  @Override
  public String toString() {
    return rectangles.toString();
  }
}
