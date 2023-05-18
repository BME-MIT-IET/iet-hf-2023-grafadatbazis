package random_csapatnev;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;
import java.io.Serializable;

import javax.swing.JPanel;

/**
 * 
 * Karakterek grafikus megjelenítéséért felelős abstract osztály.
 *
 */
public abstract class GraphicsCharacter extends JPanel implements IGraphics, Serializable 
{
	int x=0,y=0,width=0,height=0;
	JPanel parent = null;
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
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		if(parent != null)
		{
			parent.remove(this);
		}
		parent = p;
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
		g.fillOval(width/4, height/4, width/2, height/2);
		g.setColor(Color.BLACK);
		if(c.name.equals("v0"))
		{
			Graphics2D g2 = (Graphics2D) g;
			g2.setStroke(new BasicStroke(4));
			g2.draw(new Ellipse2D.Double(width/4, height/4, width/2, height/2));
		}
        g.drawChars(c.name.toCharArray(), 0, c.name.length(), width/2-3, height/2+3);
	}
	
	public void Remove() {
		if (parent != null) {
			parent.remove(this);
		}
		parent = null;
	}
}
