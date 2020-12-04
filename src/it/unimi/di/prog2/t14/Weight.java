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

package it.unimi.di.prog2.t14;

/** A class representing a weight. */
public class Weight implements Comparable<Weight> {

  /**
   * An enumeration representing some of the <a
   * href="https://www.nist.gov/pml/weights-and-measures/metric-si-prefixes">Metric (SI)
   * prefixes</a>.
   */
  enum Unit {
    KG(1000),
    HG(100),
    DAG(10),
    G(1);

    public final int grams;

    private Unit(int grams) {
      this.grams = grams;
    }
  }

  private final int quantity;
  private Unit unit;

  /**
   * Constructs a given weight given a quantity and a unit of measure for the quantity.
   *
   * @param quantity the quantity.
   * @param unit the unit.
   */
  public Weight(int quantity, Unit unit) {
    this.quantity = quantity;
    this.unit = unit;
  }

  /**
   * Returns this weight in grams.
   *
   * @return the weight in grams.
   */
  private int grams() {
    return quantity * unit.grams;
  }

  @Override
  public String toString() {
    return quantity + " " + unit;
  }

  @Override
  public int compareTo(Weight o) {
    return Integer.compare(grams(), o.grams());
  }
}
