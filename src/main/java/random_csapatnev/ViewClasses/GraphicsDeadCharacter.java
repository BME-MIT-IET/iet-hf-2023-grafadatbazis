package random_csapatnev.ViewClasses;

import random_csapatnev.ModelClasses.Character;
import java.awt.Color;

public class GraphicsDeadCharacter extends GraphicsCharacter {
	public GraphicsDeadCharacter(Character c) {
		super(c);
		color = new Color(67, 67, 67);
	}
}
