public class Course {
    private String ID;
    public String title;
    protected String lecturer;

    public Course() {}
    
    public Course(String ID, String title) {
        this.ID = ID;
        this.title = title;
    }

    public Course(String ID, String title, String lecturer) {
        this.ID = ID;
        this.title = title;
        this.lecturer = lecturer;
    }

    public void setID(String ID) { this.ID = ID; }
    public String getID() { return this.ID; }

    public void display() {
      System.out.printf("\t|  %3s  |  %-30s |  %-20s  |\n", this.ID, this.title, this.lecturer);
    }

}
