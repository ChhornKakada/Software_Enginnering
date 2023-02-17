import java.util.Scanner;

public class InputDistance {

  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in); // Create a Scanner object

    System.out.println("What is the powerhouse of the cell?"); // First question

    long startTime = System.currentTimeMillis(); // Save start time
    String answer = scanner.nextLine(); // Read user input
    long endTime = System.currentTimeMillis(); // Save time after enter
    long questionTime = (endTime - startTime) / 1000; // Calculate difference and convert to seconds

    if (answer.equals("Mitochondria")) { // Check if answer is correct and print output
      System.out.println("The answer is correct, and user took " + questionTime + " seconds to press enter");
    } else {
      System.out.println("The answer is wrong, and user took " + questionTime + " seconds to press enter");
    }

    scanner.close();
  } 
}