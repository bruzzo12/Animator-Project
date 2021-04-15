package cs5004.animator.view;

import java.awt.*;

import javax.swing.*;

/**
 * This Animation Panel represents the area where the animations of shapes will take place.
 */
class AnimationPanel extends JPanel {

  /**
   * Constructs an Animation Panel object.
   */
  public AnimationPanel() {
    super();
    ;
    this.setBackground(Color.WHITE);

  }
  /**
   * Overrides the paintComponent method in the JPanel.
   *
   * @param g;
   */
  @Override
  protected void paintComponent(Graphics g) {
    super.paintComponent(g);
    Graphics2D graphics = (Graphics2D) g;
    }

  }
  public void paintComponent(Graphics g){
    super.paintComponent(g);
    Graphics2D g2 = (Graphics2D) g;
  }
}
