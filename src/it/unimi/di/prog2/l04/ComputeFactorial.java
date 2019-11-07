package it.unimi.di.prog2.l04;

import java.util.Scanner;

public class ComputeFactorial {

  @SuppressWarnings("resource")
  public static void main(String[] args) {

    Scanner scanner = new Scanner(System.in);

    while (scanner.hasNextInt()) {
      int n = scanner.nextInt();
      if (n >= 0)
        System.out.printf("%d! = %d\n", n, Num.fact(n));
      else
        System.err.println("input not positive");
    }
  }
}
