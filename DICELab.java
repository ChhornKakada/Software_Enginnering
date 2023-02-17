import java.util.Calendar;
import java.util.Date;
import java.util.LinkedList;
import java.util.Scanner;


public class DICELab {
  private LinkedList <Computer> computers = new LinkedList<>();

  public boolean isIDExist(String ID) {
    for(Computer computer : this.computers) {
      if(computer.ID.equals(ID)) return true;
    }
    return false;
  }
  
  public void addComputer(Computer cp) {
    if(computers.size() != 0 && this.isIDExist(cp.ID)) {
      System.out.println("\tX - Cannot add, this ID is already exist.");
    } else {
      this.computers.add(new Computer(cp.ID, cp.getSerialNumber(), cp.model, cp.purchasedDate));
    }
  }

  public Computer getComputerByID(String ID) {
    for(var cp : computers) {
      if(cp.ID.equals(ID)) return cp;
    }
    return null;
  }

  public void makedStatus(String ID, boolean status) {
    if(this.isIDExist(ID)) {
      this.getComputerByID(ID).isDamaged = status;
      System.out.println("\t|> Maked successfully!");
    } else System.out.println("\t|> Cannot make, ID not found!");
  }

  public void makeNotDamaged(String ID) {
    this.makedStatus(ID, false);
  }

  public void makeDamaged(String ID) {
    this.makedStatus(ID, true);
  }

  private void showCP(Boolean status) {
    if(computers.size() == 0) {
      System.out.println("\t|> There is no data yet.");
    } else {
      System.out.println("\n\t|> List of computer in DICE Lab306.");
      System.out.println("""
        \t+-------+-----------------+--------------------------------+--------------------+-----------+
        \t|   ID  |  serial number  |             Model              |   Purchased Date   |  Damaged  |       
        \t+-------+-----------------+--------------------------------+--------------------+-----------+""");

      if(status == null) {
        for(var cp : computers) {
          cp.show();
          System.err.println("\t+-------+-----------------+--------------------------------+--------------------+-----------+");
        }
      } else {
        for(var cp : computers) {
          if(cp.isDamaged == status) {
            cp.show();
            System.err.println("\t+-------+-----------------+--------------------------------+--------------------+-----------+");
          }
        }
      }

    }

  }

  public void showDamagedCPs() {
    this.showCP(true);
  }

  public void showGoodCPs() {
    this.showCP(false);
  }

  public void showAllCPs() {
    this.showCP(null);
  }

  static void showtp6Ex3Menu() {
    System.out.println("""
      \n\t-------------- Option --------------
      \t    1. List all PCs
      \t    2. List all damages PCs
      \t    3. List all good PCs
      \t    4. Mark a PC as damaged
      \t    5. Mark a PC as not damaged
      \t    6. Quit
      \t------------------------------------
        """);
  }

}


class tp6ex3 {

  public static void main(String[] args) {
    DICELab Lab306F = new DICELab();

    Calendar inpuCalendar = Calendar.getInstance();
    String errorText = "\tX - Invalid input, try again: ";
    
    inpuCalendar.set(2017, 11, 25);
    Date date = inpuCalendar.getTime();
    Lab306F.addComputer(new Computer("001", "ASDF234SDF2", "MSI Zin II", date));
    inpuCalendar.set(2020, 7, 22);
    date = inpuCalendar.getTime();
    Lab306F.addComputer(new Computer("003", "LDGF23Q3445", "ALINE Zin II", date));
    inpuCalendar.set(2022, 04, 02);
    date = inpuCalendar.getTime();
    Lab306F.addComputer(new Computer("004", "ASDF234SDF2", "MSI Zin II", date));
    inpuCalendar.set(2019, 5, 15);
    date = inpuCalendar.getTime();
    Lab306F.addComputer(new Computer("005", "LDGF23Q3445", "ALINE Zin II", date));
    
    while(true) {
      Util util = new Util();
      util.clsScr();
      DICELab.showtp6Ex3Menu();

      Scanner input = new Scanner(System.in);

      int opt = util.getInt("\tChoose an option: ", errorText);
      if(opt == 1) {
        Lab306F.showAllCPs();
      } else if(opt == 2) {
        Lab306F.showDamagedCPs();
      } else if(opt == 3) {
        Lab306F.showGoodCPs();
      } else if(opt == 4) {
        
        System.out.print("\n\tEnter Computer's ID: ");
        Lab306F.makeDamaged(input.nextLine());
      } else if(opt == 5) {
        System.out.print("\n\tEnter Computer's ID: ");
        Lab306F.makeNotDamaged(input.nextLine());
      } else if(opt == 6) {
        System.out.println("\tThank you for using this programm.");
        break;
      } else {
        System.out.println("\t|> Invalid input!");
      }
      util.pause("\n\n\t");
    }
  }
}