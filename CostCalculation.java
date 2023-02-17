import java.util.Scanner;

public class CostCalculation {
  private double TotalBuyingCost;
  private int discount;

  public CostCalculation(double TotalBuyingCost) {
    this.TotalBuyingCost = TotalBuyingCost;
  }

  public CostCalculation() {}

  public void setTotalBuyingCost(double TotalBuyingCost) {
    this.TotalBuyingCost = TotalBuyingCost;
  }

  public double getTotalBuyingCost() {
    return this.TotalBuyingCost;
  }

  public int getDiscount() {
    if(this.TotalBuyingCost >= 300) this.discount = 25;
    else if(this.TotalBuyingCost >= 80) this.discount = 15;
    else if(this.TotalBuyingCost >= 30) this.discount = 10;

    return this.discount;
  }

  public double getTotalDiscount() {
    if(this.TotalBuyingCost >= 30) {
      return (this.TotalBuyingCost * this.discount) / 100;
    } else if(this.TotalBuyingCost >= 20) {
      return 2;
    } else return 0;
  }

  public double getTotalPayment() {
    return this.TotalBuyingCost - this.getTotalDiscount();
  }
}

class examEx2 {
  public static void main(String[] args) {

    examEx3.clsScr();

    Scanner input = new Scanner(System.in);
    double totalBuyingCost;

    CostCalculation shopping = new CostCalculation();

    while(true) {
      System.out.print("\n\tInput total buying cost: ");
      while(!input.hasNextDouble()) {
        System.out.print("\tWarn - Only positive number, no special and any characters! \n");
        System.out.print("\n\tInput total buying cost: ");
        input.nextLine();
      }

      totalBuyingCost = input.nextDouble();
      if(totalBuyingCost > 0) break;
      else {
        System.out.print("\tWarn - Cost must be positive.\n");
        input.nextLine();
      }
    }

    shopping.setTotalBuyingCost(totalBuyingCost);

    System.out.printf("\n\t    Total cost  :  %5.2f$", shopping.getTotalBuyingCost());
    if(totalBuyingCost >= 30) {
      System.out.printf("\n\t      Discount  :  %5d%%", shopping.getDiscount());
    }
    System.out.printf("\n\tTotal Discount  :  %5.2f$", shopping.getTotalDiscount());
    System.out.println("\n---------------------------------------------------------------");
    System.out.printf("\t Total payment  :  %5.2f$\n\n\n", shopping.getTotalPayment());
  }
}


