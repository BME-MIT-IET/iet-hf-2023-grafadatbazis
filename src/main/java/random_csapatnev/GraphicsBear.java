package random_csapatnev;

import java.awt.Color;
import java.io.Serializable;

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
