import java.security.DrbgParameters.Reseed;
import java.util.Scanner;

import org.xml.sax.SAXException;

public class TimeToSeconds {
    public static void main(String[] args) {
        ClearScreen.clsScr(); // clear screen 
        
        Scanner input = new Scanner(System.in);

        Time time = new Time();
        int seconds;

        System.out.print("""
            \nProgram for converting time to seconds.
            Please input time...
                """);
        System.out.print("    hours: ");
        time.hour = input.nextInt();
        System.out.print("    minutes: ");
        time.mn = input.nextInt();
        System.err.print("    seconds: ");
        time.second = input.nextInt();

        seconds = Time.timeToSecond(time.hour, time.mn, time.second);
        String timeCal = time.hour + "*3600 + " + time.mn + "*60 + " + time.second;
        System.out.println("\nNumber of seconds = " +timeCal+ " = " +seconds+ "\n");
        
    }
}

