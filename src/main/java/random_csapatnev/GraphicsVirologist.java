package random_csapatnev;

import java.awt.Color;
import java.awt.Graphics;
import java.io.Serializable;

import javax.swing.JPanel;

/**
 * 
 * Virologistot kirajzoló osztály
 *
 */
public class GraphicsVirologist extends GraphicsCharacter implements Serializable 
{
	public GraphicsVirologist(Virologist c) 
	{
		super(c);
		color = new Color(60,120,216); 
	}
}
