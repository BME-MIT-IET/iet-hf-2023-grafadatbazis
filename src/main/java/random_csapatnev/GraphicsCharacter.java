package random_csapatnev;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;

import javax.swing.JPanel;

/**
 * 
 * Karakterek grafikus megjelenítéséért felelős abstract osztály.
 *
 */
public abstract class GraphicsCharacter extends JPanel implements IGraphics 
{
	int graphicsx = 0;
	int graphicsy = 0;
	int graphicswidth = 0;
	int graphicsheight = 0;
	JPanel graphicsparent = null;
	Character c;
	Color color = Color.LIGHT_GRAY;
	GraphicsCharacter(Character c)
	{
		super();
		this.c = c;
	}
	@Override
	public void Draw(JPanel p, int x, int y, int width, int height)
	{
		graphicsx = x;
		graphicsy = y;
		graphicswidth = width;
		graphicsheight = height;
		if(graphicsparent != null)
		{
			graphicsparent.remove(this);
		}
		graphicsparent = p;
		p.add(this);
		this.setSize(width, height);
		this.setVisible(true);
		this.setOpaque(false);
		this.revalidate();
		this.repaint();
	}
	@Override
	public void paint(Graphics g)
	{
		super.paint(g);
		g.setColor(color);
		g.fillOval(graphicswidth/4, graphicsheight/4, graphicswidth/2, graphicsheight/2);
		g.setColor(Color.BLACK);
		if(c.name.equals("v0"))
		{
			Graphics2D g2 = (Graphics2D) g;
			g2.setStroke(new BasicStroke(4));
			g2.draw(new Ellipse2D.Double(graphicswidth/4, graphicsheight/4, graphicswidth/2, graphicsheight/2));
		}
        g.drawChars(c.name.toCharArray(), 0, c.name.length(), graphicswidth/2-3, graphicsheight/2+3);
	}
	
	public void remove() {
		if (graphicsparent != null) {
			graphicsparent.remove(this);
		}
		graphicsparent = null;
	}
}
