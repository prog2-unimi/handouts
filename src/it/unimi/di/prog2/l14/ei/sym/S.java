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

package it.unimi.di.prog2.l14.ei.sym;

/*
 * Per una discussione di questo codice, si veda
 * https://prog2.di.unimi.it/guides/equalityandinheritance
 */

public class S extends T {
  private final int b;

  public S(int a, int b) {
    super(a);
    this.b = b;
  }

  public boolean equals(Object o) {
    if (o instanceof S) {
      final S s = (S) o;
      return super.equals(o) && b == s.b;
    }
    return false;
  }
}
