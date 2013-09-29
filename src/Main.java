import javax.swing.*;
import java.awt.*;

public class Main extends JFrame{
  /*main method
   * this is the main method of the class
   * this will do everything
   */
  public static void main(String[] args){
    JFrame frame = new JFrame("Supply Chain Sim");
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.add(new SimPanel());
    frame.pack();
    
    frame.setVisible(true);
  }
}
