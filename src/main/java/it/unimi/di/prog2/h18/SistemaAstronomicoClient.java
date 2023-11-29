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

import java.util.Scanner;

/**
 * Consente di verificare il comportamento di un sistema astronomico.
 *
 * <p>Per maggiori dettagli si veda l'<em>overview</em> di questo pacchetto.
 */
public class SistemaAstronomicoClient {

  /**
   * Simula un sistema astronomico.
   *
   * <p>Legge le informazioni dei corpi celesti da standard input, e simula il sistema per il numero
   * di passi specificato come primo argomento sulla linea di comando.
   *
   * @param args il numero di passi della simulazione.
   */
  public static void main(String[] args) {
    final SistemaAstronomico sa = new SistemaAstronomico();

    try (final Scanner s = new Scanner(System.in)) {
      while (s.hasNextLine()) {
        if (s.next().charAt(0) == 'P')
          sa.aggiungi(new Pianeta(s.next(), s.nextInt(), s.nextInt(), s.nextInt()));
        else sa.aggiungi(new StellaFissa(s.next(), s.nextInt(), s.nextInt(), s.nextInt()));
      }
    }

    sa.simula(Integer.parseInt(args[0]));
    System.out.println(sa);
    System.out.println("Energia totale: " + sa.energia());
  }
}
