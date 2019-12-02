package it.unimi.di.prog2.l08;

import it.unimi.di.prog2.l07.NegativeExponentException;
import java.util.ArrayList;
import java.util.List;

/**
 * {@code SparsePoly}s are immutable <em>sparse</em> polynomials with integer coefficients.
 *
 * <p>
 * A typical {@code SparsePoly} is \( p = c_0 + c_1 x + c_2 x^2 + \cdots + c_n x^n \).
 */
public class SparsePoly {

  public static class Term {
    public final int coeff, degree;

    public Term(int coeff, int degree) {
      this.coeff = coeff;
      this.degree = degree;
    }
  }

  // Fields

  /** The list of terms. */
  private final List<Term> trms;

  // Constructors

  /** Initializes this to be the zero polynomial, that is \( p = 0 \). */
  public SparsePoly() {
    trms = new ArrayList<>();
  }

  /**
   * Initializes this to be the polynomial \(p = cx^n\).
   *
   * @param c the coefficient.
   * @param n the degree.
   * @throws NegativeExponentException if {@code n} &lt; 0.
   */
  public SparsePoly(int c, int n) throws NegativeExponentException {
    this();
    if (n < 0)
      throw new NegativeExponentException("Can't create a monomial with negative exponent");
    trms.add(new Term(c, n));
  }

  // Methods

  /**
   * Finds the index of a term of given degree
   *
   * @param d the degree.
   * @return the index of a term of given degree, or -1 if none is present.
   */
  private int findByDegree(int d) {
    for (int i = 0; i < trms.size(); i++)
      if (trms.get(i).degree == d)
        return i;
    return -1;
  }

  /**
   * Adds a term to this polynomial.
   *
   * @param t the term.
   */
  private void add(Term t) {
    int i = findByDegree(t.degree);
    if (i != -1) {
      int c = trms.get(i).coeff + t.coeff;
      if (c != 0)
        trms.set(i, new Term(c, t.degree));
      else
        trms.remove(i);
    } else
      trms.add(t);
  }

  /**
   * Returns the degree of this polynomial.
   *
   * @return the largest exponent with a non-zero coefficient; returns 0 if this is the zero {@code
   *     Poly}.
   */
  public int degree() {
    int deg = 0;
    for (int i = 0; i < trms.size(); i++) {
      int d = trms.get(i).degree;
      if (d > deg)
        deg = d;
    }
    return deg;
  }

  /**
   * Returns the coefficient of the term of given exponent.
   *
   * @param d the exponent of the term to consider.
   * @return the coefficient of the considered term.
   */
  public int coeff(int d) {
    int i = findByDegree(d);
    if (i != -1)
      return trms.get(i).coeff;
    return 0;
  }

  /**
   * Performs polynomial addition.
   *
   * <p>
   * If \( p \) is this polynomial, returns \( p + q \).
   *
   * @param q the polynomial to add to this one.
   * @return the sum among this and the given polynomial.
   * @throws NullPointerException if {@code q} is {@code null}.
   */
  public SparsePoly add(SparsePoly q) throws NullPointerException {
    if (q == null)
      throw new NullPointerException();
    SparsePoly result = new SparsePoly();
    for (int i = 0; i < trms.size(); i++)
      result.trms.add(trms.get(i));
    for (int i = 0; i < q.trms.size(); i++)
      result.add(q.trms.get(i));
    return result;
  }

  /**
   * Performs polynomial multiplication.
   *
   * <p>
   * If \( p \) is this polynomial, returns \( p q \).
   *
   * @param q the polynomial to multiply by this one.
   * @return the product among this and the given polynomial.
   * @throws NullPointerException if {@code q} is {@code null}.
   */
  public SparsePoly mul(SparsePoly q) throws NullPointerException {
    if (q == null)
      throw new NullPointerException();
    SparsePoly result = new SparsePoly();
    for (int i = 0; i < trms.size(); i++) {
      Term ti = trms.get(i);
      for (int j = 0; j < q.trms.size(); j++) {
        Term qj = q.trms.get(j);
        result.add(new Term(ti.coeff * qj.coeff, ti.degree + qj.degree));
      }
    }
    return result;
  }

  /**
   * Performs polynomial subtraction.
   *
   * <p>
   * If \( p \) is this polynomial, returns \( p - q \).
   *
   * @param q the polynomial to subtract from this one.
   * @return the subtraction among this and the given polynomial.
   * @throws NullPointerException if {@code q} is {@code null}.
   */
  public SparsePoly sub(SparsePoly q) throws NullPointerException {
    return add(q.minus());
  }

  /**
   * Returns the negate polynomial.
   *
   * <p>
   * If \( p \) is this polynomial, returns \( -p \).
   *
   * @return this polynomial multiplied by \( -1 \).
   */
  public SparsePoly minus() {
    SparsePoly result = new SparsePoly();
    for (int i = 0; i < trms.size(); i++) {
      Term t = trms.get(i);
      result.trms.add(new Term(-t.coeff, t.degree));
    }
    return result;
  }

  @Override
  public boolean equals(Object o) {
    if (o == null)
      return false;
    if (!(o instanceof SparsePoly))
      return false;
    SparsePoly p = ((SparsePoly) o).sub(this);
    if (p.trms.size() > 0)
      return false;
    return true;
  }

  @Override
  public int hashCode() {
    return trms.hashCode();
  }

  @Override
  public String toString() {
    if (trms.size() == 0)
      return "Poly: 0";
    Term t = trms.get(0);
    String result = "Poly: " + t.coeff + "x^" + t.degree;
    for (int i = 1; i < trms.size(); i++) {
      t = trms.get(i);
      result += (t.coeff > 0 ? " + " : "") + t.coeff + "x^" + t.degree;
    }
    return result;
  }
}
