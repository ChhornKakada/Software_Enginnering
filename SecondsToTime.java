
import java.util.Scanner;

public class SecondsToTime {
    public static void main(String[] args) {
        ClearScreen.clsScr(); // clear screen
        
        int secInput;
        Time time = new Time();

        Scanner input = new Scanner(System.in);

        System.out.print("\nInput number of seconds: ");
        secInput = input.nextInt();
        time = Time.secToTime(secInput);

        System.out.println("Time corresponding to " +secInput+ " seconds is " +time.hour+ ":" +time.mn+ ":" +time.second+ ".\n");
        
    }
}