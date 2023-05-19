package random_csapatnev;

import java.awt.Color;
import java.awt.Graphics;
import java.io.Serializable;

import javax.swing.JPanel;
import javax.swing.border.MatteBorder;

public class GraphicsField extends GraphicsFieldBase {

	public GraphicsField(int _x, int _y)
	{
		super(_x, _y);
		this.setBackground(new Color(217,217,217));
	}
}
