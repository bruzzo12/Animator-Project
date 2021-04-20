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
  public int currentTime;
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
System.out.println(currentTime);

    new Timer(this.speedValue, new ActionListener() {
      //System.out.println(String.format("Current time is: %d\n", currentTime));
      @Override
      public void actionPerformed(ActionEvent actEvt) {
        System.out.println(String.format("Current time is: %d\n", currentTime));
        if(currentTime >= startTime){
          System.out.println(String.format("Current time is: %d\n", currentTime));
          currentValue = tween(startValue, endValue, startTime, endTime, currentTime);

          repaint();

        }
        if(currentTime == endTime){
          ((Timer)actEvt.getSource()).stop();
        }
        currentTime++;
      }
    }).start();
  }

  public static int tween(int startValue, int endValue, int startTime, int endTime, int currentTime) {
    return (int)(startValue * (((double)endTime - (double)currentTime) /
            ((double)endTime - (double)startTime))) +
            (int)(endValue * (((double)currentTime - (double)startTime) /
                    ((double)endTime - (double)startTime)));
  }


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
    Graphics2D g2d = (Graphics2D) g.create();
    System.out.println(String.format("CurrentValue is :%d Current time is:%d",currentValue, currentTime));
      Color objectColor = new Color(currentValue, 12, 45);
      g2d.setColor(objectColor);
      g2d.drawRect(currentTime, 300, currentValue, 12);
      g2d.fillRect(currentTime, 300, currentValue, 12);



  }
  private static void setupGUI(){
    AnimationPanel mainPanel = new AnimationPanel(30,"Rectangle",
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

