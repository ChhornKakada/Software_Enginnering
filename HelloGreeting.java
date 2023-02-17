import java.rmi.dgc.VMID;
import java.util.Scanner;

public class HelloGreeting{
    public static void main(String[] args) {
        Scanner sn = new Scanner(System.in);
        System.out.print("Enter your name: ");
        String userName = sn.nextLine();
        System.err.printf("Hello, %s.", userName);
    }
}