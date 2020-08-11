import java.awt.BorderLayout;

import javax.swing.JFrame;

public class Main
{

	public static void main(String[] args)
	{
		JFrame frame = new JFrame();
		frame.setLayout(new BorderLayout());
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setTitle("A*");
		// set Panel
		frame.add(new MPanel(800,800));
		frame.pack();
		frame.setResizable(true);
		frame.setVisible(true);
		frame.setLocationRelativeTo(null);
	}

}
