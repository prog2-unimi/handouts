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

package it.unimi.di.prog2.h19;

import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/** A sparse implementation of {@link Poly}. */
public class SparsePoly extends AbstractPoly {

  /** The list of terms. */
  private final List<Term> terms;

  // RI: terms is not null, does not contain null, terms
  //     are in strictly increasing degree order.

  /** Initializes this to be the zero polynomial, that is \( p = 0 \). */
  public SparsePoly() {
    super(0);
    terms = new LinkedList<>();
  }

  /**
   * Initializes this to be the polynomial with given terms and degree.
   *
   * @param terms must satisfy the RI (this is a partial method).
   */
  private SparsePoly(final List<Poly.Term> terms) {
    super(terms.get(terms.size() - 1).degree());
    this.terms = new LinkedList<>(terms);
  }

  /**
   * Initializes this to be the polynomial \(p = cx^n\).
   *
   * @param coeff the coefficient.
   * @param degree the degree.
   * @throws IllegalArgumentException if {@code n} &lt; 0.
   */
  public SparsePoly(int coeff, int degree) throws IllegalArgumentException {
    this(List.of(new Term(coeff, degree)));
  }

  /**
   * Finds the index of a term of given degree.
   *
   * @param terms a not {@code null} list of terms in increasing degree order.
   * @param degree the degree.
   * @return the index of a term of given degree, or -1 if none is present.
   */
  private static int findByDegree(List<Poly.Term> terms, int degree) {
    for (int i = 0; i < terms.size(); i++) {
      int d = terms.get(i).degree();
      if (d > degree) return -1;
      else if (d == degree) return i;
    }
    return -1;
  }

  @Override
  public int coeff(int degree) {
    int i = findByDegree(terms, degree);
    if (i != -1) return terms.get(i).coeff();
    return 0;
  }

  /**
   * Adds a term to the list.
   *
   * <p>The list will remain in increasing degree order, in case a term with the same degree was
   * present, the two will be added (and removed if the coefficient will become 0).
   *
   * @param terms a not {@code null} list of terms in increasing degree order.
   * @param term the term.
   */
  private static void addTerm(List<Poly.Term> terms, Poly.Term term) {
    if (term.coeff() == 0) return;
    int i = findByDegree(terms, term.degree());
    if (i != -1) {
      int c = terms.get(i).coeff() + term.coeff();
      if (c != 0) terms.set(i, new Poly.Term(c, term.degree()));
      else terms.remove(i);
    } else {
      for (i = 0; i < terms.size(); i++) if (terms.get(i).degree() > term.degree()) break;
      terms.add(i, term);
    }
  }

  @Override
  public SparsePoly add(Poly q) throws NullPointerException {
    if (q == null) throw new NullPointerException();
    List<Poly.Term> terms = new LinkedList<>(this.terms);
    for (Poly.Term t : q) addTerm(terms, t);
    return new SparsePoly(terms);
  }

  @Override
  public SparsePoly mul(Poly q) throws NullPointerException {
    if (q == null) throw new NullPointerException();
    List<Poly.Term> terms = new LinkedList<>();
    for (Poly.Term tq : q)
      for (Poly.Term tt : this)
        addTerm(terms, new Poly.Term(tq.coeff() * tt.coeff(), tq.degree() + tt.degree()));
    return new SparsePoly(terms);
  }

  @Override
  public SparsePoly minus() {
    List<Poly.Term> terms = new LinkedList<>();
    for (Poly.Term t : this) terms.add(new Poly.Term(-t.coeff(), t.degree()));
    return new SparsePoly(terms);
  }

  @Override
  public Iterator<Term> iterator() {
    return Collections.unmodifiableList(terms).iterator();
  }
}
