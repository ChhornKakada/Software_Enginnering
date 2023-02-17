import java.util.List;
import java.util.ArrayList;

public class IntegerNumber {
  private int number;
  private List<Integer> divisible = new ArrayList<Integer>();

  public IntegerNumber(int number) { this.number = number; }
  public IntegerNumber() {}

  public void setNumber(int number) { this.number = number; }
  public int getNumber() { return this.number; }

  public boolean isPrime(){
    if(number < 2) return false;

        int half = number / 2;
        boolean _isPrime = true;
        
        for(int i=2; i<=half; i++){
            if(number % i == 0) {
                divisible.add(i);
                _isPrime = false;
            }
        }
        return _isPrime ? true : false;
  }

  public void displayDivisible() {
    if(this.isPrime()){
      for(int i=0; i<this.divisible.size(); i++){
          if(i == 0) System.out.print(this.divisible.get(i));
          else if(i == this.divisible.size()-1) System.out.print(" and " +this.divisible.get(i));
          else System.out.print(", " +this.divisible.get(i));
      }
    } System.out.print("Sorry, this number is not a prime number.");
  }

  public boolean isOdd(){ return this.number%2 == 1 ? true : false; }
  public boolean isEven() { return this.number%2 == 0 ? true : false; }

}