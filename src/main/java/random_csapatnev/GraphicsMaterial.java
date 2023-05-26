package random_csapatnev;

import java.awt.Graphics;

import javax.swing.JPanel;

public class GraphicsMaterial extends JPanel {
	JPanel graphicsParent = null;
	Material mat;
	public GraphicsMaterial(Material m, JPanel parent)
	{
		super();
		mat = m;
		graphicsParent = parent;
		parent.add(this);
	}
	public void draw()
	{
		this.setSize(graphicsParent.getWidth(), graphicsParent.getHeight());
		this.setVisible(true);
		this.setOpaque(false);
		this.revalidate();
		this.repaint();
	}
	@Override
	public void paint(Graphics g)
	{
		super.paint(g);
		String amino =  mat.container.get(MatEnum.AMINOACID).toString();
		g.drawChars(amino.toCharArray(), 0, amino.length(), 0 + 2, graphicsParent.getHeight() - 2);
		String nucleo =  mat.container.get(MatEnum.NUCLEOTIDE).toString();
		g.drawChars(nucleo.toCharArray(), 0, nucleo.length(), graphicsParent.getWidth()/2 + 2, graphicsParent.getHeight() - 2);
	}
}
