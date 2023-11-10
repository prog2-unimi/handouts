/*

Copyright 2021 Luca Prigioniero, Massimo Santini
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

package it.unimi.di.prog2.h11;

import it.unimi.di.prog2.h08.impl.NegativeExponentException;
import java.util.Arrays;
import java.util.Objects;

/**
 * {@code Poly}s are immutable polynomials with integer coefficients.
 *
 * <p>A typical {@code Poly} is \( p = c_0 + c_1 x + c_2 x^2 + \cdots + c_n x^n \).
 */
public class Poly { // we don't extend Cloneable, see EJ 3.13

  /** The array of coefficients, the {@code coeff[i]} is the coefficient of \( x^i \). */
  private final int[] coeff;

  /*
   * AF: associa a coeff il polinomio che ha per coefficiente di grado
   *    i il valore contenuto nella posizione i-esima di coeff; detto
   *    altrimenti associa a coeff il polinomio
   *
   *      coeff[0] + coeff[1]x + coeff[2]x^2 + ... + coeff[coeff.length - 1]x^(coeff.length -1)
   *
   *    in paricolare se coeff.length == 1 e coeff[0] == 0, il polinomio
   *    è il polinomio 0.
   *
   * RI: coeff != null
   *     se coeff.length > 1, allora coeff[coeff.length - 1] != 0
   *
   */

  /** Initializes this to be the zero polynomial, that is \( p = 0 \). */
  public Poly() {
    coeff = new int[1];
  }

  /**
   * Initializes this to be the polynomial \(p = cx^n\).
   *
   * @param c the coefficient.
   * @param n the degree.
   * @throws NegativeExponentException if {@code n} &lt; 0.
   */
  public Poly(int c, int n) throws NegativeExponentException {
    if (n < 0)
      throw new NegativeExponentException("Can't create a monomial with negative exponent");
    coeff = new int[n + 1];
    coeff[n] = c;
  }

  /**
   * Initializes a polynomial of given degree (with all coefficients equal to 0).
   *
   * @param n the degree.
   */
  private Poly(int n) {
    coeff = new int[n + 1];
  }

  /**
   * Returns the degree of this polynomial.
   *
   * @return the largest exponent with a non-zero coefficient; returns 0 if this is the zero {@code
   *     Poly}.
   */
  public int degree() {
    return coeff.length - 1;
  }

  /**
   * Returns the coefficient of the term of given exponent.
   *
   * @param d the exponent of the term to consider.
   * @return the coefficient of the considered term.
   */
  public int coeff(int d) {
    if (d < 0 || d >= coeff.length) return 0;
    else return coeff[d];
  }

  /**
   * Performs polynomial addition.
   *
   * <p>If \( p \) is this polynomial, returns \( p + q \).
   *
   * @param q the polynomial to add to this one.
   * @return the sum among this and the given polynomial.
   * @throws NullPointerException if {@code q} is {@code null}.
   */
  public Poly add(Poly q) throws NullPointerException {
    Objects.requireNonNull(q);
    Poly la, sm;
    if (degree() > q.degree()) {
      la = this;
      sm = q;
    } else {
      la = q;
      sm = this;
    }
    int newdeg = la.degree(); // new degree is the larger degree
    if (degree() == q.degree()) // unless there are trailing zeros
    for (int k = degree(); k > 0; k--)
        if (coeff[k] + q.coeff[k] != 0) break;
        else newdeg--;
    Poly r = new Poly(newdeg); // get a new Poly
    int i;
    for (i = 0; i <= sm.degree() && i <= newdeg; i++) r.coeff[i] = sm.coeff[i] + la.coeff[i];
    for (int j = i; j <= newdeg; j++) r.coeff[j] = la.coeff[j];
    return r;
  }

  /**
   * Performs polynomial multiplication.
   *
   * <p>If \( p \) is this polynomial, returns \( p q \).
   *
   * @param q the polynomial to multiply by this one.
   * @return the product among this and the given polynomial.
   * @throws NullPointerException if {@code q} is {@code null}.
   */
  public Poly mul(Poly q) throws NullPointerException {
    Objects.requireNonNull(q);
    if ((q.degree() == 0 && q.coeff[0] == 0) || (degree() == 0 && coeff[0] == 0)) return new Poly();
    Poly r = new Poly(degree() + q.degree());
    for (int i = 0; i <= degree(); i++)
      for (int j = 0; j <= q.degree(); j++) r.coeff[i + j] = r.coeff[i + j] + coeff[i] * q.coeff[j];
    return r;
  }

  /**
   * Performs polynomial subtraction.
   *
   * <p>If \( p \) is this polynomial, returns \( p - q \).
   *
   * @param q the polynomial to subtract from this one.
   * @return the subtraction among this and the given polynomial.
   * @throws NullPointerException if {@code q} is {@code null}.
   */
  public Poly sub(Poly q) throws NullPointerException {
    Objects.requireNonNull(q);
    return add(q.minus());
  }

  /**
   * Returns the negate polynomial.
   *
   * <p>If \( p \) is this polynomial, returns \( -p \).
   *
   * @return this polynomial multiplied by \( -1 \).
   */
  public Poly minus() {
    Poly r = new Poly(degree());
    for (int i = 0; i <= degree(); i++) r.coeff[i] = -coeff[i];
    return r;
  }

  @Override
  public boolean equals(Object o) {
    if (o == this) return true;
    if (!(o instanceof Poly)) return false;
    Poly q = (Poly) o;
    if (degree() != q.degree()) return false;
    return Arrays.equals(coeff, q.coeff);
  }

  @Override
  public int hashCode() {
    return Objects.hash(degree(), coeff);
  }

  @Override
  public String toString() {
    if (degree() > 0) {
      StringBuilder sb = new StringBuilder("Poly: ");
      int c = coeff[degree()];
      if (c < -1) sb.append(-c);
      else if (c == -1) sb.append("-");
      else if (c > 1) sb.append(c);
      sb.append("x" + (degree() > 1 ? "^" + degree() : ""));
      for (int d = degree() - 1; d > 0; d--) {
        c = coeff[d];
        if (c == 0) continue;
        if (c < -1) sb.append(" - " + (-c));
        else if (c == -1) sb.append(" - ");
        else if (c == 1) sb.append(" + ");
        else sb.append(" + " + c);
        sb.append("x" + (d > 1 ? "^" + d : ""));
      }
      c = coeff[0];
      if (c > 0) sb.append(" + " + c);
      else if (c < 0) sb.append(" - " + (-c));
      return sb.toString();
    } else return "Poly: " + coeff[0];
  }

  public static void main(String[] args) {
    Poly p = new Poly(1, 0).add(new Poly(1, 2));
    Poly q = new Poly(1, 2).add(new Poly(1, 0));
    System.out.println(p.equals(q));
  }
}
