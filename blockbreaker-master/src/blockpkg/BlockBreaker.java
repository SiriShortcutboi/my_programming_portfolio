package blockpkg;

import java.awt.Graphics;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.KeyStroke;

public class BlockBreaker extends JPanel implements KeyListener{
	ArrayList<Blocks> blocks = new ArrayList<Blocks>();
	ArrayList<Blocks> balls = new ArrayList<Blocks>();
	
	Blocks paddle;
	Blocks ball;
	Thread thread;
	Animate animate;
	
	
	public BlockBreaker() {
		paddle = new Blocks (175,480,150,25,"paddle.png");
		balls.add(new Blocks(220,437,25,25,"ball.png"));
		for(int i = 0;i<8;i++) {
			blocks.add(new Blocks((i*60+2),0,60,25,"greenBlock2.png"));
		}
		for(int i1 = 0;i1<8;i1++) {
			blocks.add(new Blocks((i1*60+2),25,60,25,"blueBlock.png"));
		}
		for(int i2 = 0;i2<8;i2++) {
			blocks.add(new Blocks((i2*60+2),50,60,25,"purpleBlock.png"));
		}
		for(int i3 = 0;i3<8;i3++) {
			blocks.add(new Blocks((i3*60+2),75,60,25,"redBlock.png"));
		}
		for(int i4 = 0;i4<8;i4++) {
			blocks.add(new Blocks((i4*60+2),100,60,25,"orangeBlock.png"));
		}
		addKeyListener(this);
	setFocusable(true);
	}

public void paintComponent(Graphics g) {
	super.paintComponent(g);
	for(Blocks b :blocks) {
		b.draw(g,this);	
		}
	for(Blocks b :balls) {
		b.draw(g,this);	
		}
	paddle.draw(g,this);

}
public void update() {
	for(Blocks ba :balls) {
		ba.x+=ba.dx;
		ba.y+=ba.dy;
		
		if (ba.x > (getWidth()-25) && ba.dx > 0 || ba.x < 0 ) {
			ba.dx*=-1;
		}
		if (ba.y < 0 || ba.intersects(paddle)) {
			ba.dy*=-1;
		}
		for (Blocks b : blocks) {
			if (ba.intersects(b) && !b.destroyed) {
				b.destroyed = true;
				ba.dy*=-1;
				}
			}
		}
		repaint();
	}
	
	@Override 
	public void keyTyped(KeyEvent e) {
		//todo
		
		
		
	}
	@Override 
	public void keyPressed(KeyEvent e) {
//		if (e.getKeyCode() == KeyEvent.VK_CONTROL && e.getKeyCode()==KeyEvent.VK_W) {
//			Runtime.getRuntime().halt(0);
//		}
//		if (e.getKeyCode() == KeyEvent.VK_META*157 && e.getKeyCode()==KeyEvent.VK_W) {
//			Runtime.getRuntime().halt(0);
//		}
		if(e.getKeyCode() ==KeyEvent.KEY_PRESSED && e.getKeyCode()==157) { 
			if ( e.getKeyCode()==KeyEvent.VK_W){
				Runtime.getRuntime().halt(0);
				//https://stackoverflow.com/questions/100123/application-wide-keyboard-shortcut-java-swing
				//https://stackoverflow.com/questions/15418987/java-robot-keypress-command-key
		}
		
		    }
		if (e.getKeyCode() == KeyEvent.VK_ENTER) {
		animate = new Animate(this);
		thread = new Thread(animate);
		thread.start();
		
	}
	if (e.getKeyCode() == KeyEvent.VK_LEFT && paddle.x >0) {
		paddle.x -= 15;
	}
	if (e.getKeyCode() == KeyEvent.VK_RIGHT && paddle.x<(getWidth()-paddle.width)) {
		paddle.x += 15;
		}
	}
	@Override 
	public void keyReleased (KeyEvent e) {
		//todo 
	}
	}
