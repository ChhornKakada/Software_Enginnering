import java.util.Scanner;

public class StopHit extends Thread {
  @Override 
  public void run() {
    while (true) {
      System.out.print("HitMe! ");
      try {
        Thread.sleep(200);
      } catch (InterruptedException e) {
        e.getStackTrace();
      }
    }
  }

  public static void main(String[] args) {
    Util.clsScr();
    StopHit th = new StopHit();
    
    th.start();
    Scanner sc = new Scanner(System.in);
    while (true) {
      String stop = sc.nextLine();
      if (stop.equals("")) {
        System.out.println("Thank you!");
        sc.close();
        System.exit(0);
      }
    }
  }
}
