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
	View(Model _model, JFrame _frame)
	{
		model = _model;
		frame = _frame;
	}
	
	public void repaintAll() 
	{
		int pLen = 1000 / model.M;
		for(int i = 0; i < model.graphicsCharacter.size(); ++i)
		{
			GraphicsCharacter gc = model.graphicsCharacter.get(i);
			gc.Draw(model.graphicsFields[gc.c.currField.x][gc.c.currField.y], model.N * gc.c.currField.y, model.M * gc.c.currField.x, pLen, pLen);
		}
		for(int i = 0; i < model.graphicsMaterial.size(); ++i)
		{
			GraphicsMaterial m = model.graphicsMaterial.get(i);
			m.Draw();
		}
		for(int i = 0; i < model.graphicsGear.size(); ++i)
		{
			GraphicsGear m = model.graphicsGear.get(i);
			m.Draw();
		}
		frame.invalidate();
		frame.repaint();
	}
}
