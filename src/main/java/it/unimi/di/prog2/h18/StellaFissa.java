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

/**
 * Una stella fissa.
 *
 * <p>Una stella fissa è un {@link CorpoCeleste} con posizione e velocità fissate (ed energia pari a
 * 0).
 */
public class StellaFissa extends CorpoCeleste {

  /** La posizione di questa stella fissa. */
  private final Punto posizione;

  /**
   * Costruisce una stella fissa.
   *
   * <p>Costruisce una stella fissa dato il suo nome e la sua posizione.
   *
   * @param nome il nome.
   * @param x la coordinata x della posizione iniziale.
   * @param y la coordinata y della posizione iniziale.
   * @param z la coordinata z della posizione iniziale.
   * @throws NullPointerException se il nome è {@code null}.
   * @throws IllegalArgumentException se il nomoe è composto di soli spazi, o vuoto.
   */
  public StellaFissa(final String nome, final int x, final int y, final int z) {
    super(nome);
    posizione = new Punto(x, y, z);
  }

  @Override
  public void aggiornaPosizione() {}

  @Override
  public void aggiornaVelocità(final CorpoCeleste c) {}

  @Override
  public String toString() {
    return String.format("Stella fissa, nome: %s, pos: %s", nome, posizione);
  }

  @Override
  public Punto velocità() {
    return Punto.ZERO;
  }

  @Override
  public Punto posizione() {
    return posizione;
  }

  @Override
  public long energia() {
    return 0;
  }
}
