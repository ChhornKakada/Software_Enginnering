import java.util.Scanner;

import javax.swing.text.html.HTMLDocument.RunElement;

public class MessageCoder {
  private String text;

  public MessageCoder(String text) { this.text = text; }
  public MessageCoder() {}
  
  public void setText(String text) { this.text = text; }
  public String getText() { return this.text; }

  public String encode() {
     String encode = "";
    for(int i=0; i<this.text.length(); i++){
      if(i == this.text.length()-1 && this.text.charAt(i) == '\\'){
        encode += "\\";
      } else{
        if(this.text.charAt(i) == '\\'){
          if(this.text.charAt(i+1) == 'n') {
            encode += "(new_line)"; 
            i += 1;
          } else if(this.text.charAt(i+1) == 't') {
            encode += "(tap)"; 
            i += 1;
          } else if(this.text.charAt(i+1) == '\\') {
            encode += "(slash)"; 
            i += 1;
          } else encode += "\\";
          
        } else if(this.text.charAt(i) == '/' && this.text.charAt(i+1) == '/'){
          encode += "(comment_line)";
          i +=1;
        } else if(this.text.charAt(i) == ':' && this.text.charAt(i+1) == ')'){
          encode += "(smile)";
          i += 1;
        } else encode += this.text.charAt(i);
      }
    }
    return encode;
  }

  public String decode() {
    String _text, check = "";

    if(this.encode() == null) _text = this.text;
    else _text = this.encode();

    String decode = "";

    for(int i=0; i<_text.length(); i++){
      if(_text.charAt(i) == '('){
        if(_text.charAt(i+1) == '(') decode += _text.charAt(i);
        else {
          for(int j=i+1; j<_text.length(); j++){
            if(_text.charAt(j) == ')') break;
            else check += _text.charAt(j);
          }
          if(check.equals("slash")) decode += "\\\\"; 
          else if(check.equals("new_line")) decode += "\\n";
          else if(check.equals("comment_line")) decode += "//";
          else if(check.equals("tap")) decode += "\\t";
          else if(check.equals("smile")) decode += ":)";
          else decode += "(" + check + ")";
        
          i = i + check.length() + 1;
          check = ""; // reset check
        }
      } else decode += _text.charAt(i);
    }
    return decode;
  }
}
