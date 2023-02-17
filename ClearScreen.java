import java.util.Scanner;
import javax.sound.midi.Synthesizer;

public class ClearScreen {
    // clear screen
    static void clsScr() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    // press any key to continue
    static void pressEnterToConti() {
        System.out.print("Press enter to continue...");
        try{System.in.read();}catch(Exception e){e.printStackTrace();}
    }
}
