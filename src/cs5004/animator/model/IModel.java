package cs5004.animator.model;

import java.util.ArrayList;
import java.util.NoSuchElementException;

/**
 * This represents a cs5004.animator.model for the animation and its required methods.
 */
public interface IModel {

  /**
   * Returns the offset values for all x and y coordinates.
   *
   * @returns offset values in Point2D form
   */
  Point2D getOffset();


  /**
   * Returns the width of the model.
   *
   * @returns width
   */
  double getWidth();

  /**
   * Returns the height of the model.
   *
   * @returns height
   */
  double getHeight();

  /**
   * Adds a given shape to the animation. The shape is in the animation for a set amount of time.
   * Throws an IllegalArgumentException if the shape already exists.
   *
   * @param object Shape to be added to animation.
   * @throws IllegalArgumentException if the shape is already in the animation.
   */
  void addShape(Shape object);

  /**
   * Removes a given shape. Throws an IllegalArgumentException if the shape is not in the
   * animation.
   *
   * @param object the object to be removed from teh animation
   * @throws NoSuchElementException if the shape does not exist in the animation
   */
  void removeShape(Shape object);

  /**
   * Returns specific shape from the animation. An IllegalArgumentException is thrown if the shape
   * is not in the animation.
   *
   * @param object the cs5004.animator.model.Shape to be returned from the animation
   * @throws NoSuchElementException the shape does not exist in the animation
   */
  Shape getSpecificShape(Shape object);

  /**
   * Changes the color of the shape by adjusting the integer values of red, green and blue. Throws
   * an IllegalArgumentException when the integer values are less than zero or greater than 255.
   * Throws NoSuchElementException if shape is not in the animation.
   *
   * @param shape     the shape whose color is changing.
   * @param red       the int value of the red color.
   * @param green     the int value of the green color.
   * @param blue      the int value of the blue color.
   * @param timeStart the int start time value of when the color changes.
   * @param timeEnd   the int end time value of when the color ends the color change.
   * @throws IllegalArgumentException when colors are less than zero or greater than 255.
   * @throws NoSuchElementException   if shape is not in animation.
   */
  void addColorTransformation(Shape shape, int red, int green, int blue,
                              int timeStart, int timeEnd);

  /**
   * Changes the shapes newLocation to the given value over a period of ticks. This allows the shape
   * to move from one location to another. An IllegalArgumentException is thrown if the location is
   * the same, a negative value, or the period of ticks is not at the current time or in the future.
   * Throws NoSuchElementException if shape is not in the animation.
   *
   * @param shape     the shape that moves.
   * @param newX      the x coordinate of the location the shape will move to
   * @param newY      the y coordinate of the location the shape will move to
   * @param timeStart time start interval of the move transformation.
   * @param timeEnd   time end interval of the move transformation.
   * @throws NoSuchElementException if shape is not in animation.
   */
  void addMoveTransformation(Shape shape, double newX, double newY, int timeStart, int timeEnd);

  /**
   * Creates a transformation that changes the Oval's size. An IllegalArgumentException is thrown if
   * the radius is equal to the original value or if it's less than zero. Throws
   * NoSuchElementException if shape is not in the animation.
   *
   * @param rectangle the oval that changes size.
   * @param newWidth  new width value.
   * @param newHeight new height value.
   * @param timeStart Start interval of the transformation.
   * @param timeEnd   End interval of the transformation.
   * @throws IllegalArgumentException if the width or height are equal to the original value or if
   *                                  it's less than zero.
   * @throws NoSuchElementException   if shape is not in animation.
   */
  void addRectangleSizeTransformation(Rectangle rectangle, double newWidth, double newHeight,
                                      int timeStart, int timeEnd);

  /**
   * Creates a transformation that changes the Oval's size. An IllegalArgumentException is thrown if
   * the radius is equal to the original value or if it's less than zero. Throws
   * NoSuchElementException if shape is not in the animation.
   *
   * @param oval       the oval that changes size.
   * @param newRadiusX new radiusX value.
   * @param newRadiusY new radiusY value.
   * @param timeStart  Start interval of the transformation.
   * @param timeEnd    End interval of the transformation.
   * @throws IllegalArgumentException if the radius is equal to the original value or if it's less
   *                                  than zero.
   * @throws NoSuchElementException   if shape is not in animation.
   */
  void addOvalSizeTransformation(Oval oval, double newRadiusX, double newRadiusY, int timeStart,
                                 int timeEnd);

  /**
   * Creates an oval that does not move, simply exists.
   *
   * @param oval      the oval that exists.
   * @param timeStart the start time.
   * @param timeEnd   the end time.
   * @throws IllegalArgumentException if the times are less than zero.
   * @throws NoSuchElementException   if the shape is not in the animation.
   */
  void addStaticOvalTransformation(Oval oval, int timeStart, int timeEnd);

  /**
   * Creates a rectangle that does not move, simply exists.
   *
   * @param rect      the oval that exists.
   * @param timeStart the start time.
   * @param timeEnd   the end time.
   * @throws IllegalArgumentException if the times are less than zero.
   * @throws NoSuchElementException   if the shape is not in the animation.
   */
  void addStaticRectangleTransformation(Rectangle rect, int timeStart, int timeEnd);

  /**
   * Sorts the list of shapes by time.
   *
   * @param comp the time comparison, earliest to latest.
   */
  void sort(ShapeTimeComparator comp);

  /**
   * Sorts the list of transformations by time.
   *
   * @param comp the time comparison, earliest to latest.
   */
  void sortTransformations(TransformationTimeComparator comp);

  /**
   * Returns a string description of the animation.
   */
  String toString();

  /**
   * Returns the end time of the transformation.
   *
   * @returns end time
   */
  int getMax();

  /**
   * Returns a collection of copy of shapes at specific time.
   *
   * @param ticker instance of time.
   */
  ArrayList<Shape> getShapesAtTicker(int ticker);


  /**
   * Sets the bounds of the animation canvas.
   *
   * @param x      the x coordinate of the topleft corner.
   * @param y      the y coordinate of the topleft corner.
   * @param width  the width of the convas.
   * @param height the height of the canvas.
   */
  void setBounds(int x, int y, int width, int height);
}
