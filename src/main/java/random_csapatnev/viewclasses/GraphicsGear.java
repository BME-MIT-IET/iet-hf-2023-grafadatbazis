package random_csapatnev.viewclasses;

import random_csapatnev.modelclasses.Safehouse;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;

public abstract class GraphicsGear extends JPanel {
	JPanel graphicsParent;
	Safehouse position;
	transient BufferedImage img;

	protected GraphicsGear(Safehouse input, JPanel parent) {
		super();
		graphicsParent = parent;
		position = input;
		parent.add(this);
	}

	public void draw() {
		this.setSize(graphicsParent.getWidth(), graphicsParent.getHeight());
		this.setVisible(true);
		this.setOpaque(false);
		this.revalidate();
		this.repaint();
	}

	@Override
	public void paint(Graphics g) {
		super.paint(g);
		if (position.getGear() != null) {
			g.drawImage(img, 0, graphicsParent.getHeight() - graphicsParent.getHeight() / 2,
					graphicsParent.getWidth() / 2, graphicsParent.getHeight() / 2, null);
		}
	}
}
