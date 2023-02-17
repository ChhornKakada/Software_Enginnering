public class SubclassThread extends Thread {
  @Override
  public void run() {
    System.out.println("Running in thread...");
  }

  public static void main(String[] args) {
    SubclassThread s = new SubclassThread();
    s.setDaemon(true); // run in background
    s.start(); // start thread
    for (int i = 0; i < 10; i++)
      System.out.println(i + ". Doing other code in main thread...");
  }
}