/*

Copyright 2020 Massimo Santini

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

package it.unimi.di.prog2.t08;

/** A counter to take track of <em>hits</em>. */
public class Counter {
  private int hits;

  /** Creates a counter that has not yet received any hit. */
  public Counter() {
    hits = 0;
    assert repOk();
  }

  /** Hits this counter, incrementing by one the number of hits. */
  public void hit() {
    hits++;
    assert repOk();
  }

  public void removeHit() {
    if (hits > 0) hits--;
    assert repOk();
  }

  /**
   * Return the number of hits received by this counter.
   *
   * @return the number of hits.
   */
  public int hits() {
    return hits;
  }

  boolean repOk() {
    return hits >= 0;
  }

  @Override
  public String toString() {
    return "hits: " + hits;
  }
}
