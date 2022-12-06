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

package it.unimi.di.prog2.t12;

import java.util.Iterator;

public abstract class AbstractPoly implements Poly {
  protected final int degree;

  protected AbstractPoly(final int degree) {
    if (degree < 0) throw new NegativeExponentException();
    this.degree = degree;
  }

  @Override
  public int degree() {
    return degree;
  }

  @Override
  public boolean equals(Object other) {
    if (other == this) return true;
    if (!(other instanceof Poly)) return false;
    Poly q = (Poly) other;
    if (degree != q.degree()) return false;
    for (Poly.Term t : this) if (q.coeff(t.degree) != t.coeff) return false;
    for (Poly.Term t : q) if (coeff(t.degree) != t.coeff) return false;
    return true;
  }

  @Override
  public int hashCode() {
    // TODO: provide a reasonable implementation!
    return 42;
  }

  @Override
  public String toString() {
    if (degree() == 0 && coeff(0) == 0) return "Poly: 0";
    Iterator<Poly.Term> it = iterator();
    Term t = it.next();
    String result = this.getClass().getSimpleName() + ": ";
    if (t.degree == 0) result += t.coeff;
    else {
      if (t.coeff < -1) result += t.coeff;
      else if (t.coeff == -1) result += "-";
      else if (t.coeff > 1) result += t.coeff;
      result += "x" + (t.degree > 1 ? "^" + t.degree : "");
    }
    while (it.hasNext()) {
      t = it.next();
      if (t.coeff < -1) result += " - " + (-t.coeff);
      else if (t.coeff == -1) result += " - ";
      else if (t.coeff == 1) result += " + ";
      else result += " + " + t.coeff;
      result += "x" + (t.degree > 1 ? "^" + t.degree : "");
    }
    return result;
  }
}
