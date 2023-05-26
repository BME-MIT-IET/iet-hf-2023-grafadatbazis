package random_csapatnev;

import java.awt.Graphics;

import javax.swing.JPanel;

public class GraphicsMaterial extends JPanel {
	JPanel graphicsparent = null;
	Material mat;
	public GraphicsMaterial(Material m, JPanel parent)
	{
		super();
		mat = m;
		graphicsparent = parent;
		parent.add(this);
	}
	public void Draw()
	{
		this.setSize(graphicsparent.getWidth(), graphicsparent.getHeight());
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
		g.drawChars(amino.toCharArray(), 0, amino.length(), 0 + 2, graphicsparent.getHeight() - 2);
		String nucleo =  mat.container.get(MatEnum.NUCLEOTIDE).toString();
		g.drawChars(nucleo.toCharArray(), 0, nucleo.length(), graphicsparent.getWidth()/2 + 2, graphicsparent.getHeight() - 2);
	}
}
