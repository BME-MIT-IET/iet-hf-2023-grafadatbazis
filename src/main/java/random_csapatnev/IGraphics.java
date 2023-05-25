package random_csapatnev;

import javax.swing.JPanel;

/**
 * 
 * Interface amit implementál az összes rajzolásra használt objektum.
 *
 */

public interface IGraphics 
{
	public void Draw(JPanel p,  int x, int y, int width, int height);
}
