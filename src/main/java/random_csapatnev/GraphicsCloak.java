package random_csapatnev;

import java.awt.Graphics;
import java.io.File;
import java.io.IOException;
import java.io.Serializable;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class GraphicsCloak extends GraphicsGear implements Serializable {

	public GraphicsCloak(Safehouse input, JPanel parent) 
	{
		super(input, parent);
		try 
		{
		    img = ImageIO.read(new File(".\\src\\images\\cloak.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
