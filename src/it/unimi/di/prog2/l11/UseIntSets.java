package it.unimi.di.prog2.l11;

public class UseIntSets {

  public static void main(String args[]) {
    IntSet s = new IntSet();

    for (String a : args)
      s.insert(Integer.parseInt(a));

    int sum = 0;
    for (int e : s) {
      sum += e;
    }
    System.out.println("Sum is " + sum);
  }
}
