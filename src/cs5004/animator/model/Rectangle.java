package cs5004.animator.model;


import java.util.ArrayList;

/**
 * This class represents a rectangle.  It defines all the operations mandated by the
 * cs5004.animator.model.Shape interface
 */
public class Rectangle extends AbstractShape {
  protected final double width;
  protected final double height;

  /**
   * Constructs a rectangle object with the given location of it's upper-left corner and dimensions.
   * An IllegalArgumentException is thrown for width or height values less than zero.
   *
   * @param width            width of this rectangle
   * @param height           height of this rectangle
   * @param startXCoordinate The x coordinate the object will originally be located.
   * @param startYCoordinate The y coordinate the object will originally be located.
   * @param red              Red value for color of shape.
   * @param green            Green value for color of shape.
   * @param blue             Blue value for color of shape.
   * @param timeAppears      The time the object is set to appear on the display.
   * @param timeDisappears   The time the object is set to disappear on the display.
   * @param name             string shape of the shape
   * @throws IllegalArgumentException for width or height less than zero
   */
  public Rectangle(double width, double height, double startXCoordinate, double startYCoordinate,
                   int red, int green, int blue, int timeAppears, int timeDisappears,
                   String name) {
    super(startXCoordinate, startYCoordinate, red, green, blue, timeAppears, timeDisappears, name);
    if (width < 0 || height < 0) {
      throw new IllegalArgumentException("Width and height must be positive!");
    }
    this.width = width;
    this.height = height;
    this.shapeType = ShapeType.RECTANGLE;
    this.transformationList = new ArrayList<>();
  }

  /**
   * Returns the width of the rectangle.
   *
   * @return width
   */
  public double getWidth() {
    return this.width;
  }

  /**
   * Returns the height of the rectangle.
   *
   * @return height
   */
  public double getHeight() {
    return this.height;
  }

  /**
   * Transforms the rectangle by giving it new width and/or height values. An
   * IllegalArgumentException is thrown if the width or height is less than zero or if both values
   * are the same as the original rectangle.
   *
   * @param newWidth  new width of rectangle
   * @param newHeight new height of rectangle
   * @param timeStart the time when the size transformation begins.
   * @param timeEnd   the time when the size transformation ends.
   * @throws IllegalArgumentException if width and height equal original values or either is less
   *                                  than zero.
   * @returns transformation object.
   */
  public Transformation changeSize(double newWidth, double newHeight, int timeStart, int timeEnd) {
    if (newHeight == this.getHeight() && newWidth == this.getWidth()
            && this.getDisappearance() == timeEnd) {
      throw new IllegalArgumentException("Height and Width of transformation cannot be "
              + "the same as original shape!");
    }
    Transformation sizeTransformation = new Transformation(this, TransformationType.SIZE,
            newHeight, newWidth, null, null, new Ticker(timeStart, timeEnd),
            null, null, null);

    this.transformationList.add(sizeTransformation);
    return sizeTransformation;
  }

  /**
   * Creates a string representation of the rectangle.
   *
   * @return string representation.
   */
  public String toString() {
    return String.format("Name: %s\nType: %s\nMin corner: (%.1f,%.1f) Width: %.1f, Height: "
                    + "%.1f,\n" + "Color: %s\nAppears at t=%s\nDisappears at t=%s",
            this.name, this.getShapeType(), this.reference.getX(), this.reference.getY(),
            this.getWidth(),
            this.getHeight(),
            this.color.toString(), this.time.getRangeStart(),
            this.time.getRangeEnd());
  }
}