import java.util.Scanner;

public class EscapeCharactersReplacement {
  public static void main(String[] args) {
    Scanner input = new Scanner(System.in);
    Util util = new Util();

    util.clsScr();
    String opt = "";

    MessageCoder message = new MessageCoder();
    System.out.println("=== Encode and Decode program ===");
    System.out.print("\n    Input message: ");
    message.setText(input.nextLine());

    boolean _conti;
    System.out.print("    Option (encode/decode): ");
    do{
      _conti = false;
      opt = input.nextLine().toLowerCase();
      if(opt.equals("encode")) {
        System.out.println(util.YELLOW+ "\nResult: " +util.RESET+message.encode()+ "\n");
      } else if(opt.equals("decode")) {
        System.out.println(util.YELLOW+ "\nResult: " +util.RESET+message.decode()+ "\n");
      } else {
        System.out.print(util.RED+ "   Invalid option, tru again: " +util.RESET);
        _conti = true;
      }
    } while(_conti);
  }
}
