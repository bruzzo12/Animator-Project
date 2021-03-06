package cs5004.animator;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;

import cs5004.animator.controller.ControllerImpl;
import cs5004.animator.model.IModelImpl;
import cs5004.animator.model.Shape;
import cs5004.animator.util.AnimationReader;
import cs5004.animator.view.ButtonSelection;
import cs5004.animator.view.ButtonView;
import cs5004.animator.view.SvgView;
import cs5004.animator.view.TextView;
import cs5004.animator.view.VisualView;

public final class EasyAnimator {

  public static void main(String[] args) throws FileNotFoundException {
    String viewType = "";
    File input = null;
    File output = null;
    int speedValue = 1;
    AnimationReader reader = new AnimationReader();
    IModelImpl.Builder builder = new IModelImpl.Builder();

    for (int i = 0; i < args.length; i++) {
      if (args[i].equalsIgnoreCase("-in")) {
        input = new File(args[++i]);
        System.out.println(input);
        if (!input.isFile()) {
          throw new IllegalArgumentException("No readable input file.");
        }
      }

      if (args[i].equalsIgnoreCase("-view")) {
        switch (args[++i]) {
          case "visual":
            viewType = "visual";
            break;
          case "text":
            viewType = "text";
            break;
          case "svg":
            viewType = "svg";
            break;
          case "playback":
            viewType = "playback";
            break;
          default:
            throw new IllegalArgumentException("View-type must be declared.");
        }
      }
      if (args[i].equalsIgnoreCase("-speed")) {
        try {
          speedValue = Integer.parseInt(args[++i]);
        } catch (NumberFormatException e) {
          System.out.println("Speed must be an integer.");
        }
      }
      if (args[i].equalsIgnoreCase("-out")) {
        output = new File(args[++i]);
      }
    }


    Readable readable = new FileReader(input);
    IModelImpl model = (IModelImpl) reader.parseFile(readable, builder);

    if (viewType.equals("text")) {
      TextView view = new TextView();
      if (output == null) {
        System.out.println(view.getText(model));
      } else {
        BufferedWriter writer = null;
        try {
          writer = new BufferedWriter(new FileWriter(output, true));
          writer.write(view.getText(model));
        } catch (Exception e) {
          e.printStackTrace();
        } finally {
          try {
            writer.close();
          } catch (Exception e) {
            System.err.println(e);
          }
        }
      }
    }

    if (viewType.equals("svg")) {
      SvgView view = new SvgView();
      if (output == null) {
        System.out.println("Must specify output file");
      } else {
        BufferedWriter writer = null;
        try {
          writer = new BufferedWriter(new FileWriter(output, true));
          writer.write(view.getText(model));
        } catch (Exception e) {
          e.printStackTrace();
        } finally {
          try {
            writer.close();
          } catch (Exception e) {
            System.err.println(e);
          }
        }
      }
    }

    if (viewType.equals("visual")) {
      VisualView view = new VisualView();
      if (output == null) {
        int ticker;
        ArrayList<ArrayList<Shape>> animationFrames = new ArrayList<> ();
        for (ticker = 0; ticker < model.getMax(); ticker++) {
          animationFrames.add(model.getShapesAtTicker(ticker));
        }
        view.setDimensions(model.getWidth(), model.getHeight());
        view.setOffset(model.getOffset());
        view.setShapes(animationFrames);
        view.setEndTime(model.getMax());
        view.setSpeedValue(speedValue);
        view.setVisible(true);
        view.startTimer();
      } else {
        System.out.println("No need for output file!");
      }
    }

    if (viewType.equals("playback")) {
      int ticker;
      ArrayList<ArrayList<Shape>> animationFrames = new ArrayList<> ();
      for (ticker = 0; ticker < model.getMax(); ticker++) {
        animationFrames.add(model.getShapesAtTicker(ticker));
      }
      ButtonView buttonView = new ButtonView(model, speedValue );
      buttonView.setDimensions(model.getWidth(), model.getHeight());
      buttonView.setOffset(model.getOffset());
      buttonView.setShapes(animationFrames);
      buttonView.setEndTime(model.getMax());
      buttonView.setSpeedValue(speedValue);
      ControllerImpl controller = new ControllerImpl(buttonView);
      controller.animate();
    }
  }

}

