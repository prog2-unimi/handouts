/*

Copyright 2019 Massimo Santini

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

package it.unimi.di.prog2.l24;

import java.util.AbstractQueue;
import java.util.ArrayDeque;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Queue;

/**
 * Un ricevitore di pacchetti.
 *
 * <p>Questa classe è in grado di ricevere una sequenza di pacchetti e raccoglierli in messaggi a
 * seconda del numero identificativo di messaggio in ciascuno di essi. La classe si comporta come
 * una {@link Queue} a cui i messaggi vengono aggiunti mano a mano che sono completati — le
 * operazioni di inserimento in tale coda sono ovviamente impedite dall'esterno (tramite il
 * sollevamento dell'eccezione {@link UnsupportedOperationException}), inoltre dato che i messaggi
 * in tale coda sono completi (ossia praticamente immutabili), questo non espone al rischio di
 * compromettere la rappresentazione dei medesimi.
 *
 * <p>Si osservi che è assolutamente plausibile un progetto molto più elementare del
 * <strong>ricevitore</strong> basato su due semplici liste di messaggi incompleti e completi.
 */
public class Ricevitore extends AbstractQueue<Messaggio> {

  // la rappresentazione del ricevitore è data da una mappa dell'identificativo al messaggio di
  // tutti  e soli i messaggi per cui è stato ricevuto almeno un pacchetto, ma che non sono ancora
  // completati, e una {@link Queue} contenente i messaggi completati (non ancora prelevati).
  // Tale rappresentazione è vera all'inizio (per come sono inizializzati i campi) e resta vera ad
  // ogni invocazione dell'unico metodo mutazionale {@link #ricevi(Pacchetto)} (come è facile
  // mostrare);
  // la coda è esposta solo alla rimozione (che non inficia l'invariante qui descritto)

  private final Map<Integer, Messaggio> incompleti = new HashMap<>();
  private final Queue<Messaggio> completi = new ArrayDeque<Messaggio>();

  public void ricevi(final Pacchetto pacchetto) {
    if (pacchetto == null) throw new NullPointerException("pacchetto non può essere null");
    Messaggio messaggio = incompleti.get(pacchetto.id);
    if (messaggio == null) {
      messaggio = new Messaggio(pacchetto);
      incompleti.put(messaggio.id, messaggio);
    } else messaggio.aggiungi(pacchetto);
    if (messaggio.completo()) {
      incompleti.remove(messaggio.id);
      completi.offer(messaggio);
    }
  }

  // tutti i metodi seguenti (la cui implementazione è richiesta dal
  // momento che la classe estende {@link AbstractQueue} non compromettono
  // la rappresentazione perché (compreso l'iteratore) consentono al più di
  // di rimuovere messaggi completi (ossia praticamente immutabili)

  @Override
  public boolean offer(Messaggio e) {
    throw new UnsupportedOperationException(
        "non è possibile aggiungere messaggi se non col metodo ricevi");
  }

  @Override
  public Messaggio poll() {
    return completi.poll();
  }

  @Override
  public Messaggio peek() {
    return completi.peek();
  }

  @Override
  public Iterator<Messaggio> iterator() {
    return completi.iterator();
  }

  @Override
  public int size() {
    return completi.size();
  }
}
