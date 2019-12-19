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

package it.unimi.di.prog2.l20;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

public class PathsTests {

  @Test
  @Tag("seems_ok")
  public void selection() {
    assertEquals(2, Paths.selection(true, false));
    assertEquals(0, Paths.selection(false, true));
  }

  @Test
  public void selection_fail() {
    assertThrows(ArithmeticException.class, () -> Paths.selection(false, false));
  }

  @Test
  @Tag("seems_ok")
  public void iteration() {
    assertEquals(-1, Paths.iteration(0));
    assertEquals(1, Paths.iteration(1));
  }

  @Test
  @Tag("seems_ok")
  public void iteration_fail() {
    assertThrows(ArithmeticException.class, () -> Paths.iteration(2));
  }
}
