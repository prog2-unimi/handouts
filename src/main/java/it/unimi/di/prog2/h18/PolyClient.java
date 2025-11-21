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

public class PolyClient {

  public static void main(String[] args) {
    Poly a, b;
    a = DensePoly.ofCoefficients(new int[] {0, 2, 0, 3}); // 2x + 3x^3
    b =
        SparsePoly.ofTerms(
            java.util.List.of(
                new Poly.Term(1, 0),
                new Poly.Term(-2, 3),
                new Poly.Term(4, 2),
                new Poly.Term(-1, 3))); // 1 + 4x^2 - 3x^3
    System.out.println("a(x) = " + a);
    System.out.println("b(x) = " + b);
    System.out.println("a(x) + b(x) = " + a.add(b));
    System.out.println("b(x) + a(x) = " + b.add(a));
    System.out.println("a(x) - b(x) = " + a.sub(b));
    System.out.println("a(x) * b(x) = " + a.mul(b));
    System.out.println("a(x) - a(x) = " + a.sub(a));
  }
}
