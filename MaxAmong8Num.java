import java.util.Scanner;

public class MaxAmong8Num {
    private int[] number;

    public int getLength() { return this.number.length; }

    public MaxAmong8Num() {}
    public MaxAmong8Num(int[] num) {this.number = num; }

    // find maximum 
    public int Max() {
        int max = number[0];
        for(int i=0; i<number.length; i++){
            if(max < number[i]) max = number[i];
        }
        return max;
    }
    
    // find minimum
    public int Min() {
        int min = number[0];
        for(int i=0; i<number.length; i++){
            if(min > number[i]) min = number[i];
        }
        return min;
    }

    // function to convert array string to array int
    static int[] strArrToInt(String[] text){
        int[] result = new int[text.length];
        for(int i=0; i<text.length; i++){
            result[i] = Integer.parseInt(text[i]);
        }
        return result;
    }

    public static void main(String[] args) {
        Util util = new Util();
        util.clsScr();
        Scanner input = new Scanner(System.in);

        System.out.println("The program will find the maximum number among 8 number.");
        System.out.print("Please input 8 number follow this format(n1, n2, ..., n8): ");

        String[] textInput;

        while(true){
            String text = input.nextLine();
            textInput = text.split(", ");
            if(textInput.length != 8){
                System.out.println("\n--> You just input " +textInput.length+ " numbers. Please input only 8 numbers.\n");
                System.out.print("Input again: ");
            } else break;
        }

        // int[] num = new int[textInput.length];
        // num = strArrToInt(textInput);
        MaxAmong8Num number = new MaxAmong8Num(strArrToInt(textInput));
        System.out.println("\nThe maximum number among these number is: " +number.Max()+ "\n");
    }
}
