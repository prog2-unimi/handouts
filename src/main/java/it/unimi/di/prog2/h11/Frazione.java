/*

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

package it.unimi.di.prog2.h11;

import java.util.Objects;

/** Una frazione. */
public class Frazione {

  /** Il numeratore della frazione. */
  private final int num;

  /** Il denominatore della frazione. */
  private final int den;

  /** La fazione 1. */
  public static final Frazione UNO = new Frazione(1, 1);

  /** La frazione 0 */
  public static final Frazione ZERO = new Frazione(0, 1);

  /*
   * AF: associa a num, den la frazione num / den.
   * RI: den > 0, num e den sono coprimi, se num == 0, allora den == 1.
   */

  /**
   * Costruisce una frazione dati numeratore e denominatore (parziale).
   *
   * @param num il numeratore.
   * @param den il denominatore (deve essere diverso da 0).
   */
  private Frazione(int num, int den) {
    // Il metodo di fabbricazione numDen consente di restituire i singoletti
    // UNO e ZERO per i casi particolari di tali frazioni, non sarebbe stato
    // possibile garantirlo usando un costruttore pubblico.
    this.num = num;
    this.den = den;
  }

  /**
   * Fabbrica una frazione dati numeratore e denominatore.
   *
   * <p>Nel caso in cui {@code num == 0} verrà restituito il singoletto {@link #ZERO}, similmente
   * nel caso in cui {@code num == den} verrà restituito il singoletto {@link #UNO}.
   *
   * @param num il numeratore.
   * @param den il denominatore.
   * @return la frazione num/den.
   * @throws IllegalArgumentException se {@code den == 0}.
   */
  public static Frazione numDen(int num, int den) throws IllegalArgumentException {
    return norm(num, den);
  }

  /**
   * Fabbrica una frazione dati numeratore e denominatore.
   *
   * <p>Nel caso in cui {@code num == 0} verrà restituito il singoletto {@link #ZERO}, similmente
   * nel caso in cui {@code num == den} verrà restituito il singoletto {@link #UNO}.
   *
   * @param num il numeratore.
   * @param den il denominatore.
   * @return la frazione num/den.
   * @throws IllegalArgumentException se {@code den == 0}.
   */
  private static Frazione norm(long num, long den) throws IllegalArgumentException {
    if (den == 0) throw new IllegalArgumentException("Il denominatore non può essere 0");
    if (num == 0) return ZERO;
    if (num == den) return UNO;
    long gcd = gcd(num, den);
    num /= gcd;
    den /= gcd;
    if (den < 0) return new Frazione((int) -num, (int) -den);
    return new Frazione((int) num, (int) den);
  }

  /**
   * Restituisce il numeratore.
   *
   * @return il numeratore (se la frazione è negativa, sarà negativo).
   */
  public int num() {
    return num;
  }

  /**
   * Restituisce il denominatore.
   *
   * @return il denominatore (è sempre positivo).
   */
  public int den() {
    return den;
  }

  /**
   * Restituisce la frazione ottenuta sommando questa frazione con una frazione data.
   *
   * @param f la frazione da sommare.
   * @return la frazione {@code this + f}.
   * @throws NullPointerException se {@code f} è {@code null}.
   */
  public Frazione somma(Frazione f) throws NullPointerException {
    Objects.requireNonNull(f);
    return norm((long) num * f.den + f.num * (long) den, (long) den * f.den);
  }

  /**
   * Restituisce la frazione ottenuta moltiplicando questa frazione per una frazione data.
   *
   * @param f la frazione da moltiplicare.
   * @return la frazione {@code this * f}.
   * @throws NullPointerException se {@code f} è {@code null}.
   */
  public Frazione prodotto(Frazione f) {
    Objects.requireNonNull(f);

    return norm((long) num * f.num, (long) den * f.den);
  }

  /**
   * Restituisce la frazione ottenuta moltiplicando questa frazione per una intero dato.
   *
   * @param n l'intero da moltiplicare.
   * @return la frazione {@code this * n}.
   */
  public Frazione prodotto(int n) {
    return norm((long) num * n, den);
  }

  /**
   * Calcola il massimo comun divisore tra due interi.
   *
   * <p>Questa funzione accetta e restituisce argomenti di tipo {@code long}, questo può essere
   * utile per trattare frazioni che ridotte ai minimi termini possono essere rappresentate tramite
   * interi di tipo {@code int}.
   *
   * @param a il primo intero.
   * @param b il secondo intero.
   * @return il loro MCD (se {@code b != 0} altrimenti {@code a}).
   */
  private static final long gcd(long a, long b) {
    while (b != 0) {
      long r = a % b;
      a = b;
      b = r;
    }
    return a;
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj) return true;
    if (!(obj instanceof Frazione)) return false;
    Frazione other = (Frazione) obj;
    return den == other.den && num == other.num;
  }

  @Override
  public int hashCode() {
    return Objects.hash(num, den);
  }

  @Override
  public String toString() {
    return den != 1 ? String.format("%d/%d", num, den) : String.format("%d", num);
  }
}
