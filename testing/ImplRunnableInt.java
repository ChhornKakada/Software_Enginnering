public class ImplRunnableInt implements Runnable {
  @Override
  public void run() {
    System.out.println("Running in thread...");
  }

  public static void main(String[] args) {
    ImplRunnableInt ir = new ImplRunnableInt();
    Thread th = new Thread(ir);
    th.setDaemon(true);
    th.start();
    for (int i = 0; i < 10; i++)
      System.out.println(i + ". Doing other code in main thread...");
  }
}