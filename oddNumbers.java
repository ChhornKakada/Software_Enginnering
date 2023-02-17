
public class oddNumbers extends betweenOf {

  public oddNumbers(int startNumber, int endNumber) {
    super(startNumber, endNumber);
  }

  public oddNumbers() {}

  public void setList(){
    IntegerNumber n = new IntegerNumber();
    if(this.getStartNumber() < this.getEndNumber()){
      for(int i=this.getStartNumber(); i<=this.getEndNumber(); i++){
        n.setNumber(i);
        if(n.isOdd()) this.addToList(i);
      }
    } else {
      for(int i=this.getStartNumber(); i>=this.getEndNumber(); i++){
        n.setNumber(i);
        if(n.isOdd()) this.addToList(i);
      }
    }
  }

  public static void main(String[] args) {
    Util util = new Util();
    util.clsScr();

    oddNumbers oddNums = new oddNumbers(0, 500);
    System.out.println("=== Display odd numbers between 0 and 500 ===");
    System.out.println(util.YELLOW+ "\nResult: " +util.RESET+oddNums+ ".\n");

  }
}
