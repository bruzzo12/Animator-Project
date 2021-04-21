package cs5004.animator.view;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.*;

import cs5004.animator.model.IModelImpl;
import cs5004.animator.model.Shape;
import cs5004.animator.model.ShapeType;
import cs5004.animator.model.Rectangle;
import cs5004.animator.model.Oval;

/**
 * This Animation Panel represents the area where the animations of shapes will take place.
 */
class AnimationPanel extends JPanel {
  private IModelImpl model;
  private String transformationType;
  private ArrayList<ArrayList<Shape>> frames;
  public int speedValue;
  private int counter = 0;

  /* public int startValue;
  public int currentValue;
  public int endValue;
  public int startTime;
  public int endTime ;
  public int currentTime;
  public int startLocation;
  public int endLocation;
  public int currentLocation; */

  /**
   * Constructs an Animation Panel object.
   */
  public AnimationPanel(ArrayList<ArrayList<Shape>> frames, int speedValue, String ShapeType /*int startValue,
                        int endValue, int startTime, int endTime, int startLocation, int endLocation*/) {
    super();
    this.frames = frames;
    this.speedValue = speedValue;

    /* this.startValue = startValue;
    currentValue = startValue;
    this.endValue = endValue;
    this.startTime = startTime;
    this.endTime = endTime;
    this.currentLocation = startLocation;
    this.endLocation = endLocation;
    currentTime = 0;
System.out.println(currentTime);*/

    new Timer(1000 / this.speedValue, new ActionListener() {
      //System.out.println(String.format("Current time is: %d\n", currentTime));
      @Override
      public void actionPerformed(ActionEvent actEvt) {
       /* //System.out.println(String.format("Current time is: %d\n", currentTime));
        if (currentTime >= startTime) {
          System.out.println(String.format("Current time is: %d\n", currentTime));
          currentValue = tween(startValue, endValue, startTime, endTime, currentTime);
          currentLocation = tween(currentLocation, endLocation, startTime, endTime, currentTime);*/
          repaint();

        }
        if (currentTime == endTime) {
          ((Timer) actEvt.getSource()).stop();
        }
        counter++;
      }
    }).start();
  }

  public static int tween(int startValue, int endValue, int startTime, int endTime, int currentTime) {
    return (int) (startValue * (((double) endTime - (double) currentTime) /
            ((double) endTime - (double) startTime))) +
            (int) (endValue * (((double) currentTime - (double) startTime) /
                    ((double) endTime - (double) startTime)));
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
    // System.out.println(String.format("CurrentValue is :%d Current time is:%d",currentValue,
    //        currentTime));
    ArrayList<Shape> shapes = frames.get(counter);
    for (Shape shape : shapes) {
      if (shape.getShapeType().toString().equals("Rectangle")) {
        Rectangle r = (Rectangle) shape;
        Color objectColor = new Color(shape.getRed(), shape.getGreen(), shape.getBlue());
        g2d.setColor(objectColor);
        g2d.drawRect((int) r.getX(), (int) r.getY(), (int) r.getWidth(), (int) r.getHeight());
      }
      if (shape.getShapeType().toString().equals("Oval")) {
        Oval o = (Oval) shape;
        Color objectColor = new Color(shape.getRed(), shape.getGreen(), shape.getBlue());
        g2d.setColor(objectColor);
        g2d.drawOval((int) o.getX(), (int) o.getY(), (int) o.getRX(), (int) o.getRY());
      }
    }
  }


  private static void setupGUI() {
    //AnimationPanel newPanel = new AnimationPanel(100, "Rectangle", 34,
    //       100, 5, 300, 100, 500);
    AnimationPanel mainPanel = new AnimationPanel(100, "Rectangle",
            11, 30, 0, 300, 50, 700);
    JFrame frame = new JFrame("Shapes");
    frame.setPreferredSize(new Dimension(700, 700));
    mainPanel.getPreferredSize();
    // newPanel.getPreferredSize();
    // frame.getContentPane().add(newPanel);
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

