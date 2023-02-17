
public class CompanyProfits {

  private Double profits[];
  
  public void setSize(int size) { profits = new Double[size]; }
  public int getSize() { return this.profits.length; }

  public CompanyProfits() {}

  public Double[] getProfits() { return profits; }

  public void setProfit(int month, Double profit) {
    this.profits[month-1] = profit;
  }

  public Double getProfit(int month) { return this.profits[month-1]; }

  public Double getTotal() { 
    Double _sum = 0.0;
    for(Double i : this.profits) _sum += i;
    return _sum;
  }

  
  public static void main(String[] args) {

    Util util = new Util();
    util.clsScr();

    CompanyProfits profits = new CompanyProfits();
    profits.setSize(12);

    System.out.println("=== Calculate company profits for 12 months ===");
    System.out.println("\n    Input profits each months...");

    String label, errorInput = util.RED+ "    X - Invalid, try again: " +util.RESET;

    for(int i=0; i<profits.getSize(); i++){
      label = "\tMonth " +(i+1)+ ": ";
      profits.setProfit(i+1, util.getDouble(label, errorInput));
    }

    System.out.println(util.YELLOW+ "\nResult: " +util.RESET+ "total is " +profits.getTotal()+ "\n");
  }
}
