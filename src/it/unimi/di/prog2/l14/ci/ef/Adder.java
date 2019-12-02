package it.unimi.di.prog2.l14.ci.ef;

import java.util.List;

public class Adder implements AdderInterface {

  private int result = 0;

  @Override
  public void add(int x) {
    result += x;
  }

  @Override
  public void add(List<Integer> l) {
    for (int x : l)
      add(x);
  }

  @Override
  public int result() {
    return result;
  }

}
