package cs5004.animator.view;

import java.awt.event.ActionListener;


/**
 * The view interface. To motivate the methods here
 * think about all the operations that the controller
 * would need to invoke on the view
 *
 */
public interface IView {
  /**
   * Make the view visible. This is usually called
   * after the view is constructed
   */
  void makeVisible();

  /**
   * Provide the view with an action listener for
   * the button that should cause the program to
   * process a command. This is so that when the button
   * is pressed, control goes to the action listener.
   * @param actionEvent
   */
  void setCommandButtonListener(ActionListener actionEvent);

  /**
   * Transmit an error message to the view, in case
   * the command could not be processed correctly.
   * @param error
   */
  void showErrorMessage(String error);

  /**
   * Signal the view to draw itself.
   */
  void refresh();

  /**
   * Sets the shapes on the screen.
   */
  void setShapes();

  /**
   * Gets the type of the shape so the view can draw it.
   */
  void getType();

  /**
   * Gets the text of the animation.
   * @param model is the model who is being represented as text
   */
  void setText();

}
