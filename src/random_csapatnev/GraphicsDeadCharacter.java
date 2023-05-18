package random_csapatnev;

import java.awt.Color;
import java.awt.Graphics;
import java.io.Serializable;

import javax.swing.JPanel;

public class GraphicsDeadCharacter extends GraphicsCharacter implements Serializable {
	GraphicsDeadCharacter(Character c) 
	{
		super(c);
		color = new Color(67,67,67);
	}
}
