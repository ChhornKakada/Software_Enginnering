package src;
import java.util.InputMismatchException;
import java.util.Scanner;

import Util.Util;

public class Point3D {
  private double x, y, z;

  // X
  public double getX() { return x; }
  public void setX(double x) { this.x = x; }

  // Y
  public double getY() { return y; }
  public void setY(double y) { this.y = y; }

  // Z
  public double getZ() { return z; }
  public void setZ(double z) { this.z = z; }

  // Construtor
  public Point3D() {
    this.x = 0;
    this.y = 0;
    this.z = 0;
  }

  public static Point3D dataInput() {
    Scanner sc = new Scanner(System.in);
    Point3D point = new Point3D();

    System.out.println("\tEnter point(x,y,z)");

    while(true) {
      try{
        System.out.print("\t  x = ");
        point.setX(sc.nextDouble());
        break;
      } catch(InputMismatchException e) {
        System.out.println("\tError - Input only number.");
        sc.next();
      }
    }
    
    // get Y
    while(true) {
      try{
        System.out.print("\t  y = ");
        point.setY(sc.nextDouble());
        break;
      } catch(InputMismatchException e) {
        System.out.println("\tError - Input only number.");
        sc.next();
      }
    }

    // get Z
    while(true) {
      try{
        System.out.print("\t  z = ");
        point.setZ(sc.nextDouble());
        break;
      } catch(InputMismatchException e) {
        System.out.println("\tError - Input only number.");
        sc.next();
      }
    }
    sc.close();
    return point;
  }

  @Override
  public String toString() {
    return "(x=" +this.x+ ", y=" +this.y+ ", z=" +this.z+ ")";
  }

}

class Tp9Ex2 {
  public static void main(String[] args) {
    Util.clsScr();
    System.out.println("\n");
    Point3D point = Point3D.dataInput();
    System.out.println("\n\tPoint : " +point+ "\n\n");
  }
}