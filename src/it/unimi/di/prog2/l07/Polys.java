package it.unimi.di.prog2.l07;

import it.unimi.di.prog2.l08.Poly;

/** A collection of methods for {@link Poly}s. */
public class Polys {

  // See EJ 2.4
  private Polys() {}

  /**
   * Returns the derivative of the given polynomial.
   *
   * @param p the polynomial to differentiate.
   * @return the derivative of {@code p}.
   * @throws NullPointerException if {@code p} is {@code null}.
   */
  public static Poly diff(Poly p) throws NullPointerException {
    Poly q = new Poly();
    for (int i = 1; i <= p.degree(); i++) q = q.add(new Poly(p.coeff(i) * i, i - 1));
    return q;
  }
}
