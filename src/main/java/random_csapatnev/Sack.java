package random_csapatnev;

/**
 * 	Gear osztályból származik, felülírja ennek a metódusait, hogy a virológus anyag hordó kapacitását megnövelje.
 */
public class Sack extends Gear
{
	public Sack()
	{
		name = GearEnum.SACK;
	}
	
	/**
	 * A zsák bővítő kapacitását mutatja meg.
	 */
	Material plusCapacity = new Material(50, 50);
	/**
	 * Felülírja a Gear Effect függvényét, akkor hívódik meg, amikor az adott c karakterre kifejti a kapacitás növelő hatását.
	 */
	@Override
	public void Effect(Character c) 
	{
		c.maxMaterial.addMaterial(plusCapacity);
	}

	/**
	 * Felülírja a Gear PickUp függvényét, 
	 * akkor hívódik meg amikor az adott c karakter felveszi a tárgyat, 
	 * bekerül a c karakter felvett tárgyai közé.
	 */
	@Override
	public void PickUp(Character c) 
	{
		c.gears.add(this);
	}

	/**
	 * Felülírja a Gear Remove függvényét, 
	 * akkor hívódik meg, 
	 * amikor az adott c karakter leveszi a tárgyat, 
	 * kikerül a karakter felvett tárgyai közül.
	 */
	@Override
	public void Remove(Character c)
	{
		if(c.activeGears.remove(this)) {
			c.maxMaterial.remove(plusCapacity);
			c.gears.add(this);
		}
	}
}
