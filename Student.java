public class Student {
  private String ID = "", name = "", sex = "", tel = "";

  public Student(String ID, String name, String sex) {
    this.ID = ID;
    this.name = name;
    this.sex = sex;
  }

  public Student() {}

  public Student(String ID, String name, String sex, String phone) {
    this.ID    = ID;
    this.name  = name;
    this.sex   = sex; 
    this.tel = phone;
  }

  public void setID(String ID) { this.ID = ID; }
  public void setName(String name) { this.name = name; }
  public void setSex(String sex) { this.sex = sex; }
  public void setPhone(String phone) { this.tel = phone; }

  public String getID() { return this.ID; }
  public String getName() { return this.name; }
  public String getSex() { return this.sex; }
  public String getTel() { return this.tel; }

  public void setInfo(String ID, String name, String sex, String phone){
    this.ID = ID;
    this.name = name;
    this.sex = sex;
    this.tel = phone;
  }

  @Override
  public String toString(){
    return this.ID+ " | " +this.name+ " | " +this.sex+ " | " +this.tel;
  }

  public void show(){
    System.out.printf("| %-9s | %-25s | %-6s | %-10s |", this.ID, this.name, this.sex, this.tel);
  }


static void showStudentMenu(){
  System.out.println("""
      \n\t----- Student of a classroom -----
      \t----------------------------------
      \t  1. Add new student
      \t  2. List students
      \t  3. Remove student by name
      \t  4. Update student info by ID
      \t  5. Quit
      \t----------------------------------
      """);
  }
}

