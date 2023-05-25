package random_csapatnev;

import java.awt.Color;
import java.io.Serializable;

public class GraphicsDeadCharacter extends GraphicsCharacter implements Serializable {
	GraphicsDeadCharacter(Character c) 
	{
		super(c);
		color = new Color(67,67,67);
	}
}
