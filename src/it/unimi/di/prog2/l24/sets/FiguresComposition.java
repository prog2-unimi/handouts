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

import java.util.AbstractSet;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Set;

/** An immutable figure defined as the union of figures with disjoint sets of coordinates. */
public class FiguresComposition implements Figure {

  // the representation invariant is that figures is a not null list of figures
  // such that no pair of figures have a coordinate in common; the class is
  // immutable and the only constructor ensures that such invariant is true
  private final List<Figure> figures;

  public FiguresComposition(final List<Figure> figures) {
    if (figures == null) throw new IllegalArgumentException("figures must not be null");
    final Set<Coord> seen = new HashSet<Coord>();
    for (final Figure f : figures) {
      for (final Coord c : f.coords())
        if (seen.contains(c))
          throw new IllegalArgumentException("figures must not have coordinates in common");
      seen.addAll(f.coords());
    }
    this.figures = new ArrayList<Figure>(figures);
  }

  // The implementation of this method resorts to an {@link AbstractSet} to
  // obtain an implicit representation of its points.
  @Override
  public Set<Coord> coords() {
    return new AbstractSet<Coord>() {

      @Override
      public Iterator<Coord> iterator() {
        return new Iterator<Coord>() {

          // the representation invariant is that figureIterator is an iterator
          // on the list of figures in this composition and coordIterator is an
          // iterator on the coordinates of the last figure returned by the
          // (last call of next of) figureIterator; such invariant ensures that
          // returning the next value of coordIterator will enumerate (without
          // repetition given that such figures have no coordinate in common)
          // the coordinates all figures
          private Iterator<Figure> figureIterator = figures.iterator();
          private Iterator<Coord> coordIterator = figureIterator.next().coords().iterator();

          @Override
          public boolean hasNext() {
            // if the current figure has another coordinates, than the
            // collection will have one
            if (coordIterator.hasNext()) return true;
            // otherwise if all the figures have been enumerated, we are done
            if (!figureIterator.hasNext()) return false;
            // otherwise we advance to the next figure and initialize the
            // iterator of its coordinates
            final Figure f = figureIterator.next();
            coordIterator = f.coords().iterator();
            return true;
          }

          @Override
          public Coord next() {
            if (!hasNext()) throw new NoSuchElementException();
            return coordIterator.next();
          }
        };
      }

      @Override
      public int size() {
        int size = 0;
        // given that figures have no common coordinates, the number of overall
        // coordinates is just the sum of the number of each figure coordinates.
        for (final Figure f : figures) size += f.coords().size();
        return size;
      }
    };
  }
}
