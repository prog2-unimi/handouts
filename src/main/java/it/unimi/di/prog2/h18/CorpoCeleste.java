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

/**
 * Un corpo celeste.
 *
 * <p>Un corpo celeste è caratterizzato da
 *
 * <ul>
 *   <li>un <em>nome</em> (non non vuoto, o composto di soli spazi);
 *   <li>una <em>posizione</em> e una <em>velocità</em> dati da un {@link Punto};
 *   <li>una <em>energia</em> data dal prodotto della norma della posizione per la norma della
 *       velocità.
 * </ul>
 *
 * <p>Un corpo celeste può aggiornare la sua velocità in funzione dell'interazione con un altro
 * corpo celeste; una volta modificata la sua velocità, esso può aggiornare la sua posizione. Per i
 * dettagli si consulsti l'<em>overview</em> di questo paccchetto.
 *
 * <p>L'uguaglianza e l'ordinamento tra corpi celesti è basata soltanto sul nome.
 */
public abstract class CorpoCeleste implements Comparable<CorpoCeleste> {

  /** Il nome del corpo celeste. */
  public final String nome;

  /**
   * Costruisce un corpo celeste con il nome dato.
   *
   * @param nome il nome.
   * @throws NullPointerException se il nome è {@code null}.
   * @throws IllegalArgumentException se il nomoe è composto di soli spazi, o vuoto.
   */
  public CorpoCeleste(final String nome) {
    if (Objects.requireNonNull(nome).isBlank()) throw new IllegalArgumentException();
    this.nome = nome;
  }

  /**
   * Restituisce l'energia totale di questo corpo celeste.
   *
   * @return l'energia.
   */
  public long energia() {
    return posizione().norma() * velocità().norma();
  }

  /**
   * Restituisce la velocità di questo corpo celeste.
   *
   * @return la velocità (non {@code null}}).
   */
  public abstract Punto velocità();

  /**
   * Restituisce la posizione di questo corpo celeste.
   *
   * @return la posizione (non {@code null}}).
   */
  public abstract Punto posizione();

  /**
   * Aggiorna la posizione di questo corpo celeste (usualmente a seguito di un cambiamento della sua
   * velocità).
   */
  public abstract void aggiornaPosizione();

  /**
   * Aggiorna la velocità di questo corpo celeste.
   *
   * <p>La velocità va aggiornata in funzione dell'interazione con il corpo celeste dato come
   * argomento secondo quanto illustrato nell'<em>overview</em> di questo pacchetto; questo metodo
   * <em>non deve modificare</em> il corpo celeste dato come argomento.
   *
   * @param c il corpo celeste con cui interagire.
   */
  public abstract void aggiornaVelocità(final CorpoCeleste c);

  @Override
  public int compareTo(CorpoCeleste o) {
    return nome.compareTo(o.nome);
  }

  @Override
  public boolean equals(Object obj) {
    if (!(obj instanceof CorpoCeleste)) return false;
    return nome.equals(((CorpoCeleste) obj).nome);
  }

  @Override
  public int hashCode() {
    return nome.hashCode();
  }
}
