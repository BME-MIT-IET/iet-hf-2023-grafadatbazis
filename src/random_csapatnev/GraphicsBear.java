package random_csapatnev;

import java.awt.Color;
import java.awt.Graphics;
import java.io.Serializable;

import javax.swing.JPanel;

/**
 * 
 * Beart kirajzoló osztály
 *
 */
public class GraphicsBear extends GraphicsCharacter implements Serializable {
	public GraphicsBear(Bear c) 
	{
		super(c);
		color = new Color(180,95,6); 
	}
}
