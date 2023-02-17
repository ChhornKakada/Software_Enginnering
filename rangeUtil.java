public class rangeUtil extends betweenOf{
  private int step;

  public rangeUtil(int startNumber, int endNumber, int step) {
    super(startNumber, endNumber);
    this.step = step;
  }

  public rangeUtil(){}

  public int getStep(){ return this.step; }
  public void setStep(int step){ this.step = step; }

  public void setList(){
    int end = this.getEndNumber(), start = this.getStartNumber();
    if(start < end){
      for(int i=start; i<=end; i+=this.step){
        this.addToList(i);
      }
    } else{
      for(int i=start; i>=end; i-=this.step){
        this.addToList(i);
      }
    }
  }

  public static void main(String[] args) {
    Util util = new Util();
    util.clsScr();

    rangeUtil rangeList = new rangeUtil();
    String errorInput = util.RED+ "X - Invalid. Try again: " +util.RESET;

    System.out.println("=== Display the number from n to m by step ===");
    System.out.println("\nPlease input...");
    rangeList.setStartNumber(util.getInt("    n value: ", errorInput));
    rangeList.setEndNumber(util.getInt("    m value: ", errorInput));
    rangeList.setStep(util.getInt("    Step: ", errorInput));

    if(rangeList.getStartNumber() == rangeList.getEndNumber()){
      System.out.println(util.YELLOW+ "\nNoted: " +util.RESET+ "n and m need to has different value.\n");
    } else{
      int _size = rangeList.getList().size();
      if(_size == 0){
        System.out.println("\nThere is no number between these numbers.\n");
      } else{
        System.out.println(util.YELLOW+ "\nResult: " +util.RESET+rangeList+ ".\n");
      }
    }
  }
}
