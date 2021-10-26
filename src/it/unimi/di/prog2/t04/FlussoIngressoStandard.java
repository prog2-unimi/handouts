/*

Copyright 2021 Massimo Santini

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

package it.unimi.di.prog2.t04;

import java.util.Scanner;

/*
 * Un esempio di programma che legge un elenco di interi dal flusso di
 * ingresso standard (stdin) e ne emette la somma.
 *
 * Una volta compilato, un esempio di esecuzione è
 *
 *  java FlussoIngressoStandard
 *
 * a cui far seguire un elenco di interi terminato da ^D (ossia premendo i
 * tasti ctrl e D contemporaneamente).
 *
 */

public class FlussoIngressoStandard {
  public static void main(String[] args) {
    int somma = 0;

    try (Scanner s = new Scanner(System.in)) {
      while (s.hasNextInt()) somma += s.nextInt();
    }

    System.out.println(somma);
  }
}
