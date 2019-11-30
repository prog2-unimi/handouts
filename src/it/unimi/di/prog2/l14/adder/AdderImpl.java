package it.unimi.di.prog2.l14.adder;

import java.util.List;

public class AdderImpl implements Adder {

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
