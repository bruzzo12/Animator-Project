package cs5004.animator.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.NoSuchElementException;

import cs5004.animator.model.AbstractShape.ShapeType;
import cs5004.animator.util.AnimationBuilder;

/**
 * This class represents the cs5004.animator.model for an animation with shapes. The class
 * implements all methods from the cs5004.animator.model.IModel interface.
 */
public final class IModelImpl implements IModel {
  protected ArrayList<Shape> shapes;
  protected ArrayList<Transformation> transformationList;
  protected int shapeCount;
  protected Point2D offset;
  protected int width;
  protected int height;

  /**
   * Constructs an animation cs5004.animator.model that starts with an empty animation screen.
   */
  public IModelImpl() {
    this.shapes = new ArrayList<>();
    this.transformationList = new ArrayList<>();
    this.shapeCount = 0;
    this.offset = new Point2D(0, 0);
  }

  @Override
  public Point2D getOffset() {
    return this.offset;
  }

  @Override
  public double getWidth() {
    return this.width;
  }

  @Override
  public double getHeight() {
    return this.height;
  }

  @Override
  public void addShape(Shape object) {
    if (shapes.contains(object)) {
      throw new IllegalArgumentException("This shape already exists.");
    }
    shapes.add(object);
    shapeCount++;
  }

  @Override
  public void removeShape(Shape object) {
    if (!shapes.contains(object)) {
      throw new NoSuchElementException("This shape is not in the animation.");
    }
    shapes.remove(object);
    shapeCount--;
  }

  @Override
  public Shape getSpecificShape(Shape object) {
    if (!shapes.contains(object)) {
      throw new NoSuchElementException("This shape is not in the animation.");
    }
    return object;
  }

  @Override
  public void addColorTransformation(Shape shape, int red, int green, int blue, int timeStart,
                                     int timeEnd) {
    if (red < 0 || green < 0 || blue < 0) {
      throw new IllegalArgumentException("Colors must be positive value!");
    }
    if (!shapes.contains(shape)) {
      throw new NoSuchElementException("Shape not in the animation, please add the shape first!");
    }
    transformationList.add(shape.changeColor(red, green, blue, timeStart, timeEnd));
  }

  @Override
  public void addMoveTransformation(Shape shape, double newX, double newY, int timeStart,
                                    int timeEnd) {
    if (!shapes.contains(shape)) {
      throw new NoSuchElementException("Shape not in the animation, please add the shape first!");
    }
    transformationList.add(shape.move(newX,
            newY, timeStart, timeEnd));
  }

  @Override
  public void addRectangleSizeTransformation(Rectangle rectangle, double newWidth, double newHeight,
                                             int timeStart, int timeEnd) {
    if (newWidth < 0 || newHeight < 0) {
      throw new IllegalArgumentException("Width and height must be positive numbers!");
    }
    if (!shapes.contains(rectangle)) {
      throw new NoSuchElementException("Shape not in the animation, please add the shape first!");
    }
    transformationList.add(rectangle.changeSize(newWidth, newHeight, timeStart, timeEnd));
  }

  @Override
  public void addOvalSizeTransformation(Oval oval, double newRadiusX, double newRadiusY,
                                        int timeStart, int timeEnd) {
    if (newRadiusX < 0 || newRadiusY < 0) {
      throw new IllegalArgumentException("newRadiusX and newRadiusY must be positive numbers!");
    }
    if (!shapes.contains(oval)) {
      throw new NoSuchElementException("Shape not in the animation, please add the shape first!");
    }
    transformationList.add(oval.changeSize(newRadiusX, newRadiusY, timeStart, timeEnd));
  }

  @Override
  public void addStaticOvalTransformation(Oval oval, int timeStart, int timeEnd) {
    if (timeStart < 0 || timeEnd < 0) {
      throw new IllegalArgumentException("newRadiusX and newRadiusY must be positive numbers!");
    }
    if (!shapes.contains(oval)) {
      throw new NoSuchElementException("Shape not in the animation, please add the shape first!");
    }
    transformationList.add(oval.staticShape(timeStart, timeEnd));
  }

  @Override
  public void addStaticRectangleTransformation(Rectangle rect, int timeStart, int timeEnd) {
    if (timeStart < 0 || timeEnd < 0) {
      throw new IllegalArgumentException("newRadiusX and newRadiusY must be positive numbers!");
    }
    if (!shapes.contains(rect)) {
      throw new NoSuchElementException("Shape not in the animation, please add the shape first!");
    }
    transformationList.add(rect.staticShape(timeStart, timeEnd));
  }

  @Override
  public void sort(ShapeTimeComparator comp) {
    Collections.sort(shapes, new ShapeTimeComparator());
  }

  @Override
  public void sortTransformations(TransformationTimeComparator comp) {
    Collections.sort(transformationList, new TransformationTimeComparator());
  }

  @Override
  public String toString() {
    Collections.sort(shapes, new ShapeTimeComparator());
    Collections.sort(transformationList, new TransformationTimeComparator());
    StringBuilder str = new StringBuilder("Shapes:");
    for (Shape object : shapes) {
      str.append("\n").append(object.toString()).append("\n");
    }
    str.append("\n");
    for (Transformation object : transformationList) {
      str.append(object.toString());
    }
    return str.toString();
  }


  public int getShapeCount() {
    return this.shapes.size();
  }

  @Override
  public int getMax() {
    Transformation max = Collections.max(transformationList, new EndTimeComparator());
    return max.getEndTime();
  }

  @Override
  public ArrayList<Shape> getShapesAtTicker(int ticker) {

    ArrayList<Shape> animation = new ArrayList<>();
    sort(new ShapeTimeComparator());
    sortTransformations(new TransformationTimeComparator());
    for (Shape shape : shapes) {
      if (shape.getAppearance() <= ticker && ticker <= shape.getDisappearance()) {
        for (Transformation t : shape.getTransformationList()) {
          if (t.getStartTime() <= ticker && ticker <= t.getEndTime()) {
            animation.add(tween(t, ticker));
          }
        }
        if (shapes.stream().allMatch(t -> t.getAppearance() > ticker)) {
          animation.add(shape);
        }
      }
    }
    return animation;
  }


  /**
   * Calculates the intermediate state of the shape being transformed at a specific tick.
   *
   * @param object the transformation object to be tweened
   * @param ticker the time at which the intermediate state is being calculated
   * @return copy of the shape with the intermediate state values
   */
  public Shape tween(Transformation object, int ticker) {
    if (object.type == TransformationType.MOVE
            && object.shape.getShapeType() == ShapeType.RECTANGLE) {
      double newX = (object.getStartXCoordinate() * ((object.getEndTime() - (double) ticker)
              / (object.getEndTime() - object.getStartTime()))) + (object.getEndXCoordinate()
              * (((double) ticker - object.getStartTime()) / (object.getEndTime()
              - object.getStartTime())));
      double newY = (object.getStartYCoordinate() * ((object.getEndTime() - (double) ticker)
              / (object.getEndTime() - object.getStartTime()))) + (object.getEndYCoordinate()
              * (((double) ticker - object.getStartTime()) / (object.getEndTime()
              - object.getStartTime())));
      Rectangle copy = new Rectangle(object.width, object.height, newX, newY,
              object.getStartColor().red, object.getStartColor().green,
              object.getStartColor().blue, ticker, ticker, object.shape.getName());
      return copy;
    }
    if (object.type == TransformationType.MOVE
            && object.shape.getShapeType() == ShapeType.OVAL) {
      double newX = (object.getStartXCoordinate() * ((object.getEndTime() - (double) ticker)
              / (object.getEndTime() - object.getStartTime()))) + (object.getEndXCoordinate()
              * (((double) ticker - object.getStartTime()) / (object.getEndTime()
              - object.getStartTime())));
      double newY = (object.getStartYCoordinate() * ((object.getEndTime() - (double) ticker)
              / (object.getEndTime() - object.getStartTime()))) + (object.getEndYCoordinate()
              * (((double) ticker - object.getStartTime()) / (object.getEndTime()
              - object.getStartTime())));
      Oval copy = new Oval(object.radiusX, object.radiusY, newX, newY, object.getStartColor().red,
              object.getStartColor().green, object.getStartColor().blue, ticker, ticker,
              object.shape.getName());
      return copy;
    }
    if (object.type == TransformationType.SIZE && object.shape.getShapeType() == ShapeType.OVAL) {
      double newRadiusX = (object.getRadiusX() * ((object.getEndTime() - (double) ticker)
              / (object.getEndTime() - object.getStartTime()))) + (object.newRadiusX)
              * (((double) ticker - object.getStartTime()) / (object.getEndTime()
              - object.getStartTime()));
      double newRadiusY = (object.getRadiusY() * ((object.getEndTime() - (double) ticker)
              / (object.getEndTime() - object.getStartTime()))) + (object.newRadiusY)
              * (((double) ticker - object.getStartTime()) / (object.getEndTime()
              - object.getStartTime()));
      Oval copy = new Oval(newRadiusX, newRadiusY, object.getStartXCoordinate(),
              object.getStartYCoordinate(), object.getStartColor().red,
              object.getStartColor().green, object.getStartColor().blue, ticker, ticker,
              object.shape.getName());
      return copy;
    }
    if (object.type == TransformationType.SIZE
            && object.shape.getShapeType() == ShapeType.RECTANGLE) {
      double newHeight = (object.height * ((object.getEndTime() - (double) ticker)
              / (object.getEndTime() - object.getStartTime()))) + (object.newHeight)
              * (((double) ticker - object.getStartTime()) / (object.getEndTime()
              - object.getStartTime()));
      double newWidth = (object.width * ((object.getEndTime() - (double) ticker)
              / (object.getEndTime() - object.getStartTime()))) + (object.newWidth)
              * (((double) ticker - object.getStartTime()) / (object.getEndTime()
              - object.getStartTime()));
      Rectangle copy = new Rectangle(newWidth, newHeight, object.getStartXCoordinate(),
              object.getStartYCoordinate(), object.getStartColor().red,
              object.getStartColor().green, object.getStartColor().blue, ticker, ticker,
              object.shape.getName());
      return copy;
    }
    if (object.type == TransformationType.COLOR
            && object.shape.getShapeType() == ShapeType.RECTANGLE) {
      int newRed = (object.startColor.red * ((object.getEndTime() - ticker)
              / (object.getEndTime() - object.getStartTime()))) + (object.endColor.red)
              * ((ticker - object.getStartTime()) / (object.getEndTime() - object.getStartTime()));
      int newGreen = (object.startColor.green * ((object.getEndTime() - ticker)
              / (object.getEndTime() - object.getStartTime()))) + (object.endColor.green)
              * ((ticker - object.getStartTime()) / (object.getEndTime() - object.getStartTime()));
      int newBlue = (object.startColor.blue * ((object.getEndTime() - ticker)
              / (object.getEndTime() - object.getStartTime()))) + (object.endColor.blue)
              * ((ticker - object.getStartTime()) / (object.getEndTime() - object.getStartTime()));
      Rectangle copy = new Rectangle(object.width, object.height, object.getStartXCoordinate(),
              object.getStartYCoordinate(), newRed, newGreen, newBlue, ticker, ticker,
              object.shape.getName());
      return copy;
    } else if (object.type == TransformationType.COLOR
            && object.shape.getShapeType() == ShapeType.OVAL) {
      int newRed = (object.startColor.red * ((object.getEndTime() - ticker)
              / (object.getEndTime() - object.getStartTime()))) + (object.endColor.red)
              * ((ticker - object.getStartTime()) / (object.getEndTime() - object.getStartTime()));
      int newGreen = (object.startColor.green * ((object.getEndTime() - ticker)
              / (object.getEndTime() - object.getStartTime()))) + (object.endColor.green)
              * ((ticker - object.getStartTime()) / (object.getEndTime() - object.getStartTime()));
      int newBlue = (object.startColor.blue * ((object.getEndTime() - ticker)
              / (object.getEndTime() - object.getStartTime()))) + (object.endColor.blue)
              * ((ticker - object.getStartTime()) / (object.getEndTime() - object.getStartTime()));
      Oval copy = new Oval(object.radiusX, object.radiusY, object.getStartXCoordinate(),
              object.getStartYCoordinate(), newRed, newGreen, newBlue, ticker, ticker,
              object.shape.getName());
      return copy;
    } else if (object.type == TransformationType.STATIC
            && object.shape.getShapeType() == ShapeType.OVAL) {
      Oval copy = new Oval(object.getRadiusX(), object.getRadiusY(), object.getStartXCoordinate(),
              object.getStartYCoordinate(), object.getStartColor().red,
              object.getStartColor().green, object.getStartColor().blue, ticker, ticker,
              object.shape.getName());
      return copy;
    } else {
      Rectangle copy = new Rectangle(object.width, object.height, object.getStartXCoordinate(),
              object.getStartYCoordinate(), object.getStartColor().red,
              object.getStartColor().green, object.getStartColor().blue, ticker, ticker,
              object.shape.getName());
      return copy;
    }

  }


  /**
   * Sets the bounds fields to be used for the animation panel.
   *
   * @param x      the x coordinate of the topleft corner.
   * @param y      the y coordinate of the topleft corner.
   * @param width  the width of the canvas.
   * @param height the height of the canvas.
   */
  public void setBounds(int x, int y, int width, int height) {
    this.offset = new Point2D((double) x, (double) y);
    this.width = width;
    this.height = height;
  }


  /**
   * This class represents a Declared Shape that contains a shape name and type. Once a shape with
   * the same name and type is instantiated by addMotion, a shape object is added to the
   * DeclaredShape object and field declared is set as true.
   */
  public static final class DeclaredShape {
    private String name;
    private String type;
    private Shape shape;
    private boolean declared;

    /**
     * Constructs a DeclaredShape object.
     *
     * @param name name of shape
     * @param type the type of shape
     */
    DeclaredShape(String name, String type) {
      this.name = name;
      this.type = type;
      this.declared = false;
    }

    public String getName() {
      return this.name;
    }

    public String getType() {
      return this.type;
    }

    /**
     * Adds a shape by setting declared to true.
     *
     * @param shape the shape to be declared
     */
    public void addShape(Shape shape) {
      this.shape = shape;
      this.declared = true;
    }

    public Shape getShape() {
      return this.shape;
    }

    public boolean getDeclared() {
      return this.declared;
    }
  }

  /**
   * Builder class for ModelImpl.
   */
  public static class Builder implements AnimationBuilder<IModel> {
    private final IModelImpl model;
    private final ArrayList<DeclaredShape> declaredShapeList;

    /**
     * Constructs a Builder object that contains an IModelImpl and list of declared shapes.
     */
    public Builder() {
      this.model = new IModelImpl();
      this.declaredShapeList = new ArrayList<DeclaredShape>();
    }

    @Override
    public IModel build() {
      return this.model;
    }

    @Override
    public AnimationBuilder<IModel> setBounds(int x, int y, int width, int height) {
      if (width < 0 || height < 0) {
        throw new IllegalArgumentException("All values must be greater than 0.");
      }
      this.model.setBounds(x, y, width, height);
      return this;
    }

    @Override
    public AnimationBuilder<IModel> declareShape(String name, String type) {
      if (declaredShapeList.contains(name)) {
        throw new IllegalArgumentException("This name already exists.");
      }
      this.declaredShapeList.add(new DeclaredShape(name, type));
      return this;
    }

    @Override
    public AnimationBuilder<IModel> addMotion(String name, int t1, int x1, int y1, int w1, int h1,
                                              int r1, int g1, int b1, int t2, int x2, int y2,
                                              int w2, int h2, int r2, int g2, int b2) {
      for (DeclaredShape shape : declaredShapeList) {
        if (shape.getName().equals(name)
                && !shape.getDeclared()) {
          switch (shape.getType().toUpperCase()) {
            case "ELLIPSE":
              Shape o = new Oval(w1 / 2, h1 / 2, x1, y1, r1, g1, b1, t1, t2, name);
              shape.addShape(o);
              o.copy();
              model.addShape(o);
              break;
            case "RECTANGLE":
              Shape r = new Rectangle(w1, h1, x1, y1,
                      r1, g1, b1, t1, t2, name);
              shape.addShape(r);
              r.copy();
              model.addShape(r);
              break;
          }
        }
        if (shape.getName().equals(name)
                && shape.getDeclared()) {
          Shape animatedShape = shape.getShape();
          if (r1 != r2 || b1 != b2 || g1 != g2) {
            model.addColorTransformation(animatedShape, 2, b2, g2, t1, t2);
          }
          if (y1 != y2 || x1 != x2) {
            model.addMoveTransformation(animatedShape, x2, y2, t1, t2);
          }
          if (w1 != w2 || h1 != h2) {
            if (animatedShape.getShapeType() == ShapeType.RECTANGLE) {
              model.addRectangleSizeTransformation(((Rectangle) animatedShape), w2, h2, t1, t2);
            }
            if (animatedShape.getShapeType() == ShapeType.OVAL) {
              model.addOvalSizeTransformation(((Oval) animatedShape), w2 / 2,
                      h2 / 2, t1, t2);
            }
          } else if (r1 == r2 && b1 == b2 && g1 == g2 && y1 == y2 && x1 == x2
                  && w1 == w2 && h1 == h2) {
            if (animatedShape.getShapeType() == ShapeType.RECTANGLE) {
              model.addStaticRectangleTransformation((Rectangle) animatedShape, t1, t2);
              animatedShape.setDisappearance(t2);
            }
            if (animatedShape.getShapeType() == ShapeType.OVAL) {
              model.addStaticOvalTransformation((Oval) animatedShape, t1, t2);
              animatedShape.setDisappearance(t2);
            }
          }
          if (t1 != t2) {
            animatedShape.setDisappearance(t2);
          }

        }
      }
      return this;
    }
  }
}

