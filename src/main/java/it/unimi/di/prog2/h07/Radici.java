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

package it.unimi.di.prog2.h07;

/** Classe di utilità per il calcolo delle radici quadrate. */
public class Radici {

  /** La costante che descrive l'approssimazione delle funzioni di questa classe. */
  public static final double EPSILON = .001;

  /**
   * Estrae se possibile la radice quadrata del numero dato.
   *
   * <p>Funzione <em>parziale</em> che restituisce una approssimazione {@code y} della radice
   * quadrata nel senso che {@code |y * y - x| <} {@link #EPSILON}.
   *
   * @param x il numero di cui estrarre la radice.
   * @return l'approssimazione della radice quadrata.
   */
  public static double radiceParziale(double x) {
    double low = 0, high = x > 1 ? x : 1;
    double mid = -1;
    for (; ; ) {
      mid = (high + low) / 2;
      double err = mid * mid - x;
      if (err < -EPSILON) low = mid;
      else if (err > EPSILON) high = mid;
      else break;
    }
    return mid;
  }

  /**
   * Estrae la radice quadrata del numero dato.
   *
   * <p>Funzione <em>totale</em> che qualora l'argomento sia non negativo restituisce una
   * approssimazione {@code y} della radice quadrata nel senso che {@code |y * y - x| <} {@link
   * #EPSILON}.
   *
   * @param x il numero di cui estrarre la radice.
   * @return l'approssimazione della radice quadrata.
   * @throws IllegalArgumentException se x è negativo.
   */
  public static double radiceTotale(double x) throws IllegalArgumentException {
    if (x < 0) throw new IllegalArgumentException("L'argomento deve essere non negativo.");
    double y = 1;
    for (; ; ) {
      y = (y + x / y) / 2;
      double err = y * y - x;
      if (-EPSILON < err && err < EPSILON) break;
    }
    return y;
  }
}
