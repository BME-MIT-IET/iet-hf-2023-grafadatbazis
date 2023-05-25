package random_csapatnev;
import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JPanel;
import javax.swing.border.MatteBorder;

public class GraphicsFieldBase extends JPanel
{
	int x;
	int y;
	public GraphicsFieldBase(int _x, int _y)
	{
		super();
		this.setBorder(new MatteBorder(1,1,1,1, Color.BLACK));
		this.setLayout(null);
		
		x = _x;
		y = _y;
	}
	@Override
	public void paint(Graphics g) 
	{
		super.paint(g);
	}
}
