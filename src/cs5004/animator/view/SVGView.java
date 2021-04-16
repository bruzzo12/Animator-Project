package cs5004.animator.view;

import java.awt.event.ActionListener;

import cs5004.animator.model.IModelImpl;
import cs5004.animator.util.AnimationBuilder;


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
  public void setShapes() {

  }

  @Override
  public void getShapeType() {

  }

  @Override
  public String getText(IModelImpl model) {
    SVG.SVGBuilder svgBuilder = new SVG.SVGBuilder(700, 500, model);
    return svgBuilder.toString();
  }

}
