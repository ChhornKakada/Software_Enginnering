import java.util.Scanner;

public class Palindrome {

  private String text;

  public String getText() { return this.text; }
  public void setText(String text) { this.text = text; }

  public Palindrome(String text) { this.text = text; }
  public Palindrome() {}

  public boolean isPalindromeLoop() {
    String org = this.text.toLowerCase();
    int _size = org.length();
    for(int i=0; i<org.length()/2; i++){
      if(org.charAt(i) != org.charAt(_size-1)) return false;
      _size -= 1;
    }
    return true;
  }

  public boolean isPalindromeRev() {
    String org = this.text.toLowerCase();
    String check = "";
    for(int i=org.length()-1; i>=0; i--){
      check += org.charAt(i);
    }
    return check.equals(org) ? true : false;
  }


  public static void main(String[] args) {
    Util util = new Util();
    util.clsScr();

    Scanner input = new Scanner(System.in);
    Palindrome word = new Palindrome();

    // get word
    System.out.println("=== Checking word whether it is palindrome ===");
    System.out.print("\n    Enter a word: ");
    word.setText(input.nextLine());

    // get method
    String method;
    System.out.print("    Choose method(REV, LOOP): ");
    method = input.nextLine().toLowerCase();
    while(!method.equals("loop") && !method.equals("rev")){
      System.out.print(util.RED+ "    X - Invalid method, try again: " +util.RESET);
      method = input.nextLine();
    }

    // checking palindrome
    boolean isPalindrome;
    if(method.equals("loop")) isPalindrome = word.isPalindromeLoop();
    else isPalindrome = word.isPalindromeRev();

    // display result
    if(isPalindrome) System.out.println(util.YELLOW+ "\nResult: " +util.RESET+word.getText().toUpperCase()+ " is a palindrome.\n");
    else System.out.println(util.YELLOW+ "\nResult: " +util.RESET+word.getText().toUpperCase()+ " is not a palindrome.\n");
  }
}
