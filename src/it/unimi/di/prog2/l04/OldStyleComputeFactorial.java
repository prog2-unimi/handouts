package it.unimi.di.prog2.l04;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;

public class OldStyleComputeFactorial {

  public static void main(String[] args) {
    PrintWriter out = new PrintWriter(System.out);
    BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
    PrintWriter err = new PrintWriter(System.err);
    out.println("Enter an integer: ");
    String s = null;
    try {
      s = in.readLine();
      int n = Integer.parseInt(s);
      if (n > 0) {
        out.print(n);
        out.print("! = ");
        out.println(Num.fact(n));
      } else
        err.println("input not positive");
    } catch (Exception e) {
      err.println("bad input");
    }
  }
}
