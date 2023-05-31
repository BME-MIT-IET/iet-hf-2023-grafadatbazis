package random_csapatnev.modelclasses;


/**
 * Gear osztályból származik, felülírja ennek a metódusait,
 * hogy a virológusnak a tulajdonságait változtassa,
 * ezzel lehetővé teszi,
 * hogy a virológus 82.3%-os eséllyel távol tartsa az ágensek hatását.
 */
public class Cloak extends Gear {
	public Cloak() {
		name = GearEnum.CLOAK;
	}

	/**
	 * Felülírja a Gear Effect függvényét, akkor hívódik meg,
	 * amikor az adott c karakterre kifejti a köpeny hatást,
	 * ami azt teszi lehetővé,
	 * hogy vírusokat/vakcinák hatását távol tartsa.
	 */
	@Override
	public void effect(Character c) {
		c.isCloaked = true;
	}

	/**
	 * Felülírja a Gear PickUp függvényét,
	 * akkor hívódik meg amikor az adott c karakter felveszi a tárgyat,
	 * bekerül a c karakter felvett tárgyai közé,
	 * ilyenkor változik a c karakter isCloaked tagváltozója.
	 */
	@Override
	public void pickUp(Character c) {
		c.gears.add(this);
	}

	/**
	 * Felülírja a Gear Remove függvényét, akkor hívódik meg,
	 * amikor az adott c karakter leveszi a tárgyat,
	 * kikerül a virológus felvett tárgyai közül,
	 * ilyenkor változik a c karakter isCloaked tagváltozója.
	 */
	@Override
	public void remove(Character c) {
		if (c.activeGears.remove(this)) {
			c.gears.add(this);
			c.isCloaked = false;
		}
	}
}
