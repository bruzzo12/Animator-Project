package cs5004.animator.view;

import javax.swing.*;

public class ButtonView extends VisualView{
  private IView visualView;

  public ButtonView(IView visualView){
    super();
    this.visualView = visualView;
    //Add radio Buttons for start, pause, resume and restart functionality
    JPanel radioPanel = new JPanel();
    radioPanel.setBorder(BorderFactory.createTitledBorder("Radio buttons"));
    radioPanel.setLayout(new BoxLayout(radioPanel, BoxLayout.PAGE_AXIS));
    JRadioButton[] radioButtons = new JRadioButton[4];

    //buttons groups are used to combine radio buttons. Only one radio
    // button in each group can be selected.
    ButtonGroup rGroup1 = new ButtonGroup();
    String[] radioButtonText = {"start","pause","resume","restart"};
    for (int i = 0; i < radioButtons.length; i++) {
      radioButtons[i] = new JRadioButton(radioButtonText[i]);
      //radioButtons[i].setSelected(false);
      radioButtons[i].setActionCommand(radioButtonText[i]);
      radioButtons[i].addActionListener(this);
      rGroup1.add(radioButtons[i]);
      radioPanel.add(radioButtons[i]);
    }
    this.add(radioPanel);
  }

  public static void main(String args[]){
    IView ButtonView = new ButtonView(new VisualView());


  }

}
