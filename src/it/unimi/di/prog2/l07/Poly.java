package it.unimi.di.prog2.l07;

/**
 * {@code Poly}s are immutable polynomials with integer coefficients.
 *
 * A typical {@code Poly} is \( p = c_0 + c_1 x + c_2 x^2 + \cdots + c_n x^n \).
 */
public class Poly {

   // Constructors

   /**
    * Initializes this to be the zero polynomial, that is \( p = 0 \).
    */
   public Poly() {
   }

   /**
    * Initializes this to be the polynomial \(p = cx^n\).
    *
    * @param c the coefficient.
    * @param n the degree.
    * @throws NegativeExponentException if {@code n} &lt; 0.
    */
   public Poly(int c, int n) throws NegativeExponentException {
   }

   /**
    * A factory method returning a monomial. (see EJ 2.1)
    *
    * @param c the coefficient.
    * @param n the degree.
    * @throws NegativeExponentException if {@code n} &lt; 0.
    * @return the monomial, if {@code n} &gt;= 0.
    */
   public static Poly monomialWithCoeffAndDegree(int c, int n) {
      return null;
   }

   // Methods

   /**
    * Returns the degree of this polynomial.
    *
    * @return the largest exponent with a non-zero coefficient; returns 0 if this is the zero
    *         {@code Poly}.
    */
   public int degree() {
      return 0;
   }

   /**
    * Returns the coefficient of the term of given exponent.
    *
    * @param d the exponent of the term to consider.
    * @return the coefficient of the considered term.
    */
   public int coeff(int d) {
      return 0;
   }

   /**
    * Performs polynomial addition.
    *
    * If \( p \) is this polynomial, returns \( p + q \).
    *
    * @param q the polynomial to add to this one.
    * @return the sum among this and the given polynomial.
    * @throws NullPointerException if {@code q} is {@code null}.
    */
   public Poly add(Poly q) throws NullPointerException {
      return null;
   }

   /**
    * Performs polynomial multiplication.
    *
    * If \( p \) is this polynomial, returns \( p q \).
    *
    * @param q the polynomial to multiply by this one.
    * @return the product among this and the given polynomial.
    * @throws NullPointerException if {@code q} is {@code null}.
    */
   public Poly mul(Poly q) throws NullPointerException {
      return null;
   }

   /**
    * Performs polynomial subtraction.
    *
    * If \( p \) is this polynomial, returns \( p - q \).
    *
    * @param q the polynomial to subtract from this one.
    * @return the subtraction among this and the given polynomial.
    * @throws NullPointerException if {@code q} is {@code null}.
    */
   public Poly sub(Poly q) throws NullPointerException {
      return null;
   }

   /**
    * Returns the negate polynomial.
    *
    * If \( p \) is this polynomial, returns \( -p \).
    *
    * @return this polynomial multiplied by \( -1 \).
    */
   public Poly minus() {
      return null;
   }

}
