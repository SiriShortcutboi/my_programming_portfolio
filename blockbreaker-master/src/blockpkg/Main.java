package blockpkg;

import javax.swing.JFrame;

public class Main {

	public static void main(String[] args) {
	JFrame Frame = new JFrame("Block Breaker");
	
	BlockBreaker panel = new BlockBreaker();
	
	Frame.getContentPane().add(panel);
	Frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	Frame.setVisible(true);
	Frame.setSize(500,600);
	Frame.setResizable(false);

	}

}
