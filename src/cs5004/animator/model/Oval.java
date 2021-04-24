package cs5004.animator.model;

import java.util.ArrayList;

/**
 * Class used to represent Oval shape.
 */
public class Oval extends AbstractShape {
  protected double radiusX;
  protected double radiusY;
  protected Oval copy;

  /**
   * Constructor for an Oval shape. An IllegalArgumentException is thrown for radiusX and radiusY
   * values less than zero or equal to each other.
   *
   * @param radiusX          size of X radius.
   * @param radiusY          size of y radius.
   * @param startXCoordinate The x coordinate the object will originally be located.
   * @param startYCoordinate The y coordinate the object will originally be located.
   * @param red              Red value for color of shape.
   * @param green            Green value for color of shape.
   * @param blue             Blue value for color of shape.
   * @param timeAppears      The time the object is set to appear on the display.
   * @param timeDisappears   The time the object is set to disappear on the display.
   * @param name             string shape of the shape.
   * @throws IllegalArgumentException if radiusX or radiusY are less than or equal to zero.
   */
  public Oval(double radiusX, double radiusY, double startXCoordinate, double startYCoordinate,
              int red, int green, int blue, int timeAppears, int timeDisappears, String name) {
    super(startXCoordinate, startYCoordinate, red, green, blue, timeAppears, timeDisappears, name);
    if (radiusX <= 0 || radiusY <= 0) {
      throw new IllegalArgumentException("Radius cant be less than or equal to zero.");
    }
    this.radiusX = radiusX;
    this.radiusY = radiusY;
    this.shapeType = ShapeType.OVAL;
    this.transformationList = new ArrayList<>();

  }

  @Override
  public void copy() {
    this.copy = new Oval(radiusX, radiusY, this.getX(), this.getY(), this.getRed(), this.getGreen(),
            this.getBlue(), timeAppears, timeDisappears, name);
  }

  /**
   * Sets the radiusX for the oval.
   *
   * @param radiusX the radiusX of the oval
   */
  public void setRadiusX(double radiusX) {
    this.radiusX = radiusX;
  }

  /**
   * Sets the radiusY for the oval.
   *
   * @param radiusY the radiusX of the oval
   */
  public void setRadiusY(double radiusY) {
    this.radiusY = radiusY;
  }

  /**
   * Creates a transformation that changes the Oval's size. An IllegalArgumentException is thrown if
   * the radius is equal to the original value or if it's less than zero.
   *
   * @param newRadiusX new radiusX value.
   * @param newRadiusY new radiusY value.
   * @param timeStart  Start interval of the transformation.
   * @param timeEnd    End interval of the transformation.
   * @throws IllegalArgumentException if the radius is equal to the original value or if it's less
   *                                  than zero
   * @returns transformation object.
   */
  public Transformation changeSize(double newRadiusX, double newRadiusY, int timeStart,
                                   int timeEnd) {
    if (newRadiusX < 0 || newRadiusY < 0 || (this.copy.radiusY == newRadiusY
            && this.copy.radiusX == newRadiusX)) {
      throw new IllegalArgumentException("RadiusX and radiusY must be positive and not the same as"
              + "original values!");
    }
    this.copy.setRadiusX(radiusX);
    this.copy.setRadiusY(radiusY);
    this.copy.setTimeAppears(timeStart);
    this.copy.setTimeDisappears(timeEnd);
    Transformation sizeTransformation = new Transformation(this, TransformationType.SIZE,
            new Ticker(timeStart, timeEnd), this.copy.radiusX, this.copy.radiusY,
            newRadiusX, newRadiusY, new Point2D(this.copy.getX(), this.copy.getY()),
            new Color(this.copy.getRed(), this.copy.getGreen(), this.copy.getBlue()));
    this.transformationList.add(sizeTransformation);
    return sizeTransformation;
  }

  @Override
  public Transformation changeColor(int newRed, int newGreen, int newBlue, int timeStart,
                                    int timeEnd) {
    if (this.copy.getRed() == newRed && this.copy.getGreen() == newGreen
            && this.copy.getBlue() == newBlue || newRed < 0 || newGreen < 0 || newBlue < 0) {
      throw new IllegalArgumentException("Color values can't be less than zero or all the same as"
              + "original values!");
    }
    this.copy.setColor(newRed, newGreen, newBlue);
    this.copy.setTimeAppears(timeStart);
    this.copy.setTimeDisappears(timeEnd);
    Transformation colorTransformation = new Transformation(this, TransformationType.COLOR,
            this.copy.getRX(), this.copy.getRY(), 0, 0, new Point2D(this.copy.getX(),
            this.copy.getY()), this.copy.getRed(), this.copy.getGreen(), this.copy.getBlue(),
            newRed, newGreen,
            newBlue, timeStart, timeEnd);
    this.transformationList.add(colorTransformation);
    return colorTransformation;
  }

  @Override
  public Transformation move(double newX, double newY, int timeStart, int timeEnd) {

    this.copy.setReference(newX, newY);
    this.copy.setTimeAppears(timeStart);
    this.copy.setTimeDisappears(timeEnd);
    Transformation moveTransformation = new Transformation(this, TransformationType.MOVE,
            this.copy.getX(), this.copy.getY(), newX, newY, this.copy.getRed(), this.copy.getGreen(),
            this.copy.getBlue(), this.copy.getRX(), this.copy.getRY(), 0, 0, timeStart,
            timeEnd);
    this.transformationList.add(moveTransformation);
    return moveTransformation;
  }

  /**
   * Allows the oval to stay in one position.
   *
   * @param timeStart beginning time interval of transformation.
   * @param timeEnd   end time interval of transformation.
   * @throws IllegalArgumentException if any of the fields except the times are the same.
   */
  public Transformation staticShape(int timeStart, int timeEnd) {
    Transformation staticTransformation = new Transformation(this, this.copy.getX(),
            this.copy.getY(), this.copy.getRed(), this.copy.getGreen(), this.copy.getBlue(),
            this.copy.getRX(), this.copy.getRY(), 0, 0, timeStart, timeEnd);
    this.copy.setTimeAppears(timeStart);
    this.copy.setTimeDisappears(timeEnd);
    this.transformationList.add(staticTransformation);
    return staticTransformation;
  }

  /**
   * Returns radius X.
   *
   * @return radius x
   */
  public double getRX() {
    return this.radiusX;
  }

  /**
   * Returns radius Y.
   *
   * @return radius Y
   */
  public double getRY() {
    return this.radiusY;
  }

  @Override
  public String toString() {
    return String.format("Name: %s\nType: oval\nCenter (%.1f,%.1f), X radius: %.1f, "
                    + "Y radius: %.1f" + "\n" + "Color: %s\nAppears at t=%s\nDisappears at t=%s",
            this.name, this.reference.getX(), this.reference.getY(), this.radiusX, this.radiusY,
            this.color.toString(), this.time.getRangeStart(), this.time.getRangeEnd());
  }

}
