package cs5004.animator.view;

import java.awt.event.ActionListener;
import java.util.ArrayList;

import cs5004.animator.model.IModelImpl;
import cs5004.animator.model.Point2D;
import cs5004.animator.model.Shape;

/**
 * Class that represents an SVG view and implements IView but does implement all methods from IView.
 */
public final class SvgView implements IView {


  @Override
  public void makeVisible() {

  }

  @Override
  public void setCommandButtonListeners(ActionListener actionEvent) {

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
  public void setOffset(Point2D offset) {

  }

  @Override
  public void setEndTime(double endTime) {

  }

  @Override
  public void setDimensions(double width, double height) {

  }

  @Override
  public String getText(IModelImpl model) {
    Svg.SvgBuilder svgBuilder = new Svg.SvgBuilder(700, 500, model);
    return svgBuilder.toString();
  }

  @Override
  public void animate() {

  }

  @Override
  public void setSpeedValue(int speed) {

  }

  @Override
  public void startTimer() {

  }

  @Override
  public void stopTimer() {
    
  }

  @Override
  public void setText(String text) {

  }

}
