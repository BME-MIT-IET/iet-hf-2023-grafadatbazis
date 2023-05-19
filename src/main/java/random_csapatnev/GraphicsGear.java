package random_csapatnev;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.Serializable;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

public abstract class GraphicsGear extends JPanel implements Serializable {
	JPanel parent;
	Safehouse position;
	transient BufferedImage img;
	
	public GraphicsGear(Safehouse input, JPanel parent) 
	{
		super();
		this.parent = parent;
		position = input;
		parent.add(this);
	}
	public void Draw()
	{
		this.setSize(parent.getWidth(), parent.getHeight());
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
			g.drawImage(img, 0, parent.getHeight() - parent.getHeight()/2, parent.getWidth()/2, parent.getHeight()/2, null);
		}
	}
}
