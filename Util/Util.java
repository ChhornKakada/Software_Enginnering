package Util;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.Scanner;

public class Util {

    private static Scanner input = new Scanner(System.in);
    public Util() {}

    // clear screen
    public static void clsScr() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    // press any key to continue
    public static void enterPause(String label) {
        System.out.print(label);
        try{System.in.read();}catch(Exception e){e.printStackTrace();}
    }

    // press any key to continue
    public static void pause(String tap) {
        System.out.print(tap+ "Press enter to continue...");
        try{System.in.read();}catch(Exception e){e.printStackTrace();}
    }

    public static int getInt(String label, String errorInput) {
        // Scanner sc = new Scanner(System.in);
        System.out.print(label);
        while(!input.hasNextInt()) {
            System.out.print("\033[0;31m" +errorInput+ "\u001B[0m");
            input.next();
        }
        return input.nextInt();
    }

    public static int getInt(String errorInput) {
        while(!input.hasNextInt()) {
            System.out.print(errorInput);
            input.next();
        }
        return input.nextInt();
    }

    public static int getOnly1or2(String errorInput) {
        int temp = Util.getInt(errorInput);
        if(temp != 1 && temp != 2) {
            System.out.print(errorInput);
            temp = Util.getOnly1or2(errorInput);
        }
        return temp;
    }

    public static double getDouble(String label, String errorInput) {
        System.out.print(label);
        while(!input.hasNextDouble()) {
            System.out.print(errorInput);
            input.next();
        }
        return input.nextDouble();
    }

    public static double getDouble(String errorInput) {
        while(!input.hasNextDouble()) {
            System.out.print(errorInput);
            input.next();
        }
        return input.nextDouble();
    }


    public static SimpleDateFormat dfm = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
    public static SimpleDateFormat sqlDfm = new SimpleDateFormat("yyyy-MM-dd");
    public static SimpleDateFormat ndfm = new SimpleDateFormat("dd-MM-yyyy");
    // public static Scanner input = new Scanner(System.in);
  
    // Insert date until exceptable
    public static Date inputDate(String lable, String dateFormat) {
      Date date = new Date();
      Scanner sc = new Scanner(System.in);
      SimpleDateFormat dfm = new SimpleDateFormat(dateFormat);
      while(true) {
        try {
          System.out.print(lable);
          date = dfm.parse(sc.nextLine());
          break;
        } catch(ParseException e) {
          System.out.println("\tError - Date formate is invalid!\n");
        }
      }
    //   sc.close();
      return date;
    }

    // Convert date to String 
    public static String formatDate(Date date, String format) {
        SimpleDateFormat dfm = new SimpleDateFormat(format);
        return dfm.format(date);
    }

    // Convert from String to Date
    public static Date lineToDate(String line, String format) {
        try {
            SimpleDateFormat dfm = new SimpleDateFormat(format);
            Date tmpDate = new Date();
            tmpDate = dfm.parse(line);
            return tmpDate;
        } catch(Exception e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    // 
    public static String trimTo(String str, int numberOfIndex) {
        if(str.length() <= numberOfIndex) return str;
        else {
            String result = "";
            for(int i=0; i<numberOfIndex-3; i++) {
                result += str.charAt(i);
            }
            result += "...";
            return result;
        }
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
