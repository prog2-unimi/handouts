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

import it.unimi.di.prog2.t07.NegativeExponentException;
import java.util.Arrays;

/**
 * {@code Poly}s are immutable polynomials with integer coefficients.
 *
 * <p>A typical {@code Poly} is \( p = c_0 + c_1 x + c_2 x^2 + \cdots + c_n x^n \).
 */
public class Poly implements Cloneable {

  // Fields

  /** The array of coefficients, the {@code trms[i]} is the coefficient of \( x^i \). */
  private final int[] trms;

  /** The degree of the polynomial. */
  private final int deg;

  // Constructors

  /** Initializes this to be the zero polynomial, that is \( p = 0 \). */
  public Poly() {
    trms = new int[1];
    deg = 0;
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
    if (c == 0) deg = 0;
    else deg = n;
    trms = new int[deg + 1];
    trms[deg] = c;
  }

  /**
   * Initializes a polynomial of given degree (with all coefficients equal to 0).
   *
   * @param n the degree.
   */
  private Poly(int n) {
    deg = n;
    trms = new int[deg + 1];
  }

  // Methods

  /**
   * Returns the degree of this polynomial.
   *
   * @return the largest exponent with a non-zero coefficient; returns 0 if this is the zero {@code
   *     Poly}.
   */
  public int degree() {
    return deg;
  }

  /**
   * Returns the coefficient of the term of given exponent.
   *
   * @param d the exponent of the term to consider.
   * @return the coefficient of the considered term.
   */
  public int coeff(int d) {
    if (d < 0 || d > deg) return 0;
    else return trms[d];
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
    Poly la, sm;
    if (deg > q.deg) {
      la = this;
      sm = q;
    } else {
      la = q;
      sm = this;
    }
    int newdeg = la.deg; // new degree is the larger degree
    if (deg == q.deg) // unless there are trailing zeros
    for (int k = deg; k > 0; k--)
        if (trms[k] + q.trms[k] != 0) break;
        else newdeg--;
    Poly r = new Poly(newdeg); // get a new Poly
    int i;
    for (i = 0; i <= sm.deg && i <= newdeg; i++) r.trms[i] = sm.trms[i] + la.trms[i];
    for (int j = i; j <= newdeg; j++) r.trms[j] = la.trms[j];
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
    if ((q.deg == 0 && q.trms[0] == 0) || (deg == 0 && trms[0] == 0)) return new Poly();
    Poly r = new Poly(deg + q.deg);
    for (int i = 0; i <= deg; i++)
      for (int j = 0; j <= q.deg; j++) r.trms[i + j] = r.trms[i + j] + trms[i] * q.trms[j];
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
    Poly r = new Poly(deg);
    for (int i = 0; i <= deg; i++) r.trms[i] = -trms[i];
    return r;
  }

  @Override
  public boolean equals(Object o) {
    if (o == this) return true;
    if (!(o instanceof Poly)) return false;
    Poly q = (Poly) o;
    if (deg != q.deg) return false;
    for (int i = 0; i <= deg; i++) if (trms[i] != q.trms[i]) return false;
    return true;
  }

  @Override
  public int hashCode() {
    int result = Integer.hashCode(deg);
    result = 31 * result + Arrays.hashCode(trms);
    return result;
  }
}
