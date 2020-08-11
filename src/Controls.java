import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

public class Controls extends JPanel
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int x, y;
	private JButton wall;

	public Controls(int x, int y)
	{
		super();
		this.setFocusable(true);
		this.setPreferredSize(new Dimension(100, y));
		this.setBackground(Color.BLACK);

		wall = new JButton("Wall");
		JButton player = new JButton("Player");

		wall.addActionListener(new ActionListener()
		{

			@Override
			public void actionPerformed(ActionEvent e)
			{
				Panel.player = false;
				if (Panel.wall)
				{
					Panel.wall = false;
				} else
				{
					Panel.wall = true;
				}

			}
		});

		player.addActionListener(new ActionListener()
		{

			@Override
			public void actionPerformed(ActionEvent e)
			{
				Panel.wall = false;
				if (Panel.player)
				{
					Panel.player = false;
				} else
				{
					Panel.player = true;
				}
			}
		});

		this.add(wall);
		this.add(player);

	}

}

class MPanel extends JPanel
{
	public MPanel(int x, int y)
	{
		super();
		this.add(new Controls(x, y));
		this.add(new Panel(x, y));

	}
}
