package cs5004.animator.view;

import java.awt.*;

import javax.swing.*;

import cs5004.animator.model.IModelImpl;

/**
 * Class for the buttons to be displayed in the animation. It extends VisualView.
 */
public class ButtonView extends VisualView {
  private IModelImpl model;
  private int speed;
  private ButtonSelection bs;

  /**
   * Constructs a button view object that takes in a visual view.
   *
   * @param model the model to be used
   * @param speed the speed of the animation
   */
  public ButtonView(IModelImpl model, int speed) {
    super();
    this.model = model;
    this.speed = speed;
    this.bs = new ButtonSelection();
    this.add(bs, BorderLayout.SOUTH);
    this.setVisible(true);

    /*
    //Add radio Buttons for start, pause, resume and restart functionality
    JPanel radioPanel = new JPanel();
    radioPanel.setBorder(BorderFactory.createTitledBorder("Radio buttons"));
    radioPanel.setLayout(new BoxLayout(radioPanel, BoxLayout.PAGE_AXIS));
    JRadioButton[] radioButtons = new JRadioButton[4];

    //buttons groups are used to combine radio buttons. Only one radio
    // button in each group can be selected.
    ButtonGroup rGroup1 = new ButtonGroup();
    String[] radioButtonText = {"start","pause","resume","restart"};
    for (int i = 0; i < radioButtons.length; i++) {
      radioButtons[i] = new JRadioButton(radioButtonText[i]);
      //radioButtons[i].setSelected(false);
      radioButtons[i].setActionCommand(radioButtonText[i]);
      radioButtons[i].addActionListener(this);
      rGroup1.add(radioButtons[i]);
      radioPanel.add(radioButtons[i]);
    }
    this.add(radioPanel);*/
  }

  public void animate() {
    this.shapePanel.startTimer();
  }

  /**
   * Returns the speed value.
   *
   * @returns speed the speed value
   */
  public int getSpeed() {
    return this.speed;
  }

  @Override
  public void setText(String text) {
    bs.setVisualLabel(text);
  }

  /**
   * Resets the animation to time 0.
   */
  public void reset() {
    this.shapePanel.reset();
  }

  public void setLoop() {
    this.shapePanel.setLoop();
  }

  /**
   * Sets the speed for the animation.
   */
  public void setSpeed() {
    try {
      int speed = Integer.parseInt(this.bs.getSpeedField().getText().trim());
      if (speed < 0) {
        throw new IllegalArgumentException("Speed must be positive!");
      }
      this.bs.setSpeedField(String.format("%s", speed));
      this.bs.setSpeedLabel(String.format("%s", speed));
      this.shapePanel.setSpeedValue(speed);
    } catch (NumberFormatException e) {
      System.out.println("There was a NumberFormatException:" + e.getMessage());
    }
  }

  public void setToggleLabel(String str) {
    this.bs.setToggleLabel(str);
  }

  public static void main(String[] args) {
    IView ButtonView = new ButtonView(new IModelImpl(), 5);


  }

}
