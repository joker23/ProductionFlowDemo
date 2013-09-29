import java.util.*;
import java.util.List;
import java.awt.*;

public class Packs{
	private Point pos;
	private final int speed = 1;
	private boolean priority;
	Color c;
	private final int SIZE = 5;
	public Packs(boolean priority, Point pos){
		this.priority = priority;
		this.pos = pos;
		if(priority) c = Color.RED;
		else c =Color.gray;
	}
	public boolean getPriority(){
		return priority;
	}
	public void setPos(Point p){
		pos = p;
	}
	public void move(List<Point> path){
		int curr = path.indexOf(pos);
		try{
			pos = path.get(curr+speed);
		}catch(Exception e){}
	}
	public Point getLocation(){
		return pos;
	}
	public void draw(Graphics g){
		g.setColor(c);
		g.fillOval(pos.x-SIZE/2,pos.y-SIZE/2,SIZE,SIZE);
	}
}
