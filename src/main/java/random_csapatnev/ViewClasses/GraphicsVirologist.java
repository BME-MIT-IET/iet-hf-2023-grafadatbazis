package random_csapatnev.ViewClasses;

import random_csapatnev.ModelClasses.Virologist;;

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
