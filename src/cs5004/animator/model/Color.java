package cs5004.animator.model;

/**
 * Class used to store color value of cs5004.animator.model.Shape. Value of integers must be between
 * 0 and 255.
 */
public class Color {
  public int red;
  public int green;
  public int blue;

  /**
   * Constructor for cs5004.animator.model.Color Class. Three integers combined creates a color.
   *
   * @param red   value of red.
   * @param green value of green.
   * @param blue  value of blue.
   * @throws IllegalArgumentException for integer values less than 0 and greater than 255
   */
  public Color(int red, int green, int blue) {
    if (red > 255 || red < 0 || green < 0 || green > 255 || blue > 255 || blue < 0) {
      throw new IllegalArgumentException("Please enter a valid integer between 0 and 255.");
    }
    this.red = red;
    this.green = green;
    this.blue = blue;
  }

  /**
   * Returns string version of color.
   *
   * @return string version of color.
   */
  @Override
  public String toString() {
    return String.format("(%d,%d,%d)", this.red, this.green, this.blue);
  }


}
