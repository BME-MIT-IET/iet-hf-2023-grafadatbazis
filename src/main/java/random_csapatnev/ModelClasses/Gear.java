package random_csapatnev.ModelClasses;


import java.io.Serializable;

/**
 * Absztrakt osztály,
 * belőle származnak a Felszerelések.
 * Biztosítja a függvényeket a felszerelések fel és levételéhez.
 */
public abstract class Gear implements Serializable {
	GearEnum name;

	/**
	 * Absztrakt függvény,
	 * akkor hívódik meg, amikor az adott c karakterre kifejti hatását.
	 * 
	 * @param c Az a karakter akire kifejti a hatását.
	 */
	public abstract void effect(Character c);

	/**
	 * Megadja, hogy lehet-e használni a fejszét, heterogenitás miatt muszáj ide
	 * tenni..
	 */
	Boolean canUse = true;

	public void deteriorate(Character c) {
	}

	/**
	 * Absztrakt függvény, akkor hívódik meg,
	 * amikor az adott c karakter felveszi a tárgyat,
	 * változtatja a c karakter tulajdonságait.
	 * 
	 * @param c Az a karakter aki felveszi.
	 */
	public abstract void pickUp(Character c);

	/**
	 * Absztrakt függvény, akkor hívódik meg,
	 * amikor az adott c karakter leveszi a tárgyat,
	 * változtatja a c karakter tulajdonságait.
	 * 
	 * @param c az a karakter aki leveszi.
	 */
	public abstract void remove(Character c);

	public GearEnum getName() {
		return name;
	}

	public void setName(GearEnum name) {
		this.name = name;
	}

	public Boolean getCanUse() {
		return canUse;
	}
}
