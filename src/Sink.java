import java.awt.*;
import java.util.*;
import javax.swing.*;
public class Sink {
	private int counter;
	public static final Point location = new Point(1200,200);
	private final ImageIcon img = new ImageIcon(getClass().getResource("Sink.png"));
	public Sink(){
		counter = 0;
	}
	
	public int getCount(){
		return counter;
	}
	
	public void recievePack(){
		counter++;
	}
	
	public void draw(Graphics g){
		img.paintIcon(null, g, location.x-img.getIconWidth()/2, location.y-img.getIconHeight()/2);
	}
}
