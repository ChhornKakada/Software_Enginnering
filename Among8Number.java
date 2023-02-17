import java.util.ArrayList;
import java.util.List;

public class Among8Number {
    private List<Double> list = new ArrayList<Double>();

    public Among8Number(List<Double> list) { this.list = list; }
    public Among8Number() {}
    public void setList(List<Double> data) { this.list = data; }
    public void addEle(double data) { this.list.add(data); }
    public List<Double> getList() { return list; }
    public int getSize() {return this.list.size(); }
    
    public boolean isValid() { 
        return this.getSize() == 8 ? true : false; 
    }

    public double Max() {
        double max = this.list.get(0);
        for(int i=0; i<this.list.size(); i++){
            if(this.list.get(i) > max) max = this.list.get(i);
        }
        return max;
    }
    
    public static void main(String[] args) {
        Util util = new Util();
        util.clsScr();

        Among8Number list = new Among8Number();

        System.out.println("""
            \n=== Find maximum number among 8 numbers ===
            \nPlease input 8 number...""");

        for(int i=0; i<8; i++){
            System.out.printf("    number #%d: ", i+1);
            list.addEle(util.getDouble("X - Invalid input! Input again: "));
        }
        System.out.printf("\nResult: The maximum among these %d number is %.2f\n\n", list.getSize(), list.Max() );
    }
}
