package blockpkg;

import java.awt.Component;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Toolkit;
	
	public class Blocks extends Rectangle{
	Image pic;
	int dx = 3;
	int dy = -3;
	
	public boolean destroyed = false;

public Blocks(int xpos,int ypos,int w,int h,String S) {
	x= xpos;
	y= ypos;
	width = w;
	height = h;
	pic = Toolkit.getDefaultToolkit().getImage(S);
	
}
public void draw(Graphics g,Component C) {
	if (!destroyed) { 
	g.drawImage(pic,x,y,width,height,C);
} 
}
	}
