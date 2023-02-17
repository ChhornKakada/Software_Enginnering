import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class StudentList {
  // private static final String Department = null;
  private ArrayList <Student> stus = new ArrayList<>();
  // private Department department = new Department();

  public int sizeOfStudent() {
    return this.stus.size();
  }

  // Add only a student to `stus`
  public void addStudent(Student student) {
    if(stus.size() !=0 && this.isIDExist(student.getID())) {
      System.out.println("\tX - Cannot add, this ID is already exist.");
    } else {
      // Department.Student tps = wer.new Student();
      this.stus.add(new Student(student.getID(), student.getName(), student.getSex(), student.getTel()));
      System.out.println("\t|> Add successfully!");
    }
  }

  // Check if the input ID is in `stus` or not
  public boolean isIDExist(String ID) {
    for(int i=0; i<this.stus.size(); i++){
      if(stus.get(i).getID().equals(ID)) return true;
    }
    return false;
  }

  // Display only student by ID
  public void display(String ID) {
    System.out.println("""
        \t+----+-----------+---------------------------+--------+------------+
        \t| No |    ID     |           Name            |  Sex   |     Tel    |
        \t+----+-----------+---------------------------+--------+------------+""");
    int index = this.getIndexByID(ID);
    System.out.printf("\t|  1 ");
    stus.get(index).show();
    System.out.println("\n\t+----+-----------+---------------------------+--------+------------+");
  }

  // Display all student in the list
  public void showList() {
    if(stus.size() == 0) {
      System.out.print("\tThe list of student has no data yet.");
    } else {
      System.out.println("\n\tStudent's list");
      System.out.println("""
        \t+----+-----------+---------------------------+--------+------------+
        \t| No |    ID     |           Name            |  Sex   |     Tel    |
        \t+----+-----------+---------------------------+--------+------------+""");
      for(int i=0; i<stus.size(); i++){
        System.out.printf("\t| %2d ", i+1);
        stus.get(i).show();
        System.out.println("\n\t+----+-----------+---------------------------+--------+------------+");
      }
    }
  }

  // Remove Student by name
  public void removeByName(String name) {
    boolean isRemove = false;
    for(int i=0; i<this.stus.size(); i++) {
      if(stus.get(i).getName().equals(name)) {
        stus.remove(i);
        isRemove = true;
      }
    }

    if(isRemove) System.out.print("\t|> Remove successfully!");
    else System.out.print("\t|> Name not found.");
  }

  // get only index in stus by ID
  private int getIndexByID(String ID) {
    for(int i=0; i<stus.size(); i++) {
      if(stus.get(i).getID().equals(ID)) return i;
    }
    return -1;
  }

  // Update any student by ID
  public void updateByID(String ID) {
    if(this.isIDExist(ID)) {
      Scanner input = new Scanner(System.in);
      int index = this.getIndexByID(ID);
      this.display(ID);
      String update = "yes";

      while(true) {
        if(update.equals("yes") || update.equals("y")) {
          System.out.print("\t|> Enter (Key, newData): ");
          String[] newData = input.nextLine().split(",");

          // Woking when newData.length = 2, cuz it has only key and value
          if(newData.length == 2) {
            if(newData[0].toLowerCase().trim().equals("name")) {
              this.stus.get(index).setName(newData[1].trim());
            } 
            else if(newData[0].toLowerCase().trim().equals("sex")) {
              this.stus.get(index).setSex(newData[1].trim());
            } 
            else if(newData[0].toLowerCase().trim().equals("tel")) {
              this.stus.get(index).setPhone(newData[1].trim());
            } 
            else System.out.print("\tX - Invalid Key, Cannot update.\n");
          } 

          // Invalid cuz it has more than 2 data (key and newData)
          else System.out.print("\tX - Invalid input.");
        } 
        
        // when (update = no) means that the user stop updating
        else if(update.equals("no") || update.equals("n")) {
          System.out.print("\t|> Update successfully!");
          break;
        } 
        
        // When the input of update != yes and no
        else System.out.print("\tX - Invalid input.\n");

        System.out.print("\n\t\\> Any update (yes/no): ");
        update = input.nextLine().toLowerCase();
      }
      
    } else System.out.print("\t|> ID not found.");
  }

  // read student from storage
  public void readDataFromStorage(String filename) throws IOException {
    List <String> textList = Files.readAllLines(Path.of(filename, new String[]{}));
    for(String txt : textList) {
      String[] text = txt.split(",");
      this.addStudent(new Student(text[0].trim(), text[1].trim(), text[2].trim(), text[3].trim()));
    }
  }

  // update data of the student list
  public void updateStorage(String fileame) throws IOException {
    Files.delete(Path.of(fileame));
    String text = "";

    for(int i=0; i<this.stus.size(); i++){
      if(i == 0) {
        text = stus.get(i).getID() + "," + stus.get(i).getName() + "," + stus.get(i).getSex() + "," + stus.get(i).getTel();
        Files.write(Path.of(fileame, new String[]{}), text.getBytes(), StandardOpenOption.CREATE);
      } else {
        text = "\n" + stus.get(i).getID() + "," + stus.get(i).getName() + "," + stus.get(i).getSex() + "," + stus.get(i).getTel();
        Files.write(Path.of(fileame, new String[]{}), text.getBytes(), StandardOpenOption.APPEND);
      }
    }
  }

}


class MAIN {

  public static void main(String[] args) throws IOException {
    Util util = new Util();
    StudentList students = new StudentList();
    students.readDataFromStorage("StudentList.txt");
    String errorMes = "\tX - Invalid input, try again: ";
    Scanner input = new Scanner(System.in);

    while(true) {
      util.clsScr();
      Student.showStudentMenu();
      // Department department = new Department();
  
      int opt = util.getInt(util.YELLOW+ "\t\\> " + util.RESET+ "Choose a option: ", errorMes);
      
      // add a student into list
      if(opt == 1) {
        System.out.println(util.YELLOW+ "\n\t\\> " +util.RESET+ "Enter a student following by this format (ID, name, sex, phone)");
        System.out.print("\t   Add new student: ");
        String[] text = input.nextLine().split(",");
        students.addStudent(new Student(text[0].trim(), text[1].trim(), text[2].trim(), text[3].trim()));
      } 
      
      // Show student list
      else if(opt == 2) {
        students.showList();
      } 
      
      // Remove student by name
      else if(opt == 3) {
        System.out.print(util.YELLOW+ "\n\t\\> " +util.RESET+ "Enter student'name: ");
        students.removeByName(input.nextLine());
      } 
      
      // Update student by ID
      else if(opt == 4) {
        System.out.print("\t|> Enter student's ID: ");
        students.updateByID(input.nextLine());
      } 
      
      // break the program and update the storage
      else if(opt == 5) {
        students.updateStorage("StudentList.txt");
        break;
      } else {
        System.out.println("\tX - Invalid option.");
      }
      util.pause("\n\n\t");
    }
  }

}