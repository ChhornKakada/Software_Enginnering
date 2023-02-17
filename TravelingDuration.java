
import java.util.Scanner;

public class TravelingDuration {
    public static void main(String[] args) {
        ClearScreen.clsScr(); // clear screen

        double speed = 30000/3600, percentage, distance = 7000, timeSec;
        Scanner input = new Scanner(System.in);
        Time duration = new Time();

        System.out.println("\nProgram for calculating duration of travel from ITC to Airport.");
        System.out.print("Please input traffic jam factor (in percentage [0-100]): ");

        percentage = input.nextDouble();
        timeSec = distance / (speed * (percentage / 100));

        long timeSecInt = Math.round(timeSec);
        duration = Time.secToTime((int)timeSecInt);
        System.out.println("\nTravelling Duration = " +duration.hour+ ":" +duration.mn+ ":" +duration.second+ "\n");
    }
}
