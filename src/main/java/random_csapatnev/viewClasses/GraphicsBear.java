package random_csapatnev.viewClasses;

import random_csapatnev.modelclasses.Bear;

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
