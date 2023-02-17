public class ThreadGenerator2 extends Thread {
  private int index, start, end, numberOfPrimes = 0, sumOfPrimes = 0;

  public ThreadGenerator2(int index, int start, int end) {
    this.index = index;
    this.start = start;
    this.end = end;
  }

  private boolean isPrimeNumber(int number) {
    if (number <= 1)
      return false;
    for (int i = 2; i <= number / 2; i++) {
      if (number % i == 0)
        return false;
    }
    return true;
  }

  @Override
  public void run() {
    for (int i = this.start; i <= this.end; i++) {
      if (this.isPrimeNumber(i)) {
        System.out.print("t" + this.index + "-" + i + "  ");
        this.numberOfPrimes++;
        this.sumOfPrimes += i;
      }
    }
  }

  public int getNumberOfPrimes() {
    return this.numberOfPrimes;
  }

  public int getSumOfPrimes() {
    return this.sumOfPrimes;
  }

  public static void main(String[] args) {
    Util.clsScr();

    String errorInput = "X - Integer only. Input again : ";
    int sumOfPrimeNumbers = 0;
    int numberOfAllPrimes = 0;

    System.out.print("\nStart number : ");
    int start = Util.getInt(errorInput);
    System.out.print("End number   : ");
    int end = Util.getInt(errorInput);

    // Number of threads need to implement.
    int numThreads = (end - start) / 100 + ((end - start) % 100 == 0 ? 0 : 1);
    System.out.println("Running " + numThreads + " thread" + (numThreads > 1 ? "s" : "") + ".");

    ThreadGenerator2 threads[] = new ThreadGenerator2[numThreads];
    System.out.println();
    for (int i = 0; i < numThreads; i++) {
      threads[i] = new ThreadGenerator2(i, start, (start + 100) < end ? (start + 100) : end);
      threads[i].start();
      start += 100;
      sumOfPrimeNumbers += threads[i].getSumOfPrimes();
      numberOfAllPrimes += threads[i].getNumberOfPrimes();
    }

    /*
     * you need to use join function to ensure that all threads are done
     * before summation and count the number of prime-numbers
     */
    for (int i = 0; i < numThreads; i++) {
      try {
        threads[i].join();
      } catch (InterruptedException e) {
        e.getStackTrace();
      }
      sumOfPrimeNumbers += threads[i].getSumOfPrimes();
      numberOfAllPrimes += threads[i].getNumberOfPrimes();
    }

    System.out.println("\n\nTotal prime-numbers is " + numberOfAllPrimes + ".");
    System.out.println("Summation of all prime-numbers is " + sumOfPrimeNumbers + ".\n\n");

  }

}
