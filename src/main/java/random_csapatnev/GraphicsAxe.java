package random_csapatnev;

import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class GraphicsAxe extends GraphicsGear {

	public GraphicsAxe(Safehouse input, JPanel parent)
	{
		super(input, parent);
		try 
		{
		    img = ImageIO.read(new File(".\\src\\images\\axe.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
