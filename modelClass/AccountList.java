import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AccountList {
  private HashMap <String, String> accoutList = new HashMap<>();

  // read data(account) from the storage
  public void readDataFromStorage(String filename) throws IOException {
    List <String> textList = Files.readAllLines(Path.of(filename, new String[]{}));

    for(String txt : textList) {
      String[] line = txt.split(","); 
      this.accoutList.put(line[0], line[1]);
    }
  }

  // update the storage of the account
  public void updateDataStorage(String filename) throws IOException {
    Files.delete(Path.of(filename));
    String text;

    int j = 0;
    for(Map.Entry m : this.accoutList.entrySet()){    
      System.out.println(m.getKey()+" "+m.getValue());    
      if(j == 0) {
        text = m.getKey() + "," + m.getValue();
        Files.write(Path.of(filename, new String[]{}), text.getBytes(), StandardOpenOption.CREATE);
      } else {
        text = "\n" + m.getKey() + "," + m.getValue();
        Files.write(Path.of(filename, new String[]{}), text.getBytes(), StandardOpenOption.APPEND);
      }
      j++;
    }  
  }

  // register account
  public void register(String username, String password) {
    this.accoutList.put(username, password);
  }

  // delete specific account 
  public void remove(String username, String password) {
    this.accoutList.remove(username, password);
  }

  // check if the account is valid
  public boolean isAccExist(String username, String password) {
    for(Map.Entry acc : this.accoutList.entrySet()) {
      if(acc.getKey().equals(username) && acc.getValue().equals(password)) return true;
    }
    return false;
  }
}
