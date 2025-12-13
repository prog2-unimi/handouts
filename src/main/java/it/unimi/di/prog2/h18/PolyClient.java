/*

Copyright 2025 Massimo Santini

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

package it.unimi.di.prog2.h18;

import static it.unimi.di.prog2.h18.Poly.term;

/** A simple client for {@link Poly}s. */
public class PolyClient {

  /** . */
  private PolyClient() {}

  /**
   * A simple client for {@link Poly}s.
   *
   * @param args not used.
   */
  public static void main(String[] args) {
    Poly a, b;
    a = DensePoly.ofCoefficients(new int[] {0, 2, 0, 3});
    b = SparsePoly.ofTerms(term(1, 0), term(-2, 3), term(4, 2), term(-1, 3));
    System.out.println("a(x) = " + a);
    System.out.println("b(x) = " + b);
    System.out.println("a(x) + b(x) = " + a.add(b));
    System.out.println("b(x) + a(x) = " + b.add(a));
    System.out.println("a(x) - b(x) = " + a.sub(b));
    System.out.println("a(x) * b(x) = " + a.mul(b));
    System.out.println("a(x) - a(x) = " + a.sub(a));
  }
}
