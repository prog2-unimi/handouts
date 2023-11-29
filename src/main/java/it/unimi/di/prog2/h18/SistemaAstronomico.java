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

import java.util.Iterator;
import java.util.Objects;
import java.util.SortedSet;
import java.util.TreeSet;

// un sistema astronomico è una insieme ordinato (non null, in cui l'ordine è
// dato dall'ordine natuarle nome del corpo celeste) di corpi celesti (ciascuno
// non null); questo è vero in costruzione e mai invalidato da alcun metodo

/**
 * Un sistema astronomico.
 *
 * <p>Un sistema astronomico è un insieme ordinato di corpi celesti; esso è in grado di simulare
 * l'evoluzione (di posizione e velocità) dei corpi celesti in esso contenuti. e di calcolare la sua
 * energia totale.
 *
 * <p>L'uso inteso di questa classe è:
 *
 * <ul>
 *   <li>creare un sistema astronomico;
 *   <li>aggiungervi un elencod di corpi celesti;
 *   <li>effettuare la simulazione;
 *   <li>calcolare l'energia totale.
 * </ul>
 *
 * <p>Il metodo {@link #aggiungi(CorpoCeleste)} non effettua una copia del corpo celeste, pertanto
 * se il corpo celeste viene modificato dopo l'aggiunta, il comportamento del sistema astronomico
 * non è definito. Gli ultimi due passi possono essere ripetuti, per conoscere lo stato intermedio
 * della simulazione; viceversa, l'aggiunta di corpi celesti dopo l'inizio della simulazione rende
 * il comportamento del sistema astronomico non definito.
 */
public class SistemaAstronomico {

  /** L'insieme dei corpi celesti. */
  private final SortedSet<CorpoCeleste> corpiCelesti = new TreeSet<>();

  /**
   * Aggiunge un corpo celeste al sistema.
   *
   * <p>Se il corpo celeste è già presente nel sistema (ossia c'è già un corpo celeste con lo stesso
   * nome), non viene aggiunto.
   *
   * @param c li corpo celeste.
   * @return {@code true} se il corpo celeste è stato aggiunto, {@code false} altrimenti.
   * @throws NullPointerException se c è null.
   */
  public boolean aggiungi(final CorpoCeleste c) {
    return corpiCelesti.add(Objects.requireNonNull(c));
  }

  /**
   * Svolge un passo di simulazione.
   *
   * <p>Per dettagli vedere l'<em>overview</em> di questo pacchetto.
   */
  private void passo() {
    for (final CorpoCeleste p : corpiCelesti)
      for (final CorpoCeleste c : corpiCelesti) {
        if (p == c) continue;
        p.aggiornaVelocità(c);
      }
    for (final CorpoCeleste c : corpiCelesti) c.aggiornaPosizione();
  }

  /**
   * Simula l'evoluzione del sistema per un dato numero di passi.
   *
   * @param passi il numero di passi da simulare.
   * @throws IllegalArgumentException se passi non è positivo.
   */
  public void simula(final int passi) {
    if (passi <= 0) throw new IllegalArgumentException();
    for (int i = 0; i < passi; i++) passo();
  }

  /**
   * Restituisce l'energia complessiva del sistema.
   *
   * @return l'energia totale.
   */
  public long energia() {
    long res = 0;
    for (final CorpoCeleste c : corpiCelesti) res += c.energia();
    return res;
  }

  @Override
  public String toString() {
    final StringBuffer sb = new StringBuffer();
    final Iterator<CorpoCeleste> it = corpiCelesti.iterator();
    while (it.hasNext()) {
      sb.append(it.next());
      if (it.hasNext()) sb.append('\n');
    }
    return sb.toString();
  }
}
