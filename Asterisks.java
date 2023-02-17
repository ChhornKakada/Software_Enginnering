import java.util.Scanner;

public class Asterisks {
    void A(){
      System.out.println("""
        \nA.
        *************************
        *                       *
        *                       *
        *                       *
        *                       *
        *************************
          """);
    }

    void B(){
      System.out.println("""
        \nB.
            *
           ***
          *****
         *******
        *********
          """);
    }

    void C(){
      System.out.println("""
        \nC.
        *
        ***
        *****
        *******
        *********
          """);
    }

    void D(){
      System.out.println("""
        \nD. 
                *
              ***
            *****
          *******
        *********
          """);
    }

    void E(){
      System.out.println("""
        \nE.
            *
          ***
        *****
          ***
            *
          """);
    }

    void F(){
      System.out.println("""
        \nF.
          *
         ***
        *****
         ***
          *
          """);
    }

    void G(){
      System.out.println("""
        \nG.
        *****
         ***
          *
         ***
        *****
          """);
    }

    void H(){
      System.out.println("""
        \nH.
            *
          *   *
        *       *
          *   *
            *
          """);
    }
    public static void main(String[] args) {
      Asterisks ast = new Asterisks();
      Scanner scan = new Scanner(System.in); // create reader
      System.out.print("Choose your display from A to H: ");
      char choosed = scan.next().charAt(0); // Read Value from User
      choosed = Character.toUpperCase(choosed); // Char to uppercase

      if (choosed == 'A'){
        ast.A();
      } else if (choosed == 'B'){
        ast.B();
      } else if (choosed == 'C'){
        ast.C();
      } else if (choosed == 'D'){
        ast.D();
      } else if (choosed == 'E'){
        ast.E();
      } else if (choosed == 'F'){
        ast.F();
      } else if (choosed == 'G'){
        ast.G();
      } else if (choosed == 'H'){
        ast.H();
      } else {
        System.out.println("You inputted the invalid character!");
      }
    }
}
