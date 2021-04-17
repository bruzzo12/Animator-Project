import org.junit.Test;

import java.io.FileReader;
import cs5004.animator.model.IModelImpl;
import cs5004.animator.util.AnimationReader;
import cs5004.animator.view.IView;
import cs5004.animator.view.TextView;
import static org.junit.Assert.assertEquals;

/**
 * This test class tests the TextView class.
 */
public class TextViewTest {

  /**
   * Tests the getText() method in the TextView.
   */
  @Test
  public void testGetText() {
    try {
      IModelImpl.Builder builder = new IModelImpl.Builder();
      AnimationReader reader = new AnimationReader();
      IView view = new TextView();
      Readable f = new FileReader("smalldemo.txt");
      IModelImpl m = (IModelImpl) reader.parseFile(f, builder);
      assertEquals("Shapes:\n"
              + "Name: R\n"
              + "Type: Rectangle\n"
              + "Min corner: (200.0,200.0) Width: 50.0, Height: 100.0,\n"
              + "Color: (255,0,0)\n"
              + "Appears at t=1\n"
              + "Disappears at t=100\n"
              + "\n"
              + "Name: C\n"
              + "Type: oval\n"
              + "Center (440.0,70.0), X radius: 60.0, Y radius: 30.0\n"
              + "Color: (0,0,255)\n"
              + "Appears at t=6\n"
              + "Disappears at t=100\n"
              + "\n"
              + "Shape R moves from (200.0,200.0) to (300.0,300.0) from t=10 to t=50\n"
              + "Shape C moves from (440.0,70.0) to (440.0,250.0) from t=20 to t=50\n"
              + "Shape C changes color from (0, 0, 255) to (2, 85, 170) from t=50 to t=70\n"
              + "Shape C moves from (440.0,250.0) to (440.0,370.0) from t=50 to t=70\n"
              + "Shape R scales from Width: 50.0, Height: 100.0 to Width: 25.0, "
              + "Height: 100.0 from t=51 to t=70\n"
              + "Shape R moves from (300.0,300.0) to (200.0,200.0) from t=70 to t=100\n"
              + "Shape C changes color from (2, 85, 170) to (2, 0, 255) from t=70 to t=80",
              view.getText(m));
    } catch (Exception e) {
      System.out.println(e);
    }
  }
}
