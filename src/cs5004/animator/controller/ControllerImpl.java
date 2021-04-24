package cs5004.animator.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import cs5004.animator.view.ButtonView;
import cs5004.animator.view.IView;

public class ControllerImpl implements IController, ActionListener {
  private ButtonView view;

  public ControllerImpl(ButtonView view) {
    this.view = view;
    view.setCommandButtonListeners(this);

  }

  @Override
  public void animate() {
    view.setSpeed();
    view.animate();
  }

  @Override
  public void actionPerformed(ActionEvent e) {

    switch (e.getActionCommand()) {
      case "start":
        view.setSpeedValue(view.getSpeed());
        view.startTimer();
        view.setText("Start");
        break;
      case "pause":
        view.stopTimer();
        view.setText("Paused");
        break;
      case "resume":
        view.startTimer();
        view.setText("Resumed");
        break;
      case "restart":
        view.reset();
        view.setText("Restarting Animation");
        break;
      case "change speed":
        view.setSpeed();
        break;
      case "toggle loop":
        view.setToggleLabel("Toggled the loop");
    }
  }

}

