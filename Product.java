public class Product {
  private String number;
  public String name;
  public int amount;
  private double price;

  public Product() {}

  public Product(String number, String name, double price, int amount) {
    this.number = number;
    this.name = name;
    this.price = price;
    this.amount = amount;
  }

  public void setNumber(String number) {
    this.number = number;
  }

  public String getNumber() {
    return this.number;
  }

  public void setPrice(double price) {
    this.price = price;
  }

  public double getPrice() {
    return this.price;
  }

  public void show() {
    System.out.printf("|      %-12s|   %-30s  |  $%8.2f  |   %3d    |\n", this.number, this.name, this.price, this.amount);
  }

}
