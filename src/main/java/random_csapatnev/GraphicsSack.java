package random_csapatnev;

import java.awt.Graphics;
import java.io.File;
import java.io.IOException;
import java.io.Serializable;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class GraphicsSack extends GraphicsGear implements Serializable {

	public GraphicsSack(Safehouse input, JPanel parent) 
	{
		super(input, parent);
		try 
		{
		    img = ImageIO.read(new File(".\\src\\images\\sack.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
