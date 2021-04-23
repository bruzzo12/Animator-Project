package cs5004.animator.view;

import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.geom.Point2D;
import java.util.ArrayList;

import javax.swing.*;

import cs5004.animator.model.IModelImpl;

public class DrawPanel extends JPanel implements IView {
  private Point2D shapePosition;


  public DrawPanel(){
    super();
    this.setBackground(Color.WHITE);
    JFrame frame = new JFrame();
    frame.getContentPane().setLayout(null);

  }
  //protected void Tween();
  @Override
  protected void paintComponent(Graphics g){
    super.paintComponent(g);
    Graphics2D g2d = (Graphics2D) g;
    g2d.setColor(Color.BLACK);

  }

  @Override
  public void makeVisible() {

  }

  @Override
  public void setCommandButtonListener(ActionListener actionEvent) {

  }

  @Override
  public void showErrorMessage(String error) {

  }

  @Override
  public void refresh() {

  }

  @Override
  public void setShapes(ArrayList<ArrayList<cs5004.animator.model.Shape>> frames) {

  }

  @Override
  public void getShapeType() {

  }

  @Override
  public void setOffset(cs5004.animator.model.Point2D offset) {

  }

  @Override
  public void setDimensions(double width, double height) {

  }

  @Override
  public String getText(IModelImpl model) {
    return null;
  }
}
