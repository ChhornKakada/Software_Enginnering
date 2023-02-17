import java.util.Timer;
import java.util.TimerTask;
import java.io.*;

public class TestInputTimmer {
  private String str = "";

  TimerTask task = new TimerTask() {
    public void run() {
      if (str.equals("")) {
        System.out.println("you input nothing. exit...");
        System.exit(0);
      }
    }
  };

  // get input from user
  public void getInput() throws Exception {
    Timer timer = new Timer();
    int delayTimeInSecond = 5;
    timer.schedule(task, delayTimeInSecond * 1000);

    System.out.println("Input a string within 10 seconds: ");

    // Scanner 
    BufferedReader in = new BufferedReader(
        new InputStreamReader(System.in));
    str = in.readLine();

    // timer cancel is like return when the code meet it, it will break other code below won't execute
    timer.cancel();
    System.out.println("you have entered: " + str);
  }

  public static void main(String[] args) {
    try {
      (new TestInputTimmer()).getInput();
    } catch (Exception e) {
      System.out.println(e);
    }
    System.out.println("main exit...");
  }
}