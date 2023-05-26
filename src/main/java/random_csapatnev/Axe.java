package random_csapatnev;

/**
 * Gear osztályból származik, felülírja ennek a metódusait, hogy a karakterek a tulajdonságait változtassa, ezzel lehetővé teszi, hogy a karakterek baltát tudjanak használni ellenségek megölésére.
 */
public class Axe extends Gear 
{
	public Axe() 
	{
		name = GearEnum.AXE;
	}
	/**
	 * Felülírja a Gear Effect függvényét, akkor hívódik meg, amikor az adott c karakterre ami lehetővé teszi, hogy más karaktert meg lehessen ölni.
	 */
	@Override
	public void Effect(Character c) 
	{
		c.die();
		canUse = false;
	}

	/**
	 * Felülírja a Gear PickUp függvényét, akkor hívódik meg amikor az adott c karakter felveszi a tárgyat, bekerül a c karakter aktív tárgyai közé.
	 */
	@Override
	public void PickUp(Character c) 
	{
		if(c.activeGears.size() < 3) 
		{
			c.activeGears.add(this);
		}
	}

	/**
	 * Felülírja a Gear Remove függvényét, akkor hívódik meg, amikor az adott c karakter el akarja dobni a baltát.
	 */
	@Override
	public void Remove(Character c)
	{
		c.activeGears.remove(this);
	}
}
