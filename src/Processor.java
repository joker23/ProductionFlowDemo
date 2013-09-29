import java.awt.*;
import java.awt.List;
import java.util.*;
import javax.swing.*;
public class Processor {
	public static final Point location = new Point(750,200);
	LinkedList<Packs> list;
	final int numBoxes = 6;
	Random rand;
	private final ImageIcon img = new ImageIcon(getClass().getResource("Factory.png"));
	Box[] arr;
	public Processor(){
		arr = new Box[numBoxes];
		for(int i=0;i<arr.length;i++) arr[i] = new Box();
	}
	
	public boolean isFull(){
		boolean ret = true;
		for(Box b: arr){
			if(!b.isFull()){
				ret = false;
				break;
			}
		} return ret;
	}
	
	public void input(Packs p){
		int i;
		for(i=0;i<arr.length&&arr[i].isFull();i++);
		arr[i].store.add(p);
	}
	
	public LinkedList<Packs> output(){
		LinkedList<Packs> ret = new LinkedList<Packs>();
		for(Box b: arr){
			if(b.isFull()&&b.time >= b.finishTime){
				b.time = 0;
				while(!b.store.isEmpty()){
					ret.add(b.store.poll());
				}
			}
		} return ret;
	}
	public void draw(Graphics g){
		img.paintIcon(null, g, location.x-img.getIconWidth()/2, location.y-img.getIconHeight()/2);
		g.setColor(Color.black);
		g.drawString("Box1: "+arr[0].time+"%", 400, 400);
		arr[0].draw(g, new Point(400,450));
		g.setColor(Color.black);
		g.drawString("Box2: "+arr[1].time+"%", 500, 400);
		arr[1].draw(g, new Point(500,450));
		g.setColor(Color.black);
		g.drawString("Box3: "+arr[2].time+"%", 600, 400);
		arr[2].draw(g, new Point(600,450));
		g.setColor(Color.black);
		g.drawString("Box4: "+arr[3].time+"%", 700, 400);
		arr[3].draw(g, new Point(700,450));
		g.setColor(Color.black);
		g.drawString("Box5: "+arr[4].time+"%", 800, 400);
		arr[4].draw(g, new Point(800,450));
		g.setColor(Color.black);
		g.drawString("Box6: "+arr[5].time+"%", 900, 400);
		arr[5].draw(g, new Point(900,450));
	}
	public void updateBox(){
		for(Box b: arr){
			if(b.isFull()) b.time++;
		}
	}
	private class Box{
		public final int maxCapacity = 1,finishTime = 100;
		int time;
		LinkedList<Packs> store;
		public Box(){
			store = new LinkedList<Packs>();
			time = 0;
		}
		public boolean isFull(){
			return store.size()>=maxCapacity;
		}
		public void add(Packs p){
			store.add(p);
		}
		public void draw(Graphics g,Point loc){
			int offset = 0;
			for(Packs p: store){
				new Packs(p.getPriority(),new Point(loc.x+offset,loc.y)).draw(g);
				offset+=10;
			}
		}
	}
}
