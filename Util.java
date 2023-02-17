import java.util.Scanner;

import javax.swing.text.Document;

public class Util {

    private Scanner input = new Scanner(System.in);

    public Util() {}


    // clear screen
    public void clsScr() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    // press any key to continue
    public void pause() {
        System.out.print("Press enter to continue...");
        try{System.in.read();}catch(Exception e){e.printStackTrace();}
    }

    public int getInt(String label, String errorInput) {
        System.out.print(label);
        while(!input.hasNextInt()) {
            System.out.print(errorInput);
            input.next();
        }
        return input.nextInt();
    }

    public int getInt(String errorInput) {
        while(!input.hasNextInt()) {
            System.out.print(errorInput);
            input.next();
        }
        return input.nextInt();
    }

    public double getDouble(String label, String errorInput) {
        System.out.print(label);
        while(!this.input.hasNextDouble()) {
            System.out.print(errorInput);
            this.input.next();
        }
        return this.input.nextDouble();
    }

    public double getDouble(String errorInput) {
        while(!this.input.hasNextDouble()) {
            System.out.print(errorInput);
            this.input.next();
        }
        return this.input.nextDouble();
    }
}
