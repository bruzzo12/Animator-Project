package cs5004.animator.view;

import java.awt.event.ActionListener;
import java.util.ArrayList;

import cs5004.animator.model.IModelImpl;
import cs5004.animator.model.Point2D;
import cs5004.animator.util.AnimationBuilder;

/**
 * This class represents a text view of the animation. It implements the methods from IView.
 */
public class TextView implements IView {

  private AnimationBuilder input;

  /**
   * Constructs a new TextView object.
   */
  public TextView() {

  }

  @Override
  public String getText(IModelImpl model) {
    return model.toString();
  }

  @Override
  public void animate() {

  }

  @Override
  public void setSpeedValue(int speed) {

  }


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
  public void setShapes(ArrayList<ArrayList<cs5004.animator.model.Shape>> frames) {

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


}

