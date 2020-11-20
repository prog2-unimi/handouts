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

package it.unimi.di.prog2.t10;

/**
 * Example of a <a href="https://docs.oracle.com/javase/tutorial/java/javaOO/nested.html">Static
 * Nested Class</a>.
 */
public class OuterSN {

  private final String state;

  public OuterSN(final String state) {
    this.state = state;
  }

  static class StaticNested {

    private final OuterSN outer;

    StaticNested(final OuterSN outer) {
      this.outer = outer;
    }

    void print() {
      System.out.println(outer.state);
    }
  }

  public void print() {
    final StaticNested sn = new StaticNested(this);
    sn.print();
  }
}
