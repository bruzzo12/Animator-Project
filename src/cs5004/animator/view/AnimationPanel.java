package cs5004.animator.view;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.*;

import cs5004.animator.model.Oval;
import cs5004.animator.model.Point2D;
import cs5004.animator.model.Rectangle;
import cs5004.animator.model.Shape;

/**
 * This Animation Panel represents the area where the animations of shapes will take place.
 */
class AnimationPanel extends JPanel implements ActionListener {

  private ArrayList<ArrayList<Shape>> frames;
  public int speedValue = 1;
  private int counter = 0;
  private double offsetX;
  private double offsetY;
  private int ticks;
  private double endTime;
  private Timer timer;

  /**
   * Constructs an Animation Panel object.
   */
  public AnimationPanel() {
    super();
    this.setBackground(Color.WHITE);
    this.ticks = 0;
    this.timer = new Timer(1000 / speedValue, this);
  }

  public void setOffset(Point2D offset) {
    this.offsetX = offset.getX();
    this.offsetY = offset.getY();
  }

  public void setSpeedValue(int speed) {
    this.speedValue = speed;
  }

  public void setShapes(ArrayList<ArrayList<Shape>> frames) {
    this.frames = frames;
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
    ArrayList<Shape> shapes = frames.get(ticks);
    for (Shape shape : shapes) {
      System.out.println(shape.toString());
      if (shape.getShapeType().toString().equals("Rectangle")) {
        Rectangle r = (Rectangle) shape;
        Color objectColor = new Color(shape.getRed(), shape.getGreen(), shape.getBlue());
        g2d.setColor(objectColor);
        g2d.drawRect((int) (r.getX() - this.offsetX), (int) (r.getY() - this.offsetY),
                (int) r.getWidth(), (int) r.getHeight());
        System.out.println(r.toString());
      }
      if (shape.getShapeType().toString().equals("Oval")) {
        Oval o = (Oval) shape;
        Color objectColor = new Color(shape.getRed(), shape.getGreen(), shape.getBlue());
        g2d.setColor(objectColor);
        g2d.drawOval((int) (o.getX() - this.offsetY), (int) (o.getY() - this.offsetY),
                (int) o.getRX(), (int) o.getRY());
        System.out.println(o.toString());
      }
    }
  }

  public void setEndTime(double endTime) {
    this.endTime = endTime;
  }

  @Override
  public void actionPerformed(ActionEvent e) {
    ticks++;
    System.out.println(String.format("ticks: %d\n", ticks++));
    if (ticks > endTime) {
      ticks = 0;
    }
    this.repaint();
  }

  public void startTimer() {
    this.timer.start();
  }

  public void stopTimer() {
    this.timer.stop();
  }
}

