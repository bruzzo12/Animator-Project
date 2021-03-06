package cs5004.animator.view;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.*;

import cs5004.animator.model.IModelImpl;
import cs5004.animator.model.Point2D;

/**
 * This class represents a visual view of the shape animation.
 */
public class VisualView extends JFrame implements IView, ActionListener {
  protected AnimationPanel shapePanel;
  private double width;
  private double height;

  /**
   * Constructs a new VisualView object.
   */
  public VisualView() {
    super();
    this.setTitle("Shape Animator!");
    this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    //sets the border and then adds the panel where shapes will be drawn in center. Also adds
    //scrollbars on bottom and left side. Will need to somehow get the dimension size from text
    //file using the AnimationReader and then input them in new Dimension() (500 is temp for now)
    this.setLayout(new BorderLayout());
    shapePanel = new AnimationPanel();
    shapePanel.setPreferredSize(new Dimension((int) width,
            (int) height));
    this.add(shapePanel, BorderLayout.CENTER);
    this.add(new Scrollbar(), BorderLayout.SOUTH);
    this.add(new Scrollbar(), BorderLayout.WEST);
    pack();

  }

  @Override
  public void makeVisible() {
    this.setVisible(true);
  }

  @Override
  public void setCommandButtonListeners(ActionListener actionEvent) {

  }

  @Override
  public void showErrorMessage(String error) {
    JOptionPane.showMessageDialog(this, error,
            "Error", JOptionPane.ERROR_MESSAGE);
  }

  @Override
  public void refresh() {

  }

  @Override
  public void setShapes(ArrayList<ArrayList<cs5004.animator.model.Shape>> frames) {
    shapePanel.setShapes(frames);
  }


  @Override
  public void setOffset(Point2D offset) {
    shapePanel.setOffset(offset);
  }

  @Override
  public void setEndTime(double endTime) {
    shapePanel.setEndTime(endTime);
  }

  @Override
  public void setDimensions(double width, double height) {
    this.width = width;
    this.height = height;
  }

  @Override
  public String getText(IModelImpl model) {
    return null;
  }

  @Override
  public void animate() {

  }

  @Override
  public void setSpeedValue(int speed) {
    shapePanel.setSpeedValue(speed);
  }

  @Override
  public void actionPerformed(ActionEvent e) {

  }

  public void startTimer() {
    shapePanel.startTimer();
  }

  @Override
  public void stopTimer() {
    shapePanel.stopTimer();

  }


  @Override
  public void setText(String text) {

  }

}
