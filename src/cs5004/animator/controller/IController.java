package cs5004.animator.controller;

import java.awt.event.ActionEvent;

import cs5004.animator.model.IModel;
import cs5004.animator.view.IView;
/**
 * This represents a cs5004.animator.controller for the animation and its required methods.
 */
public interface IController {
  /**
   * Initializes the view.
   */
  public void initView();

  /**
   * Collection of the actions the controller can perform.
   */
  public void actionPerformed(ActionEvent e);




}
