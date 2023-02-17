import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;

public class SMSList {
  private ArrayDeque <SMS> SMSList = new ArrayDeque<>();
  static int max_characters_per_sms = 160;

  public SMSList() {}

  public void add(SMS sms) {
    this.SMSList.addFirst(sms);
  }

  public void add(ArrayList<String> smg) {
    this.SMSList.addFirst(new SMS("", smg));
  }

  public void add(ArrayList<String> smg, String title) {
    this.SMSList.addFirst(new SMS(title, smg));
  }

  public void removeSMSByIndex(int index) {
    SMS sms = this.getSMSByIndex(index);
    this.SMSList.remove(sms);
  }

  public void removeSMSByIndex(String username, int index) {
    int check = 0;
    for(var sms : SMSList) {
      if(sms.from.equals(username) || sms.receiver.equals(username)) {
        check++;
        if(check == index) {
          this.SMSList.remove(sms);
          break;
        }
      }
    }
  }


  public void viewSMSDetail(int index) {
    int i=0;
    for(var sms : this.SMSList) {
      if(index == i) {
        sms.showDetail();
        break;
      }
      i++;
    }
  }

  public void viewSMSDetail(String username, int index) {
    int check = 0;
    for(var sms : SMSList) {
      if(sms.from.equals(username) || sms.receiver.equals(username)) {
        check++;
        if(check == index) {
          sms.showDetailWithoutPasswd();
          break;
        }
      }
    }
  }

  // this function display the SMS using password to decrypt the content and 'isRead = true' after display
  public void viewSMSDetail(int index, String passwd) {
    ArrayList <SMS> temp = new ArrayList<>();
    if(index > this.SMSList.size()/2) {
      int i=0;
      for(var sms : this.SMSList) {
        if(i == index) {
          this.SMSList.getLast().read();
          sms.showDetail(passwd);
          break;
        }
        sms = this.SMSList.pollLast();
        temp.add(sms);
        i++;
      }

      for(int j=temp.size()-1; j>=0; j--) {
        this.SMSList.addLast(temp.get(j));
      }

    } else {
      int i=0;
      for(var sms : this.SMSList) {
        if(i == index) {
          this.SMSList.getFirst().read();
          sms.showDetail(passwd);
          break;
        }
        sms = this.SMSList.pollFirst();
        temp.add(sms);
        i++;
      }

      for(int j=temp.size()-1; j>=0; j--) {
        this.SMSList.addFirst(temp.get(j));
      }
    }
  }

  // read student from storage
  public void readDataFromStorage(String filename) throws IOException {
    List <String> textList = Files.readAllLines(Path.of(filename, new String[]{}));
    
    for(String txt : textList) {
      SMS sms = new SMS();
      sms.toSMS(txt);
      this.SMSList.addLast(sms);
    }
  }

  // update data of the student list
  public void updateStorage(String fileame) throws IOException {
    Files.delete(Path.of(fileame));
    String text;

    int i = 0;
    for(var sms : this.SMSList) {
      if(i == 0) {
        text = sms.toLine();
        Files.write(Path.of(fileame, new String[]{}), text.getBytes(), StandardOpenOption.CREATE);
      } else {
        text = "\n" + sms.toLine();
        Files.write(Path.of(fileame, new String[]{}), text.getBytes(), StandardOpenOption.APPEND);
      }
      i++;
    }
  }
  

  public void displaySMSesList() {
    if(this.SMSList.size() == 0) System.out.println("\tThere is no data yet.");
    else {
      System.err.println("\n\tAll SMSes in the storage.");
      System.out.println("""
          \t+----+--------------------+--------------------+------+--------+---------------+-------------------------+
          \t| No |       Sender       |      Receiver      | Type | Status |    Subject    |         Content         |
          \t+----+--------------------+--------------------+------+--------+---------------+-------------------------+""");

      int i = 1;
      for(var sms : this.SMSList) {
        // | %-20s | %-20s | %-4s |  %-4s  | %-15s | %-23s |
        System.out.printf("\t| %2d ", i);
        sms.display();
        System.out.print("\t+----+--------------------+--------------------+------+--------+---------------+-------------------------+\n");
        i++;
      }
    }
  }

  public void displaySMSesList(String username) {
    if(this.SMSList.size() == 0) System.out.println("\tThere is no data yet.");
    else {
      System.err.println("\n\tAll SMSes in the storage.");
      System.out.println("""
          \t+----+--------------------+--------------------+------+--------+---------------+-------------------------+
          \t| No |       Sender       |      Receiver      | Type | Status |    Subject    |         Content         |
          \t+----+--------------------+--------------------+------+--------+---------------+-------------------------+""");
      
      int i = 1;
      for(var sms : this.SMSList) {
        if(sms.from.equals(username) || sms.receiver.equals(username)){
          System.out.printf("\t| %2d ", i);
          sms.display();
          System.out.print("\t+----+--------------------+--------------------+------+--------+---------------+-------------------------+\n");
          i++;
        }
      }
    }
  }


  public void displayReadableMsg(String passwd) {
    System.err.println("\n\tAll readable SMSes in the storage using password: " +passwd+ "\n");
    System.out.println("""
          \t+----+--------------------+--------------------+------+--------+---------------+-------------------------+
          \t| No |       Sender       |      Receiver      | Type | Status |    Subject    |         Content         |
          \t+----+--------------------+--------------------+------+--------+---------------+-------------------------+""");
    int i = 1;
    for(var sms : this.SMSList) {
      if(sms.isPasswordCorrect(passwd)) {
        System.out.printf("\t| %2d ", i);
        sms.display();
        System.out.print("\t+----+--------------------+--------------------+------+--------+---------------+-------------------------+\n");
        i++;
      }
    }
  }

  
  public SMS getSMSByIndex(int index) {
    int i=0;
    for(var sms : this.SMSList) {
      if(i == index) return sms;
      i++;
    }
    return null;
  }

  public int size() { return this.SMSList.size(); }

  public int size(String username) {
    int count = 0;
    for(var sms : this.SMSList) {
      if(sms.from.equals(username) || sms.receiver.equals(username)) count++;
    }
    return count;
  }

}
