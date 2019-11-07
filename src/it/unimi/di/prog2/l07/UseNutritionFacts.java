package it.unimi.di.prog2.l07;

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
