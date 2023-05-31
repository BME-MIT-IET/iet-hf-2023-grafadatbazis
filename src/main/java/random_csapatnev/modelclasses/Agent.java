package random_csapatnev.modelclasses;


import java.io.Serializable;

/**
 * Felelőssége az adott ágens működéséhez szükséges adatok tárolása, pl.:
 * lejárati idő, név, aktív idő,
 * majd ezeknek a paramétereknek a segítségével a hatásának kifejtése.
 */
public abstract class Agent implements Serializable {
	/**
	 * Az adott ágens neve.
	 */
	String name;
	/**
	 * Azt tárolja, hogy az adott ágens mennyi ideig fejti még ki a hatását.
	 */
	Integer activeTime = 0;
	/**
	 * Azt mutatja, hogy az adott ágensnek mennyi ideig tart a hatása.
	 */
	Integer effectTime = 3;
	/**
	 * Azt mutatja, hogy az adott ágenst mennyi és milyen anyagokba kerül
	 * előállítani
	 */
	Material cost = new Material(5, 5);
	/**
	 * Azt mutatja, hogy jelenleg melyik karakterre fejti ki a hatását.
	 */
	Character currCharacter;

	Character ownerCharacter;
	/**
	 * Azt mutatja, hogy mikor jár le az adott ágens (nem lehet utána felhasználni).
	 */
	Integer expireDate = 3;

	/**
	 * Absztrakt metódus, ez hívódik meg amikor egy karakter aktív ágensei között el
	 * van tárolva, a karakter Round függvénye hívja meg.
	 */
	public abstract void round();

	/**
	 * Absztrakt metódus, ez hívódik meg amikor az adott ágens kifejti a hatását a
	 * megadott karakterre.
	 * 
	 * @param c Karakter akire hatással van.
	 */
	public abstract void affect(Character c);

	/**
	 * Ez hívódik meg amikor az adott ágenst megtanulja egy karakter.
	 * 
	 * @param c Karakter aki megtanulja.
	 */
	public void learn(Character c) {
		c.knownAgents.add(this);
	}

	/**
	 * Absztrakt metódus, ez hívódik meg amikor lejár az ágens hatása.
	 */
	public abstract Agent createNew();

	public abstract void expire();
	public String getName(){
		return this.name;
	}
}
