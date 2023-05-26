package random_csapatnev;

import java.io.Serializable;

import javax.swing.JFrame;

/**
 * 
 * MVC modellből a View-t kezelő rész.
 *
 */
public class View implements Serializable  
{
	JFrame frame;
	int pLen = 0;
	
	Model model;
	View(Model inputModel, JFrame inputFrame)
	{
		model = inputModel;
		frame = inputFrame;
	}
	
	public void repaintAll() 
	{
		pLen = 1000 / model.sizeM;
		for(int i = 0; i < model.graphicsCharacter.size(); ++i)
		{
			GraphicsCharacter gc = model.graphicsCharacter.get(i);
			gc.draw(model.graphicsFields[gc.c.currField.x][gc.c.currField.y], model.sizeN * gc.c.currField.y, model.sizeM * gc.c.currField.x, pLen, pLen);
		}
		for(int i = 0; i < model.graphicsMaterial.size(); ++i)
		{
			GraphicsMaterial m = model.graphicsMaterial.get(i);
			m.draw();
		}
		for(int i = 0; i < model.graphicsGear.size(); ++i)
		{
			GraphicsGear m = model.graphicsGear.get(i);
			m.draw();
		}
		frame.invalidate();
		frame.repaint();
	}
}
