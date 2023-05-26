package random_csapatnev;

/**
 * Gear osztályból származik, 
 * felülírja ennek a metódusait, hogy a virológusnak a tulajdonságait változtassa, 
 * lehetővé teszi, 
 * hogy visszaadja azokat az ágenseket amit rajta próbálnak használni.
 */
public class Gloves extends Gear
{
	public Gloves() 
	{
		name = GearEnum.GLOVES;
	}
	
	int durability = 3;
	/**
	 * Felülírja a Gear Effect függvényét, akkor hívódik meg, 
	 * amikor az adott c karakterre kifejti a kesztyű hatást, 
	 * ami azt teszi lehetővé, hogy vírusokat/vakcinákat visszaadjuk az adónak.
	 */
	@Override
	public void effect(Character c) 
	{
		c.isGloved = true;
	}
	
	@Override
	public void deteriorate(Character c) {
		durability -= 1;
		if(durability == 0) {
			c.activeGears.remove(this);
			c.isGloved = false;
		}
	}

	/**
	 * Felülírja a Gear PickUp függvényét, 
	 * akkor hívódik meg amikor az adott c karakter felveszi a tárgyat, 
	 * bekerül a c karakter felvett tárgyai közé, 
	 * ilyenkor változik a c karakter isGloved tagváltozója.
	 */
	@Override
	public void pickUp(Character c) 
	{
		c.gears.add(this);
	}

	/**
	 * Felülírja a Gear Remove függvényét, akkor hívódik meg, 
	 * amikor az adott c karakter leveszi a tárgyat, 
	 * kikerül a karakter felvett tárgyai közül, 
	 * ilyenkor változik a c karakter isGloved tagváltozója.
	 */
	@Override
	public void remove(Character c)
	{
		if(c.activeGears.remove(this)) {
			c.gears.add(this);
			c.isGloved = false;
		}
	}
}
