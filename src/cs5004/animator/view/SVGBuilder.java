package cs5004.animator.view;

import cs5004.animator.model.AbstractShape;
import cs5004.animator.model.IModelImpl;
import cs5004.animator.util.AnimationBuilder;

public class SVGBuilder {
  private String svgOutput;

  public SVGBuilder() {
    svgOutput = "<svg width=\"700\" height=\"500\" version=\"1.1\"\n"
            +"     xmlns=\"http://www.w3.org/2000/svg\">\n";
  }

  public void addShape(String type, String name, int x, int y, int width, int height, int r, int g, int b) {
    String t;
    if (type.equals("Rectangle")) {
      t = "rect";
    }
    else {
      t = "ellipse";
    }

  }
}
