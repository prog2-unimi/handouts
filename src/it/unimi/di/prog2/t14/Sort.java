/*

Copyright 2020 Massimo Santini

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

package it.unimi.di.prog2.t14;

import static it.unimi.di.prog2.t14.Weight.Unit.DAG;
import static it.unimi.di.prog2.t14.Weight.Unit.HG;
import static it.unimi.di.prog2.t14.Weight.Unit.KG;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.function.Function;

public class Sort {

  public static void main(String[] args) {

    String[] words = new String[] {"one", "two", "three", "four"};

    // "natural" sort (refers to the comparator in String)

    Arrays.sort(words);
    System.out.println(Arrays.toString(words));

    // sort by string length

    Arrays.sort(
        words,
        new Comparator<>() {
          @Override
          public int compare(String o1, String o2) {
            return Integer.compare(o1.length(), o2.length());
          }
        });
    System.out.println(Arrays.toString(words));

    // same as above, but using Comparator.comparing magic :)

    Arrays.sort(
        words,
        Comparator.comparing(
            new Function<String, Integer>() {
              @Override
              public Integer apply(String t) {
                return t.length();
              }
            }));
    System.out.println(Arrays.toString(words));

    // sort by weight, based on the class comparator

    List<Weight> weights =
        new ArrayList<>(List.of(new Weight(56, HG), new Weight(4, KG), new Weight(123, DAG)));
    Collections.sort(weights); // uses the fact that Weight is comparable
    System.out.println(weights);
  }
}
