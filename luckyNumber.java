public class luckyNumber {
    private int num;

    public int getNum() { return num; }
    public void setNum(int n) { this.num = n; }
    public int getDigit() { return String.valueOf(num).length(); }
    public luckyNumber(int n) { this.num = n; }
    
    public boolean isLucky() {
        int sumFirst = 0, sumLast = 0, divide = 100000, n, k = num;
        if(this.isValid()){
            for(int i=1; i<=6; i++){
                n = k / divide;
                if(i <= 3) sumFirst += n;
                else sumLast += n;
                k = num % divide;
                divide /= 10;
            }
            if(sumFirst == sumLast) return true;
            return false;
        } 
        return false;
    }

    public boolean isValid() {
        if(num <= 0) return false;
        if(this.getDigit() != 6) return false;
        return true;
    }

    public static void main(String[] args) {
        Util util = new Util();
        util.clsScr();

        System.out.println("=== Checking Lucky Number Program ===");

        luckyNumber number = new luckyNumber(util.getInt("\nPlease input 6 digits number: ", 
                                                     "x - Invalid input, please input again: "));

        if(number.getNum() < 0) {
            System.out.println("""
                    \nError: Invalid input number!
                           Please input only positive number with 6 digits.\n
                    """);
        } else if(number.isValid()){
            if(number.isLucky()) System.out.println("\nResult: " +number.getNum() + " is a lucky number.\n");
            else System.out.println("\nResult: " +number.getNum() + " is not a lucky number.\n");
        } else {
            System.out.printf("""
                    \nError: Invalid input number!
                           Your number you just input is %d""", number.getDigit());
            if(number.getDigit() == 1) System.out.print(" digit.");
            else System.out.print(" digits.");
            System.out.println("\n       Please input only 6 digits positive number.\n");
        }
    }
}
