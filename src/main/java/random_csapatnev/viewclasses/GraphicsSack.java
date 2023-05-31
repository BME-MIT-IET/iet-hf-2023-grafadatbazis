package random_csapatnev.viewclasses;

import random_csapatnev.Logger;

import random_csapatnev.modelclasses.Safehouse;
import random_csapatnev.StringLiterals;

import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class GraphicsSack extends GraphicsGear {

	public GraphicsSack(Safehouse input, JPanel parent) {
		super(input, parent);
		try {
			img = ImageIO.read(new File(StringLiterals.SACK_PATH));
		} catch (IOException e) {
			Logger.out(java.util.logging.Level.SEVERE, e.getMessage());
		}
	}
}
