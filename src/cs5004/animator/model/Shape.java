package cs5004.animator.model;

/**
 * This interface contains all operations that all types of shapes should support.
 */
public interface Shape {

  /**
   * Get x coordinate value of the shape.
   *
   * @return x coordinate value as double
   */
  double getX();

  /**
   * Get y coordinate value of the shape.
   *
   * @return y coordinate value as double
   */
  double getY();

  /**
   * Get red color value of the shape.
   *
   * @return red color value as int
   */
  int getRed();

  /**
   * Get green color value of the shape.
   *
   * @return green color value as int
   */
  int getGreen();

  /**
   * Get blue color value of the shape.
   *
   * @return blue color value as int
   */
  int getBlue();

  /**
   * Get appearance time of the shape.
   *
   * @return appearance time value as int
   */
  int getAppearance();

  /**
   * Get disappearance time of the shape.
   *
   * @return disappearance time value as int
   */
  int getDisappearance();

  /**
   * Sets the disappearance time of the shape.
   * @param newEnd time shape disappears.
   */
  void setDisappearance(int newEnd);

  /**
   * Get string shape of the shape.
   *
   * @return shape of the shape as string
   */
  String getName();




  /**
   * Changes the color of the shape by adjusting the integer values of red, green and blue. Throws
   * an IllegalArgumentException when the integer values are less than zero or greater than 255.
   *
   * @param red       the int value of the red color
   * @param green     the int value of the green color
   * @param blue      the int value of the blue color
   * @param timeStart the int start time value of when the color changes.
   * @param timeEnd   the int end time value of when the color ends the color change.
   * @returns transformation object.
   * @throws IllegalArgumentException when colors are less than zero or greater than 255
   */
  Transformation changeColor(int red, int green, int blue, int timeStart, int timeEnd);

  /**
   * Changes the shapes newLocation to the given value over a period of ticks. This allows the shape
   * to move from one location to another. An IllegalArgumentException is thrown if the location is
   * the same, a negative value, or the period of ticks is not at the current time or in the
   * future.
   *
   * @param newX      the x coordinate of the location the shape will move to
   * @param newY      the y coordinate of the location the shape will move to
   * @param timeStart time start interval of the move transformation.
   * @param timeEnd   time end interval of the move transformation.
   * @returns transformation object.
   * @throws IllegalArgumentException if the location is the same, a negative value, or the period
   *                                  of ticks is not at the current time or in the future.
   */
  Transformation move(double newX, double newY, int timeStart, int timeEnd);

  /**
   * Returns the shapeType of the shape as a ShapeType enum.
   * @return shapeType as an enum.
   */
  AbstractShape.ShapeType getShapeType();

}