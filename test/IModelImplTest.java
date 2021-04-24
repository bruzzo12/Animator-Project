
import org.junit.Test;

import java.util.NoSuchElementException;

import cs5004.animator.model.Circle;
import cs5004.animator.model.IModel;
import cs5004.animator.model.IModelImpl;
import cs5004.animator.model.Oval;
import cs5004.animator.model.Rectangle;
import cs5004.animator.model.Shape;

import static org.junit.Assert.assertEquals;


public class IModelImplTest {
  IModel t;
  Shape c;
  Shape r;
  Shape o;

  /**
   * Tests the addShape() method.
   */
  @Test
  public void testAddShape() {
    IModel t = new IModelImpl();
    Shape c = new Circle(3, 1, 2, 100, 100, 100,
            0, 100, "First Circle");
    Shape r = new Rectangle(2, 3, 1, 1,
            200, 200, 200,
            20, 50, "First Rectangle");
    Shape o = new Oval(10.5, 5.3, 100.0, 800, 0,
            0, 100, 10, 20, "First Oval");
    t.addShape(c);
    t.addShape(r);
    t.addShape(o);
    assertEquals(3, t.getShapeCount());
  }

  /**
   * Test IllegalArgumentException for addShape() method.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testIllegalArgAddShape() {
    IModel t = new IModelImpl();
    Shape c = new Circle(3, 1, 2, 100, 100, 100,
            0, 100, "First Circle");
    t.addShape(c);
    t.addShape(c);
  }

  /**
   * Tests the removeShape() method.
   */
  @Test
  public void testRemoveShape() {
    IModel t = new IModelImpl();
    Shape c = new Circle(3, 1, 2, 100, 100, 100,
            0, 100, "First Circle");
    Shape r = new Rectangle(2, 3, 1, 1,
            200, 200, 200,
            20, 50, "First Rectangle");
    Shape o = new Oval(10.5, 5.3, 100.0, 800, 0,
            0, 100, 10, 20, "First Oval");
    t.addShape(c);
    t.addShape(r);
    t.addShape(o);
    t.removeShape(o);
    assertEquals(2, t.getShapeCount());
  }

  /**
   * Tests the NoSuchElementException in removeShape() method.
   */
  @Test
  public void testNoSuchElementRemoveShape() {
    try {
      IModel model = new IModelImpl();
      Shape r = new Rectangle(2, 3, 1, 1,
              200, 200, 200,
              20, 50, "First Rectangle");
      Shape o = new Oval(10.5, 5.3, 100.0, 800, 0,
              0, 100, 10, 20, "First Oval");
      model.addShape(o);
      model.removeShape(r);
    } catch (NoSuchElementException e) {
      IModel model = new IModelImpl();
      assertEquals(0, model.getShapeCount());
    }
    try {
      IModel model = new IModelImpl();
      Shape r = new Rectangle(2, 3, 1, 1,
              200, 200, 200,
              20, 50, "First Rectangle");
      model.removeShape(r);
    } catch (NoSuchElementException e) {
      IModel model = new IModelImpl();
      assertEquals(0, model.getShapeCount());
    }
  }

  /**
   * Tests the getSpecificShape() method.
   */
  @Test
  public void testGetSpecificShape() {
    IModel t = new IModelImpl();
    Shape c = new Circle(3, 1, 2, 100, 100, 100,
            0, 100, "First Circle");
    Shape r = new Rectangle(2, 3, 1, 1,
            200, 200, 200,
            20, 50, "First Rectangle");
    Shape o = new Oval(10.5, 5.3, 100.0, 800, 0,
            0, 100, 10, 20, "First Oval");
    t.addShape(c);
    t.addShape(r);
    t.addShape(o);
    assertEquals(r, t.getSpecificShape(r));
  }

  /**
   * Tests NoSuchElementException in getSpecificShape() method.
   */
  @Test(expected = NoSuchElementException.class)
  public void testNoSuchElementGetSpecificShape() {
    IModel t = new IModelImpl();
    Shape c = new Circle(3, 1, 2, 100, 100, 100,
            0, 100, "First Circle");
    t.getSpecificShape(c);
  }

  /**
   * Tests the addSizeTransformation() methods for each shape type.
   */
  @Test
  public void testAddSizeTransformation() {
    IModel t = new IModelImpl();
    Circle c = new Circle(3, 1, 2, 100, 100, 100,
            0, 100, "First Circle");
    Rectangle r = new Rectangle(2, 3, 1, 1,
            200, 200, 200,
            20, 50, "First Rectangle");
    Oval o = new Oval(10.5, 5.3, 100.0, 800, 0,
            0, 100, 10, 20, "First Oval");
    t.addShape(c);
    t.addShape(r);
    t.addShape(o);
    t.addOvalSizeTransformation(o, 5.5, 10.1, 15, 18);
    t.addRectangleSizeTransformation(r, 10, 100, 28, 50);
    assertEquals(3, t.getShapeCount());
  }

  /**
   * Tests NoSuchElementException in addSizeTransformation() method.
   */
  @Test
  public void testNoSuchElementAddSizeTransformation() {
    try {
      IModel t = new IModelImpl();
      Circle c = new Circle(3, 1, 2, 100, 100, 100,
              0, 100, "First Circle");
    } catch (NoSuchElementException e) {
      IModel t = new IModelImpl();
      Circle c = new Circle(3, 1, 2, 100, 100, 100,
              0, 100, "First Circle");
      t.addShape(c);

    }
    try {
      IModel t = new IModelImpl();
      Rectangle r = new Rectangle(2, 3, 1, 1,
              200, 200, 200,
              20, 50, "First Rectangle");
      t.addRectangleSizeTransformation(r, 3, 4, 25, 30);
    } catch (NoSuchElementException e) {
      IModel t = new IModelImpl();
      Rectangle r = new Rectangle(2, 3, 1, 1,
              200, 200, 200,
              20, 50, "First Rectangle");
      t.addShape(r);
      t.addRectangleSizeTransformation(r, 3, 4, 25, 30);
    }
    try {
      IModel t = new IModelImpl();
      Oval o = new Oval(10.5, 5.3, 100.0, 800, 0,
              0, 100, 10, 20, "First Oval");
      t.addOvalSizeTransformation(o, 9, 10, 11, 17);
    } catch (NoSuchElementException e) {
      IModel t = new IModelImpl();
      Oval o = new Oval(10.5, 5.3, 100.0, 800, 0,
              0, 100, 10, 20, "First Oval");
      t.addShape(o);
      t.addOvalSizeTransformation(o, 9, 10, 11, 17);
    }
  }

  /**
   * Tests the IllegalArgumentException in the addSizeTransformation() method.
   */
  @Test
  public void testIllegalArgAddSizeTransformation() {
    try {
      IModel t = new IModelImpl();
      Circle c = new Circle(3, 1, 2, 100, 100, 100,
              0, 100, "First Circle");
      t.addShape(c);

    } catch (IllegalArgumentException e) {
      IModel t = new IModelImpl();
      Circle c = new Circle(3, 1, 2, 100, 100, 100,
              0, 100, "First Circle");
      t.addShape(c);

    }
    try {
      IModel t = new IModelImpl();
      Rectangle r = new Rectangle(2, 3, 1, 1,
              200, 200, 200,
              20, 50, "First Rectangle");
      t.addShape(r);
      t.addRectangleSizeTransformation(r, 2, 3, 20, 50);
    } catch (IllegalArgumentException e) {
      IModel t = new IModelImpl();
      Rectangle r = new Rectangle(2, 3, 1, 1,
              200, 200, 200,
              20, 50, "First Rectangle");
      t.addShape(r);
      t.addRectangleSizeTransformation(r, 3, 4, 25, 30);
    }
    try {
      IModel t = new IModelImpl();
      Oval o = new Oval(10.5, 5.3, 100.0, 800, 0,
              0, 100, 10, 20, "First Oval");
      t.addShape(o);
      t.addOvalSizeTransformation(o, 10.5, 5.3, 9, 17);
    } catch (IllegalArgumentException e) {
      IModel t = new IModelImpl();
      Oval o = new Oval(10.5, 5.3, 100.0, 800, 0,
              0, 100, 10, 20, "First Oval");
      t.addShape(o);
      t.addOvalSizeTransformation(o, 9, 10, 11, 17);
    }
  }

  /**
   * Tests the addColorTransformation() method.
   */
  @Test
  public void testAddColorTransformation() {
    IModel t = new IModelImpl();
    Oval o = new Oval(10.5, 5.3, 100.0, 800, 0,
            0, 100, 10, 20, "First Oval");
    t.addShape(o);
    t.addColorTransformation(o, 101, 0, 250, 10, 20);
    assertEquals(1, t.getShapeCount());
  }

  /**
   * Tests the NoSuchElementException in addColorTransformation() method.
   */
  @Test
  public void testNoSuchElementAddColorTransformation() {
    try {
      IModel t = new IModelImpl();
      Oval o = new Oval(10.5, 5.3, 100.0, 800, 0,
              0, 100, 10, 20, "First Oval");
      t.addColorTransformation(o, 101, 0, 250, 10, 20);
    } catch (NoSuchElementException e) {
      IModel t = new IModelImpl();
      Oval o = new Oval(10.5, 5.3, 100.0, 800, 0,
              0, 100, 10, 20, "First Oval");
      t.addShape(o);
      t.addColorTransformation(o, 101, 0, 250, 10, 20);
    }
  }

  /**
   * Tests the IllegalArgumentException in addColorTransformation() method.
   */
  @Test
  public void testIllegalArgAddColorTransformation() {
    try {
      IModel t = new IModelImpl();
      Oval o = new Oval(10.5, 5.3, 100.0, 800, 0,
              0, 100, 10, 20, "First Oval");
      t.addShape(o);
      t.addColorTransformation(o, 0, 0, 100, 10, 21);
    } catch (IllegalArgumentException e) {
      IModel t = new IModelImpl();
      Oval o = new Oval(10.5, 5.3, 100.0, 800, 0,
              0, 100, 10, 20, "First Oval");
      t.addShape(o);
      t.addColorTransformation(o, 101, 0, 250, 10, 20);
    }
  }

  /**
   * Tests the addMoveTranformation() method.
   */
  @Test
  public void testAddMoveTransformation() {
    IModel t = new IModelImpl();
    Oval o = new Oval(10.5, 5.3, 100.0, 800, 0,
            0, 100, 10, 20, "First Oval");
    t.addShape(o);
    t.addMoveTransformation(o, 0, 200, 10, 11);
    assertEquals(1, t.getShapeCount());
  }

  /**
   * Tests the NoSuchElementException in the addMoveTransformation() method.
   */
  @Test
  public void testNoSuchElementAddMoveTransformation() {
    try {
      IModel t = new IModelImpl();
      Oval o = new Oval(10.5, 5.3, 100.0, 800, 0,
              0, 100, 10, 20, "First Oval");
      t.addMoveTransformation(o, 0, 200, 10, 11);
    } catch (NoSuchElementException e) {
      IModel t = new IModelImpl();
      Oval o = new Oval(10.5, 5.3, 100.0, 800, 0,
              0, 100, 10, 20, "First Oval");
      t.addShape(o);
      t.addMoveTransformation(o, 0, 200, 10, 11);
    }
  }

  /**
   * Tests the IllegalArgumentException in addMoveTransformation() method.
   */
  @Test
  public void testIllegalArgAddMoveTransformation() {
    try {
      IModel t = new IModelImpl();
      Oval o = new Oval(10.5, 5.3, 100.0, 800, 0,
              0, 100, 10, 20, "First Oval");
      t.addShape(o);
      t.addMoveTransformation(o, -1, 200, 10, 20);
    } catch (IllegalArgumentException e) {
      IModel t = new IModelImpl();
      Oval o = new Oval(10.5, 5.3, 100.0, 800, 0,
              0, 100, 10, 20, "First Oval");
      t.addShape(o);
      t.addMoveTransformation(o, 0, 200, 10, 11);
    }
  }

  /**
   * Tests the toString() method.
   */
  @Test
  public void testToString() {
    IModel t = new IModelImpl();
    Shape c = new Circle(3, 1, 2, 100, 100, 100,
            0, 100, "First Circle");
    Rectangle r = new Rectangle(2, 3, 1, 1,
            200, 200, 200,
            20, 50, "First Rectangle");
    Shape o = new Oval(10.5, 5.3, 100.0, 800, 0,
            0, 100, 10, 20, "First Oval");
    t.addShape(c);
    t.addShape(r);
    t.addShape(o);
    t.addRectangleSizeTransformation(r, 5, 4, 25, 30);
    t.addColorTransformation(c, 105, 0, 200, 10, 60);
    t.addMoveTransformation(o, 105, 650, 15, 19);
    assertEquals("Shapes:\n"
            + "Name: First Circle\n"
            + "Type: circle\n"
            + "Center (1.0,2.0), radius: 3.0\n"
            + "Color: (100,100,100)\n"
            + "Appears at t=0\n"
            + "Disappears at t=100\n"
            + "\n"
            + "Name: First Oval\n"
            + "Type: oval\n"
            + "Center (100.0,800.0), X radius: 10.5, Y radius: 5.3\n"
            + "Color: (0,0,100)\n"
            + "Appears at t=10\n"
            + "Disappears at t=20\n"
            + "\n"
            + "Name: First Rectangle\n"
            + "Type: Rectangle\n"
            + "Min corner: (1.0,1.0) Width: 2.0, Height: 3.0\n"
            + "Color: (200,200,200)\n"
            + "Appears at t=20\n"
            + "Disappears at t=50\n"
            + "\n"
            + "Shape First Circle changes color from (100, 100, 100) to (105, 0, 200) from"
            + " t=10 to t=60\n"
            + "Shape First Oval moves from (100.0,800.0) to (105.0,650.0) from t=15 to"
            + " t=19\n"
            + "Shape First Rectangle scales from Width: 5.0, Height: 4.0 to from t=25 to"
            + " t=30\n", t.toString());
  }

  /**
   * Tests the getShapeCount() method.
   */
  @Test
  public void getShapeCount() {
    IModel t = new IModelImpl();
    Shape c = new Circle(3, 1, 2, 100, 100, 100,
            0, 100, "First Circle");
    t.addShape(c);
    assertEquals(1, t.getShapeCount());
    t.removeShape(c);
    assertEquals(0, t.getShapeCount());
  }
}