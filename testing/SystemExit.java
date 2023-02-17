public class SystemExit {
  public static void main(String[] args) {
    int arr[] = { 1, 2, 3, 4, 5, 6, 7, 8 };

    for (int i = 0; i < arr.length; i++) {
      if (arr[i] == 5) {
        System.out.println("exit...");

        // Terminate JVM
        System.exit(0);
        // break;

        /*
         * System.exit(0) is similar to break;
         * but 'break' is only break the loop while 'System.exit(0)'  break the program
         */

      } else
        System.out.println("arr[" + i + "] = " +
            arr[i]);
    }
    System.out.println("End of Program");
  }
}
