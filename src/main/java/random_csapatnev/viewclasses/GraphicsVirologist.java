package random_csapatnev.viewclasses;

import random_csapatnev.modelclasses.Virologist;

import java.awt.Color;

/**
 * 
 * Virologistot kirajzoló osztály
 *
 */
public class GraphicsVirologist extends GraphicsCharacter {
	public GraphicsVirologist(Virologist c) {
		super(c);
		color = new Color(60, 120, 216);
	}
}
