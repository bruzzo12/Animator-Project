package cs5004.animator.model;

import java.util.ArrayList;

/**
 * Abstract Class for cs5004.animator.model.Shape Class.
 */
public abstract class AbstractShape implements Shape {
  protected Point2D reference;
  protected Color color;
  protected int timeAppears;
  protected int timeDisappears;
  protected Ticker time;
  protected String name;
  protected ShapeType shapeType;
  protected ArrayList<Transformation> transformationList;
  protected Shape copy;

  /**
   * Enum class used to identify Shape type for concrete instances of AbstractShape.
   */
  public enum ShapeType {
    CIRCLE, RECTANGLE, OVAL;

    /**
     * Method for enum class used to represent enum as a string.
     *
     * @return String representation of ShapeType.
     */
    @Override
    public String toString() {
      String symbol;
      switch (this) {
        case RECTANGLE:
          symbol = "Rectangle";
          break;
        case OVAL:
          symbol = "Oval";
          break;
        default:
          throw new IllegalArgumentException("Shape Type is invalid");
      }
      return symbol;
    }
  }

  /**
   * Constructs an Abstract shape with a given reference point, color, time, shape.
   *
   * @param startXCoordinate The x coordinate the object will originally be located.
   * @param startYCoordinate The y coordinate the object will originally be located.
   * @param red              Red value for color of shape.
   * @param green            Green value for color of shape.
   * @param blue             Blue value for color of shape.
   * @param timeAppears      The time the object is set to appear on the display.
   * @param timeDisappears   The time the object is set to disappear on the display.
   * @param name             string shape of the shape
   */
  public AbstractShape(double startXCoordinate, double startYCoordinate,
                       int red, int green, int blue, int timeAppears, int timeDisappears,
                       String name) {
    this.reference = new Point2D(startXCoordinate, startYCoordinate);
    this.color = new Color(red, green, blue);
    this.timeAppears = timeAppears;
    this.timeDisappears = timeDisappears;
    this.time = new Ticker(timeAppears, timeDisappears);
    this.name = name;
  }

  @Override
  public Shape getCopy() {
    return this.copy;
  }

  @Override
  public double getX() {
    return this.reference.x;
  }

  @Override
  public double getY() {
    return this.reference.y;
  }

  @Override
  public int getRed() {
    return this.color.red;
  }

  @Override
  public int getGreen() {
    return this.color.green;
  }

  @Override
  public int getBlue() {
    return this.color.blue;
  }

  @Override
  public int getAppearance() {
    return this.time.getRangeStart();
  }

  @Override
  public int getDisappearance() {
    return this.time.getRangeEnd();
  }

  @Override
  public void setDisappearance(int newEnd) {
    this.time = new Ticker(timeAppears, newEnd);
  }

  @Override
  public String getName() {
    return this.name;
  }

  @Override
  public void setReference(double x, double y) {
    this.reference = new Point2D(x, y);
  }

  @Override
  public void setTimeAppears(int appears) {
    this.timeAppears = appears;
  }

  @Override
  public void setTimeDisappears(int disappears) {
    this.timeDisappears = disappears;
  }

  @Override
  public void setColor(int red, int green, int blue) {
    this.color = new Color(red, green, blue);
  }

  @Override
  public ShapeType getShapeType() {
    return this.shapeType;
  }

  @Override
  public ArrayList<Transformation> getTransformationList() {
    return this.transformationList;
  }

}