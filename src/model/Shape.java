package model;

/**
 * This interface contains all operations that all types of shapes should support.
 */
public interface Shape extends Comparable<Shape> {

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
   * Get string name of the shape.
   *
   * @return name of the shape as string
   */
  String getName();


  /**
   * Returns the distance of this shape from the origin. The distance is measured from whatever
   * reference position a shape is (e.g. a center for a circle)
   *
   * @return the distance from the origin
   */
  double distanceFromOrigin();

  /**
   * Computes and returns the area of this shape.
   *
   * @return the area of the shape
   */
  double area();

  /**
   * Computes and returns the perimeter of this shape.
   *
   * @return the perimeter of the shape
   */
  double perimeter();

  /**
   * Changes the color of the shape by adjusting the integer values of red, green and blue. Throws
   * an IllegalArgumentException when the integer values are less than zero or greater than 255.
   *
   * @param red       the int value of the red color
   * @param green     the int value of the green color
   * @param blue      the int value of the blue color
   * @param timeStart the int start time value of when the color changes.
   * @param timeEnd   the int end time value of when the color ends the color change.
   * @throws IllegalArgumentException when colors are less than zero or greater than 255
   */
  void changeColor(int red, int green, int blue, int timeStart, int timeEnd);

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
   * @throws IllegalArgumentException if the location is the same, a negative value, or the period
   *                                  of ticks is not at the current time or in the future.
   */
  void move(double newX, double newY, int timeStart, int timeEnd);

  /**
   * Returns the shapeType of the shape as a ShapeType enum.
   * @return shapeType as an enum.
   */
  AbstractShape.ShapeType getShapeType();


}