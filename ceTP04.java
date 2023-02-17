public class ceTP04 {

    public void _leapYear(){ LeapYear.main(null); }
    public void _luckyNumber(){ luckyNumber.main(null); }
    public void _among8Number(){ Among8Number.main(null); }
    public void _moneyExchanges(){ moneyExchanges.main(null); }
    public void _primeNumber(){ PrimeNumber.main(null); }
    public void _reservsingNumber(){ reversingNumber.main(null); }
    public void _shipping(){ Shipping.main(null); }

    public static void main(String[] args) {

        Util util = new Util();
        ceTP04 tp4 = new ceTP04();
        int opt;

        do{
            util.clsScr();
            System.out.println("""
            ---------- Menu ----------
              1. Prime number
              2. Lucky number
              3. Reversing number
              4. Money exchange
              5. Max among 8 numbers
              6. Shipping
              7. Leap year
              0. Exit
            --------------------------""");

            opt = util.getInt("Choose an option: ", "X - Invalid input, choose again: ");
            while(opt < 0 || opt > 7){
                opt = util.getInt("X - Number not exist, Choose again: ", 
                             "X - Invalid input, Choose again: ");
            }

            if(opt == 0) {
                System.out.println("\nThanks for using this program! *-*\n");
                continue;
            }
            else if(opt == 1) tp4._primeNumber();
            else if(opt == 2) tp4._luckyNumber();
            else if(opt == 3) tp4._reservsingNumber();
            else if(opt == 4) tp4._moneyExchanges();
            else if(opt == 5) tp4._among8Number();
            else if(opt == 6) tp4._shipping();
            else if(opt == 7) tp4._leapYear();

            util.pause();
        } while(opt != 0);
    }
}
