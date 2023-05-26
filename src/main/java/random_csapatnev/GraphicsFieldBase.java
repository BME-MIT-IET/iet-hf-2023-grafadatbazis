package random_csapatnev;
import java.awt.Color;

import javax.swing.JPanel;
import javax.swing.border.MatteBorder;

public class GraphicsFieldBase extends JPanel
{
	int graphicsx;
	int graphicsy;
	public GraphicsFieldBase(int _x, int _y)
	{
		super();
		this.setBorder(new MatteBorder(1,1,1,1, Color.BLACK));
		this.setLayout(null);
		
		graphicsx = _x;
		graphicsy = _y;
	}
}
