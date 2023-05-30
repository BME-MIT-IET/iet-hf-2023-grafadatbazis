package random_csapatnev;

import random_csapatnev.ViewClasses.GraphicsCharacter;
import random_csapatnev.ViewClasses.GraphicsGear;
import random_csapatnev.ViewClasses.GraphicsMaterial;

import java.io.Serializable;

import javax.swing.JFrame;

/**
 * 
 * MVC modellből a View-t kezelő rész.
 *
 */
public class View implements Serializable {
	JFrame frame;
	int pLen = 0;

	Model model;

	View(Model inputModel, JFrame inputFrame) {
		model = inputModel;
		frame = inputFrame;
	}

	public void repaintAll() {
		pLen = 1000 / model.sizeM;
		for (int i = 0; i < model.getGraphicsCharacter().size(); ++i) {
			GraphicsCharacter gc = model.getGraphicsCharacter().get(i);
			gc.draw(model.graphicsFields[gc.getC().getCurrField().getX()][gc.getC().getCurrField().getY()],
					model.sizeN * gc.getC().getCurrField().getY(),
					model.sizeM * gc.getC().getCurrField().getX(), pLen, pLen);
		}
		for (int i = 0; i < model.getGraphicsMaterial().size(); ++i) {
			GraphicsMaterial m = model.getGraphicsMaterial().get(i);
			m.draw();
		}
		for (int i = 0; i < model.getGraphicsGear().size(); ++i) {
			GraphicsGear m = model.getGraphicsGear().get(i);
			m.draw();
		}
		frame.invalidate();
		frame.repaint();
	}
}
