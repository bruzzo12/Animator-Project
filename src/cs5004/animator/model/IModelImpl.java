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
  protected Point2D upperLeft;
  protected int width;
  protected int height;

  /**
   * Constructs an animation cs5004.animator.model that starts with an empty animation screen.
   */
  public IModelImpl() {
    this.shapes = new ArrayList<>();
    this.transformationList = new ArrayList<>();
    this.shapeCount = 0;
    this.upperLeft = new Point2D(0, 0);
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
    if (newX < 0 || newY < 0) {
      throw new IllegalArgumentException("Coordinates must be a positive!");
    }
    if (!shapes.contains(shape)) {
      throw new NoSuchElementException("Shape not in the animation, please add the shape first!");
    }
    transformationList.add(shape.move(newX, newY, timeStart, timeEnd));
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
  public void addCircleSizeTransformation(Circle circle, double newRadius, int timeStart,
                                          int timeEnd) {
    if (newRadius < 0) {
      throw new IllegalArgumentException("new Radius must be a positive number!");
    }
    if (!shapes.contains(circle)) {
      throw new NoSuchElementException("Shape not in the animation, please add the shape first!");
    }
    transformationList.add(circle.changeSize(newRadius, timeStart, timeEnd));
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

  @Override
  public ArrayList<Shape> getShapesAtTicker(int ticker) {
    ArrayList<Shape> animation = new ArrayList<>();
    for (Shape shape:shapes) {
      if (shape.getAppearance() <= ticker && ticker <= shape.getDisappearance()) {
        for (Transformation t : shape.getTransformationList()) {
          if (t.getStartTime() <= ticker && ticker <= t.getEndTime()) {


          }
        }
      }
    }
    return animation;
  }


  /**
   * Calculates the intermediate state of the shape being transformed at a specific tick
   * @param object the transformation object to be tweened
   * @param ticker the time at which the intermediate state is being calculated
   * @return copy of the shape with the intermediate state values
   */
  public Shape tween(Transformation object, int ticker) {
    if (object.type == TransformationType.MOVE) {
      double newX = (object.getStartXCoordinate() * ((object.getEndTime() - ticker)
              / (object.getEndTime() - object.getStartTime()))) + (object.getEndXCoordinate()
              * ((ticker - object.getStartTime()) / (object.getEndTime() - object.getStartTime())));
      double newY = (object.getStartYCoordinate() * ((object.getEndTime() - ticker)
              / (object.getEndTime() - object.getStartTime()))) + (object.getEndYCoordinate()
              * ((ticker - object.getStartTime()) / (object.getEndTime() - object.getStartTime())));
      Shape copy = object.shape.getCopy();
      copy.setReference(newX, newY);

      return copy;
    }

  }

  public int getShapeCount() {
    return shapes.size();
  }

  public void setBounds(int x, int y, int width, int height) {
    this.upperLeft.setXandY((double) x, (double) y);
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
  public static final class Builder implements AnimationBuilder<IModel> {
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
      if (x < 0 || y < 0 || width < 0 || height < 0) {
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
          } else if (t1 != t2) {
            animatedShape.setDisappearance(t2);
          }
        }
      }
      return this;
    }
  }

}