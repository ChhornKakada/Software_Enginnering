import java.util.Scanner;
import javax.sql.rowset.spi.TransactionalWriter;

public class CallingCost {
    public static void main(String[] args) {
        ClearScreen.clsScr(); // clear screen
        
        Scanner input = new Scanner(System.in);
        Time startTime = new Time(), endTime = new Time(), duraTime = new Time();
        int totalSec1, totalSec2;

        System.out.println("\nProgram for calculating cost of a call.");
        
        // read the start time
        System.out.println("Please input start time... ");
        System.out.print("\thours: ");
        startTime.hour = input.nextInt();
        System.out.print("\tminutes: ");
        startTime.mn = input.nextInt();
        System.out.print("\tseconds: ");
        startTime.second = input.nextInt();
        totalSec1 = Time.timeToSecond(startTime.hour, startTime.mn, startTime.second);

        // read the end time
        boolean Continue = false;
        do {
            if(Continue){ // telling user that the end time was smaller than start time
                System.out.print("""
                    \n--> End time need to be grater than Start time.
                        Input again.
                        """);
            }

            Continue = false;

            System.out.println("\nPlease input end time... ");
            System.out.print("\thours: ");
            endTime.hour = input.nextInt();
            System.out.print("\tminutes: ");
            endTime.mn = input.nextInt();
            System.out.print("\tseconds: ");
            endTime.second = input.nextInt();
            totalSec2 = Time.timeToSecond(endTime.hour, endTime.mn, endTime.second);

            if(totalSec2 < totalSec1) Continue = true;
        } while(Continue);

        // calculating Calling Cost
        int duraSec = totalSec2 - totalSec1;
        duraTime = Time.secToTime(duraSec);
        double minutes = duraSec / 60.00;
        double cost = minutes * 0.05;

        // display result
        System.out.println("\nTotal call duration: " +duraTime.hour+ "h " +duraTime.mn+ "mn " +duraTime.second+ "s");
        System.out.println("Total call of this call: $" +String.format("%.4f", cost)+ "\n");

    }
}
