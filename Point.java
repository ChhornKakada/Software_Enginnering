public class Point {
  private int x, y;

  public Point() { this(0, 0); }
  public Point(int x) { this(x, 0); }
  public Point(Point p) {
    this.x = p.x;
    this.y = p.y;
  }

  public Point(int x, int y) {
    this.x = x;
    this.y = y;
  }

  public int getX () { return this.x; }
  public int getY () { return this.y; }

  public void setX(int x) { this.x = x; }
  public void setY(int y) { this.y = y; }

  public void setXY(int x, int y) {
    this.x = x;
    this.y = y;
  }

  public double distance(Point p) {
    double x2 = this.x, y2 = this.y;
    double x1 = p.x, y1 = p.y;
    return Math.sqrt(Math.pow(x2-x1, 2) + Math.pow(y2-y1, 2));
  }

}
