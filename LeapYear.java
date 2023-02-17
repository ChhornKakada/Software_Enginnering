import java.util.Scanner;

public class LeapYear {
    private int year;
    public LeapYear() {}
    public LeapYear(int year) {this.year = year;}
    public void setYear(int year) {this.year = year;}
    public int getYear() { return this.year; }

    public boolean isValid() {
        if(this.year <= 0) return false;
        return true;
    }

    public boolean isLeapYear() {
        if(this.year % 4 != 0) return false;
        else {
            if(this.year % 100 != 0) return true;
            else return this.year % 400 == 0 ? true:false;
        }
    }

    public static void main(String[] args) {
        Util util = new Util();
        util.clsScr();

        System.out.println("\n=== Checking weather it is a Leap year or not ===\n");
        LeapYear year = new LeapYear(util.getInt("Input year: ", 
                                            "X - Invalid input. Input again: "));

        if(year.isValid()) { // check validaion
            if(year.isLeapYear()) System.out.println("\nResult: " +year.getYear()+ " is a Leap year.\n");
            else System.out.println("\nResult: " +year.getYear()+ " is not a Leap year.\n");
        } 
        else System.out.println("\nError: Year need to be greater than 0.\n");
    }
}
