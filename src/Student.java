package src;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Scanner;

import Util.MyFile;
import Util.Util;

public class Student {
  private String name, tel, country, city, group, ID;
  private Date dob;
  private boolean isBorrowing = false;

  // Is Borrowing
  public boolean getBorrowing() { return this.isBorrowing; }
  public void setBorrowing(boolean borrow) { this.isBorrowing = borrow; }

  // ID
  public String getID() { return ID; }
  public void setID(String ID) { this.ID = ID; }

  // Tel 
  public String getTel() { return tel; }
  public void setTel(String tel) { this.tel = tel; }

  // Country
  public String getCountry() { return country; }
  public void setCountry(String country) { this.country = country; }

  // City
  public String getCity() { return city; }
  public void setCity(String city) { this.city = city; }

  // Group
  public String getGroup() { return group; }
  public void setGroup(String group) { this.group = group; }

  // Name
  public String getName() { return name; }
  public void setName(String name) { this.name = name; }

  // Birth of date
  public Date getDob() { return dob; }
  public void setDob(Date dob){
    Calendar now = Calendar.getInstance();
    Calendar born = Calendar.getInstance();
    born.setTime(dob);
    if(now.compareTo(born) > 0) {
      this.dob = dob;
    } else {
      // throw new InvalidBirthDateException();
      // throw new ArithmeticException("\tInvalid Birth date.");
      System.out.println("\tError - Invalid BirthDate.");
    } 
  }


  // Data input of the student
  public static Student dataInput() {
    Scanner sc = new Scanner(System.in);
      SimpleDateFormat format = new SimpleDateFormat("dd.MM.yyyy");
      Student stu = new Student();
      // Calendar temp = Calendar.getInstance();
      Date temp = new Date();

      System.out.print("                     Name : ");
      stu.name = sc.nextLine();
      System.out.print("                       ID : ");
      stu.ID = sc.nextLine();

      System.out.print("    Birth Date(dd.MM.yyy) : ");
      try{
        temp = format.parse(sc.nextLine());
        Calendar now = Calendar.getInstance();
        Calendar born = Calendar.getInstance();
        born.setTime(temp);
        if(now.compareTo(born) > 0) {
          stu.dob = temp;
        } else {
          System.out.println("\tError - Date is invalid.");
        }
      } catch (Exception e) {
        System.out.println("\tInvalid birth date format.");
      }
      
      System.out.print("                      Tel : ");
      stu.tel = sc.nextLine();
      System.err.print("                    Group : ");
      stu.group = sc.nextLine();
      System.err.print("                  Country : ");
      stu.country = sc.nextLine();
      System.err.print("                     City : ");
      stu.city = sc.nextLine();

      String tmpStu = stu.toLine();
      MyFile.appendLine("Storage/StudentList.txt", tmpStu);
      // sc.close();
      return stu;    
    
  }

  // data output of the student (display student info)
  public void dataOutput() {
    SimpleDateFormat DateFormat = new SimpleDateFormat("EE dd.MMM.yyyy");
    System.out.printf("""
        \t\tStudent info...\n
        \t\t          Name  :  %s
        \t\t            ID  :  %s
        \t\t       Born on  :""", this.name, this.ID);
 
    if(this.dob != null) {
      System.out.print("  " +DateFormat.format(this.dob));
    }
 
    System.out.printf("""
        \n\t\t           Tel  :  %s
        \t\t         Group  :  %s
        \t\t       Country  :  %s
        \t\t          City  :  %s
        """, this.tel, this.group, this.country, this.city);
    
  }

  // Student to sting
  public String toLine() {
    // id, name, borndate, tel, group, country, city
    String result = this.ID+ "~`~" +this.name+ "~`~" +Util.formatDate(this.dob, "dd.MM.yyyy")
                    + "~`~" +this.tel+ "~`~" +this.group+ "~`~" +this.country+ "~`~"+ this.city;
    return result;
  }

  // Line to student
  public static Student lineToStudent(String line) {
    String[] lines = line.split("~`~");
    Student stu = new Student();
    stu.ID = lines[0];
    stu.name = lines[1];
    stu.dob = Util.lineToDate(lines[2],"dd.MM.yyyy");
    stu.tel = lines[3];
    stu.group = lines[4];
    stu.country = lines[5];
    stu.city = lines[6];
    return stu;
  }

  // get student by ID
  public static Student getByID(String ID) {
    String filePath = "Storage/StudentList.txt";
    BufferedReader reader;
    try {
			reader = new BufferedReader(new FileReader(filePath));
			String line = reader.readLine();

			while (line != null) {
        Student tmpStu = Student.lineToStudent(line);
        if(tmpStu.ID.equals(ID)) {
          reader.close();
          return tmpStu;
        }
				line = reader.readLine();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
    return null;
  }

  // header of the student
  public static void header() {
    System.out.println("""
      \t| No |     ID     |          Name          |   Birth Date   |    tel     | Group |     Country     |      City      |
      \t+----+------------+------------------------+----------------+------------+-------+-----------------+----------------+""");
  }

  // display student as line
  public void displayAsLine() {
    // | No |     ID     |          Name          |   Birth Date   |    tel     | Group |     Country     |      City      |
    // +----+------------+------------------------+----------------+------------+-------+-----------------+----------------+
    System.out.printf(" %-10s   %-22s   %-14s   %-10s   %-5s   %-15s   %-14s", 
                      this.ID, this.name, Util.formatDate(this.dob, "EEE dd.MM.yyyy"), this.tel, this.group,
                      Util.trimTo(this.country, 15), Util.trimTo(this.city, 14));
  }

  public static void displayAllStudents() {
    String filePath = "Storage/StudentList.txt";
    BufferedReader reader;
    try {
			reader = new BufferedReader(new FileReader(filePath));
			String line = reader.readLine();
      Student.header();
      int i = 1;
			while (line != null) {
        Student stu = Student.lineToStudent(line);
        System.out.printf("\t  %02d  ", i);
        stu.displayAsLine();
        System.out.println();
				line = reader.readLine();
        i++;
			}
      reader.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
  }

}

