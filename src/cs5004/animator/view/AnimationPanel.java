package cs5004.animator.view;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.*;

import cs5004.animator.model.IModel;
import cs5004.animator.model.IModelImpl;
import cs5004.animator.model.Oval;
import cs5004.animator.model.Point2D;
import cs5004.animator.model.Rectangle;
import cs5004.animator.model.Shape;

/**
 * This Animation Panel represents the area where the animations of shapes will take place.
 */
class AnimationPanel extends JPanel implements ActionListener {
  private IModel model;
  private String transformationType;
  private ArrayList<ArrayList<Shape>> frames;
  private Timer timer;
  public int speedValue;
  private int counter;
  private double offsetX;
  private double offsetY;

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
  public AnimationPanel(IModel m, int speedValue;/*ArrayList<ArrayList<Shape>> frames, int speedValue, String ShapeType int startValue,
                        int endValue, int startTime, int endTime, int startLocation, int endLocation*/) {
    super();
    this.model = m;
    this.speedValue = speedValue;
    this.timer = new Timer(1000 / speedValue, this);
    this.counter = 0;
    this.setBackground(Color.WHITE);

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


  public void setOffset(Point2D offset) {
    this.offsetX = offset.getX();
    this.offsetY = offset.getY();
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
        g2d.drawRect((int) (r.getX() - this.offsetX), (int) (r.getY() - this.offsetY),
                (int) r.getWidth(), (int) r.getHeight());
      }
      if (shape.getShapeType().toString().equals("Oval")) {
        Oval o = (Oval) shape;
        Color objectColor = new Color(shape.getRed(), shape.getGreen(), shape.getBlue());
        g2d.setColor(objectColor);
        g2d.drawOval((int) (o.getX() - this.offsetY), (int) (o.getY() - this.offsetY),
                (int) o.getRX(), (int) o.getRY());
      }
    }
  }


  @Override
  public void actionPerformed(ActionEvent e) {
    this.counter++;
    repaint();

  }

  public void beginTimer(){
    this.timer.start();
  }

  public void pauseTimer(){
    this.timer.stop();
  }

  public void resetTimer(){
    counter = 0;
    this.timer.restart();
  }
}