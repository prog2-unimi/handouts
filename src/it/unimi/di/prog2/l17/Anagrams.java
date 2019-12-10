/*

Copyright 2019 Massimo Santini

This file is part of "Programmazione 2 @ UniMI" teaching material.

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

package it.unimi.di.prog2.l17;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Anagrams {

  public static String signature(final String word) {
    char[] chars = word.toCharArray();
    Arrays.sort(chars);
    return new String(chars);
  }

  public static void main(String[] args) throws IOException {
    List<String> words = Files.readAllLines(new File("words.txt").toPath());

    // Builds the map signature(w) -> { s : s is anagram of w }

    final Map<String, Set<String>> signature2anagrams = new HashMap<>();
    for (final String word : words) {
      final String signature = signature(word);
      Set<String> anagrams = signature2anagrams.get(signature);
      if (anagrams == null) {
        anagrams = new HashSet<>();
        signature2anagrams.put(signature, anagrams);
      }
      anagrams.add(word);
    }

    // Extract the list of all anagrams and (reverse) sorts it based on list size

    List<Set<String>> lisfOfAnagrams = new ArrayList<>(signature2anagrams.values());
    lisfOfAnagrams.sort(
        new Comparator<Set<String>>() {
          public int compare(Set<String> o1, Set<String> o2) {
            return Integer.compare(o1.size(), o2.size());
          }
        }.reversed());

    // Prints the list of anagrams (of size at least 1)

    for (final Set<String> anagrams : lisfOfAnagrams) {
      if (anagrams.size() == 1) break;
      System.out.println(anagrams);
    }
  }
}
