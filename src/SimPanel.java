import javax.swing.*;

import java.awt.*;
import java.awt.event.*;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.*;
import java.text.*;
public class SimPanel extends JPanel{
	private final int WIDTH = 1500, HEIGHT = 600;
	private Source s;
	private Sink e;
	private Funnel f;
	private Processor proc;
	private LinkedList<Point> path;
	private static int ptr = 10,p1=0;
	private LinkedList<Packs> pOnField,pOnField2,pOnField3;
	private javax.swing.Timer packetTimer,packetTimer2,SourceTimer1,SourceTimer2,SourceTimer3,packetTimer3;
	public SimPanel(){
			setPreferredSize(new Dimension(WIDTH,HEIGHT));
			
			path = new LinkedList<Point>();
			proc = new Processor();
			pOnField = new LinkedList<Packs>();
			pOnField2 = new LinkedList<Packs>();
			pOnField3 = new LinkedList<Packs>();
			s = new Source();
			for(Packs p: s.getList()){
				if(p.getPriority()) p1++;
			} 
			e = new Sink();
			f = new Funnel();
			for(int i=s.location.x;i<=1200;i++){
				path.add(new Point(i,s.location.y));
			}
			packetTimer = new javax.swing.Timer(10, new PacketListener());
		    packetTimer.setRepeats(true);
		    packetTimer.start();
		    
		    packetTimer3 = new javax.swing.Timer(10, new PacketListener3());
		    packetTimer3.setRepeats(true);
		    packetTimer3.start();
		    
		    packetTimer2 = new javax.swing.Timer(10, new PacketListener2());
		    packetTimer2.setRepeats(true);
		    packetTimer2.start();
		    
		    SourceTimer1 = new javax.swing.Timer(200, new SourceListener1());
		    SourceTimer1.setRepeats(true);
		    SourceTimer1.start();
		    
		    SourceTimer2 = new javax.swing.Timer(200, new SourceListener2());
		    SourceTimer2.setRepeats(true);
		    SourceTimer2.start();
		    
		    SourceTimer3 = new javax.swing.Timer(200, new SourceListener3());
		    SourceTimer3.setRepeats(true);
		    SourceTimer3.start();
		    
	}
	public void paintComponent(Graphics page){
	    super.paintComponent(page);
	    s.draw(page);
	    e.draw(page);
	    f.draw(page);
	    proc.draw(page);
	    page.setColor(Color.black);
	    page.drawString("completed: "+ e.getCount(), 1200, 175);
	    for(Packs p: pOnField){
			p.draw(page);
		}
	    for(Packs p: pOnField2){
			p.draw(page);
		}
	    for(Packs p: pOnField3){
			p.draw(page);
		}
	    int num = 0, offset = 300, offy = 300;
	    for(Packs p: f.getList()){
	    	new Packs(p.getPriority(),new Point(num+offset,offy)).draw(page);
	    	num-=10;
	    }
	    
	    SourceTimer1.start();
	    packetTimer.start();
	    SourceTimer2.start();
	    packetTimer2.start();
	    packetTimer3.start();
	    SourceTimer3.start();
	    
	    if(f.isFull()){
	    	SourceTimer1.stop();
	    	packetTimer.stop();
	    } if(pOnField.isEmpty()) packetTimer.stop();
	    if(s.isEmpty()) SourceTimer1.stop();
	    if(proc.isFull()){
	    	SourceTimer2.stop();
	    	packetTimer2.stop();
	    }
	    
	    
	}
	private class PacketListener implements ActionListener{
		public void actionPerformed(ActionEvent c) {
			LinkedList<Packs> rem = new LinkedList<Packs>();
			for(Packs p : pOnField){
				p.move(path);
				if(p.getLocation().equals(f.input)){
						f.recievePack(p);
						rem.add(p);
				}
			}for(Packs i: rem){
				pOnField.remove(i);
			}
			repaint();
		}
	}
	
	private class PacketListener3 implements ActionListener{
		public void actionPerformed(ActionEvent c) {
			LinkedList<Packs> rem = new LinkedList<Packs>();
			for(Packs p : pOnField3){
				p.move(path);
				if(p.getLocation().equals(path.getLast())){
						e.recievePack();
						rem.add(p);
				}
			}for(Packs i: rem){
				pOnField3.remove(i);
			}
			repaint();
		}
	}
	
	private class PacketListener2 implements ActionListener{
		public void actionPerformed(ActionEvent c) {
			LinkedList<Packs> rem = new LinkedList<Packs>();
			for(Packs p: pOnField2){
				p.move(path);
				if(p.getLocation().equals(proc.location)){
						proc.input(p);
						rem.add(p);
				}
			}for(Packs i: rem){
				pOnField2.remove(i);
			}
			repaint();
		}
	}
	
	
	private class SourceListener1 implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			Packs p = s.sendPack();
			pOnField.add(p);
		}
	}
	
	private class SourceListener3 implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			proc.updateBox();
			LinkedList<Packs> temp = proc.output();
			if(temp.size()>0){
				while(!temp.isEmpty())
					pOnField3.add(temp.poll());
			}
		} 
	}
	
	private class SourceListener2 implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			Packs curr = f.sendPack();
			if(curr!=null){
				if(curr.getPriority()){
					pOnField2.add(curr);
					p1--;
				}
				else if(p1<ptr){
					pOnField2.add(curr);
				} else{
					f.recievePack(curr);
				}
			} boolean purge =false;
			if(f.isFull()){
				purge = true;
				for(Packs p:f.getList()){
					if(p.getPriority()){
						purge = false;
						break;
					}
				}
			}
			if(purge){
				while(!f.getList().isEmpty()){
					s.list.add(f.getList().poll());
				}
			}
			repaint();
		}
	}
}
