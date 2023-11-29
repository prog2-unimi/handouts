/*

Copyright 2021 Luca Prigioniero, Massimo Santini
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

package it.unimi.di.prog2.h18;

import java.util.Objects;

/** Punto tridimensionale, immutabile e a coordinate intere. */
public record Punto(int x, int y, int z) {

  /** Il punto corrispondente all'origine. */
  public static final Punto ZERO = new Punto(0, 0, 0);

  /**
   * Restituisce la somma tra questo e il punto dato.
   *
   * @param q il punto da sommare a questo.
   * @return la somma.
   * @throws NullPointerException se q è null.
   */
  public Punto somma(final Punto q) {
    Objects.requireNonNull(q);
    return new Punto(x + q.x, y + q.y, z + q.z);
  }

  /**
   * Restituisce la diffenza tra questo e il punto dato.
   *
   * @param q il punto da sottrarre da questo.
   * @return la differenza.
   * @throws NullPointerException se q è null.
   */
  public Punto sottrai(final Punto q) {
    Objects.requireNonNull(q);
    return new Punto(x - q.x, y - q.y, z - q.z);
  }

  /**
   * Resituisce la norma l1 di questo punto.
   *
   * @return la norma.
   */
  public int norma() {
    return (x > 0 ? x : -x) + (y > 0 ? y : -y) + (z > 0 ? z : -z);
  }

  /**
   * Restituisce un punto avente per coordinate -1 o + 1 a seconda del segno delle coordinate di
   * questo punto.
   *
   * @return il punto avente per coordinate il segno delle coordinate di questo
   */
  public Punto segno() {
    return new Punto((int) Math.signum(x), (int) Math.signum(y), (int) Math.signum(z));
  }

  @Override
  public String toString() {
    return String.format("(%d, %d, %d)", x, y, z);
  }
}
