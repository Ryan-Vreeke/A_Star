import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JPanel;
import javax.swing.Timer;

public class Panel extends JPanel implements MouseListener
{
	private static final long serialVersionUID = 1L;
	private int myTimerDelay;
	private final Timer myTimer;
	private Astar a;
	public static boolean wall = false;
	public static boolean player = false;
	int n = 20;
	int w;
	boolean draw = false;

	public Panel(int width, int height)
	{
		super();
		this.setFocusable(true);
		this.requestFocusInWindow(true);
		this.setPreferredSize(new Dimension(width + 1, height + 1));
		this.setBackground(Color.WHITE);

		a = new Astar(width, height);
		this.addMouseListener(this);
		this.w = width / n;

		myTimerDelay = 16;
		myTimer = new Timer(myTimerDelay, gameTimer);
		myTimer.start();
	}

	ActionListener gameTimer = new ActionListener()
	{

		@Override
		public void actionPerformed(ActionEvent e)
		{

			tick();
			redraw();
		}

	};

	public void tick()
	{
		a.tick();
		if (draw)
		{
			try
			{

				Point p = new Point(getMousePosition().x / w, getMousePosition().y / w);
				if (this.wall)
				{
					a.click(p, Item.WALL);
				}
			} catch (Exception e)
			{
				// TODO: handle exception
			}
		}

	}

	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D) g;
		g2d.setColor(Color.black);

		a.draw(g2d);

	}

	public void redraw()
	{
		this.repaint();

	}

	@Override
	public void mouseClicked(MouseEvent e)
	{
		Point p = new Point(e.getX() / w, e.getY() / w);
		System.out.println(p);
		if (this.wall)
		{
			a.click(p, Item.WALL);
		}

		if (this.player)
		{
			a.click(p, Item.PLAYER);
		}

	}

	@Override
	public void mousePressed(MouseEvent e)
	{
		draw = true;

	}

	@Override
	public void mouseReleased(MouseEvent e)
	{
		draw = false;

	}

	@Override
	public void mouseEntered(MouseEvent e)
	{
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent e)
	{
		// TODO Auto-generated method stub

	}
}
