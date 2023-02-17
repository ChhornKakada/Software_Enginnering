import com.github.kwhat.jnativehook.GlobalScreen;
import com.github.kwhat.jnativehook.NativeHookException;
import com.github.kwhat.jnativehook.keyboard.NativeKeyEvent;
import com.github.kwhat.jnativehook.keyboard.NativeKeyListener;

public class StopHit2 implements NativeKeyListener, Runnable {
  public static String word4Display = "HitMe! ";

  public void nativeKeyPressed(NativeKeyEvent e) {
    word4Display = NativeKeyEvent.getKeyText(e.getKeyCode());
    // if key == enter => break
    if (e.getKeyCode() == NativeKeyEvent.VC_ENTER) {
      try {
        GlobalScreen.unregisterNativeHook();
      } catch (NativeHookException nativeHookException) {
        nativeHookException.printStackTrace();
      }
    }
  }

  @Override
  public void run() {
    try {
      GlobalScreen.registerNativeHook();
    } catch (NativeHookException ex) {
      System.err.println("There was a problem registering the native hook.");
      System.err.println(ex.getMessage());
      System.exit(1);
    }
    GlobalScreen.addNativeKeyListener(new StopHit2());
  }

  public static void main(String[] args) {
    Util.clsScr();
    StopHit2 hitMe = new StopHit2();
    Thread tr = new Thread(hitMe);
    tr.start();

    while (!word4Display.equals("Enter")) {
      System.out.print(word4Display);
      try {
        Thread.sleep(200);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    }
    System.out.println("\nThank You!\n\n");
  }
  
  
}
