package random_csapatnev;

import java.awt.Color;
import java.awt.Graphics;
import java.io.Serializable;

import javax.swing.JPanel;
import javax.swing.border.MatteBorder;

public class GraphicsWarehouse extends GraphicsFieldBase 
{
	public GraphicsWarehouse(int _x, int _y)
	{
		super(_x, _y);
		this.setBackground(new Color(144, 238, 144));
	}
}
