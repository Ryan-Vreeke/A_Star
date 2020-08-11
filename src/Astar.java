import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Stack;

public class Astar
{
	private int width, height;
	public static int n = 20;
	private int w;
	private HashMap<Point2D, Cell> map = new HashMap<Point2D, Cell>();
	private Stack<Cell> closedSet = new Stack<Cell>();
	private Stack<Cell> openSet = new Stack<Cell>();
	Cell start;
	Cell end;

	public Astar(int width, int height)
	{
		this.width = width;
		this.height = height;

		this.w = (width / n);

		for (int i = 0; i < n; i++)
		{
			for (int j = 0; j < n; j++)
			{
				Cell temp = new Cell(i * w, j * w, w);
				temp.neighbors();
				map.put(new Point(i, j), temp);
			}
		}
		map.get(new Point(19, 19)).setEnd();

		end = map.get(new Point(19, 19));
	}

	public void tick()
	{
		if (!openSet.isEmpty())
		{

		} else
		{
			// no solution
		}
	}

	public void draw(Graphics2D g2d)
	{

		g2d.setColor(Color.black);

		for (Point2D p : map.keySet())
		{
			map.get(p).draw(g2d);
		}
	}

	public void click(Point p, Item i)
	{
		switch (i)
		{
			case WALL:
				map.get(p).setWall();

				for (Point pt : map.get(p).adj())
				{
					if (pt != null)
					{

						map.get(pt).setWall();
					}
				}

				break;
			case PLAYER:
				map.get(p).setPlayer();
				start = map.get(p);
				openSet.push(map.get(p));
				break;
		}
	}

}

enum Item
{
	WALL, PLAYER, END
}
