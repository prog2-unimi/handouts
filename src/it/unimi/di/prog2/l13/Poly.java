package it.unimi.di.prog2.l13;

public interface Poly extends Iterable<Poly.Term> {

  @SuppressWarnings("serial")
  public static class NegativeExponentException extends RuntimeException {
    public NegativeExponentException() {
      super();
    }

    public NegativeExponentException(String message) {
      super(message);
    }
  }

  public static class Term {
    final int coeff, degree;

    public Term(final int coeff, final int degree) {
      if (degree < 0)
        throw new NegativeExponentException();
      this.degree = degree;
      this.coeff = coeff;
    }
  }

  /**
   * Returns the degree of this polynomial.
   *
   * @return the largest exponent with a non-zero coefficient; returns 0 if this is the zero {@code
   *     Poly}.
   */
  public int degree();

  /**
   * Returns the coefficient of the term of given exponent.
   *
   * @param degree the exponent of the term to consider.
   * @return the coefficient of the considered term.
   */
  default public int coeff(int degree) {
    for (Poly.Term t : this)
      if (t.degree == degree)
        return t.coeff;
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
  public Poly add(Poly q);

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
  public Poly mul(Poly q);

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
  default public Poly sub(Poly q) {
    return this.add(q.minus());
  }

  /**
   * Returns the negated polynomial.
   *
   * <p>
   * If \( p \) is this polynomial, returns \( -p \).
   *
   * @return this polynomial multiplied by \( -1 \).
   */
  public Poly minus();

  public boolean repOk();

}
