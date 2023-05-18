package random_csapatnev;

import java.awt.Color;
import java.awt.Graphics;
import java.io.Serializable;

import javax.swing.JPanel;
import javax.swing.border.MatteBorder;

@SuppressWarnings("serial")
public class GraphicsSafehouse extends GraphicsFieldBase 
{
	public GraphicsSafehouse(int _x, int _y)
	{
		super(_x, _y);
		this.setBackground(new Color(255,153,0));
	}
}
