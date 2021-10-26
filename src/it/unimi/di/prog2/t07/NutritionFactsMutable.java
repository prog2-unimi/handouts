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

package it.unimi.di.prog2.t07;

/** See EJ 2.1 for a discussion of this class. */
@SuppressWarnings("unused")
public class NutritionFactsMutable {

  private int servingSize = -1;
  private int servings = -1;
  private int calories = 0;
  private int fat = 0;
  private int sodium = 0;
  private int carbohydrate = 0;

  public NutritionFactsMutable() {}

  public void setServingSize(int val) {
    servingSize = val;
  }

  public void setServings(int val) {
    servings = val;
  }

  public void setCalories(int val) {
    calories = val;
  }

  public void setFat(int val) {
    fat = val;
  }

  public void setSodium(int val) {
    sodium = val;
  }

  public void setCarbohydrate(int val) {
    carbohydrate = val;
  }
}
