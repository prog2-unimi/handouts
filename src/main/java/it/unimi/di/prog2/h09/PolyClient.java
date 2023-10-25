/*

Copyright 2023 Massimo Santini

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

package it.unimi.di.prog2.h09;

import java.util.Scanner;

public class PolyClient {
  public static void main(String[] args) {
    Poly result = null;
    Poly xp1 = new Poly(1, 1).add(new Poly(1, 0));
    try (Scanner s = new Scanner(System.in)) {
      while (s.hasNextInt()) {
        Poly term = new Poly(s.nextInt(), s.nextInt());
        if (result == null) result = term;
        else result = result.mul(xp1).add(term);
      }
      for (int d = 0; d <= result.degree(); d++) System.out.println(result.coeff(d) + ", " + d);
    }
  }
}
