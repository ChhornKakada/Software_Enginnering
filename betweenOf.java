import java.util.ArrayList;
import java.util.List;

public class betweenOf {
  private int startNumber, endNumber;
  private List<Integer> list = new ArrayList<Integer>();

  public betweenOf(int startNumber, int endNumber) {
    this.startNumber = startNumber;
    this.endNumber = endNumber;
  }

  public betweenOf() {}

  public void addToList(int number) { this.list.add(number); }

  public int getStartNumber() { return startNumber; }
  public void setStartNumber(int number) { this.startNumber = number; }

  public int getEndNumber() { return endNumber; }
  public void setEndNumber(int number) { this.endNumber = number; }

  public void setList() {
    if(this.startNumber < this.endNumber){
      for(int i=this.startNumber; i<=this.endNumber; i++){ this.list.add(i); }
    } for(int i=this.startNumber; i>=this.endNumber; i--){ this.list.add(i); }
  }

  public List<Integer> getList() { 
    this.setList();
    return this.list; 
  }

  @Override
  public String toString(){
    String result = "";
    if(this.list.size() == 0) this.setList();
    for(int i=0; i<this.list.size(); i++){
      if(i == 0) result += this.list.get(i);
      else if(i == list.size()-1) result += " and " + this.list.get(i);
      else result += ", " + list.get(i);
    }
    return result;
  }
  
}
