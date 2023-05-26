package random_csapatnev;
import java.awt.Color;

import javax.swing.JPanel;
import javax.swing.border.MatteBorder;

public class GraphicsFieldBase extends JPanel
{
	int graphicsx;
	int graphicsy;
	public GraphicsFieldBase(int inputX, int inputY)
	{
		super();
		this.setBorder(new MatteBorder(1,1,1,1, Color.BLACK));
		this.setLayout(null);
		
		graphicsx = inputX;
		graphicsy = inputY;
	}
}
