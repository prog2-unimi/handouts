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

package it.unimi.di.prog2.t13;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class Client {

  public static void consume(final String name, final Picker p, final int k) {
    Objects.requireNonNull(name);
    Objects.requireNonNull(p);
    if (k < 1) throw new IllegalArgumentException("Il numero di candidati deve essere >= 1");
    System.out.println(name + ":");
    if (k == 1) while (p.remaining() >= 1) System.out.println("\t" + p.pick());
    else while (p.remaining() >= k) System.out.println("\t" + Arrays.toString(p.pick(k)));
  }

  public static void main(String[] args) {
    final List<String> candidates = List.of("Pippo", "Pluto", "Paperino", "Qui", "Quo", "Qua");
    final int k = 2;
    consume("SequentialListPicker", new SequentialListPicker(candidates), k);
    consume("RandomListPicker", new RandomListPicker(candidates), k);
    consume("SequentialNumberPicker", new SequentialNumberPicker(4), k);
    consume("RandomPicker", new RandomNumberPicker(4), k);
  }
}
