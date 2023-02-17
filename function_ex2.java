import javax.swing.text.Highlighter.Highlight;
import javax.swing.text.html.StyleSheet;
import javax.swing.text.html.HTMLDocument.HTMLReader.SpecialAction;

public class function_ex2 {

    // Asterisks A
    static void asterisks_A(int width, int length){
        for (int i=1; i<=width; i++){
            if ((i != 1) && (i != width)){
                System.out.print("*");
                for (int j=1; j<=length-2; j++){
                    System.out.print(" ");
                }
                System.out.print("*");
            } else {
                for (int k=1; k<=length; k++){
                    System.out.print("*");
                }
            }
            System.out.println("");
        }
    }

    // Asterisk B
    static void asterisks_B(int height){
        int k = 1; // for asterisks
        int o = 1; // for spacing
        for (int i=1; i<=height; i++){

            // print spacing
            for (int m=1; m<=height-o; m++){
                System.out.print(" ");
            }
            o += 1; // update spacing

            // print asterisks
            for (int j=1; j<=k; j++){
                System.out.print("*");
            }
            k += 2; // update asterisks
            System.err.println(""); // new line
        }
    }

    // Asterisk C
    static void asterisks_C(int height){
        int k = 1;
        for (int i=1; i<=height; i++){
            for (int j=1; j<=k; j++){
                System.out.print("*");
            }
            k += 2;
            System.out.println("");
        }
    }

    // Asterisks D
    static void asterisks_D(int height){
        int n = 1;
        for(int i=height; i>=1; i--){
            for (int j=1; j<=(2*i)-2; j++){
                System.out.print(" ");
            }
            for (int k=1; k<=n; k++){
                System.out.print("*");
            }
            n += 2;
            System.out.println("");
        }
    }

    // Asterisks E
    static void asterisks_E(int height){
        int space1, space2, ast1 = 1, ast2, line1, line2 = height/2;
        if (height%2 == 1){
            space1 = height - 1;
            line1 = (int)(height / 2) + 1;
            space2 = 2;
            ast2 = height - 2;
        } else {
            space1 = (height-1) - 1;
            line1 = height/2;
            space2 = 0;
            ast2 = height - 1;
        }

        for (int i=line1; i>=1; i--){
            for (int j=1; j<=space1; j++){
                System.out.print(" ");
            }
            for (int j=1; j<=ast1; j++){
                System.out.print("*");
            }
            ast1 += 2;
            space1 -= 2;
            System.out.println("");
        }

        for (int i=line2; i>=1; i--){
            for (int j=1; j<=space2; j++){
                System.out.print(" ");
            }
            for (int j=1; j<=ast2; j++){
                System.out.print("*");
            }
            ast2 -= 2;
            space2 += 2;
            System.out.println("");
        }
    }

    // Asterisks F
    static void asterisks_F(int height){
        int space1, space2, ast1 = 1, ast2, line1, line2 = height/2;
        if (height%2 == 1){
            space1 = (height-1)/2;
            line1 = (int)(height / 2) + 1;
            space2 = 1;
            ast2 = height - 2;
        } else {
            space1 = (height-2)/2;;
            line1 = height/2;
            space2 = 0;
            ast2 = height - 1;
        }

        for (int i=line1; i>=1; i--){
            for (int j=1; j<=space1; j++){
                System.out.print(" ");
            }
            for (int j=1; j<=ast1; j++){
                System.out.print("*");
            }
            ast1 += 2;
            space1 -= 1;
            System.out.println("");
        }

        for (int i=line2; i>=1; i--){
            for (int j=1; j<=space2; j++){
                System.out.print(" ");
            }
            for (int j=1; j<=ast2; j++){
                System.out.print("*");
            }
            ast2 -= 2;
            space2 += 1;
            System.out.println("");
        }
    }

    // Asterisks G
    static void asterisks_G(int height){
        int ast2 = 1, ast1, line1, line2, space1, space2;
        if (height%2 == 1){
            ast1 = height; // -2
            space1 = 0;
            line1 = height/2;
            line2 = (height/2) + 1;
            space2 = (height-1)/2;
        } else {
            ast1 = height - 1;
            space1 = 0; // +1
            line1 = height/2;
            line2 = height/2;
            space2 = (height-2)/2;
        }

        for (int i=1; i<=line1; i++){
            for (int j=1; j<=space1; j++){
                System.out.print(" ");
            }
            for(int k=1; k<=ast1; k++){
                System.out.print("*");
            }
            space1 += 1;
            ast1 -= 2;
            System.out.println("");
        }

        for (int i=1; i<=line2; i++){
            for (int j=1; j<=space2; j++){
                System.out.print(" ");
            }
            for (int k=1; k<=ast2; k++){
                System.out.print("*");
            }
            ast2 += 2;
            space2 -= 1;
            System.out.println("");
        }
    }

    // Asterisks H
    static void asterisks_H(int height){
        int space1, line1, hold1, hold2, space2, line2;
        if (height%2 == 1){
            space1 = height - 1; // -= 2
            space2 = 2;
            line1 = height/2 + 1;
            line2 = height/2;
            hold1 = 1; // += 2
            hold2 = 2*(height-1) - 5; // -=4
        } else {
            space1 = height - 2;
            space2 = 0;
            line1 = height/2;
            line2 = height/2;
            hold1 = 1;
            hold2 = 2*(height-2) - 1;
        }   

        for (int i=1; i<=line1; i++){
            for (int j=1; j<=space1; j++){
                System.out.print(" ");
            }
            System.out.print("*");
            
            //display hold
            if (hold1 > 1){
                for (int k=1; k<=hold1; k++){
                    System.out.print(" ");
                }
                System.out.print("*");
                hold1 += 4;
            } else {
                hold1 = 3;
            }
            space1 -= 2;
            System.out.println("");
        }

        for (int i=1; i<=line2; i++){
            for (int j=1; j<=space2; j++){
                System.out.print(" ");
            }
            System.out.print("*");
            if (hold2 > 1){
                for (int k=1; k<=hold2; k++){
                    System.out.print(" ");
                }
                System.out.print("*");
            }

            hold2 -= 4;
            space2 += 2;
            System.out.println("");
        }
    }

    public static void main(String[] args) {
        // asterisks_A(6, 50);
        // asterisks_B(20);
        // asterisks_C(5);
        // asterisks_D(5);
        // asterisks_E(10);
        // asterisks_F(8);
        // asterisks_G(10);
        asterisks_H(6);
    }
}