import java.util.ArrayList;
import java.util.List;

public class PrimeNumber {

    private int number;
    private List<Integer> divisible = new ArrayList<Integer>();

    public List<Integer> getDivisible() { return this.divisible; }
    public int getNumber() { return number; }
    public PrimeNumber(int n){ this.number = n;}

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

    public boolean isValid(){ return this.number >= 0 ? true : false; }

    public void displayDivisible() {
        for(int i=0; i<this.getDivisible().size(); i++){
            if(i == 0) System.out.print(this.divisible.get(i));
            else if(i == this.getDivisible().size()-1) System.out.print(" and " +this.getDivisible().get(i));
            else System.out.print(", " +this.getDivisible().get(i));
        }
    }

    public static void main(String[] args) {
        Util util = new Util();
        util.clsScr();

        System.out.print("""
                === Checking Prime Number Program ===
                """);
        PrimeNumber number = new PrimeNumber(util.getInt("\nInput a number: ", 
                                                    "X - Invalid input. Input again: "));

        if(number.getNumber() < 0) System.out.println("\nErorr: please input as a positive ingeter.\n");
        else if(number.getNumber() == 0) System.out.println("\nResult: 0 is null number.\n");
        else if(number.getNumber() == 1) System.out.println("\nResult:  1 is not a prime number.\n");
        else {
            boolean result = number.isPrime();
            if(result){
                System.out.println("\nResult: " +number.getNumber() + " is a prime number.\n");
            } else {
                System.out.println("\nResult: " +number.getNumber() + " is not a prime number.");
                System.out.print("\tBecause it is divisible by ");
                number.displayDivisible();
                System.out.println(".\n");
            } 
        }   
    }
}