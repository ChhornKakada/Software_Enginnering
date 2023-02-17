import java.lang.invoke.CallSite;
import java.util.Scanner;

public class TasksRunner {
    public static void main(String[] args) {
        ClearScreen.clsScr(); // clear screen

        int opt;
        Scanner input = new Scanner(System.in);

        do {
            System.out.println("""
            \n-------- Menu ---------
            1. Seconds to Time
            2. Time to Seconds
            3. Calling Cost
            4. Riels to Dollar
            5. Traveling Duration
            0. Exit
            -----------------------
                """);

            System.out.print("Choose an option: ");
            opt = input.nextInt();

            switch (opt) {
                case 1:
                    SecondsToTime.main(null);
                    break;
                case 2:
                    TimeToSeconds.main(null);
                    break;
                case 3:
                    CallingCost.main(null);
                    break;
                case 4:
                    RielsToDollars.main(null);
                    break;
                case 5:
                    TravelingDuration.main(null);
                    break;
                default:
                    System.out.println("--> Input not valid. Try again.\n");
                    break;
            }

            if(opt != 0){
                ClearScreen.pressEnterToConti(); // press any key to continue
                ClearScreen.clsScr(); // clear screen
            } else System.out.println("The program end");

        } while(opt != 0);
    }
}
