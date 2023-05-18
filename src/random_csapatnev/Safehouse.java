package random_csapatnev;

import java.util.Random;

/**
 * Óvóhely típusú mező. Védőfelszerelést tartalmazhat, feladata hogy a Virológusoknak átadja a védőfelszerelést amit tárol.
 */
public class Safehouse extends Field
{

	Gear gear;
	/**
	 * Védőfelszerelés, ami az adott óvóhelyen elhelyezkedik.
	 */
	
	/**
	 * Konstruktor.
	 * @param g Az adott Safehouse-ban található Gear.
	 */
	public Safehouse(int _x, int _y){
		super(_x, _y);
		Random r = new Random();
		switch(r.nextInt(4)) {
		case 0:
			gear = new Axe();
			break;
		case 1:
			gear = new Gloves();
			break;
		case 2:
			gear = new Cloak();
			break;
		case 3:
			gear = new Sack();
			break;
		}
	}
	
	/**
	 * Felülírja a Field Interact függvényét, 
	 * ezzel biztosítva, hogy a c Karakter amikor interaktál 
	 * ezzel a mezővel, akkor átadja neki a védőfelszereléseket.
	 */
	@Override
	public void Interact(Character c)
	{
		if(gear != null) {
			gear.PickUp(c);
			gear = null;
		}
	}
}
