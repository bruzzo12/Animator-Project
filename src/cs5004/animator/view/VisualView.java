package cs5004.animator.view;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;

import javax.swing.*;

public class VisualView extends JFrame implements IView {
  private JButton commandButton, quitButton;
  private JPanel buttonPanel;
  private DrawPanel drawPanel;
  private JTextField input;
  private JLabel display;
  private Timer timer;
  private IView view;


  public VisualView() {
    super();
    view = this;
    this.setTitle("ModelAnimator!");
    this.setSize(500, 500);
    this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

    this.setLayout(new BorderLayout());
    drawPanel = new DrawPanel();
    drawPanel.setPreferredSize(500, 500);
    this.add(drawPanel, BorderLayout.CENTER);

    buttonPanel = new JPanel();
    buttonPanel.setLayout(new FlowLayout());
    this.add(buttonPanel, BorderLayout.SOUTH);

    input = new JTextField(15);
    buttonPanel.add(input);

    timer = new Timer(1000, new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        view.refresh();
      }
    });

    commandButton = new JButton("Start Animation");
    setCommandButtonListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        timer.start();
        // Code/methods that loads and run the animations.
      }
    });
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
    this.repaint();
  }

  @Override
  public void setShapes() {

  }

  @Override
  public void setText() {

  }
}
///test