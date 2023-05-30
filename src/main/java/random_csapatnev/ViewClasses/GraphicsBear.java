package random_csapatnev.ViewClasses;

import random_csapatnev.ModelClasses.Bear;

import java.awt.Color;

/**
 * 
 * Beart kirajzoló osztály
 *
 */
public class GraphicsBear extends GraphicsCharacter {
	public GraphicsBear(Bear c) {
		super(c);
		color = new Color(180, 95, 6);
	}
}
