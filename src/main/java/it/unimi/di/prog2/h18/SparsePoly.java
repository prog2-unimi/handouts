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

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

/** A sparse implementation of non-zero {@link Poly}s. */
public class SparsePoly extends AbstractPoly {

  /** The list of terms. */
  private final List<Term> terms;

  // RI: terms != null, does not contain null, terms
  //     are in strictly increasing degree order.

  /**
   * Initializes this to be the polynomial with given terms and degree.
   *
   * @param terms must satisfy the RI (this is a partial method).
   */
  private SparsePoly(final List<Term> terms) {
    super(terms.get(terms.size() - 1).degree());
    this.terms = List.copyOf(terms);
  }

  /**
   * Copies the sparse polynomial.
   *
   * @param poly the polynomial to copy.
   * @return the copy of the polynomial.
   * @throws NullPointerException if {@code poly} is {@code null}.
   */
  public static SparsePoly copyOf(SparsePoly poly) throws NullPointerException {
    return new SparsePoly(poly.terms);
  }

  /**
   * Copies the polynomial.
   *
   * @param poly the polynomial to copy.
   * @return the copy of the polynomial.
   * @throws NullPointerException if {@code poly} is {@code null}.
   */
  public static SparsePoly copyOf(Poly poly) throws NullPointerException {
    List<Term> terms = new ArrayList<>();
    for (Term t : poly) terms.add(t);
    return new SparsePoly(terms);
  }

  /**
   * Creates a polynomial given a list of terms.
   *
   * <p>The list of terms can be empty, and can contain terms in any order (even with the same
   * degree). Terms with the same degree are added and, if the resulting list is empty, the zero
   * polynomial is returned.
   *
   * @param terms the list of terms.
   * @return the polynomial.
   * @throws NullPointerException if {@code terms} is or contains {@code null}.
   */
  public static Poly ofTerms(final List<Term> terms) {
    Objects.requireNonNull(terms, "The terms list must not be null.");
    List<Term> reduced = new ArrayList<>(terms.size());
    for (Term t : terms) addTerm(reduced, Objects.requireNonNull(t));
    return reduced.isEmpty() ? ZERO : new SparsePoly(reduced);
  }

  /**
   * Creates a polynomial given its terms.
   *
   * <p>The array of terms can be empty, and can contain terms in any order (even with the same
   * degree). Terms with the same degree are added and, if the resulting list is empty, the zero
   * polynomial is returned.
   *
   * @param terms the array of terms.
   * @return the polynomial.
   * @throws NullPointerException if {@code terms} is or contains {@code null}.
   */
  public static Poly ofTerms(final Term... terms) {
    Objects.requireNonNull(terms, "The terms list must not be null.");
    List<Term> reduced = new ArrayList<>(terms.length);
    for (Term t : terms) addTerm(reduced, Objects.requireNonNull(t));
    return reduced.isEmpty() ? ZERO : new SparsePoly(reduced);
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
    return c == 0 ? ZERO : new SparsePoly(List.of(new Term(c, n)));
  }

  /**
   * Finds the index of a term of given degree.
   *
   * @param terms a not {@code null} list of terms in increasing degree order.
   * @param degree the degree.
   * @return the index of a term of given degree, or -1 if none is present.
   */
  private static int findByDegree(List<Term> terms, int degree) {
    for (int i = 0; i < terms.size(); i++) {
      int d = terms.get(i).degree();
      if (d > degree) return -1;
      else if (d == degree) return i;
    }
    return -1;
  }

  /**
   * Adds a term to the list.
   *
   * <p>The list will remain in increasing degree order, in case a term with the same degree was
   * present, the two will be summed (and removed if the coefficient will become 0).
   *
   * @param terms a not {@code null} list of terms in increasing degree order.
   * @param term the term.
   */
  private static void addTerm(List<Term> terms, Term term) {
    int i = findByDegree(terms, term.degree());
    if (i != -1) {
      int c = terms.get(i).coefficient() + term.coefficient();
      if (c != 0) terms.set(i, new Term(c, term.degree()));
      else terms.remove(i);
    } else {
      for (i = 0; i < terms.size(); i++) if (terms.get(i).degree() > term.degree()) break;
      terms.add(i, term);
    }
  }

  @Override
  public Poly add(Poly q) throws NullPointerException {
    if (Objects.requireNonNull(q).isZero()) return this;
    List<Term> terms = new ArrayList<>(this.terms);
    for (Term t : q) terms.add(t);
    return SparsePoly.ofTerms(terms);
  }

  @Override
  public Poly mul(Poly q) throws NullPointerException {
    if (Objects.requireNonNull(q).isZero()) return ZERO;
    List<Term> terms = new LinkedList<>();
    for (Term tq : q)
      for (Term tt : this)
        terms.add(new Term(tq.coefficient() * tt.coefficient(), tq.degree() + tt.degree()));
    return SparsePoly.ofTerms(terms);
  }

  @Override
  public SparsePoly minus() {
    List<Term> terms = new LinkedList<>();
    for (Term t : this) terms.add(new Term(-t.coefficient(), t.degree()));
    return new SparsePoly(terms);
  }

  @Override
  public Iterator<Term> iterator() {
    return Collections.unmodifiableList(terms).iterator();
  }
}
