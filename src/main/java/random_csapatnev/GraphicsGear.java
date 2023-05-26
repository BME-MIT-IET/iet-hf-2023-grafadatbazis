package random_csapatnev;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;

public abstract class GraphicsGear extends JPanel {
	JPanel graphicsparent;
	Safehouse position;
	transient BufferedImage img;
	
	public GraphicsGear(Safehouse input, JPanel parent) 
	{
		super();
		graphicsparent = parent;
		position = input;
		parent.add(this);
	}
	public void Draw()
	{
		this.setSize(graphicsparent.getWidth(), graphicsparent.getHeight());
		this.setVisible(true);
		this.setOpaque(false);
		this.revalidate();
		this.repaint();
	}
	@Override
	public void paint(Graphics g)
	{
		super.paint(g);
		if(position.gear != null)
		{
			g.drawImage(img, 0, graphicsparent.getHeight() - graphicsparent.getHeight()/2, graphicsparent.getWidth()/2, graphicsparent.getHeight()/2, null);
		}
	}
}
