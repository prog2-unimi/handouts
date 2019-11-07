package it.unimi.di.prog2.l08;

import it.unimi.di.prog2.l07.IntSets;

public class UseIntSets {

  public static void main(String args[]) {
    int[] a = new int[args.length];
    int i = 0;
    for (String s : args)
      a[i++] = Integer.valueOf(s);
    IntSet S = IntSets.getElements(a);
    System.out.println(S);
  }
}
