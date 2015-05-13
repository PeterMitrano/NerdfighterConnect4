import javax.swing.JFrame;
public class Driver 
{
	public static void main(String[] args) 
	{
		JFrame JFwindow = new JFrame("Game");
		JFwindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
                        // close window when someone hit’s "x"
		
		JFwindow.getContentPane().add(new GUICLASS()); 
                        // get’s Calc, and places it into THIS window/frame
		JFwindow.pack();
		JFwindow.setVisible(true);
	}
}
