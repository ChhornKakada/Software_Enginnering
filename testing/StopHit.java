import java.util.Scanner;

public class StopHit extends Thread {
  public static boolean isStop = false;
  public static Scanner sc = new Scanner(System.in);

  @Override
  public void run() {
    while (!isStop) {
      isStop = sc.nextLine().equals("") ? true : false;
    }
  }

  public static void main(String[] args) {
    StopHit hitMe = new StopHit();
    hitMe.start();
    while (!isStop) {
      System.out.print("HitMe! ");
      try {
        Thread.sleep(200);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    }

    System.out.println("Thank you!");
  }
}
