package random_csapatnev;

import java.io.Serializable;

/**
 * Absztrakt osztály, 
 * belőle származnak a Felszerelések. 
 * Biztosítja a függvényeket a felszerelések fel és levételéhez.
 */
public abstract class Gear implements Serializable
{
	GearEnum name;
	/**
	 * Absztrakt függvény, 
	 * akkor hívódik meg, amikor az adott c karakterre kifejti hatását.
	 * @param c Az a karakter akire kifejti a hatását.
	 */
	public abstract void Effect(Character c);
	
	/**
	 * Megadja, hogy lehet-e használni a fejszét, heterogenitás miatt muszáj ide tenni..
	 */
	Boolean canUse = true;
	
	public void Deteriorate(Character c) {}
	/**
	 * Absztrakt függvény, akkor hívódik meg, 
	 * amikor az adott c karakter felveszi a tárgyat, 
	 * változtatja a c karakter tulajdonságait.
	 * @param c Az a karakter aki felveszi.
	 */
	public abstract void PickUp(Character c);
	/**
	 * Absztrakt függvény, akkor hívódik meg, 
	 * amikor az adott c karakter leveszi a tárgyat, 
	 * változtatja a c karakter tulajdonságait.
	 * @param c az a karakter aki leveszi.
	 */
	public abstract void Remove(Character c);
	

	public GearEnum getName() {
		return name;
	}
	public void setName(GearEnum name) {
		this.name = name;
	}
}
