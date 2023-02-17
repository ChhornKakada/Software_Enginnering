import java.util.Scanner;

public class RescueTheQueen {
  private String[] question = {
    "\n\tQ1. You enter into the first room, here it is a lot of gold. Whether you will take it?",
    "\n\tQ2. You pass in a following room. It is full of diamonds, whether you will take diamonds?",
    "\n\tQ3. You enter the third room. A person was attacking by a dragon! To move further, not paying to them of attention?"
  };

  private String[][] answer = {
    { "\n\t\\> Gold remains to you, but you have ruined test. \n\t   GAME is over!!!", 
      "\n\t\\> Congratulation, you have passed the first test honor!"},
    { "\n\t\\> Diamonds remain to you, but you have ruined the second test. \n\t   GAME is over!!!", 
      "\n\t\\> Congratulation, you have passed the second test of honor!!!"},
    { "\n\t\\> You try to pass past, but a dragon notice your presence and transforms you into ashes. You are dead!!! \n\t   GAME is over!!!", 
      "\n\t\\> Congratulation, you have pass all tests of honor. \n\t   Princess gets to you!!!"}
  };

  public boolean checkAnswer(int question, String answer) {
    if(question == 1 && answer.equals("B")) return true;
    else if(question == 2 && answer.equals("B")) return true;
    else if(question == 3 && answer.equals("B")) return true;
    else return false; 
  }

  public void showQuestion(int i) {
    System.out.println(this.question[i-1]);
  }

  public void showAnswer(int question, String answer) {
    int ans;
    if(answer.equals("A")) ans = 0;
    else ans = 1;
    System.out.println(this.answer[question-1][ans]);
  }

}

class examEx3 {

  static String getOnlyAorB() {
    Scanner input = new Scanner(System.in);
    String answer;
    while(true) {
      answer = input.nextLine().toUpperCase();
      if(!answer.equals("A") && !answer.equals("B")) {
        System.out.print("\tEnter only a character(A/B): ");
      } else break;
    }
    return answer;
  }

  public static void clsScr() {
    System.out.print("\033[H\033[2J");
    System.out.flush();
}

  // press any key to continue
  public static void pause(String tap) {
      System.out.print(tap+ "Press enter to continue...");
      try{System.in.read();}catch(Exception e){e.printStackTrace();}
  }

  public static void main(String[] args) {
    RescueTheQueen Hero = new RescueTheQueen();

    clsScr();

    // get answer from user
    Hero.showQuestion(1);
    System.out.print("\t   (A. Yes, B. No): ");
    String answer = getOnlyAorB().toUpperCase();

    // if(Hero.checkAnswer(1, answer)) {
    //   Hero.showAnswer(1, answer);

    //   // Ask question 2 to user and get the answer
    //   Hero.showQuestion(2);
    //   System.out.print("\t   (A. Yes, B. No): ");
    //   answer = getOnlyAorB().toUpperCase();

    //   if(Hero.checkAnswer(2, answer)) {
    //     Hero.showAnswer(2, answer);

    //     // Ask question 3 to user and get the answer
    //     Hero.showQuestion(3);
    //     System.out.print("\t   (A. Yes, B. No): ");
    //     answer = getOnlyAorB().toUpperCase();

    //     Hero.showAnswer(3, answer);

    //   } else Hero.showAnswer(2, answer);
    // } else Hero.showAnswer(1, answer);

    // System.out.println("\n\n");

    // for my algorithm, I ask the question and show the answer before chack the correction of the answer.

    Hero.showAnswer(1, answer);
    if(Hero.checkAnswer(1, answer)) {

      // Ask question 2 to user
      Hero.showQuestion(2);
      System.out.print("\t   (A. Yes, B. No): ");

      // get asnwer and display answer
      answer = getOnlyAorB().toUpperCase();
      Hero.showAnswer(2, answer);

      if(Hero.checkAnswer(2, answer)) {

        // Ask question 2 to user
        Hero.showQuestion(3);
        System.out.print("\t   (A. Yes, B. No): ");

        // get answer and display answer
        answer = getOnlyAorB().toUpperCase();
        Hero.showAnswer(3, answer);
      }
    }
    System.out.println("\n\n");

  }
    
}
