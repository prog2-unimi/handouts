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

package it.unimi.di.prog2.h06;

/** Classe di utilità per il calcolo delle radici quadrate. */
public class Radici {

  // Nota: Usando {@literal \( ... \)} è possibile includere LaTeX nella documentazione

  /**
   * Estrae se possibile la radice quadrata del numero dato.
   *
   * <p>Funzione <em>parziale</em> che qualora l'argomento sia non negativo restituisce una
   * approssimazione {@literal \( y \)} della radice quadrata nel senso che {@literal \( |y^2 - x| <
   * 10^{-3} \)}.
   *
   * @param x il numero di cui estrarre la radice, deve essere non negativo.
   * @return l'approssimazione della radice quadrata.
   */
  public static double radiceParziale(double x) {

    // L'implementazione si basa sul metodo di bisezione
    // https://en.wikipedia.org/wiki/Bisection_method

    double low = 0, high = x > 1 ? x : 1, mid = -1;
    while (high - low > .00001) {
      mid = (high + low) / 2;
      if (mid * mid - x < 0) low = mid;
      else high = mid;
    }
    return mid;
  }

  /**
   * Estrae la radice quadrata del numero dato.
   *
   * <p>Funzione <em>totale</em> che qualora l'argomento sia non negativo restituisce una
   * approssimazione {@literal \( y \)} della radice quadrata nel senso che {@literal \( |y^2 - x| <
   * 10^{-3} \)}; viceversa, a fronte di un argomento negativo, resituisce il valore convenzionale
   * -1.
   *
   * @param x il numero di cui estrarre la radice.
   * @return l'approssimazione della radice quadrata se x è non negativo, altrimenti -1.
   */
  public static double radiceTotale(double x) {

    // Sapreste dare una implementazione basata sul metodo di Newton?
    // https://en.wikipedia.org/wiki/Newton's_method
    // Può essere d'aiuto la nota https://math.mit.edu/~stevenj/18.335/newton-sqrt.pdf

    return x;
  }
}
