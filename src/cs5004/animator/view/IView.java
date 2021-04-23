package cs5004.animator.view;

import java.awt.event.ActionListener;
import java.util.ArrayList;

import cs5004.animator.model.IModelImpl;
import cs5004.animator.model.Point2D;
import cs5004.animator.model.Shape;


/**
 * The view interface. To motivate the methods here think about all the operations that the
 * controller would need to invoke on the view
 */
public interface IView {
  /**
   * Make the view visible. This is usually called after the view is constructed
   */
  void makeVisible();

  /**
   * Provide the view with an action listener for the button that should cause the program to
   * process a command. This is so that when the button is pressed, control goes to the action
   * listener.
   *
   * @param actionEvent
   */
  void setCommandButtonListener(ActionListener actionEvent);

  /**
   * Transmit an error message to the view, in case the command could not be processed correctly.
   *
   * @param error
   */
  void showErrorMessage(String error);

  /**
   * Signal the view to draw itself.
   */
  void refresh();

  /**
   * Sets the shapes for the animation.
   * @param frames list of list of shapes at each ticker as they appear in the animation
   *
   */
  void setShapes(ArrayList<ArrayList<Shape>> frames);

  /**
   * Sets the dimensions of the JFrame.
   * @param width the width of the JFrame
   * @param height the height of the JFrame
   */
  void setDimensions(double width, double height);

  /**
   * Sets the offset x and y values for all x and y coordinates.
   * @param offset Point2D point
   */
  void setOffset(Point2D offset);

  /**
   * Sets the end time of the animation.
   * @param endTime the last time a shape exists in the animation
   */
  void setEndTime(double endTime);

  /**
   * Gets the text of the animation.
   *
   * @param model is the model who is being represented as text
   */
  String getText(IModelImpl model);

  /**
   * Begins the animation.
   */
  void animate();

}
