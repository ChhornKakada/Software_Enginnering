import java.util.Scanner;

import javax.net.ssl.SNIMatcher;

public class Rectangle{
    public static void main(String[] args) {
        Scanner sn = new Scanner(System.in);
        System.out.println("Program for calculating permimeter and surface of a Rectangle.");

        // get width
        System.out.print("Please input width (in meter): ");
        while (!sn.hasNextFloat()){
            sn.nextLine();
            System.out.println("\t--> The input need to be number.");
            System.out.print("    Input again: ");
        }
        Float width = sn.nextFloat();

        // get height
        System.out.print("Please input height (in meter): ");
        while (!sn.hasNextFloat()){
            sn.nextLine(); sn.nextLine();
            System.out.println("\t--> The input need to be number.");
            System.out.print("    Input again: ");
        }
        Float height = sn.nextFloat();

        // calculating
        Float surface = width * height;
        Float perimeter = (width + height) * 2;

        // printing result
        System.out.println("\nResult: ");
        System.out.printf("Perimeter = (%.2f + %.2f) * 2 = %.2fm\n", width, height, perimeter);
        System.out.println("Surface = " +width+ " * " +height+ " = " +surface+ "m^2");
    }
}