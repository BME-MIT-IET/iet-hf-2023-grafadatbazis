package random_csapatnev;

import java.util.Random;

/**
 * Raktár típusú mező. Anyagot, azaz nukleotidot és aminosavat tartalmazhat, 
 * feladata, hogyha egy virológus interaktál ezzel a mezővel, 
 * akkor annak átadja a mezőn tárolt anyagokat.
 */
public class Warehouse extends Field
{
	public Warehouse(int _x, int _y) 
	{
		super(_x, _y);
		material = new Material(new Random().nextInt(100) + 1, new Random().nextInt(100) + 1);
	}
	/**
	 * A mezőn lévő anyag.
	 */
	Material material;
	/**
	 * Felülírja a Field Interact függvényét, az átadott c karakternek átadja, a tárolt anyagokat.
	 */
	@Override
	public void interact(Character c)
	{
		material.PickUp(c);
	}
	/**
	 * Az a függvény, amikor a medve interaktál a jelenlegi mezővel, ezzel elpusztítva az itt lévő anyagokat.
	 */
	@Override
	public void bearInteract(Bear b)
	{
		super.bearInteract(b);
		material = material.Remove(material);
	}
}
