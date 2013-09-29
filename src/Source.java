import java.awt.*;
import java.util.*;
import javax.swing.*;
public class Source {
	public static final Point location = new Point(50,200);
	LinkedList<Packs> list;
	Random rand;
	public final int numOfPacks = 50;
	private final ImageIcon img = new ImageIcon(getClass().getResource("Factory.png"));
	public Source(){
		rand = new Random();
		list = new LinkedList<Packs>();
		for(int i=0;i<numOfPacks;i++){
			list.add(new Packs(rand.nextInt(100)%2==0,location));
		}
	}
	
	public Packs sendPack(){
		if(!list.isEmpty()) return list.poll();
		return null;
	}
	
	public void draw(Graphics g){
		img.paintIcon(null, g, location.x-img.getIconWidth()/2, location.y-img.getIconHeight()/2);
	}
	
	public LinkedList<Packs> getList(){
		return list;
	}
	
	public boolean isEmpty(){
		return list.isEmpty();
	}

}
