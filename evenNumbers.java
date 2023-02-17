import java.util.List;

public class evenNumbers extends betweenOf{

  public evenNumbers(int startNumber, int endNumber) {
    super(startNumber, endNumber);
  }

  public evenNumbers() {}

  public void setList(){
    IntegerNumber num = new IntegerNumber();
    if(this.getStartNumber() < this.getEndNumber()){
      for(int i=this.getStartNumber(); i<=this.getEndNumber(); i++){
        num.setNumber(i);
        if(num.isEven()) this.addToList(i);
      }
    } else {
      for(int i=this.getStartNumber(); i>=this.getEndNumber(); i--){
        num.setNumber(i);
        if(num.isEven()) this.addToList(i);
      }
    }
  }

  public static void main(String[] args) {
    Util util = new Util();
    util.clsScr();

    String errorInput = util.RED+ "X - Invalid, try again: " +util.RESET;
    
    evenNumbers evenNums = new evenNumbers(0, 500);
    System.out.println("=== Display even numbers from n to 500 ===");
    evenNums.setStartNumber(util.getInt("\n    Input n value: ", errorInput));

    if(evenNums.getStartNumber() > 500 || evenNums.getStartNumber() < 0) {
      System.out.println(util.RED+ "\nError:" +util.RESET+ " The start point need to be in range of 0 to 500.\n");
    } else {
      System.out.println(util.YELLOW+ "\nResult: " +util.RESET+evenNums+ "\n");
    }

  }
}
