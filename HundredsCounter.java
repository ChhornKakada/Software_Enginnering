import java.util.Scanner;

public class HundredsCounter {
    public static void main(String[] args) {
        Scanner sn = new Scanner(System.in);
        System.out.println("Program for counting the number of hundreds.");
        System.out.print("Please input a positive number: ");
        int number;

        // check validation of the input
        while (!sn.hasNextInt()){
            sn.nextLine();
            System.out.println("\t--> Please input as number!");
            System.out.print("    Input again: ");
        }

        // calculating
        number = sn.nextInt();
        int result = number / 100;

        // result 
        System.out.println("\n--- Result ---");
        System.out.printf("There are %d hundred in number %d.\n", result, number);
    }
}
