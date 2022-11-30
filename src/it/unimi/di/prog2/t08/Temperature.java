/*

Copyright 2022 Massimo Santini

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

package it.unimi.di.prog2.t08;

import java.util.Objects;

public class Temperature {

  final double kelvin;

  private Temperature(double kelvin) {
    if (kelvin < 0)
      throw new IllegalArgumentException("A temperature in Kelvin cannot be negative");
    this.kelvin = kelvin;
    assert repOk();
  }

  public static Temperature fromKelvin(double kelvin) {
    return new Temperature(kelvin);
  }

  public static Temperature fromCelsius(double celsius) {
    double kelvin = celsius + 273.15;
    if (kelvin < 0)
      throw new IllegalArgumentException("A temperature in Celsius cannot be less than -273.15");
    return new Temperature(kelvin);
  }

  public static Temperature fromFahrenheit(double fahrenheit) {
    double kelvin = (fahrenheit + 459.67) * 5.0 / 9.0;
    if (kelvin < 0)
      throw new IllegalArgumentException("A temperature in Fahrenheit cannot be less than -459.67");
    return new Temperature(kelvin);
  }

  public double asKelvin() {
    return kelvin;
  }

  public double asCelsius() {
    return kelvin - 273.15;
  }

  public double asFahrenheit() {
    return kelvin * 9.0 / 5.0 - 459.67;
  }

  @Override
  public String toString() {
    return String.format("%.2fK", kelvin);
  }

  @Override
  public boolean equals(Object other) {
    if (!(other instanceof Temperature)) return false;
    return ((Temperature) other).kelvin == this.kelvin;
  }

  @Override
  public int hashCode() {
    return Objects.hash(kelvin);
  }

  private boolean repOk() {
    return kelvin >= 0;
  }
}
