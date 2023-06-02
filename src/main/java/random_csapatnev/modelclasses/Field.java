package random_csapatnev.modelclasses;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * A pályának egy alap építő eleme,
 * a mezőkön keresztül juthatunk el a kívánt úticélhoz,
 * kapcsolatban áll és tárolja a szomszédos mezőket,
 * illetve a rajta álló virológusokat.
 */
public class Field implements Serializable {
	int x;
	int y;

	public Field(int inputX, int inputY) {
		x = inputX;
		y = inputY;
	}

	/**
	 * A szomszédos mezők listája.
	 */
	private List<Field> neighbours = new ArrayList<>();
	/**
	 * Az adott mezőn aktuálisan elhelyezkedő virológusok listája.
	 */
	private List<Character> characters = new ArrayList<>();

	/**
	 * Megállapítja egy paraméterül kapott mezőről, hogy szomszédos-e.
	 * 
	 * @param f A mező amiről ki kell deríteni hogy szomszédos-e?
	 * @return Ténylegesen szomszédos-e a mező.
	 */

	public Boolean isNeighbour(Field f) {
		return neighbours.contains(f);
	}

	/**
	 * Eltávolítja a mezőről a paraméterül kapott virológust.
	 * 
	 * @param c Eltávolítandó virológus.
	 */
	public void removeCharacter(Character c) {
		characters.remove(c);
	}

	/**
	 * Visszaadja, hogy egy adot karakter rajta áll-e a mezőn.
	 * 
	 * @param c Az ellenőrizendő karakter
	 * @return Ezen a mezőn áll-e a v Virológus.
	 */
	public Boolean containsCharacter(Character c) {
		return characters.contains(c);
	}

	/**
	 * Az éppen itt álló c karakter interaktál a mezővel.
	 *
	 * @param c Az interaktáló karakter.
	 */
	public void interact(Character c) {
		/*
		 * Üres implementáció, mivel az üres mező nem csinál semmit amikor interaktál
		 * vele egy karakter.
		 */
	}

	public void bearInteract(Bear b) {
		ArrayList<Character> tempList = new ArrayList<>(characters);
		for (Character c : tempList) {
			if (!c.name.startsWith("b")) {
				b.characterInteract(c);
			}
		}
	}

	/**
	 * Átmozgatja a megadott mezőről a paraméterül kapott karaktert a jelenlegi
	 * mezőre.
	 * 
	 * @param f A mező amiről el akar mozogni a Virológus.
	 * @param c A karakter aki mozogni kíván.
	 */
	public void moveFrom(Field f, Character c) {
		if(!f.containsCharacter(c)) {
			return;
		}
		this.characters.add(c);
		f.removeCharacter(c);
	}

	/**
	 * Visszaadja, a letárolt szomszédos mezők listáját.
	 * 
	 * @return Szomszédos mezők listája.
	 */
	public List<Field> getNeighbours() {
		return neighbours;
	}

	/**
	 * Visszaadja a letárolt a mezőn rajta álló karakterek listáját.
	 * 
	 * @return A jelenlegi mezőn álló karakterek.
	 */
	public List<Character> getCharacters() {
		return characters;
	}
	public int getX(){
		return this.x;
	}
	public int getY(){
		return this.y;
	}
}
