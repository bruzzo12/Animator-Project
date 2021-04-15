package cs5004.animator.view;

import java.awt.event.ActionEvent;
import java.awt.*;
import javax.swing.*;
import java.awt.event.ActionListener;
import java.awt.event.KeyListener;

/**
 * This class represents a visual view of the shape animation.
 */
public class VisualView extends JFrame implements IView {
  private AnimationPanel shapePanel;

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
    shapePanel.setPreferredSize(new Dimension(500,500));
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
  public void setCommandButtonListener(ActionListener actionEvent) {

  }

  @Override
  public void showErrorMessage(String error) {
    JOptionPane.showMessageDialog(this,error,"Error", JOptionPane.ERROR_MESSAGE);
  }

  @Override
  public void refresh() {
    this.repaint();
  }

  @Override
  public void setShapes() {

  }

  @Override
  public void getType() {

  }

  @Override
  public void setText() {

  }
}
//test