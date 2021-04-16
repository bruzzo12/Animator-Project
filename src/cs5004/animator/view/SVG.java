package cs5004.animator.view;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;


import cs5004.animator.model.Color;
import cs5004.animator.model.IModelImpl;
import cs5004.animator.model.Oval;
import cs5004.animator.model.Rectangle;
import cs5004.animator.model.Shape;
import cs5004.animator.model.Transformation;


public class SVG {
  private String shapeDeclaration;
  private ArrayList<String> animations;
  private String type;
  private String name;
  private double lastWidth;
  private double lastHeight;

  /**
   * This class builds the string for a shape in an SVG file
   * including animations
   * @param type of shape
   * @param name of shape
   * @param x start location (lower left if rectangle, center if ellipse)
   * @param y start location (lower left if rectangle, center if ellipse)
   * @param width of shape (or x radius)
   * @param height of shape (or y radius)
   * @param r red value
   * @param g green value
   * @param b blue value
   */
  public SVG(String type, String name, double x, double y, double width, double height, int r, int g, int b) {
    this.name = name;
    this.lastWidth = width;
    this.lastHeight = height;
    this.animations = new ArrayList<>();
    if (type.equals("Rectangle")) {
      this.shapeDeclaration = "<rect id=\"" + name  + "\" x=\"%f\" y=\"%f\" width=\"%f\" height=\"%f\" fill=\"rgb(%d,%d,%d)\" visibility=\"visible\" >\n";
      this.type = "rect";
    }
    else {
      this.shapeDeclaration = "<ellipse id=\"" + name  + "\" cx=\"%f\" cy=\"%f\" rx=\"%f\" ry=\"%f\" fill=\"rgb(%d,%d,%d)\" visibility=\"visible\" >\n";
      this.type = "ellipse";
    }
    this.shapeDeclaration = String.format(shapeDeclaration, x, y, width, height, r, g, b);

  }

  public void addAnimation(int t1, int t2, double x1, double x2, double y1, double y2, double w1, double w2, double h1, double h2,
                           String startColor, String endColor) {
    this.lastWidth = w2;
    this.lastHeight = h2;
    int start = t1 * 1000;
    int dur = (t2 - t1) * 1000;
    if (x1 != x2) {
      String animate;
      if (type.equals("Rectangle")) {
        animate = "    <animate attributeType=\"xml\" begin=\"base.begin+%dms\" dur=\"%dms\" attributeName=\"x\" from=\"%f\" to=\"%f\" fill=\"freeze\" />\n";
      } else {
        animate = "    <animate attributeType=\"xml\" begin=\"base.begin+%dms\" dur=\"%dms\" attributeName=\"cx\" from=\"%f\" to=\"%f\" fill=\"freeze\" />\n";
      }
      animate = String.format(animate, start, dur, x1, x2);
      this.animations.add(animate);
    }
    if (y1 != y2) {
      String animate;
      if (type.equals("Rectangle")) {
        animate = "    <animate attributeType=\"xml\" begin=\"base.begin+%dms\" dur=\"%dms\" attributeName=\"y\" from=\"%f\" to=\"%f\" fill=\"freeze\" />\n";
      } else {
        animate = "    <animate attributeType=\"xml\" begin=\"base.begin+%dms\" dur=\"%dms\" attributeName=\"cy\" from=\"%f\" to=\"%f\" fill=\"freeze\" />\n";
      }
      animate = String.format(animate, start, dur, y1, y2);
      this.animations.add(animate);
    }
    if (w1 != w2) {
      String animate;
      if (type.equals("Rectangle")) {
        animate = "    <animate attributeType=\"xml\" begin=\"base.begin+%dms\" dur=\"%dms\" attributeName=\"width\" from=\"%f\" to=\"%f\" fill=\"freeze\" />\n";
      } else {
        animate = "    <animate attributeType=\"xml\" begin=\"base.begin+%dms\" dur=\"%dms\" attributeName=\"rx\" from=\"%f\" to=\"%f\" fill=\"freeze\" />\n";
      }
      animate = String.format(animate, start, dur, w1, w2);
      this.animations.add(animate);
    }
    if (h1 != h2) {
      String animate;
      if (type.equals("Rectangle")) {
        animate = "    <animate attributeType=\"xml\" begin=\"base.begin+%dms\" dur=\"%dms\" attributeName=\"height\" from=\"%f\" to=\"%f\" fill=\"freeze\" />\n";
      } else {
        animate = "    <animate attributeType=\"xml\" begin=\"base.begin+%dms\" dur=\"%dms\" attributeName=\"ry\" from=\"%f\" to=\"%f\" fill=\"freeze\" />\n";
      }
      animate = String.format(animate, start, dur, h1, h2);
      this.animations.add(animate);
    }
    if (!startColor.equals(endColor)) {
      String animate = "    <animate attributeType=\"xml\" begin=\"base.begin+%dms\" dur=\"%dms\" attributeName=\"fill\" from=\"rgb"
              + startColor + "\" to=\"rgb" + endColor + "\" fill=\"freeze\" />\n";
      animate = String.format(animate, start, dur);
      this.animations.add(animate);
    }
  }

  /**
   * This returns an SVG formatted string of a shape and its movements.
   * @return SVG string
   */
  public String toString() {
    String SVGString = this.shapeDeclaration;
    for (String animation : animations) {
      SVGString = SVGString.concat(animation);
    }
    SVGString = SVGString + "</" + this.type + ">\n";
    return SVGString;
  }

  /**
   * This class takes in an animation builder argument and returns a formatted SVG string of
   * the entire animation.
   */
  public static final class SVGBuilder {
    private ArrayList<SVG> shapes;
    private int width;
    private int height;
    private int duration;

    public SVGBuilder(int width, int height, IModelImpl model) {
      shapes = new ArrayList<>();
      this.width = width;
      this.height = height;
      ArrayList<Shape> shapesIn = model.getShapesAtTicker(0);
      int scount = shapesIn.size();
      int i = 1;
      while (scount < model.getShapeCount()) {
        ArrayList<Shape> shapesAt = model.getShapesAtTicker(i);
        List<Shape> notIn = shapesAt.stream().filter(s -> !shapesIn.contains(s))
                .collect(Collectors.toList());
        if (notIn.size() == 0) {
          i++;
        }
        else {
          for (Shape shape:notIn) {
            shapesIn.add(shape);
            i++;
          }
        }
      }
      for (Shape currentShape:shapesIn) {
        if (currentShape.getClass() == Rectangle.class) {
          SVG SVGRep = new SVG("Rectangle", currentShape.getName(), currentShape.getX(), currentShape.getY(),
                  ((Rectangle) currentShape).getWidth(), ((Rectangle) currentShape).getHeight(), currentShape.getRed(), currentShape.getGreen(), currentShape.getBlue());
          for (Transformation t:currentShape.getTransformationList()) {
            double startx;
            double endx;
            double starty;
            double endy;
            double startWidth;
            double newWidth;
            double startHeight;
            double newHeight;
            String startColor;
            String endColor;
            if (Objects.isNull(t.getStartLocation())) {
              startx = endx = 0;
              starty = endy = 0;
            } else {
              startx = t.getStartXCoordinate();
              endx = t.getEndXCoordinate();
              starty = t.getStartYCoordinate();
              endy = t.getEndYCoordinate();
            }
            if (Objects.isNull(t.getNewWidth())) {
              startWidth = newWidth = 0;
            } else {
              startWidth = SVGRep.lastWidth;
              newWidth = t.getNewWidth();
            }
            if (Objects.isNull(t.getNewHeight())) {
              startHeight = newHeight = 0;
            } else {
              startHeight = SVGRep.lastHeight;
              newHeight = t.getNewHeight();
            }
            if (Objects.isNull(t.getStartColor())) {
              startColor = endColor = new Color(0, 0, 0).toString();
            } else {
              startColor = t.getStartColor().toString();
              endColor = t.getEndColor().toString();
            }
            SVGRep.addAnimation(t.getStartTime(), t.getEndTime(), startx, endx,
                    starty, endy, startWidth, newWidth, startHeight,
                    newHeight, startColor, endColor);
          }
          this.shapes.add(SVGRep);
        }
        if (currentShape.getClass() == Oval.class) {
          SVG SVGRep = new SVG("Oval", currentShape.getName(), currentShape.getX(), currentShape.getY(),
                  ((Oval) currentShape).getRX(), ((Oval) currentShape).getRY(), currentShape.getRed(), currentShape.getGreen(), currentShape.getBlue());
          for (Transformation t:currentShape.getTransformationList()) {
            double startx;
            double endx;
            double starty;
            double endy;
            double startWidth;
            double newWidth;
            double startHeight;
            double newHeight;
            String startColor;
            String endColor;
            if (Objects.isNull(t.getStartLocation())) {
              startx = endx = 0;
              starty = endy = 0;
            } else {
              startx = t.getStartXCoordinate();
              endx = t.getEndXCoordinate();
              starty = t.getStartYCoordinate();
              endy = t.getEndYCoordinate();
            }
            if (Objects.isNull(t.getNewWidth())) {
              startWidth = newWidth = 0;
            } else {
              startWidth = SVGRep.lastWidth;
              newWidth = t.getNewWidth();
            }
            if (Objects.isNull(t.getNewHeight())) {
              startHeight = newHeight = 0;
            } else {
              startHeight = SVGRep.lastHeight;
              newHeight = t.getNewHeight();
            }
            if (Objects.isNull(t.getStartColor())) {
              startColor = endColor = new Color(0, 0, 0).toString();
            } else {
              startColor = t.getStartColor().toString();
              endColor = t.getEndColor().toString();
            }
            SVGRep.addAnimation(t.getStartTime(), t.getEndTime(), startx, endx,
                    starty, endy, startWidth, newWidth, startHeight,
                    newHeight, startColor, endColor);
          }
          this.shapes.add(SVGRep);
      }
      if (currentShape.getDisappearance() > this.duration) {
        this.duration = currentShape.getDisappearance();
      }
    }
  }

    public String toString() {
      String ret = "<svg width=\"%d\" height=\"%d\" version=\"1.1\"\n"
              + "     xmlns=\"http://www.w3.org/2000/svg\">\n\n"
              + "<rect>\n    <animate id=\"base\" begin=\"0,base.end\" dur=\"%dms\" attributeName=\"visibility\" from=\"hide\" to=\"hide\"/>\n</rect>\n\n";
      ret = String.format(ret, this.width, this.height, this.duration);
      for (SVG shape : shapes) {
        ret = ret.concat(shape.toString() + "\n\n");
      }
      ret = ret + "</svg>";
      return ret;
    }
}

}
