package cs5004.animator.view;

import java.awt.*;
import java.awt.event.ActionListener;

import javax.swing.*;

/**
 * Creates the button for the Playback View
 **/
public class ButtonSelection extends JPanel {


  private JButton changeSpeed;
  private JLabel speedLabel;

  private JButton start;
  private JButton pause;
  private JButton restart;
  private JButton loop;
  private JButton resume;

  private JTextField visualText;
  private JTextField speedField;

  /**
   * Constructor for button Selection Panel
   **/
  public ButtonSelection() {
    this.setLayout(new FlowLayout());

    visualText = new JTextField("");
    visualText.setPreferredSize(new Dimension(150, 20));

    speedLabel = new JLabel("Speed: 0");

    start = new JButton("Start");
    start.setActionCommand("start");


    pause = new JButton("Pause");
    pause.setActionCommand("pause");

    resume = new JButton("Resume");
    resume.setActionCommand("resume");

    restart = new JButton("Restart");
    restart.setActionCommand("restart");

    loop = new JButton("Toggle Loop");
    loop.setActionCommand("toggle loop");

    speedField = new JTextField("Choose a speed");
    speedField.setPreferredSize(new Dimension(150, 20));
    changeSpeed = new JButton("Change Speed");
    changeSpeed.setActionCommand("change speed");

    this.add(visualText);
    this.add(speedLabel);
    this.add(start);
    this.add(pause);
    this.add(resume);
    this.add(restart);
    this.add(loop);
    this.add(speedField);
    this.add(changeSpeed);
  }

  /**
   * Assigns listeners to the panel buttons.
   *
   * @param click is a mouse click action.
   **/
  public void setListeners(ActionListener click) {

    this.resume.addActionListener(click);
    this.loop.addActionListener(click);
    this.start.addActionListener(click);
    this.restart.addActionListener(click);
    this.pause.addActionListener(click);
    this.changeSpeed.addActionListener(click);
  }

  /**
   * Used to display the most recent action.
   *
   * @param str the label's new text.
   **/
  public void setVisualLabel(String str) {
    this.visualText.setText(str);
  }

  /**
   * Changes Toggle label's text to update user on most recent action.
   *
   * @param str New text of the toggle label.
   **/
  public void setToggleLabel(String str) {
    this.loop.setText(str);
  }

  /**
   * Changes speed label's text to update user on most recent action.
   *
   * @param str the text the speed label should be set to.
   */
  public void setSpeedLabel(String str) {
    this.speedLabel.setText(str);
  }

  /**
   * Gets the current speed field.
   *
   * @return speedField
   **/
  public JTextField getSpeedField() {
    return this.speedField;
  }

  /**
   * Sets the speed field.
   *
   * @param speed how fast animation should be set.
   **/
  public void setSpeedField(String speed) {
    this.speedField.setText(speed);
  }
}