package cs5004.animator.view;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;


import cs5004.animator.model.Color;
import cs5004.animator.model.IModel;
import cs5004.animator.model.IModelImpl;
import cs5004.animator.model.Oval;
import cs5004.animator.model.Rectangle;
import cs5004.animator.model.Shape;
import cs5004.animator.model.Transformation;

/**
 * This class represents the SVG view.
 */

public class SVG {
  private String shapeDeclaration;
  private ArrayList<String> animations;
  private String type;
  private double lastWidth;
  private double lastHeight;
  private double lastX;
  private double lastY;
  private int lastR;
  private int lastG;
  private int lastB;
  private String name;

  /**
   * This class builds the string for a shape in an SVG file including animations.
   *
   * @param type   of shape
   * @param name   of shape
   * @param x      start location (lower left if rectangle, center if ellipse)
   * @param y      start location (lower left if rectangle, center if ellipse)
   * @param width  of shape (or x radius)
   * @param height of shape (or y radius)
   * @param r      red value
   * @param g      green value
   * @param b      blue value
   */
  public SVG(String type, String name, double x, double y, double width, double height,
             int r, int g, int b) {
    this.name = name;
    this.lastWidth = width;
    this.lastHeight = height;
    this.animations = new ArrayList<>();
    if (type.equals("Rectangle")) {
      this.shapeDeclaration = "<rect id=\"" + name + "\" x=\"%f\" y=\"%f\" width=\"%f\" "
              + "height=\"%f\" fill=\"rgb(%d,%d,%d)\" visibility=\"visible\" >\n";
      this.type = "rect";
    } else {
      this.shapeDeclaration = "<ellipse id=\"" + name + "\" cx=\"%f\" cy=\"%f\" rx=\"%f\" "
              + "ry=\"%f\" fill=\"rgb(%d,%d,%d)\" visibility=\"visible\" >\n";
      this.type = "ellipse";
    }
    this.shapeDeclaration = String.format(shapeDeclaration, x, y, width, height, r, g, b);

  }

  public String getName() {
    return this.name;
  }



  /**
   * Adds animation to the file.
   * @param t1 time 1
   * @param t2 time 2
   * @param x1 x1
   * @param x2 x2
   * @param y1 y1
   * @param y2 y2
   * @param w1 width 1
   * @param w2 width 2
   * @param h1 height 1
   * @param h2 height 2
   * @param r1 beginning color
   * @param r2 ending color
   */
  public void addAnimation(int t1, int t2, double x1, double x2, double y1, double y2,
                           double w1, double w2, double h1, double h2,
                           int r1, int r2, int g1, int g2, int b1, int b2) {
    this.lastX = x2;
    this.lastY = y2;
    this.lastWidth = w2;
    this.lastHeight = h2;
    this.lastR = r2;
    this.lastG = g2;
    this.lastB = b2;
    String startColor = String.format("(%d, %d, %d)", r1, g1, b1);
    String endColor = String.format("(%d, %d, %d)", r2, g2, b2);
    int start = t1 * 1000;
    int dur = (t2 - t1) * 1000;
    if (x1 != x2) {
      String animate;
      if (type.equals("Rectangle")) {
        animate = "    <animate attributeType=\"xml\" begin=\"base.begin+%dms\" dur=\"%dms\""
                + " attributeName=\"x\" from=\"%f\" to=\"%f\" fill=\"freeze\" />\n";
      } else {
        animate = "    <animate attributeType=\"xml\" begin=\"base.begin+%dms\" dur=\"%dms\" "
                + "attributeName=\"cx\" from=\"%f\" to=\"%f\" fill=\"freeze\" />\n";
      }
      animate = String.format(animate, start, dur, x1, x2);
      this.animations.add(animate);
    }
    if (y1 != y2) {
      String animate;
      if (type.equals("Rectangle")) {
        animate = "    <animate attributeType=\"xml\" begin=\"base.begin+%dms\" dur=\"%dms\" "
                + "attributeName=\"y\" from=\"%f\" to=\"%f\" fill=\"freeze\" />\n";
      } else {
        animate = "    <animate attributeType=\"xml\" begin=\"base.begin+%dms\" dur=\"%dms\" "
                + "attributeName=\"cy\" from=\"%f\" to=\"%f\" fill=\"freeze\" />\n";
      }
      animate = String.format(animate, start, dur, y1, y2);
      this.animations.add(animate);
    }
    if (w1 != w2) {
      this.lastWidth = w2;
      String animate;
      if (type.equals("Rectangle")) {
        animate = "    <animate attributeType=\"xml\" begin=\"base.begin+%dms\" dur=\"%dms\" "
                + "attributeName=\"width\" from=\"%f\" to=\"%f\" fill=\"freeze\" />\n";
      } else {
        animate = "    <animate attributeType=\"xml\" begin=\"base.begin+%dms\" dur=\"%dms\""
                + " attributeName=\"rx\" from=\"%f\" to=\"%f\" fill=\"freeze\" />\n";
      }
      animate = String.format(animate, start, dur, w1, w2);
      this.animations.add(animate);
    }
    if (h1 != h2) {
      this.lastHeight = h2;
      String animate;
      if (type.equals("Rectangle")) {
        animate = "    <animate attributeType=\"xml\" begin=\"base.begin+%dms\" "
                + "dur=\"%dms\" "
                + "attributeName=\"height\" from=\"%f\" to=\"%f\" fill=\"freeze\" />\n";
      } else {
        animate = "    <animate attributeType=\"xml\" begin=\"base.begin+%dms\" dur=\"%dms\" "
                + "attributeName=\"ry\" from=\"%f\" to=\"%f\" fill=\"freeze\" />\n";
      }
      animate = String.format(animate, start, dur, h1, h2);
      this.animations.add(animate);
    }
    if (!startColor.equals(endColor)) {
      String animate = "    <animate attributeType=\"xml\" begin=\"base.begin+%dms\" dur=\"%dms\" "
              + "attributeName=\"fill\" from=\"rgb"
              + startColor + "\" to=\"rgb" + endColor + "\" fill=\"freeze\" />\n";
      animate = String.format(animate, start, dur);
      this.animations.add(animate);
    }
  }

  /**
   * This returns an SVG formatted string of a shape and its movements.
   *
   * @return SVG string
   */
  public String toString() {
    String svgString = this.shapeDeclaration;
    for (String animation : animations) {
      svgString = svgString.concat(animation);
    }
    svgString = svgString + "</" + this.type + ">\n";
    return svgString;
  }

  /**
   * This class takes in an animation builder argument and returns a formatted SVG string of the
   * entire animation.
   */
  public static final class SVGBuilder {
    private ArrayList<SVG> shapes;
    private int width;
    private int height;
    private int duration;

    /**
     * Builds an SVG string.
     * @param width of window
     * @param height of window
     * @param model to use
     */
    public SVGBuilder(int width, int height, IModelImpl model) {
      shapes = new ArrayList<>();
      this.width = width;
      this.height = height;


      for (int i = 0; i <= 100; i++) {
        ArrayList<Shape> shapesAt = model.getShapesAtTicker(i);
        for (Shape currentShape : shapesAt) {
          if (!shapes.stream().anyMatch(s -> s.getName() == currentShape.getName())) {
            if (currentShape.getClass() == Rectangle.class) {
              SVG svgRep = new SVG("Rectangle", currentShape.getName(), currentShape.getX(),
                      currentShape.getY(),
                      ((Rectangle) currentShape).getWidth(), ((Rectangle) currentShape).getHeight(),
                      currentShape.getRed(), currentShape.getGreen(), currentShape.getBlue());
              this.shapes.add(svgRep);
            }
            if (currentShape.getClass() == Oval.class) {
              SVG svgRep = new SVG("Oval", currentShape.getName(), currentShape.getX(),
                      currentShape.getY(),
                      ((Oval) currentShape).getRX(), ((Oval) currentShape).getRY(),
                      currentShape.getRed(), currentShape.getGreen(), currentShape.getBlue());
                this.shapes.add(svgRep);
              }
            }
          for (SVG extantshape : shapes) {
            if (extantshape.getName().equals(currentShape.getName())) {
              if (currentShape.getClass() == Rectangle.class) {
                extantshape.addAnimation(i - 1, i, extantshape.lastX, currentShape.getX(),
                        extantshape.lastY, currentShape.getY(), extantshape.lastWidth, ((Rectangle) currentShape).getWidth(),
                        extantshape.lastHeight, ((Rectangle) currentShape).getHeight(), extantshape.lastR, currentShape.getRed(),
                        extantshape.lastG, currentShape.getBlue(), extantshape.lastG, currentShape.getGreen());

              }
              }
            }
          if (currentShape.getDisappearance() > this.duration) {
            this.duration = currentShape.getDisappearance();
          }
        }
      }
    }

    /**
     * Returns a string version.
     * @return String svg
     */
    public String toString() {
      String ret = "<svg width=\"%d\" height=\"%d\" version=\"1.1\"\n"
              + "     xmlns=\"http://www.w3.org/2000/svg\">\n\n"
              + "<rect>\n    <animate id=\"base\" begin=\"0,base.end\" dur=\"%dms\" "
              + "attributeName=\"visibility\" from=\"hide\" to=\"hide\"/>\n</rect>\n\n";
      ret = String.format(ret, this.width, this.height, this.duration*1000);
      int i = 0;
      for (SVG shape : shapes) {
        String istring = "";
        ret = ret.concat(istring);
        ret = ret.concat(shape.toString() + "\n\n");
      }
      ret = ret + "</svg>";
      return ret;
    }
  }

}
