package it.unimi.di.prog2.l13;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class DensePoly extends AbstractPoly {

  /** The array of coefficients, the {@code coeffs[i]} is the coefficient of \( x^i \). */
  private final int[] coeffs;

  /** Initializes this to be the zero polynomial, that is \( p = 0 \). */
  public DensePoly() {
    super(0);
    coeffs = new int[1];
  }

  /**
   * Initializes this to be the polynomial \(p = cx^n\).
   *
   * @param coeff the coefficient.
   * @param degree the degree.
   * @throws NegativeExponentException if {@code n} &lt; 0.
   */
  public DensePoly(int coeff, int degree) throws NegativeExponentException {
    super(degree);
    coeffs = new int[degree + 1];
    coeffs[degree] = coeff;
  }

  /**
   * Initializes a polynomial of given degree (with all coefficients equal to 0).
   *
   * @param degree the degree.
   */
  private DensePoly(int degree) {
    super(degree);
    coeffs = new int[degree + 1];
  }

  // Methods

  @Override
  public int coeff(int degree) {
    if (degree < 0 || degree > this.degree)
      return 0;
    else
      return coeffs[degree];
  }

  @Override
  public DensePoly add(Poly q) throws NullPointerException {
    int newdeg = degree > q.degree() ? degree : q.degree();
    if (degree == q.degree()) // decrease according to trailing zeros
      for (int k = degree; k > 0; k--)
        if (coeffs[k] != -q.coeff(k))
          break;
        else
          newdeg--;
    DensePoly r = new DensePoly(newdeg);
    for (int i = 0; i <= newdeg; i++)
      r.coeffs[i] = (i <= degree ? coeffs[i] : 0) + q.coeff(i);
    return r;
  }

  @Override
  public DensePoly mul(Poly q) throws NullPointerException {
    if ((q.degree() == 0 && q.coeff(0) == 0) || (degree == 0 && coeffs[0] == 0))
      return new DensePoly();
    DensePoly r = new DensePoly(degree + q.degree());
    for (int i = 0; i <= degree; i++)
      for (Poly.Term t : q)
        r.coeffs[i + t.degree] += coeffs[i] * t.coeff;
    return r;
  }

  @Override
  public DensePoly minus() {
    DensePoly r = new DensePoly(degree);
    for (int i = 0; i <= degree; i++)
      r.coeffs[i] = -coeffs[i];
    return r;
  }

  @Override
  public boolean repOk() {
    // HOMEWORK: describe and implement the representation invariant
    return false;
  }

  @Override
  public Iterator<Term> iterator() {
    return new Iterator<Poly.Term>() {
      private int i = 0;

      @Override
      public boolean hasNext() {
        if (degree == 0 && coeffs[0] == 0)
          return false;
        return i <= degree;
      }

      @Override
      public Term next() {
        if (!hasNext())
          throw new NoSuchElementException();
        while (coeffs[i] == 0)
          i++;
        final int j = i;
        i++;
        return new Poly.Term(coeffs[j], j);
      }
    };
  }

}
