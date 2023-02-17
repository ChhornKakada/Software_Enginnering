import java.util.Scanner;

public class moneyExchanges {

    private double riels, dollars, bahts;

    public double getRiels() { return this.riels; }
    public double getDollars() { return this.dollars; }
    public double getBahts() { return this.bahts; }

    public moneyExchanges() {
        this.riels = 0;
        this.dollars = 0;
        this.bahts = 0;
    }

    public void setRiels(double riel){
        this.riels = riel;
        this.dollars = this.riels / 4000;
        this.bahts = this.dollars * 30;
    }

    public void setDollors(double dollor){
        this.dollars = dollor;
        this.riels = dollor * 4000;
        this.bahts = dollor * 30;
    }

    public void setBahts(double baht){
        this.bahts = baht;
        this.dollars = baht / 30;
        this.riels = this.dollars * 4000;
    }


    public static void main(String[] args) {
        
        // Scanner input = new Scanner(System.in);
        moneyExchanges money = new moneyExchanges();
        int opt;
        String errorMoney = "X - Invalid input, input again: ";

        do{
            Util util = new Util();
            util.clsScr();

            System.err.println("""
                \nWelcome to program Money Exchanges!

                      1. Riels to Dollar
                      2. Riels to Thai Baht
                      3. Dollar to Riels
                      4. Dollar to Thai Baht
                      5. Thai Baht to Riels
                      6. Exit
                -----------------------------------""");
            // System.out.print("Choose your option: ");
            opt = util.getInt("Choose the option: ", 
                          "X - Invalid input! Choose again: ");
            while(opt < 1 || opt > 6){
                opt = util.getInt("X - Option not exist, Choose again: ", 
                                "X - Invalid input, Choose again: ");
            }

            // exchange from Riels
            if(opt == 1 || opt == 2){
                // System.out.print("Input money in RIELS: ");
                money.setRiels(util.getDouble("\nInput money in RIELS: ", errorMoney));
                if(money.getRiels() > 0){
                    if(opt == 1) System.out.printf("\nResult: %.0f RIELS = %.4f USD\n\n", money.getRiels(), money.getDollars());
                    else System.out.printf("\nResult: %.0f RIELS = %.0f Thai Bahts\n\n", money.getRiels(), money.getBahts());
                } else System.out.println("\nPlease input money as positive number.");
            } 
            
            // exchange from dollars
            else if(opt == 3 || opt == 4){
                // System.out.print("Input money in USD: ");
                money.setDollors(util.getDouble("\nInput money in USD: ", errorMoney));
                if(money.getDollars() > 0){
                    if(opt == 3) System.out.printf("\nResult: %.4f USD = %.0f RIELS\n\n", money.getDollars(), money.getRiels());
                    else System.out.printf("\nResult: %.4f USD = %.0f Thai Bahts\n\n", money.getDollars(), money.getBahts());
                } else System.out.println("\nPlease input money as positive number.");
            } 
            
            // exchange from Thai Bahts
            else if(opt == 5){
                // System.out.print("Input money in Thai Bahts: ");
                money.setBahts(util.getDouble("\nInput money in Thai Bahts: ", errorMoney));
                if(money.getBahts() < 0) System.out.println("Please input money as positive number.");
                else System.out.printf("\nResult: %.0f Thai Bahts = %.0f REILS\n\n", money.getBahts(), money.getRiels());
            }

            // exit
            else if(opt == 6){
                System.out.println("Thank you for using this Money Exchange Program!\n");
                break;
            }
            util.pause();
        } while(opt != 6);
    }
    
}
