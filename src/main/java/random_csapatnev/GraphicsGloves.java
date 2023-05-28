package random_csapatnev;

import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class GraphicsGloves extends GraphicsGear {

	public GraphicsGloves(Safehouse input, JPanel parent) {
		super(input, parent);
		try {
			img = ImageIO.read(new File(StringLiterals.GLOVES_PATH));
		} catch (IOException e) {
			Logger.out(java.util.logging.Level.SEVERE, e.getMessage());
		}
	}
}
