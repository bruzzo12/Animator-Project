package cs5004.animator.view;

import java.awt.event.ActionListener;
import java.util.ArrayList;

import cs5004.animator.model.IModelImpl;
import cs5004.animator.model.Point2D;
import cs5004.animator.model.Shape;


public class SVGView implements IView {


  @Override
  public void makeVisible() {

  }

  @Override
  public void setCommandButtonListener(ActionListener actionEvent) {

  }

  @Override
  public void showErrorMessage(String error) {

  }

  @Override
  public void refresh() {

  }

  @Override
  public void setShapes(ArrayList<ArrayList<Shape>> frames) {

  }

  @Override
  public void getShapeType() {

  }

  @Override
  public void setOffset(Point2D offset) {

  }

  @Override
  public void setDimensions(double width, double height) {

  }

  @Override
  public String getText(IModelImpl model) {
    SVG.SVGBuilder svgBuilder = new SVG.SVGBuilder(700, 500, model);
    return svgBuilder.toString();
  }

}
