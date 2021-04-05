package model;

/**
 * Class used to represent Oval shape.
 */
public class Oval extends AbstractShape {
  double radiusX;
  double radiusY;

  /**
   * Constructor for an Oval shape.
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
   * @param name             string name of the shape
   */
  public Oval(double radiusX, double radiusY, double startXCoordinate, double startYCoordinate,
              int red, int green, int blue, int timeAppears, int timeDisappears, String name) {
    super(startXCoordinate, startYCoordinate, red, green, blue, timeAppears, timeDisappears, name);

    this.radiusX = radiusX;
    this.radiusY = radiusY;
    this.shapeType = ShapeType.OVAL;
  }

  @Override
  public int getRed() {
    return 0;
  }

  @Override
  public int getGreen() {
    return 0;
  }

  @Override
  public int getBlue() {
    return 0;
  }

  @Override
  public double area() {
    return radiusX * radiusY * Math.PI;
  }

  @Override
  public double perimeter() {
    return 2 * Math.PI * Math.sqrt((radiusX * radiusX + radiusY * radiusY) / 2);
  }

  /**
   * Creates a transformation that changes the Oval's size. An IllegalArgumentException is thrown if
   * the radius is equal to the original value or if it's less than zero.
   *
   * @param radiusX   new radiusX value.
   * @param radiusY   new radiusY value.
   * @param timeStart Start interval of the transformation.
   * @param timeEnd   End interval of the transformation.
   * @throws IllegalArgumentException if the radius is equal to the original value or if it's less
   *                                  than zero
   */

  public void changeSize(double radiusX, double radiusY, int timeStart, int timeEnd) {
    Transformation sizeTransformation = new Transformation(this.reference, null,
            new Ticker(timeStart, timeEnd), null, null,
            null, radiusX, radiusY);
    this.transformationList.add(sizeTransformation);
  }

  @Override
  public void changeColor(int red, int green, int blue, int timeStart, int timeEnd) {
    super.changeColor(red, green, blue, timeStart, timeEnd);
  }

  @Override
  public void move(double newX, double newY, int timeStart, int timeEnd) {
    super.move(newX, newY, timeStart, timeEnd);
  }

  @Override
  public int compareTo(Shape o) {
    return 0;
  }

  @Override
  public String toString() {
    return String.format("Type: oval\nCenter (%.3f,%.3f) X radius: %.3f, Y radius: %.3f\n",
            this.reference.getX(), this.reference.getY(), this.radiusX, this.radiusY);

  }

}