package cs5004.animator.model;

import java.util.ArrayList;

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
   *
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
   * Sets the reference point.
   *
   * @param x coordinate value
   * @param y coordinate value
   */
  void setReference(double x, double y);

  /**
   * Sets the time appears.
   *
   * @param appears time it appears
   */
  void setTimeAppears(int appears);

  /**
   * Sets the time disappears.
   *
   * @param disappears time it appears
   */
  void setTimeDisappears(int disappears);

  /**
   * Sets the color values.
   *
   * @param red   color value
   * @param green color value
   * @param blue  color value
   */
  void setColor(int red, int green, int blue);

  /**
   * Creates a copy of the original shape.
   */
  void copy();

  /**
   * Gets the copy of the shape.
   *
   * @return copy
   */
  Shape getCopy();

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
   * @returns transformation object.
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
   * @throws IllegalArgumentException if the location is the same, a negative value, or the period
   *                                  of ticks is not at the current time or in the future.
   * @returns transformation object.
   */
  Transformation move(double newX, double newY, int timeStart, int timeEnd);

  /**
   * Returns the shapeType of the shape as a ShapeType enum.
   *
   * @return shapeType as an enum.
   */
  AbstractShape.ShapeType getShapeType();

  /**
   * Returns the list of transformations.
   *
   * @return transformations as an ArrayList
   */
  ArrayList<Transformation> getTransformationList();

}