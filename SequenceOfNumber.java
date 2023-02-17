import java.util.Scanner;

public class SequenceOfNumber {
  public static void main(String[] args) {

    Scanner input = new Scanner(System.in);
    examEx3.clsScr();

    System.out.print("\n\tEnter a positive number: ");
    int number;

    while(true) {
      while(!input.hasNextInt()) {
        System.out.print("\tInput only positive number: ");
        input.nextLine();
      }

      number = input.nextInt();
      if(number > 1) break;
      else System.out.print("\tInput only positive number: ");
    }

    int[] sequence = new int[number];

    System.out.println("\n\t");
    int check = 1;
    for(int i=1; i<=number; i++) {
      if(check == 1 || check == 2) {
        System.out.printf("%02d ", i);
        sequence[i-1] = i;
      }
      else if(check == 3) {
        System.out.printf("%02d ", i+1);
        sequence[i-1] = i+1;
      }
      else if(check == 4) {
        System.out.printf("%02d ", i-1);
        sequence[i-1] = i-1;
        check = 0;
      }
      check ++;
    }

    for(int i=number-1; i>=0; i--) {
      System.out.printf("%02d ", sequence[i]);
    }
    
    System.out.println("\n\n");

  }

}