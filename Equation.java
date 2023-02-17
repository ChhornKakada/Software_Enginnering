import java.util.Scanner;

public class Equation {
    public static void main(String[] args) {
        Scanner sn = new Scanner(System.in);
        System.out.println("Program for calculating equation 1/x = 1/y + 1/z");

        System.out.print("Please input y: ");
        // check validation of y
        while (!sn.hasNextFloat()){
            sn.nextLine();
            System.out.println("\t--> The input need to be a number.");
            System.out.print("    Input y again: ");
        }
        Float y = sn.nextFloat();
        
        System.out.print("Please input z: ");
        while (!sn.hasNextFloat()){
            sn.nextLine(); sn.nextLine();
            System.out.println("\t--> The input need to be a number.");
            System.out.print("    Input y again: ");
        }
        Float z = sn.nextFloat();

        // Calculating
        Float x = 1 / (1/y + 1/z);
        System.out.printf("Result x = %.2f", x);
    }
}
