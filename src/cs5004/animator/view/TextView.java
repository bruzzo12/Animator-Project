package cs5004.animator.view;

import java.awt.event.ActionListener;

import cs5004.animator.model.IModelImpl;
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


}

