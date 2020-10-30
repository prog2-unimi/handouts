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

package it.unimi.di.prog2.t07;

/** See EJ 2.1 for a discussion of the alternatives presented here. */
public class UseNutritionFacts {

  @SuppressWarnings("unused")
  public static void main(String[] args) {

    NutritionFactsImmutable immutableCola = new NutritionFactsImmutable(240, 8, 100, 0, 35, 27);

    NutritionFactsMutable mutableCola = new NutritionFactsMutable();
    mutableCola.setServingSize(240);
    mutableCola.setServings(8);
    mutableCola.setCalories(100);
    mutableCola.setSodium(35);
    mutableCola.setCarbohydrate(27);

    NutritionFacts cocaCola =
        new NutritionFacts.Builder(240, 8).calories(100).sodium(35).carbohydrate(27).build();
  }
}
