import java.util.Random;

public class brainTest extends Thread {
  private Integer result = null;
  public static int min = 0, max = 9;

  public Integer getResult() {
    return result;
  }

  @Override
  public void run() {
    this.result = Util.getInt("\tX - Integer only. Input again : ");
  }

  public static void totalFinal(int numberOfCorrect, int numberOfWrong) {
    int total = numberOfCorrect + numberOfWrong;
    String correctS = numberOfCorrect > 1 ? "s" : "";
    String totalS = total > 1 ? "s" : "";

    System.out.println("Among " + total + " question" + totalS + ", you can answer " + numberOfCorrect + " question" + correctS + ".");
    System.out.println(numberOfCorrect <= 4 ? "Baby brain" : (numberOfCorrect <= 7 ? "Normal brain" : "Master Brain"));
    System.out.println("\n\n");
  }

  public static int getRandomNumber() {
    return new Random().nextInt(max - min + 1) + min;
  }

  public static void main(String[] args) {
    Util.clsScr();
    int correct = 0, wrong = 0;
    System.out.println("""
        Let's test your brain...
        You have only 2s for each question.
          """);

    while (correct + wrong != 10) {
      int num1 = getRandomNumber();
      int num2 = getRandomNumber();
      System.out.print("\t" + num1 + " + " + num2 + " = ");
      brainTest bt = new brainTest();
      bt.start();

      // delay time 2s
      try {
        Thread.sleep(2000);
      } catch (InterruptedException e) {
        e.getStackTrace();
      }

      if (bt.result == null) {
        wrong++;
        System.out.print("?\n\nTime out!\n");
        totalFinal(correct, wrong);
        System.exit(0);
      }

      if (bt.result == num1 + num2)
        correct++;
      else
        wrong++;
    }

    System.out.println("\nCongrat!!! You have finished all the tests.");
    totalFinal(correct, wrong);
  }
}
