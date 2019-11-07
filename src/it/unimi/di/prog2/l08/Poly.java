package it.unimi.di.prog2.l08;

import it.unimi.di.prog2.l07.NegativeExponentException;

/**
 * {@code Poly}s are immutable polynomials with integer coefficients.
 *
 * <p>A typical {@code Poly} is \( p = c_0 + c_1 x + c_2 x^2 + \cdots + c_n x^n \).
 */
public class Poly {

  // Fields

  /** The array of coefficients, the {@code trms[i]} is the coefficient of \( x^i \). */
  private int[] trms;

  /** The degree of the polynomial. */
  private int deg;

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
    if (c == 0) {
      trms = new int[1];
      deg = 0;
      return;
    }
    trms = new int[n + 1];
    for (int i = 0; i < n; i++) trms[i] = 0;
    trms[n] = c;
    deg = n;
  }

  /**
   * Initializes a polynomial of given degree.
   *
   * @param n the degree.
   */
  private Poly(int n) {
    trms = new int[n + 1];
    deg = n;
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
    r.trms[deg + q.deg] = 0; // prepare to compute coefficients
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
    for (int i = 0; i < deg; i++) r.trms[i] = -trms[i];
    return r;
  }
}
