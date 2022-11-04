/*

Copyright 2022 Massimo Santini

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

package it.unimi.di.prog2.t07.use;

import it.unimi.di.prog2.t07.impl.Poly;

/** A collection of methods for {@link Poly}s. */
public class Polys {

  // See EJ 2.4
  private Polys() {}

  /**
   * Returns the derivative of the given polynomial.
   *
   * @param p the polynomial to differentiate.
   * @return the derivative of {@code p}.
   * @throws NullPointerException if {@code p} is {@code null}.
   */
  public static Poly diff(Poly p) throws NullPointerException {
    Poly q = new Poly();
    for (int i = 1; i <= p.degree(); i++) q = q.add(new Poly(p.coeff(i) * i, i - 1));
    return q;
  }
}
