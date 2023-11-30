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

package it.unimi.di.prog2.h19;

import java.util.Iterator;
import java.util.NoSuchElementException;

/** Lista ordinata di interi senza ripetizioni. */
public class OrderedIntList {

  /** Indica se la lista è vuota. */
  private boolean isEmpty;

  /** Il valore conservato da questo nodo della lista. */
  private int value;

  /** Due sottoliste, contenenti i valori rispettivamente minori e maggiori di {@link #value}. */
  private OrderedIntList left, right;

  /*-
   * RI: se isEmpty è false:
   *      - left e right sono non null
   *      - gli elementi di left sono minori di value
   *      - gli elementi di right sono maggiori di value
   *     se isEmpty è true:
   *      - left e right sono null
   *
   * AF: se isEmpty è false: AF(left) + [value] + AF(right),
   *     se isEmpty è true: []
   *
   * Si osservi che AF/RI, per induzione strutturale, consentono di dimostrare che
   * la lista ha dimensione n >= 1, allora i suoi elementi sono ordinati in ordine
   * strettamente crescente:
   *
   * - se n = 1 è banalmente vero.
   * - se n > 1, allora left e right contengono meno di n elementi e per ipotesi di induzione
   * sono strettamente ordinate; per l'AF la lista è data da AF(left) + [v] + AF(right), per
   * induzione AF(left) è strettamente crescente, per RI il suo massimo valore è minore di value,
   * ancora per il RI value è minore degli elementi in AF(right) che strettamente crescente per
   * induzione, quindi AF è strettamente crescente.
   *
   */

  /** Costruisce una lista vuota. */
  public OrderedIntList() {
    this.isEmpty = true;
    left = right = null;
  }

  /**
   * Restituisce il numero di elementi nella lista.
   *
   * @return il numero di elementi nella lista, 0 se è vuota.
   */
  public int size() {
    return isEmpty ? 0 : 1 + left.size() + right.size();
  }

  /**
   * Consente di determinare se la lista è vuota.
   *
   * @return se la lista è vuota.
   */
  public boolean isEmpty() {
    return isEmpty;
  }

  /**
   * Consente di determinare se un valore appartiene alla lista.
   *
   * @param value il valore da cercare.
   * @return se appartiene alla lista.
   */
  public boolean contains(int value) {
    if (isEmpty) return false;
    if (value == this.value) return true;
    if (value < this.value) return left.contains(value);
    else return right.contains(value);
  }

  /**
   * Aggiunge un elemento alla lista.
   *
   * @param value l'elemento da aggiungere.
   * @throws IllegalArgumentException se l'elemento è già presente.
   */
  public void add(int value) {
    if (value == this.value) throw new IllegalArgumentException("Duplicate value");
    if (isEmpty) {
      this.value = value;
      isEmpty = false;
      left = new OrderedIntList();
      right = new OrderedIntList();
    } else if (value < this.value) left.add(value);
    else right.add(value);
  }

  /**
   * Restituisce il minimo valore della lista.
   *
   * @return il minimo valore della lista.
   * @throws NoSuchElementException se la lista è vuota.
   */
  public int min() {
    if (isEmpty) throw new NoSuchElementException();
    if (left.isEmpty) return value;
    return left.min();
  }

  /**
   * Restituisce il massimo valore della lista.
   *
   * @return il massimo valore della lista.
   * @throws NoSuchElementException se la lista è vuota.
   */
  public int max() {
    if (isEmpty) throw new NoSuchElementException();
    if (right.isEmpty) return value;
    return right.max();
  }

  /**
   * Rimuove un elemento dalla lista.
   *
   * @param value l'elemento da rimuovere.
   * @return se l'elemento è stato rimosso.
   */
  public boolean remove(int value) {
    if (isEmpty) return false;
    if (value == this.value) {
      if (left.isEmpty && right.isEmpty) {
        isEmpty = true;
        left = right = null;
      } else if (left.isEmpty) {
        this.value = right.value;
        left = right.left;
        right = right.right;
      } else if (right.isEmpty) {
        this.value = left.value;
        right = left.right;
        left = left.left;
      } else {
        int min = right.min();
        this.value = min;
        right.remove(min);
      }
      return true;
    }
    if (value < this.value) return left.remove(value);
    else return right.remove(value);
  }

  /**
   * Un iteratore vuoto.
   *
   * @return l'iteratore.
   */
  private Iterator<Integer> emptyIterator() {
    return new Iterator<>() {
      @Override
      public boolean hasNext() {
        return false;
      }

      @Override
      public Integer next() {
        throw new NoSuchElementException();
      }
    };
  }

  /**
   * Un ieratore ottenuto per concatenazione.
   *
   * <p>L'iteratore elenca dapprima gli elementi di {@code first}, poi {@code value} e quindi quelli
   * di {@code second}.
   *
   * @param first il primo iteratore, non deve essere {@code null}.
   * @param second il secondo iteratore, non deve essere {@code null}.
   * @return l'iteratore concatenato.
   */
  private Iterator<Integer> concat(final Iterator<Integer> first, final Iterator<Integer> second) {
    return new Iterator<>() {

      /*-
       * RI: - se hasNext è true, allora next è il prossimo valore da restituire.
       *     - se valueUsed è true, allora value è già stato restituito.
       * AF: - se hasNext è false, non ci sono interi da restituire;
       *     - se hasNext è true, gli elementi da restituire sono:
       *        - i restanti di first, se first.hasNext() è true;
       *        - value, se first.hasNext() è false e valueUsed è false;
       *        - i restanti di second, se first.hasNext() è false e valueUsed è true.
       */

      /** Indica se {@code value} è già stato restituito. */
      private boolean valueUsed = false;

      /** Il prossimo valore da restituire. */
      private int next;

      /** Indica se c'è un valore da restituire. */
      private boolean hasNext;

      @Override
      public boolean hasNext() {
        if (isEmpty) return false;
        if (hasNext) return true;
        if (first.hasNext()) {
          next = first.next();
          hasNext = true;
        } else if (valueUsed == false) {
          valueUsed = true;
          next = value;
          hasNext = true;
        } else if (second.hasNext()) {
          next = second.next();
          hasNext = true;
        }
        return hasNext;
      }

      @Override
      public Integer next() {
        if (!hasNext()) throw new NoSuchElementException();
        hasNext = false;
        return next;
      }
    };
  }

  /**
   * Iteratore che elenca gli elementi della lista in ordine crescente.
   *
   * @return l'iteratore.
   */
  public Iterator<Integer> smallToBig() {
    return isEmpty ? emptyIterator() : concat(left.smallToBig(), right.smallToBig());
  }

  /**
   * Iteratore che elenca gli elementi della lista in ordine decrescente.
   *
   * @return l'iteratore.
   */
  public Iterator<Integer> bigToSmall() {
    return isEmpty ? emptyIterator() : concat(right.bigToSmall(), left.bigToSmall());
  }

  @Override
  public boolean equals(Object obj) {
    if (!(obj instanceof OrderedIntList)) return false;
    OrderedIntList other = (OrderedIntList) obj;
    if (isEmpty != other.isEmpty) return false;
    if (isEmpty) return true;
    final Iterator<Integer> thisIterator = smallToBig(), otherIterator = other.smallToBig();
    while (thisIterator.hasNext() && otherIterator.hasNext())
      if (!thisIterator.next().equals(otherIterator.next())) return false;
    if (thisIterator.hasNext() || otherIterator.hasNext()) return false;
    return true;
  }

  @Override
  public int hashCode() {
    if (isEmpty) return 0;
    int result = 0;
    final Iterator<Integer> it = smallToBig();
    while (it.hasNext()) result = 31 * result + it.next().hashCode();
    return 0;
  }
}
