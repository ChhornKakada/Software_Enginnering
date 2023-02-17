package solutions;
import utils.Util;

public class Rectangle {
  public int width, height;

  public Rectangle(int width, int height) {
    this.height = height;
    this.width = width;
  }

  public int calculatePerimeter() {
    return (this.height + this.width) * 2;
  }

  public int calculateSurface() {
    return this.width * this.height;
  }
}

class RectangleTest {
  public static void main(String[] args) {
    Util util = new Util();
    util.clsScr();

    Rectangle rec = new Rectangle(30, 20);
    System.out.printf(util.YELLOW+"\n\t|> " +util.RESET+ "(Height: %d, Width: %d) of the rectangle.", rec.height, rec.width);
    System.out.printf(util.YELLOW+"\n\t|> " +util.RESET+ "The surface of the rectangle is: %d\n\n", rec.calculateSurface());
  }

}