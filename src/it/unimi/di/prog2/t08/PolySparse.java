/*

Copyright 2021 Massimo Santini

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
import java.util.ArrayList;
import java.util.List;

/**
 * {@code Poly}s are immutable polynomials with integer coefficients.
 *
 * <p>A typical {@code Poly} is \( p = c_0 + c_1 x + c_2 x^2 + \cdots + c_n x^n \).
 */
public class PolySparse {

  /** A record holding a term of the polynomial. */
  public record Term(int coeff, int degree) {
    /**
     * Builds a term.
     *
     * @param coeff the coefficient.
     * @param degree the degree.
     * @throws NegativeExponentException if if {@code n} &lt; 0.
     */
    public Term { // using the compact contructor
      if (degree < 0)
        throw new NegativeExponentException("A term cannot have a negative exponent.");
    }
  }

  // Fields

  /** The array of terms. */
  private final List<Term> trms;

  /** The degree of the polynomial. */
  private final int deg;

  // Constructors

  /** Initializes this to be the zero polynomial, that is \( p = 0 \). */
  public PolySparse() {
    trms = new ArrayList<>();
    deg = 0;
  }

  /**
   * Initializes this to be the polynomial containing just the given term.
   *
   * @param t the term.
   */
  public PolySparse(Term t) {
    trms = new ArrayList<>();
    if (t.coeff == 0) deg = 0;
    else {
      deg = t.degree;
      trms.add(t);
    }
  }

  // Methods

  /**
   * Finds the index of a term of given degree.
   *
   * @param d the degree.
   * @return the index of a term of given degree, or -1 if none is present.
   */
  private int findByDegree(int d) {
    for (int i = 0; i < trms.size(); i++) if (trms.get(i).degree == d) return i;
    return -1;
  }

  /**
   * Returns the coefficient of the term of given exponent.
   *
   * @param d the exponent of the term to consider.
   * @return the coefficient of the considered term.
   */
  public int coeff(int d) {
    if (d > deg) return 0;
    int i = findByDegree(d);
    if (i != -1) return trms.get(i).coeff;
    return 0;
  }
}
