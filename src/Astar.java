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
	private Stack<Cell> path = new Stack<Cell>();
	Cell start;
	Cell end;
	Cell current;
	boolean newPath = false;

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
				temp.findNeighbors();
				map.put(new Point(i, j), temp);
			}
		}
		map.get(new Point(8, 8)).setEnd();
		end = map.get(new Point(8, 8));

	}

	public void tick()
	{

		if (!openSet.isEmpty())
		{
			int winner = 0;
			for (int i = 0; i < openSet.size(); i++)
			{
				if (openSet.get(i).f < openSet.get(winner).f)
				{
					winner = i;
				}
			}

			current = openSet.get(winner);

			if (current == end)
			{
				Cell tC = current;
				path.push(tC);
				while (tC.previous != null)
				{
					path.push(tC);
					tC = tC.previous;
				}

				return;
			} else
			{

				openSet.remove(current);
				closedSet.push(current);

				List<Cell> neighbors = getNCell(current.neighbors());

				for (Cell c : neighbors)
				{
					if (!closedSet.contains(c))
					{
						int tempG = current.g + 1;

						newPath = false;
						if (openSet.contains(c))
						{
							if (tempG < c.g)
							{
								c.g = tempG;
								newPath = true;
							}
						} else
						{
							c.g = tempG;
							newPath = true;
							openSet.push(c);
						}

						if (newPath)
						{

							c.h = heuristic(c, end);
							c.f = c.g + c.h;
							c.previous = current;

						}

					}
				}

			}

		} else
		{
			// fin
			return;
		}

	}

	public List<Cell> getNCell(List<Point> n)
	{
		List<Cell> nCell = new ArrayList<Cell>();

		for (Point p : n)
		{
			if (map.get(p).wall)
			{

			} else
			{

				nCell.add(map.get(p));
			}
		}

		return nCell;
	}

	public void draw(Graphics2D g2d)
	{

		g2d.setColor(Color.black);

		for (Point2D p : map.keySet())
		{
			map.get(p).draw(g2d);
		}

		for (Cell c : closedSet)
		{
			c.draw(g2d, new Color(255, 0, 0));
		}

		for (Cell c : openSet)
		{
			c.draw(g2d, new Color(0, 255, 0));
		}

		for (Cell c : path)
		{
			c.draw(g2d, new Color(0, 0, 255));
		}

	}

	public void click(Point p, Item i)
	{
		switch (i)
		{
			case WALL:
				map.get(p).setWall();

				break;
			case PLAYER:
				map.get(p).setPlayer();
				start = map.get(p);
				openSet.push(map.get(p));
				break;
		}
	}

	public double heuristic(Cell a, Cell b)
	{
		// double d = Math.sqrt(Math.pow(b.x - a.x, 2) + Math.pow(b.y - a.y, 2));
		double d = Math.abs(a.x - b.x) + Math.abs(a.y - b.y);
		return d;
	}

}

enum Item
{
	WALL, PLAYER, END
}
