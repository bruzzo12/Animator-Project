package cs5004.animator.model;

/**
 * Class used to represent time intervals.
 */
public class Ticker {
  private int rangeStart;
  private int rangeEnd;

  /**
   * Constructs a cs5004.animator.model.Ticker object that represents a time interval. If either time is less
   * than zero or they equal each other than an IllegalArgumentException is thrown.
   *
   * @param rangeStart    start of time range as an int.
   * @param rangeEnd      end of time range as an int.
   * @throws IllegalArgumentException when either time is less than zero or are equal to each other
   */
  public Ticker(int rangeStart, int rangeEnd) {
    if (rangeStart < 0 || rangeEnd < 0 ) {
      throw new IllegalArgumentException("Parameters may not be less than zero.");
    }
    this.rangeStart = rangeStart;
    this.rangeEnd = rangeEnd;
  }

  public int getRangeStart() {
    return rangeStart;
  }

  public int getRangeEnd() {
    return rangeEnd;
  }
}
