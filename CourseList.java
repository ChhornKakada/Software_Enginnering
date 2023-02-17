import java.util.Scanner;
import java.util.Vector;

public class CourseList {
    private Vector <Course> Courses = new Vector<>();

    public CourseList() {}

    public void add(Course course) {
      if(this.isIDExist(course.getID())) {
        System.out.println("\n\tX - Cannot add, ID is already exist.");
      } else {
        this.Courses.addElement(course);
        System.out.println("\n\t|> The course has been add successfully!");
      }
    }

    public void displayList() {
      if(this.Courses.size() == 0) {
        System.out.println("\t|> Sorry, there is not data yet.");
      } else {
        System.out.println("""
            \n\t|> List of the courses in the department.
            \t+-------+---------------------------------+------------------------+
            \t|  ID   |              Title              |        Lecturer        |
            \t+-------+---------------------------------+------------------------+""");
        for(var course : Courses) {
          course.display();
        }
        System.out.println("\t+-------+---------------------------------+------------------------+");
      }
    }

    public boolean isIDExist(String ID) {
      for(var course : this.Courses) {
        if(course.getID().equals(ID)) return true;
      }
      return false;
    }

    public boolean isCourseExist(String title) {
      for(var course : Courses) {
        if(course.title.toLowerCase().equals(title.toLowerCase())) return true;
      }
      return false;
    }

    // input the 'name' of the course, then get the course that contain 'name'
    public Course getCourseByName(String name) {
      for(int i=0; i<Courses.size(); i++) {
        if(Courses.elementAt(i).title.toLowerCase().equals(name.toLowerCase())) return Courses.get(i);
      }
      return null;
    }

    public void showCourseByName(String name) {
      if(this.isCourseExist(name)) {
        System.out.println("""
            \t+-------+---------------------------------+------------------------+
            \t|  ID   |              Title              |        Lecturer        |
            \t+-------+---------------------------------+------------------------+""");
        this.getCourseByName(name).display();
        System.out.println("\t+-------+---------------------------------+------------------------+");
      } else {
        System.out.println("X - Sorry, name not found!");
      }
    }

}

class tp6ex4 {

  public static void main(String[] args) {
    CourseList Courses = new CourseList();
    Util util = new Util();
    Scanner input = new Scanner(System.in);

    Courses.add(new Course("001", "Softwear Engineering", "Deourn Den"));
    Courses.add(new Course("002", "Internet Programming", "Chhorn Kakada"));
    Courses.add(new Course("003", "Computer A rchitecture", "Deourn Sreynith"));

    while(true) {
      util.clsScr();
      System.err.println("""
        \n\t----------- Option ----------- 
        \t    1. List all courses
        \t    2. Find courses by name
        \t    3. Add new course
        \t    4. Quit
        \t-------------------------------""");

      int opt = util.getInt("\tChoose an option: ", "\tX - Invalid input, try again: ");

      if(opt == 1) {
        Courses.displayList();
      } 
      
      else if(opt == 2) {
        System.out.print("\n\t|> Enter name of the course: ");
        String name = input.nextLine();
        Courses.showCourseByName(name);
      } 
      
      else if(opt == 3) {
        Course course = new Course();

        System.out.println("\n\tEnter infor of the course...");
        System.out.print("\t    ID: ");
        course.setID(input.nextLine());
        System.err.print("\t    Name: ");
        course.title = input.nextLine();
        System.out.print("\t    Lecturer: ");
        course.lecturer = input.nextLine();

        Courses.add(course);

      } else System.out.println("\tX - Sorry, Invalid option.");

      util.pause("\n\n\t");
    }

  }
}
