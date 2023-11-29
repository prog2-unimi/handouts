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

// un pianeta è un corpo celeste con posizione velocità non null, questo fatto
// è vero in costruzione e mai modificato da alcun metodo; sebbene velocità e
// posizione siano sostituite dai metodi mutazionali, i campi possono essere
// esposti senza rischio in quanto immutabili

/**
 * Un pianeta.
 *
 * <p>Un pianeta è un {@link CorpoCeleste} con posizione e velocità variabili.
 */
public class Pianeta extends CorpoCeleste {

  /** La velocità di questo pianeta. */
  private Punto velocità;

  /*-
   * RI: - velocità non deve essere null;
   *     - gli attributi della superclasse sono private quindi non riguardano questo RI;
   *
   * AF: - la velocità del pianeta è contenuta nel campo omonimo, per il resto vale l'AF del supertipo.
   */

  /**
   * Costruisce un pianeta.
   *
   * <p>Costruisce un pianeta inizialmente fermo (cioè con velocità di norma 0) dato il suo nome e
   * la sua posizione iniziale.
   *
   * @param nome il nome.
   * @param x la coordinata x della posizione iniziale.
   * @param y la coordinata y della posizione iniziale.
   * @param z la coordinata z della posizione iniziale.
   * @throws NullPointerException se il nome è {@code null}.
   * @throws IllegalArgumentException se il nomoe è composto di soli spazi, o vuoto.
   */
  public Pianeta(final String nome, final int x, final int y, final int z) {
    super(nome, x, y, z);
    velocità = Punto.ZERO;
    assert repOk();
  }

  @Override
  public Punto velocità() {
    return velocità;
  }

  @Override
  public void aggiornaPosizione() {
    posizione(posizione().somma(velocità));
    assert repOk();
  }

  @Override
  public void aggiornaVelocità(final CorpoCeleste c) {
    Objects.requireNonNull(c);
    final Punto dv = c.posizione().sottrai(posizione()).segno();
    velocità = velocità.somma(dv);
    assert repOk();
  }

  @Override
  public String toString() {
    return String.format("Pianeta, nome: %s, pos: %s, vel: %s", nome(), posizione(), velocità());
  }

  private boolean repOk() {
    return velocità != null;
  }
}
