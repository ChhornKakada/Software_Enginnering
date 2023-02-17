public class ThreadGenerator implements Runnable {
  private int index;

  public ThreadGenerator(int index) { this.index = index; }

  @Override
  public void run() {
    System.out.print(index + " ");
  }

  public static void main(String[] args) {
    Util.clsScr();
    System.out.print("\n\tEnter number of threads : ");
    int numberOfThread = Util.getInt("\tX - Integer only. Input again : ");

    Thread threads[] = new Thread[numberOfThread];
    System.out.print("\tThreads : ");
    for (int i = 0; i < numberOfThread; i++) {
      ThreadGenerator tGenerator = new ThreadGenerator(i);
      threads[i] = new Thread(tGenerator);
      // threads[i] = new Thread(new ThreadGenerator(i));
      threads[i].start();
    }

    // delay of the threads speed
    try {
      Thread.sleep(100);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }

    /* 
    Once use join function, the threads are like the loop.
    The next thread execute even if the current thread is finished.
    It execute by order. (FIFO)
    */
    System.out.print("\n\tThreads by order : ");
    for (int i = 0; i < numberOfThread; i++) {
      threads[i] = new Thread(new ThreadGenerator(i));
      threads[i].start();
      try {
        threads[i].join();
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    }
    System.out.println("\n\n");
  }

}
