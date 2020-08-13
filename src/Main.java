import java.awt.BorderLayout;
import java.util.Properties;

import javax.swing.JFrame;
import javax.swing.UIManager;

import com.jtattoo.plaf.smart.SmartLookAndFeel;

public class Main
{

	Main()
	{
		JFrame frame = new JFrame("Themes-Demo-Application");
		frame.setLayout(new BorderLayout());
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setTitle("A*");
		// set Panel
		frame.add(new MPanel(800, 800));
		frame.pack();
		frame.setResizable(true);
		frame.setVisible(true);
		frame.setLocationRelativeTo(null);
	}

	public static void main(String[] args)
	{

		try
		{
			Properties props = new Properties();


			SmartLookAndFeel.setCurrentTheme(props);
			UIManager.setLookAndFeel("com.jtattoo.plaf.hifi.HiFiLookAndFeel");
		} catch (Exception e)
		{
			System.out.println("Look and Feel not set");
		}

		Main m = new Main();

	}

}
