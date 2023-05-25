package random_csapatnev;

import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class GraphicsGloves extends GraphicsGear {

	public GraphicsGloves(Safehouse input, JPanel parent) 
	{
		super(input, parent);
		try 
		{
		    img = ImageIO.read(new File(".\\src\\images\\gloves2.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
