
public class Circle {
  private Point center, p;

  public Circle(Point center, Point p) {
    this.center = center;
    this.p = p;
  }

  public Point getPoint() { return this.p; }
  public Point getCenter() { return this.center; }

  public void setCenter(Point center){
    if(this.center != null) this.center = center;
    else System.out.println("ERROR: center can't be null.");
    // TODO: Use Exception instead of sysout
  }

  public double radius() { return center.distance(p); }
  public double length() { return 2 * Math.PI * radius(); }
  public double surface() { return Math.PI * Math.pow(radius(), 2); }

  static void showMenu() {
    System.out.println("""
        \t-----------------------------------------------
        \t    1. Calculate the radius 
        \t    2. Calculate the length 
        \t    3. Calculate the surface
        \t    4. Reset center and point of the circle
        \t    5. End the program
        \t-----------------------------------------------
        """);
  }

  public static void main(String[] args) {

    Util util = new Util();
    String errorText = util.RED+ "\tX - Invalid, try again: " +util.RESET;
    int opt = 1;
  
    while(opt != 5){
      util.clsScr();

      Point center = new Point(util.getPoint("\n\tEnter center(x, y): ", errorText));
      Point p = new Point(util.getPoint("\tEnter point(x, y): ", errorText));
      Circle circle = new Circle(center, p);

      
      while(2 > 1){
        util.clsScr();
        System.out.printf(util.YELLOW+ "\n\t|>" +util.RESET+ " center(%d,%d) and point(%d,%d) of Circle.\n\n", circle.getCenter().getX(), 
                          circle.getCenter().getY(), circle.getPoint().getX(), circle.getPoint().getY());

        showMenu(); 

        opt = util.getInt("\tInput option: ", errorText);
        if(opt == 1) System.out.printf(util.YELLOW+ "\n\t|> Radius: " +util.RESET+ "%.4f\n\n", circle.radius());
        else if(opt == 2) System.err.printf(util.YELLOW+ "\n\t|> Length: " +util.RESET+ "%.4f\n\n" ,circle.length());
        else if(opt == 3) System.out.printf(util.YELLOW+ "\n\t|> Surface: " +util.RESET+ "%.4f\n\n", circle.surface());
        else if(opt == 4 || opt == 5) break;
        else System.out.println(util.RED+ "\tX - invalid input.\n" +util.RESET); 

        // System.out.println("\t");
        util.pause("\t");
        
      }
    }
  }
}