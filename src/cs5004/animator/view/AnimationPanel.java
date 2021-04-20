package cs5004.animator.view;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

/**
 * This Animation Panel represents the area where the animations of shapes will take place.
 */
class AnimationPanel extends JPanel {
  private String ShapeType;
  private int speedValue;
  private static int startValue;
  private static int currentValue;
  private static int endValue;
  public static int startTime;
  private static int endTime ;
  private int currentTime;
  /**
   * Constructs an Animation Panel object.
   */
  public AnimationPanel(int speedValue, String ShapeType, int startValue, int endValue,
                        int startTime, int endTime) {
    super();
    this.speedValue = speedValue;
    this.startValue = startValue;
    this.currentValue = startValue;
    this.endValue = endValue;
    this.startTime = startTime;
    this.endTime = endTime;
    this.currentTime = 0;
    this.setBackground(Color.WHITE);

    new Timer(speedValue, new ActionListener() {

      @Override
      public void actionPerformed(ActionEvent e) {
        if(currentTime >= startTime){
          currentValue = tween(startValue, endValue, startTime, endTime, currentTime);
          repaint();

        }
        if(currentTime == endTime){
          ((Timer)e.getSource()).stop();
        }
        currentTime++;
      }
    });
  }

  protected int tween(int startValue, int endValue, int startTime, int endTime, int currentTime){
    return startValue*((endTime - currentTime)/(endTime-startTime)) +
            endValue*((currentTime - startTime)/(endTime-startTime));
  };

  /**
   * Overrides the paintComponent method in the JPanel.
   *
   * @param g;
   */
  @Override
  protected void paintComponent(Graphics g) {
    super.paintComponent(g);
    if(ShapeType == "Rectangle"){
      g.drawRect();
    }
  }
  public static void main(String[] args) {
    SwingUtilities.invokeLater(new Runnable() {
      public void run() {
        AnimationPanel mainPanel = new AnimationPanel(30);
        JFrame frame = new JFrame("Shapes");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().add(mainPanel);
        frame.setVisible(true);
      }
    });
  }
}

