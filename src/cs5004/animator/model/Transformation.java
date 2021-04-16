package cs5004.animator.model;

import java.awt.*;

/**
 * Class used to represent transformation of a specific shape.
 */
public class Transformation {
  protected Shape shape;
  protected TransformationType type;
  protected double newWidth;
  protected double newHeight;
  protected double radiusX;
  protected double radiusY;
  protected double radius;
  protected Point2D startLocation;
  protected Point2D endLocation;
  protected Color startColor;
  protected Color endColor;
  protected Ticker colorChangePeriod;
  protected Ticker sizeChangePeriod;
  protected Ticker locationChangePeriod;

  /**
   * Constructs a transformation for rectangle. Rectangle has new height and/or width values.
   *
   * @param shape             the shape being transformed.
   * @param type              the type of transformation as an Enum.
   * @param newHeight         the new height for the rectangle.
   * @param newWidth          the new width for the rectangle.
   * @param startLocation     beginning transition coordinate.
   * @param endLocation       end of transition coordinate.
   * @param sizeChangePeriod  denotes time size transformation happens.
   * @param startColor        the starting color of shape.
   * @param endColor          ending color of shape.
   * @param colorChangePeriod denotes time color transformation happens.
   */
  public Transformation(Shape shape, TransformationType type, double newHeight, double newWidth,
                        Point2D startLocation, Point2D endLocation, Ticker sizeChangePeriod,
                        Color startColor, Color endColor, Ticker colorChangePeriod) {
    if (newWidth < 0 || newHeight < 0) {
      throw new IllegalArgumentException("Width and height must be positive and not the same as"
              + "original values!");
    }
    this.shape = shape;
    this.type = type;
    this.newHeight = newHeight;
    this.newWidth = newWidth;
    this.startLocation = startLocation;
    this.endLocation = endLocation;
    this.startColor = startColor;
    this.endColor = endColor;
    this.sizeChangePeriod = sizeChangePeriod;
    this.colorChangePeriod = colorChangePeriod;
  }

  /**
   * Transform Constructor for Oval Transformation. The oval has new radiusX and/or radiusY values.
   *
   * @param shape             the shape being transformed.
   * @param type              the type of transformation as an Enum.
   * @param startLocation     beginning transition coordinate.
   * @param endLocation       end of transition coordinate.
   * @param sizeChangePeriod  denotes time size transformation happens.
   * @param startColor        the starting color of shape.
   * @param endColor          ending color of shape.
   * @param colorChangePeriod denotes time color transformation happens.
   * @param radiusX           new value for the x radius.
   * @param radiusY           new value for the y radius.
   */
  public Transformation(Shape shape, TransformationType type, Point2D startLocation,
                        Point2D endLocation,
                        Ticker sizeChangePeriod, Color startColor, Color endColor,
                        Ticker colorChangePeriod, double radiusX, double radiusY) {
    if (radiusX < 0 || radiusY < 0 || radiusX == radiusY) {
      throw new IllegalArgumentException("RadiusX and radiusY must be positive and not the "
              + "same as" + "original values!");
    }
    this.shape = shape;
    this.type = type;
    this.radiusX = radiusX;
    this.radiusY = radiusY;
    this.startLocation = startLocation;
    this.endLocation = endLocation;
    this.startColor = startColor;
    this.endColor = endColor;
    this.sizeChangePeriod = sizeChangePeriod;
    this.colorChangePeriod = colorChangePeriod;
  }

  /**
   * Transform Constructor for Circle Transformation. The circle has a new value for the radius
   * field.
   *
   * @param shape             the shape being transformed.
   * @param type              the type of transformation as an Enum.
   * @param startLocation     beginning transition coordinate.
   * @param endLocation       end of transition coordinate.
   * @param sizeChangePeriod  denotes time size transformation happens.
   * @param startColor        the starting color of shape.
   * @param endColor          ending color of shape.
   * @param colorChangePeriod denotes time color transformation happens.
   * @param radius            new value for the radius.
   */
  public Transformation(Shape shape, TransformationType type, Point2D startLocation,
                        Point2D endLocation,
                        Ticker sizeChangePeriod, Color startColor, Color endColor,
                        Ticker colorChangePeriod, double radius) {
    if (radius < 0) {
      throw new IllegalArgumentException("Radius must be positive and not the same as"
              + "original value!");
    }
    this.shape = shape;
    this.type = type;
    this.radius = radius;
    this.startLocation = startLocation;
    this.endLocation = endLocation;
    this.startColor = startColor;
    this.endColor = endColor;
    this.sizeChangePeriod = sizeChangePeriod;
    this.colorChangePeriod = colorChangePeriod;
  }

  /**
   * Constructor for a color transformation.
   *
   * @param shape     the shape being transformed.
   * @param type      the type of transformation as an Enum.
   * @param red       red value of the new color.
   * @param green     green value of the new color.
   * @param blue      blue value of the new color.
   * @param timeStart beginning time interval of transformation.
   * @param timeEnd   end time interval of transformation.
   */
  public Transformation(Shape shape, TransformationType type, int red, int green, int blue,
                        int timeStart, int timeEnd) {
    this.shape = shape;
    this.type = type;
    this.endColor = new Color(red, green, blue);
    this.colorChangePeriod = new Ticker(timeStart, timeEnd);
  }

  /**
   * Constructor for a Move that takes in a new x and y coordinate along with a start and end time.
   *
   * @param shape     the shape being transformed.
   * @param type      the type of transformation as an Enum.
   * @param newX      the new x coordinate of the shape.
   * @param newY      the new Y coordinate of the shape.
   * @param timeStart beginning time interval of transformation.
   * @param timeEnd   end time interval of transformation.
   */
  public Transformation(Shape shape, TransformationType type, double newX, double newY,
                        int timeStart, int timeEnd) {
    this.shape = shape;
    this.type = type;
    this.endLocation = new Point2D(newX, newY);
    this.locationChangePeriod = new Ticker(timeStart, timeEnd);
  }

  /**
   * Gets the start x coordinate point where the shape's transformation begins.
   *
   * @return x coordinate x coordinate of shape at start of transformation.
   */
  public double getStartXCoordinate() {
    return this.startLocation.x;
  }

  /**
   * Gets the start y coordinate point where the shape's transformation begins.
   *
   * @return y coordinate y coordinate of shape at end of transformation.
   */
  public double getStartYCoordinate() {
    return this.startLocation.y;
  }

  /**
   * Gets the end x coordinate point for the shape's transformation.
   *
   * @return x coordinate x coordinate of shape at end of transformation.
   */
  public double getEndXCoordinate() {
    return this.endLocation.x;
  }

  /**
   * Gets the end y coordinate point for the shape's transformation.
   *
   * @return y coordinate y coordinate of shape at end of transformation.
   */
  public double getEndYCoordinate() {
    return this.endLocation.y;
  }

  /**
   * Gets the start time of the transformation change.
   *
   * @return start time of the transformation
   */
  public int getStartTime() {
    if (this.sizeChangePeriod == null && this.colorChangePeriod == null)  {
      return this.locationChangePeriod.getRangeStart();
    } else if (this.sizeChangePeriod == null && this.locationChangePeriod == null) {
      return this.colorChangePeriod.getRangeStart();
    } else {
      return sizeChangePeriod.getRangeStart();
    }
  }

  /**
   * Gets the end time of the transformation change.
   *
   * @return end time of the transformation change
   */
  public int getEndTime() {
    if (this.sizeChangePeriod == null && this.colorChangePeriod == null)  {
      return this.locationChangePeriod.getRangeEnd();
    } else if (this.sizeChangePeriod == null && this.locationChangePeriod == null) {
      return this.colorChangePeriod.getRangeEnd();
    } else {
      return sizeChangePeriod.getRangeEnd();
    }
  }

  /**
   * Gets the start color of the shape.
   *
   * @return color of the shape to start, all three rbg values
   */
  public Color getStartColor() {
    return this.startColor;
  }

  /**
   * Gets the end color of the shape.
   *
   * @return color the shape ends with, all three rbg values.
   */
  public Color getEndColor() {
    return this.endColor;
  }

  /**
   * Gets the start time of the color change.
   *
   * @return start time of the color transformation.
   */
  public int getColorStartTime() {
    return this.colorChangePeriod.getRangeStart();
  }

  /**
   * Gets the end time of the color change.
   *
   * @return end time of the color transformation.
   */
  public int getColorEndTime() {
    return this.colorChangePeriod.getRangeEnd();
  }


  /**
   * Gets the new width.
   * @return new width
   */
  public double getNewWidth() { return this.newWidth; }

  /**
   * Gets the new height.
   * @return new height
   */
  public double getNewHeight() { return this.newHeight; }

  /**
   * Returns the radius X of an oval.
   * @return radius x
   */
  public double getRadiusX() { return this.radiusX; }

  /**
   * Returns the radius Y of an oval.
   * @return radius y
   */
  public double getRadiusY() { return this.radiusY; }

  /**
   * Returns the point2D start location.
   * @return Start location
   */
  public Point2D getStartLocation() { return this.startLocation; }

  /**
   * Creates a string version of the transformation.
   *
   * @return string description of transformation.
   */
  @Override
  public String toString() {
    String string = "";
    if (this.type == TransformationType.MOVE) {
      string = String.format("Shape %s moves from (%.1f,%.1f) to (%.1f,%.1f) from t=%d to t=%d\n",
              this.shape.getName(), this.shape.getX(), this.shape.getY(),
              this.getEndXCoordinate(), this.getEndYCoordinate(),
              this.locationChangePeriod.getRangeStart(), this.locationChangePeriod.getRangeEnd());
    }

    if (this.type == TransformationType.COLOR) {
      string = string + String.format("Shape %s changes color from (%d, %d, %d) to "
                      + "(%d, %d, %d) from t=%d to t=%d\n",
              this.shape.getName(), this.shape.getRed(), this.shape.getGreen(),
              this.shape.getBlue(), endColor.red, endColor.green, endColor.blue,
              this.colorChangePeriod.getRangeStart(), this.colorChangePeriod.getRangeEnd());
    }

    if (this.type == TransformationType.SIZE
            && this.shape.getShapeType() == AbstractShape.ShapeType.RECTANGLE) {
      Rectangle shape = (Rectangle)this.shape;
      string = string + String.format("Shape %s scales from Width: %.1f, Height: %.1f to "
                      + "Width: %.1f, Height: %.1f "
                      + "from t=%d to t=%d\n", this.shape.getName(),shape.getWidth(),
              shape.getHeight(), newWidth, newHeight, sizeChangePeriod.getRangeStart(),
              sizeChangePeriod.getRangeEnd());
    }

    if (this.type == TransformationType.SIZE
            && this.shape.getShapeType() == AbstractShape.ShapeType.OVAL) {
      Oval shape = (Oval)this.shape;
      string = string + String.format("Shape %s scales from RadiusX: %.1f, RadiusY: %.1f to "
                      + "RadiusX: %.1f, RadiusY %.1f from t=%d to t=%d\n", this.shape.getName(),
              shape.radiusX, shape.radiusY, radiusX, radiusY, sizeChangePeriod.getRangeStart(),
              sizeChangePeriod.getRangeEnd());
    }

    if (this.type == TransformationType.SIZE
            && this.shape.getShapeType() == AbstractShape.ShapeType.CIRCLE) {
      Circle shape = (Circle)this.shape;
      string = string + String.format("Shape %s scales from Radius: %.1f to "
                      + "Radius: %.1f from t=%d to t=%d\n", this.shape.getName(),
              shape.radius, radius, sizeChangePeriod.getRangeStart(),
              sizeChangePeriod.getRangeEnd());
    }
    return string;
  }
}
