import java.util.Scanner;

public class StringMirroring {

  private String text;

  public void setText(String text) { this.text = text; }
  public String getText() { return this.text; }

  public StringMirroring(String text) { this.text = text; }
  public StringMirroring() {}

  public String makePalindrome() {
    String result = this.text;
    for(int i=this.text.length()-1; i>=0; i--){
      result += this.text.charAt(i);
    }
    return result;
  }

  public static void main(String[] args) {
    Util util = new Util();
    util.clsScr();

    Scanner input = new Scanner(System.in);

    StringMirroring text = new StringMirroring();
    
    System.out.println("=== Make word to palindrone ===");
    System.out.print("\n    Enter a word: ");
    text.setText(input.nextLine().toUpperCase());
    
    System.out.println(util.YELLOW+ "\nResult: " +util.RESET+text.makePalindrome()+ "\n");
  }
}
