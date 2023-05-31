package random_csapatnev.viewclasses;


import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;
import random_csapatnev.modelclasses.Character;
import javax.swing.JPanel;

/**
 * 
 * Karakterek grafikus megjelenítéséért felelős abstract osztály.
 *
 */
public abstract class GraphicsCharacter extends JPanel implements IGraphics {
	int graphicsX = 0;
	int graphicsY = 0;
	int graphicsWidth = 0;
	int graphicsHeight = 0;
	JPanel graphicsParent = null;
	Character c;
	Color color = Color.LIGHT_GRAY;

	GraphicsCharacter(Character c) {
		super();
		this.c = c;
	}

	@Override
	public void draw(JPanel p, int x, int y, int width, int height) {
		graphicsX = x;
		graphicsY = y;
		graphicsWidth = width;
		graphicsHeight = height;
		if (graphicsParent != null) {
			graphicsParent.remove(this);
		}
		graphicsParent = p;
		p.add(this);
		this.setSize(width, height);
		this.setVisible(true);
		this.setOpaque(false);
		this.revalidate();
		this.repaint();
	}

	@Override
	public void paint(Graphics g) {
		super.paint(g);
		g.setColor(color);
		g.fillOval(graphicsWidth / 4, graphicsHeight / 4, graphicsWidth / 2, graphicsHeight / 2);
		g.setColor(Color.BLACK);
		if (c.getName().equals("v0")) {
			Graphics2D g2 = (Graphics2D) g;
			g2.setStroke(new BasicStroke(4));
			g2.draw(new Ellipse2D.Double(graphicsWidth / 4.0, graphicsHeight / 4.0, graphicsWidth / 2.0,
					graphicsHeight / 2.0));
		}
		g.drawChars(c.getName().toCharArray(), 0, c.getName().length(), graphicsWidth / 2 - 3, graphicsHeight / 2 + 3);
	}

	public void remove() {
		if (graphicsParent != null) {
			graphicsParent.remove(this);
		}
		graphicsParent = null;
	}
	public Character getC(){
		return this.c;
	}
}
