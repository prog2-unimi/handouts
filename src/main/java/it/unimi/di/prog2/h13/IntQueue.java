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

package it.unimi.di.prog2.h13;

import java.util.NoSuchElementException;

/**
 * Una conda limitata contenente interi.
 *
 * <p>La coda è una struttura dati con modalità d'accesso FIFO che può contenere al più un numero
 * assegnato di interi, detto <i>capacità</i>.
 */
public class IntQueue {

  /** Gli elementi della coda (vedi AF per spiegazione di come sono organizzati). */
  private final int[] elements;

  /** La posizione in {@link #elements} del primo elemento della coda (o -1 se la coda è vuota). */
  private int head;

  /** La prima posizione libera in {@link #elements} (se la coda non è piena). */
  private int tail;

  /*-
   * RI:
   *      - elements != null,
   *      - -1 <= head < elements.length,
   *      - 0 <= tail < elements.length,
   *      - head == -1 => tail = 0
   *
   * AF:
   *      - la capacità della coda è data da elements.length;
   *      - gli elementi [x_1, ..., x_k] della coda sono contenuti in elements, più precisamente:
   *        - se head == -1, la coda è vuota,
   *        - se head <= tail la coda è:
   *            [elements[head], elements[head + 1], …, elements[tail - 1]];
   *        - se tail < head la coda è:
   *            [elements[head], elements[head + 1], …, elements[capacità - 1]] seguita da
   *            [elements[0], elements[1], …, elements[tail - 1]].
   */

  /**
   * Costruisce una coda di <i>capacità</i> assegnata.
   *
   * @param capacità la capacità.
   * @throws IllegalArgumentException se la <i>capacità</i> non è positiva.
   */
  public IntQueue(final int capacità) {
    if (capacità <= 0) throw new IllegalArgumentException();
    elements = new int[capacità];
    head = -1;
    tail = 0;

    assert repOk();
  }

  /** Determina se la coda è vuota, ossia se non contiene interi. */
  public boolean isEmpty() {
    return head == -1;
  }

  /**
   * Determina se la coda è piena, ossia se non contiene tanti interi quanto è la sua
   * <i>capacità</i>.
   */
  public boolean isFull() {
    return tail == head;
  }

  /**
   * Restituisce il numero di elementi nella coda.
   *
   * @return il numero di elementi.
   */
  public int size() {
    if (isEmpty()) return 0;
    if (isFull()) return elements.length;
    return (tail - head + elements.length) % elements.length;
  }

  /**
   * Accoda un intero.
   *
   * @param x l'intero da accodare.
   * @throws IllegalStateException se la coda è piena.
   */
  public void enqueue(final int x) {
    if (isFull()) throw new IllegalStateException("La coda è piena");
    if (head == -1) head = 0;
    elements[tail] = x;
    tail = (tail + 1) % elements.length;

    assert repOk();
  }

  /**
   * Prelava un intero.
   *
   * @return l'intero prelevato dalla coda.
   * @throws NoSuchElementException se la coda è vuota.
   */
  public int dequeue() {
    if (isEmpty()) throw new NoSuchElementException("La coda è vuota");
    final int r = elements[head];
    head = (head + 1) % elements.length;
    if (head == tail) {
      head = -1;
      tail = 0;
    }

    assert repOk();

    return r;
  }

  private boolean repOk() {
    if (elements == null) return false;
    if (size() > elements.length) return false;
    if (head < -1 || head > elements.length) return false;
    if (tail < 0 || tail > elements.length) return false;
    if (head == -1 && tail != 0) return false;
    return true;
  }

  @Override
  public String toString() {
    if (isEmpty()) return "IntQueue: []";
    final StringBuilder sb = new StringBuilder("IntQueue: [");
    int i = head, n = 0;
    while (n < size() - 1) {
      sb.append(elements[i] + ", ");
      i = (i + 1) % elements.length;
      n += 1;
    }
    sb.append(elements[i] + "]");
    return sb.toString();
  }

  @Override
  public int hashCode() {
    int result = 0;
    int i = head, n = 0;
    while (n < size()) {
      result = 31 * result + Integer.hashCode(elements[i]);
      i = (i + 1) % elements.length;
      n += 1;
    }
    return result;
  }

  @Override
  public boolean equals(Object obj) {
    if (!(obj instanceof IntQueue)) return false;
    final IntQueue other = (IntQueue) obj;
    if (size() != other.size()) return false;
    int i = head, j = other.head, n = 0;
    while (n < size()) {
      if (elements[i] != other.elements[j]) return false;
      i = (i + 1) % elements.length;
      j = (j + 1) % other.elements.length;
      n += 1;
    }
    return true;
  }
}
