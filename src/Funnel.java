import java.awt.*;
import java.awt.List;
import java.util.*;
import javax.swing.*;
public class Funnel {
	public static final Point location = new Point(300,200);
	public static final Point input = new Point(275,200);
	public static final Point out = new Point(340,200);
	private static final int MAX = 25;
	private final ImageIcon img = new ImageIcon(getClass().getResource("Funnel.png"));
	private static LinkedList<Packs> list;
	public Funnel(){
		list =new LinkedList<Packs>();
	}
	
	public Packs sendPack(){
		if(!list.isEmpty()) return list.poll();
		return null;
	}
	
	public void recievePack(Packs p){
		p.setPos(out);
		list.add(p);
	}
	
	public LinkedList<Packs> getList(){
		return list;
	}
	
	public boolean isFull(){
		return list.size()>=MAX;
	}
	
	
	public void draw(Graphics g){
		img.paintIcon(null, g, location.x-img.getIconWidth()/2, location.y-img.getIconHeight()/2);
	}
	
	public boolean isEmpty(){
		return list.isEmpty();
	}
}
