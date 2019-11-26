package it.unimi.di.prog2.l13;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class SparsePoly extends AbstractPoly {

  /** The list of terms, it is kept in increasing degree order. */
  private final List<Term> terms;

  /** Initializes this to be the zero polynomial, that is \( p = 0 \). */
  public SparsePoly() {
    super(0);
    terms = new LinkedList<>();
  }

  /**
   * Initializes this to be the polynomial \(p = cx^n\).
   *
   * @param coeff the coefficient.
   * @param degree the degree.
   * @throws NegativeExponentException if {@code n} &lt; 0.
   */
  public SparsePoly(int coeff, int degree) throws NegativeExponentException {
    this();
    terms.add(new Term(coeff, degree));
    this.degree = degree;
  }

  // Methods

  /**
   * Finds the index of a term of given degree.
   *
   * @param degree the degree.
   * @return the index of a term of given degree, or -1 if none is present.
   */
  private int findByDegree(int degree) {
    for (int i = 0; i < terms.size(); i++) {
      int d = terms.get(i).degree;
      if (d > degree)
        return -1;
      else if (d == degree)
        return i;
    }
    return -1;
  }

  /**
   * Adds a term to this polynomial.
   *
   * @param t the term.
   */
  private void add(Poly.Term t) {
    int i = findByDegree(t.degree);
    if (i != -1) {
      int c = terms.get(i).coeff + t.coeff;
      if (c != 0)
        terms.set(i, new Poly.Term(c, t.degree));
      else {
        terms.remove(i);
        int newDegree = 0;
        for (Poly.Term u : this)
          if (u.degree > newDegree)
            newDegree = u.degree;
        degree = newDegree;
      }
    } else {
      for (i = 0; i < terms.size(); i++)
        if (terms.get(i).degree > t.degree)
          break;
      terms.add(i, t);
      if (t.degree > degree)
        degree = t.degree;
    }
  }

  @Override
  public int coeff(int degree) {
    int i = findByDegree(degree);
    if (i != -1)
      return terms.get(i).coeff;
    return 0;
  }

  @Override
  public SparsePoly add(Poly q) throws NullPointerException {
    if (q == null)
      throw new NullPointerException();
    SparsePoly result = new SparsePoly();
    for (int i = 0; i < terms.size(); i++)
      result.terms.add(terms.get(i));
    for (Poly.Term t : q)
      result.add(t);
    return result;
  }

  @Override
  public SparsePoly mul(Poly q) throws NullPointerException {
    if (q == null)
      throw new NullPointerException();
    SparsePoly result = new SparsePoly();
    for (int i = 0; i < terms.size(); i++) {
      Poly.Term ti = terms.get(i);
      for (Poly.Term qj : q)
        result.add(new Poly.Term(ti.coeff * qj.coeff, ti.degree + qj.degree));
    }
    return result;
  }

  @Override
  public SparsePoly minus() {
    SparsePoly result = new SparsePoly();
    for (Poly.Term t : this)
      result.terms.add(new Poly.Term(-t.coeff, t.degree));
    return result;
  }

  @Override
  public Iterator<Term> iterator() {
    return terms.iterator();
  }

  @Override
  public boolean repOk() {
    // HOMEWORK: describe and implement the representation invariant
    return false;
  }
}
