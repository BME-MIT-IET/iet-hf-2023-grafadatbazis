package random_csapatnev.viewclasses;

import random_csapatnev.modelclasses.Character;
import java.awt.Color;

public class GraphicsDeadCharacter extends GraphicsCharacter {
	public GraphicsDeadCharacter(Character c) {
		super(c);
		color = new Color(67, 67, 67);
	}
}
