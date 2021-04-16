package cs5004.animator.model;

import java.util.ArrayList;

/**
 * This class represents a circle.  It offers all the operations mandated by the
 * cs5004.animator.model.Shape interface.
 */
public class Circle extends AbstractShape {
  protected final double radius;

  /**
   * Constructs a cs5004.animator.model. Circle with a given center, radius, color, time of
   * appearance and disappearance, shape, and shapeType. An IllegalArgumentException is thrown for
   * radius size less than zero.
   *
   * @param radius           the radius of the circle
   * @param startXCoordinate The x coordinate the object will originally be located.
   * @param startYCoordinate The y coordinate the object will originally be located.
   * @param red              Red value for color of shape.
   * @param green            Green value for color of shape.
   * @param blue             Blue value for color of shape.
   * @param timeAppears      The time the object is set to appear on the display.
   * @param timeDisappears   The time the object is set to disappear on the display.
   * @param name             string shape of the shape
   * @throws IllegalArgumentException for radius less than zero
   */
  public Circle(double radius, double startXCoordinate, double startYCoordinate, int red,
                int green, int blue,
                int timeAppears, int timeDisappears,
                String name) {
    super(startXCoordinate, startYCoordinate, red, green, blue, timeAppears, timeDisappears, name);
    if (radius < 0) {
      throw new IllegalArgumentException("Radius can't be negative size!");
    }
    this.radius = radius;
    super.shapeType = ShapeType.CIRCLE;
    this.transformationList = new ArrayList<>();
  }

  /**
   * Returns the radius of the circle.
   *
   * @return radius
   */
  public double getRadius() {
    return this.radius;
  }

  /**
   * Creates a transformation that changes the circle's size. An IllegalArgumentException is thrown
   * if the radius is equal to the original value or if it's less than zero.
   *
   * @param newRadius new radius value.
   * @param timeStart Start interval of the transformation.
   * @param timeEnd   End interval of the transformation.
   * @throws IllegalArgumentException if the radius is equal to the original value or if it's less
   *                                  than zero
   * @returns transformation object.
   */
  public Transformation changeSize(double newRadius, int timeStart, int timeEnd) {
    if (newRadius < 0 || this.radius == newRadius) {
      throw new IllegalArgumentException("Radius must be positive and different value than original"
              + "radius size!");
    }
    Transformation sizeTransformation = new Transformation(this, TransformationType.SIZE,
            null, null, new Ticker(timeStart, timeEnd), null,
            null, null, newRadius);

    this.transformationList.add(sizeTransformation);
    return sizeTransformation;
  }

  @Override
  public void copy() {

  }

  @Override
  public Transformation changeColor(int newRed, int newGreen, int newBlue, int timeStart, int timeEnd) {
    if (this.copy.getRed() == newRed && this.copy.getGreen() == newGreen
            && this.copy.getBlue() == newBlue || newRed < 0 || newGreen < 0 || newBlue < 0) {
      throw new IllegalArgumentException("Color values can't be less than zero or all the same as"
              + "original values!");
    }
    Transformation colorTransformation = new Transformation(this, TransformationType.COLOR,
            0,0,0,0,new Point2D(0,0),
            this.copy.getRed(), this.copy.getGreen(), this.copy.getBlue(), newRed, newGreen,
            newBlue, timeStart, timeEnd);
    this.copy.setColor(newRed, newGreen, newBlue);
    this.copy.setTimeAppears(timeStart);
    this.copy.setTimeDisappears(timeEnd);
    this.transformationList.add(colorTransformation);
    return colorTransformation;
  }

  @Override
  public Transformation move(double newX, double newY, int timeStart, int timeEnd) {
    Transformation moveTransformation = new Transformation(this, TransformationType.MOVE,
            this.copy.getX(), this.copy.getY(),newX, newY, this.copy.getRed(), this.copy.getGreen(),
            this.copy.getBlue(), 0,0, 0, 0, timeStart, timeEnd);
    this.copy.setReference(newX, newY);
    this.copy.setTimeAppears(timeStart);
    this.copy.setTimeDisappears(timeEnd);
    this.transformationList.add(moveTransformation);
    return moveTransformation;
  }

  /**
   * Creates a string representation of the circle.
   *
   * @return string representation
   */
  public String toString() {
    return String.format("Name: %s\nType: circle\nCenter (%.1f,%.1f), radius: %.1f\n"
                    + "Color: %s\nAppears at t=%s\nDisappears at t=%s", this.name,
            this.reference.getX(), this.reference.getY(), this.radius,
            this.color.toString(), this.time.getRangeStart(), this.time.getRangeEnd());
  }

}