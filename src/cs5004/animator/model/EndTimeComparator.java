package cs5004.animator.model;

import java.util.Comparator;

public class EndTimeComparator implements Comparator<Transformation> {
  @Override
  public int compare(Transformation t1, Transformation t2) {
    return t1.getEndTime() - t2.getEndTime();
  }
}
