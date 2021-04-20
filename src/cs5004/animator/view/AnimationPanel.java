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
  private String transformationType;
  public int speedValue;
  public int startValue;
  public int currentValue;
  public int endValue;
  public int startTime;
  public int endTime ;
  private int currentTime;
  /**
   * Constructs an Animation Panel object.
   */
  public AnimationPanel(int speedValue, String ShapeType, int startValue, int endValue,
                        int startTime, int endTime) {
    super();
    this.speedValue = speedValue;
    this.startValue = startValue;
    currentValue = startValue;
    this.endValue = endValue;
    this.startTime = startTime;
    this.endTime = endTime;
    currentTime = 0;


    new Timer(this.speedValue, new ActionListener() {

      @Override
      public void actionPerformed(ActionEvent e) {
        if(currentTime >= startTime){
          System.out.println(String.format("Current time is: %d\n", currentTime));
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

  private int tween(int startValue, int endValue, int startTime, int endTime, int currentTime){
    return startValue*((endTime - currentTime)/(endTime-startTime)) +
            endValue*((currentTime - startTime)/(endTime-startTime));
  };

  public Dimension getPreferredSize() {
    return new Dimension(500, 500);
  }
  /**
   * Overrides the paintComponent method in the JPanel.
   *
   * @param g;
   */
  @Override
  protected void paintComponent(Graphics g) {
    super.paintComponent(g);
      Color objectColor = new Color(currentValue, 12, 45);
      g.setColor(objectColor);
      g.drawRect(300, 300, currentValue, 12);
      g.fillRect(300, 300, currentValue, 12);



  }
  private static void setupGUI(){
    AnimationPanel mainPanel = new AnimationPanel(1,"Rectangle",
            11,30,0, 50);
    JFrame frame = new JFrame("Shapes");
    frame.setPreferredSize(new Dimension(700,700));
    mainPanel.getPreferredSize();
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.pack();
    frame.setLocationByPlatform(true);
    frame.getContentPane().add(mainPanel);
    frame.setVisible(true);

  }

  public static void main(String[] args) {
    SwingUtilities.invokeLater(new Runnable() {
      public void run() {
        setupGUI();
      }
    });
  }
}

