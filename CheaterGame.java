import java.util.Scanner;

import javax.net.ssl.SNIHostName;

public class CheaterGame {
    public static void main(String[] args) {
        Scanner sn = new Scanner(System.in);
        System.out.println("Program for guessing your luckiness.");
        
        int number;
        // check validation user-input
        System.out.print("Please input a positive number: ");
        while (!sn.hasNextInt()) {
            sn.nextLine();
            System.out.println("\t--> Please input as a positive number!");
            System.out.println("    Input again: ");
        }

        number = sn.nextInt(); // scan user
        System.out.printf("--> I got %d. I am luckier.", number + 1);
    }
}
