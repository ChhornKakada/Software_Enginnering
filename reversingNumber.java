import java.util.Scanner;

public class reversingNumber {
    private int num;

    public int getNum() { return num; }
    public int getDigit() { return String.valueOf(num).length(); }

    public reversingNumber(int n) { num = n; }

    public int getReverse() {
        int result = 0;
        if(this.isValid()){
            int remainder, n = this.num;
            for(int i=1; i<=4; i++){
                remainder = n % 10;
                result = result*10 + remainder;
                n /= 10;
            }
        }
        return result;
    }

    public boolean isValid() {
        if(num < 0) return false;
        if(this.getDigit() != 4) return false;
        return true;
    }
    
    public static void main(String[] args) {
        Util util = new Util();
        util.clsScr();

        String errorInt = "x - Invalid input, please input again: ";

        System.out.println("=== Reversing 4 digits number program ===");
        reversingNumber number = new reversingNumber(util.getInt("\nPlease input 4 digits number: ", errorInt));

        if(number.getNum() <= 0){
            System.out.println("\nError: invalid number.");
            System.out.println("       please input only positive number with 4 digits.\n");
        } 
        else if(number.isValid()) System.out.println("\nAfter reverse: " +number.getReverse()+ "\n");
        else {
            System.out.print("\nError: Invalid number!\n       Your input number is " +number.getDigit());
            if(number.getDigit() == 1) System.out.print(" digit.");
            else System.out.print(" digits.");
            System.out.print("\n       Please input only 4 digits number.\n\n");
        }
    }
}
