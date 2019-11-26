package it.unimi.di.prog2.l13;

import java.util.Iterator;

public abstract class AbstractPoly implements Poly {
  protected int degree;

  protected AbstractPoly(final int degree) {
    if (degree < 0)
      throw new NegativeExponentException();
    this.degree = degree;
  }

  @Override
  public int degree() {
    return degree;
  }

  @Override
  public boolean equals(Object other) {
    if (other == this)
      return true;
    if (!(other instanceof Poly))
      return false;
    Poly q = (Poly) other;
    if (degree() != q.degree())
      return false;
    for (Poly.Term t : this)
      if (q.coeff(t.degree) != t.coeff)
        return false;
    for (Poly.Term t : q)
      if (coeff(t.degree) != t.coeff)
        return false;
    return true;
  }

  @Override
  public String toString() {
    if (degree() == 0 && coeff(0) == 0)
      return "Poly: 0";
    Iterator<Poly.Term> it = iterator();
    Term t = it.next();
    String result = this.getClass().getSimpleName() + ": ";
    if (t.degree == 0)
      result += t.coeff;
    else {
      if (t.coeff < -1)
        result += t.coeff;
      else if (t.coeff == -1)
        result += "-";
      else if (t.coeff > 1)
        result += t.coeff;
      result += "x" + (t.degree > 1 ? "^" + t.degree : "");
    }
    while (it.hasNext()) {
      t = it.next();
      if (t.coeff < -1)
        result += " - " + (-t.coeff);
      else if (t.coeff == -1)
        result += " - ";
      else if (t.coeff == 1)
        result += " + ";
      else
        result += " + " + t.coeff;
      result += "x" + (t.degree > 1 ? "^" + t.degree : "");
    }
    return result;
  }

}
