import java.util.Scanner;
import javax.print.FlavorException;

public class RielsToDollars {
    public static void main(String[] args) {
        ClearScreen.clsScr(); // clear screen

        Scanner input = new Scanner(System.in);
        double dollars;
        int riels;

        System.out.print("""
            \nProgram for converting money in Riels to Dollars.
            Conversion rate is: 1 USD = 4000 RIELS
                """);
        
        System.out.print("Please input money in Riels: ");
        riels = input.nextInt();
        dollars = riels / 4000.00;

        System.out.println("\n" +riels+ " RIELS = " +String.format("%.4f", dollars)+ " USD\n");
    }
}
