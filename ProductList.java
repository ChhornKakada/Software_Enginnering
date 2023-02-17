import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.List;
import java.util.Scanner;
import java.util.Vector;

public class ProductList {
  private Vector <Product> products = new Vector<>();
  
  public ProductList() {}

  public void addProduct(Product p) {
    if(this.isNumProExist(p.getNumber())) {
      System.out.println("\n\t|> Sorry, cannot add, this ID is already exist.");
    } else {
      this.products.addElement(p);
      System.out.println("\n\t|> The product has been add successfully!");
    }
  }

  public void removeProduct(int j) {
    if(j < 0 || j >= this.products.size()) {
      System.out.println("\n\t|> Cannot remove, invalid index.");
    } else {
      for(int i=0; i<this.products.size(); i++){
        if(i == j) {
          this.products.remove(i);
        }
      }
      System.out.println("\n\t|> Remove successfully!");
    }
  }

  public boolean isNumProExist(String number) {
    for(var p : this.products) {
      if(p.getNumber().equals(number)) return true;
    }
    return false;
  }

  public Product getProductByNumber(String number) {
    for(int i=0; i<this.products.size(); i++) {
      if(this.products.get(i).getNumber().equals(number)) {
        return this.products.get(i);
      }
    }
    return null;
  }

  public void updateProduct(String number, Product p) {
    boolean isSuccess = false;

    for(int i=0; i<this.products.size(); i++) {
      if(this.products.get(i).getNumber().equals(p.getNumber())) {
        if(p.name.trim().equals("")) p.name = this.products.get(i).name;
        if(p.getPrice() == -1) p.setPrice(this.products.get(i).getPrice()); 
        if(p.amount == -1) p.amount = this.products.get(i).amount;
        this.products.set(i, p);
        isSuccess = true;
        break;
      }
    }

    if(isSuccess) System.out.println("\n\t|> Update the product successfully!");
    else System.out.println("\n\t|> Cannot update, number of product is invalid.");
  }

  public void showList() {
    if(this.products.size() == 0) {
      System.out.println("\t|> There is no data yet.");
    } else {
      System.out.println("\n\tAll products in the store.");
      System.out.println("""
        \t+----+------------------+-----------------------------------+-------------+----------+
        \t| No |  Product-Number  |               Name                |    Price    | In stoke |
        \t+----+------------------+-----------------------------------+-------------+----------+""");
      for(int i=0; i<this.products.size(); i++){
        System.out.printf("\t| %2d ", i+1);
        products.get(i).show();
        System.out.println("\t+----+------------------+-----------------------------------+-------------+----------+");
      }
    }
  }


  public void readDataFromStorage(String filename) throws IOException {
    List <String> textList = Files.readAllLines(Path.of(filename, new String[]{}));
    for(String txt : textList) {
      String[] text = txt.split(",");

      Product p = new Product();
      p.setNumber(text[0]);
      p.name = text[1];
      p.setPrice(Double.valueOf(text[2]));
      p.amount = Integer.valueOf(text[3]);

      this.products.addElement(p);
    }
  }

  // update data of the student list
  public void updateStorage(String fileame) throws IOException {
    Files.delete(Path.of(fileame));
    String text = "";

    for(int i=0; i<this.products.size(); i++){
      if(i == 0) {
        text = products.get(i).getNumber() + "," + products.get(i).name + "," + products.get(i).getPrice() + "," + products.get(i).amount;
        Files.write(Path.of(fileame, new String[]{}), text.getBytes(), StandardOpenOption.CREATE);
      } else {
        text = "\n" + products.get(i).getNumber() + "," + products.get(i).name + "," + products.get(i).getPrice() + "," + products.get(i).amount;
        Files.write(Path.of(fileame, new String[]{}), text.getBytes(), StandardOpenOption.APPEND);
      }
    }
  }
}

class tp6ex5 {
  public static void main(String[] args) throws IOException {
    Util util = new Util() ;
    Scanner input = new Scanner(System.in);

    ProductList products = new ProductList();
    products.readDataFromStorage("ProductList.txt");
    Product p = new Product();

    String errorInput = "\tX - invalid input, try again: ";

    while(true) {
      util.clsScr();

      System.out.println("""
        \n\t------------------ Option ------------------
        
        \t    1. List all products in shop
        \t    2. Add new product to the list
        \t    3. Remove product from list by index
        \t    4. Update product in list
        \t    5. Exit program
        \t--------------------------------------------""");

      System.out.println();
      int opt = util.getInt("\t|> Choose an option: ", errorInput);

      // Show all products in the store
      if(opt == 1) {
        products.showList();
      } 
      
      // Add a new product into the store
      else if(opt == 2) {

        System.out.println("\n\tEnter a product info....\n");
        System.out.print("\t    Product-Number: ");
        p.setNumber(input.nextLine());
        System.out.print("\t    Name: ");
        p.name = input.nextLine();
        p.setPrice(util.getDouble("\t    Price: ", errorInput));
        p.amount = util.getInt("\t    Amount in stoke: ", errorInput);

        products.addProduct(p);
      } 
      
      // Remove a product by index
      else if(opt == 3) {
        products.removeProduct(util.getInt("\n\t|> Enter the index(No) of the product: ", errorInput));
      } 
      
      // Update product in the store
      else if(opt == 4) {
        String get;
        System.out.println("\n\tEnter new product info....\n");
        System.out.print("\t    Product-Number: ");
        p.setNumber(input.nextLine());
        System.out.print("\t    Name: ");
        p.name = input.nextLine();
        System.out.print("\t    Price: ");
        get = input.nextLine();
        if(get.trim().equals("")) p.setPrice(-1);
        else p.setPrice(Double.valueOf(get));
        System.out.print("\t    Amount in stoke: ");
        get = input.nextLine();
        if(get.trim().equals("")) p.amount = -1;
        else p.amount = Integer.valueOf(get);
        
        
        // p.setPrice(util.getDouble("\t    Price: ", errorInput));
        // p.amount = util.getInt("\t    Amount in stoke: ", errorInput);

        products.updateProduct(p.getNumber(), p);
      } 
      
      // End the program and update the storage
      else if(opt == 5) {
        System.out.println("\tThank you for using this program.");
        products.updateStorage("ProductList.txt");
        break;
      } else {
        System.out.println("\tX - Invalid option.");
      }

      util.pause("\n\n\t");
    }

  }
}