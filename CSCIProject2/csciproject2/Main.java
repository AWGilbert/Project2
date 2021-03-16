package csciproject2;

import javax.swing.JFrame;


public class Main
{

	public static void main(String[] args) 
	{
	       JFrame frame = new JFrame("Song Directory");
	       frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	       SongPanel panel = new SongPanel();
	       frame.getContentPane().add(panel);
	       
	       frame.pack();
	       frame.setResizable(false);
	       frame.setVisible(true);

	}
}