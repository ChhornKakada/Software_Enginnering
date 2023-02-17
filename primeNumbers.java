
public class primeNumbers extends betweenOf{

  public primeNumbers(int startNumber, int endNumber) {
    super(startNumber, endNumber);
  }

  public primeNumbers() {}

  public boolean isValid(){
    if(this.getStartNumber() > 1 && this.getEndNumber() > this.getStartNumber()) return true;
    return false;
  }

  public void setList(){
    if(this.isValid()){
      int n = this.getStartNumber(), m = this.getEndNumber();
      IntegerNumber num = new IntegerNumber();
      for(int i=n; i<=m; i++){
        num.setNumber(i);
        if(num.isPrime()) this.addToList(i);
      }
    }
  }

  public static void main(String[] args) {
    Util util = new Util();
    util.clsScr();

    String errorInput = util.RED+ "X - invalid, try again: " +util.RESET;

    System.out.println("=== Display the prime number between 2 and n ===");
    primeNumbers primeList = new primeNumbers();
    primeList.setStartNumber(2);

    primeList.setEndNumber(util.getInt("\n    Input n value: ", errorInput));
    if(primeList.getEndNumber() > 2){
      int _size = primeList.getList().size();
      if(_size == 0) {
        System.out.println("\nThere is no prime between these number.\n");
      } else {
        System.out.println(util.YELLOW+ "\nResult: " +util.RESET+primeList+ ".\n");
      }
    } else System.out.println(util.RED+ "\nError: " +util.RESET+ "n need to be greater than 2.\n" );

  }
  
}
