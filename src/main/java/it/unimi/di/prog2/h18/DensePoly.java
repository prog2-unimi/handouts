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

import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;

/** A dense implementation of non-zero {@link Poly}s. */
public class DensePoly extends AbstractPoly {

  /** The array of coefficients, the {@code coeff[i]} is the coefficient of \( x^i \). */
  private final int[] coefficient;

  /*
   * RI:
   *
   *  - coefficient != null
   *  - coefficient.length > 0 and coefficient[coefficient.length - 1] != 0
   *
   * AF:
   *
   *  - a polynomial of degree n is represented by an array of length n+1,
   *    where coefficient[i] is the coefficient of x^i.
   */

  /**
   * Initializes the polynomial given an array of coefficients.
   *
   * @param coefficient must satisfy the RI (this is a partial method).
   */
  private DensePoly(int[] coefficient) {
    super(coefficient.length - 1);
    this.coefficient = coefficient.clone();
  }

  /**
   * Copies the dense polynomial.
   *
   * @param poly the polynomial to copy.
   * @return the copy of the polynomial.
   * @throws NullPointerException if {@code poly} is {@code null}.
   */
  public static DensePoly copyOf(DensePoly poly) throws NullPointerException {
    return new DensePoly(poly.coefficient);
  }

  /**
   * Copies the polynomial.
   *
   * @param poly the polynomial to copy.
   * @return the copy of the polynomial.
   * @throws NullPointerException if {@code poly} is {@code null}.
   */
  public static DensePoly copyOf(Poly poly) throws NullPointerException {
    if (poly instanceof DensePoly dense) return copyOf(dense);
    int[] coefficient = new int[poly.degree() + 1];
    for (Term t : poly) coefficient[t.degree()] = t.coefficient();
    return new DensePoly(coefficient);
  }

  /**
   * Creates a polynomial given an array of coefficients.
   *
   * <p>The coefficient array can have trailing zeros, in case all coefficients are zero, this will
   * return the zero polynomial.
   *
   * @param coefficient the array of coefficients.
   * @return the polynomial.
   * @throws NullPointerException if {@code coefficient} is {@code null}.
   */
  public static Poly ofCoefficients(int[] coefficient) {
    Objects.requireNonNull(coefficient, "The coefficient array must not be null.");
    int degree = coefficient.length - 1;
    while (degree >= 0 && coefficient[degree] == 0) degree--;
    return degree == -1 ? ZERO : new DensePoly(Arrays.copyOf(coefficient, degree + 1));
  }

  /**
   * Creates the polynomial \(p = cx^n\).
   *
   * <p>The coefficient can be zero, resulting in the zero polynomial.
   *
   * @param c the coefficient.
   * @param n the degree.
   * @return the polynomial \( p = cx^n \).
   * @throws IllegalArgumentException if {@code n} &lt; 0.
   */
  public static Poly ofCoefficientDegree(int c, int n) throws IllegalArgumentException {
    if (n < 0) throw new IllegalArgumentException("Can't create a monomial with negative exponent");
    if (c == 0) return ZERO;
    else {
      int[] coefficient = new int[n + 1];
      coefficient[n] = c;
      return new DensePoly(coefficient);
    }
  }

  @Override
  public int coefficient(int d) {
    if (d < 0 || d >= coefficient.length) return 0;
    else return coefficient[d];
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
    if (Objects.requireNonNull(q).isZero()) return this;
    final Poly larger, smaller;
    if (degree() > q.degree()) {
      larger = this;
      smaller = q;
    } else {
      larger = q;
      smaller = this;
    }
    int[] result = new int[larger.degree() + 1];
    int i, j;
    for (i = 0; i <= smaller.degree(); i++)
      result[i] = smaller.coefficient(i) + larger.coefficient(i);
    for (j = i; j <= larger.degree(); j++) result[j] = larger.coefficient(j);
    return DensePoly.ofCoefficients(result);
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
    if (Objects.requireNonNull(q).isZero()) return ZERO;
    int[] result = new int[degree() + q.degree() + 1];
    for (int i = 0; i <= degree(); i++)
      for (int j = 0; j <= q.degree(); j++)
        result[i + j] = result[i + j] + coefficient[i] * q.coefficient(j);
    return DensePoly.ofCoefficients(result);
  }

  /**
   * Returns the negate polynomial.
   *
   * <p>If \( p \) is this polynomial, returns \( -p \).
   *
   * @return this polynomial multiplied by \( -1 \).
   */
  public Poly minus() {
    int[] result = new int[coefficient.length];
    for (int i = 0; i <= degree(); i++) result[i] = -coefficient[i];
    return DensePoly.ofCoefficients(result);
  }

  @Override
  public Iterator<Term> iterator() {
    return new Iterator<Term>() {

      /** A lower bound for the degree of the next term to return. */
      private int idx = 0;

      /*-
       * RI: - 0 <= i <= degree() + 1
       * AF: - if i == degree() + 1, there are no more terms to return;
       *     - else the next term is coefficient[j] * x^j where j is the smallest integer
       *       such that j >= i e coefficient[j] != 0.
       */

      @Override
      public boolean hasNext() {
        if (isZero()) return false;
        return idx <= degree();
      }

      @Override
      public Term next() {
        if (!hasNext()) throw new NoSuchElementException();
        while (coefficient[idx] == 0) idx++;
        final int j = idx++;
        return new Term(coefficient[j], j);
      }
    };
  }
}
