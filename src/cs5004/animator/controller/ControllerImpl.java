package cs5004.animator.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import cs5004.animator.view.IView;

public class ControllerImpl implements IController, ActionListener {
  private IView view;

  ControllerImpl(IView view){
    this.view = view;
    view.setCommandButtonListeners(this);

  }
  @Override
  public void initView() {

  }

  @Override
  public void actionPerformed(ActionEvent e) {
    switch(e.getActionCommand()){
    case "start":
    view.startTimer();
    view.se
}
  }
}
