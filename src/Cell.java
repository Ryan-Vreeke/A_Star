
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.List;

public class Cell extends Rectangle
{
	boolean wall, player, end;
	Point left, right, top, bottom;
	int w;
	double f = 0;
	int g = 0;
	double h = 0;
	Cell previous;

	public Cell(int x, int y, int w)
	{
		super(x, y, w, w);
		this.w = w;

	}

	public void draw(Graphics2D g)
	{
		if (wall)
		{
			g.setColor(Color.DARK_GRAY);
			g.fill(new Rectangle(this));
		} else if (player)
		{
			g.setColor(Color.GREEN);
			g.fill(new Rectangle(this));
		} else if (end)
		{
			g.setColor(Color.RED);
			g.fill(this);
		} else
		{
			g.setColor(Color.black);
			g.draw(new Rectangle(this));
		}
	}

	public void draw(Graphics2D g, Color c)
	{
		if (!player && !wall && !end)
		{
			g.setColor(c);
			g.fill(this);
		}
	}

	public void setWall()
	{
		if (!this.player && !this.end)
		{

			this.wall = true;
		}

	}

	public void setPlayer()
	{
		if (!end)
		{

			this.player = true;
			this.end = false;
			this.wall = false;
		}
	}

	public void setEnd()
	{
		this.end = true;
		this.player = false;
		this.wall = false;
	}

	public void findNeighbors()
	{

		Point p = new Point(x / w, y / w);

		if (p.x > 0)
		{
			this.left = new Point(p.x - 1, p.y);
		}

		if (p.x < Astar.n - 1)
		{
			this.right = new Point(p.x + 1, p.y);
		}

		if (p.y > 0)
		{
			this.top = new Point(p.x, p.y - 1);
		}
		if (p.y < Astar.n - 1)
		{
			this.bottom = new Point(p.x, p.y + 1);
		}
	}

	public List<Point> neighbors()
	{
		List<Point> temp = new ArrayList<Point>();
		if (left != null)
		{

			temp.add(left);
		}
		if (right != null)
		{

			temp.add(right);
		}
		if (bottom != null)
		{

			temp.add(bottom);
		}
		if (top != null)
		{
			temp.add(top);

		}

		return temp;
	}

}
