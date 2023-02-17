import java.nio.channels.Pipe;
import java.util.Scanner;

import javax.swing.text.AbstractDocument.LeafElement;

public class Util {

    private Scanner input = new Scanner(System.in);

    public Util() {}


    // clear screen
    public void clsScr() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    // press any key to continue
    public void pause(String tap) {
        System.out.print(tap+ "Press enter to continue...");
        try{System.in.read();}catch(Exception e){e.printStackTrace();}
    }

    public int getInt(String label, String errorInput) {
        System.out.print(label);
        while(!input.hasNextInt()) {
            System.out.print("\033[0;31m" +errorInput+ "\u001B[0m");
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

    // method get Point
    public Point getPoint(String lable, String ErrorInput) {
        System.out.print(lable);
        
        String[] get = this.input.nextLine().split(",");
        boolean conti_ = false;

        do{

            // when (get[0], get[1] != int) and get.length != 2
            if(conti_) {
                System.out.print(ErrorInput);
                get = this.input.nextLine().split(",");
            }
            conti_ = false;

            if(get.length == 2){
                get[0] = get[0].trim();
                get[1] = get[1].trim();
                if(!isInteger(get[0]) || !isInteger(get[1])) conti_ = true;
            } else conti_ = true;
        } while(conti_);

        Point p = new Point(Integer.parseInt(get[0]), Integer.parseInt(get[1]));
        return p;
    }

    public boolean isInteger(String s) {
        try { 
            Integer.parseInt(s); 
        } catch(NumberFormatException e) { 
            return false; 
        } catch(NullPointerException e) {
            return false;
        }
        // only got here if we didn't return false
        return true;
    }

    // Reset
    public static final String RESET = "\033[0m";  // Text Reset

    // Regular Colors
    public static final String BLACK = "\033[0;30m";   // BLACK
    public static final String RED = "\033[0;31m";     // RED
    public static final String GREEN = "\033[0;32m";   // GREEN
    public static final String YELLOW = "\033[0;33m";  // YELLOW
    public static final String BLUE = "\033[0;34m";    // BLUE
    public static final String PURPLE = "\033[0;35m";  // PURPLE
    public static final String CYAN = "\033[0;36m";    // CYAN
    public static final String WHITE = "\033[0;37m";   // WHITE

}
