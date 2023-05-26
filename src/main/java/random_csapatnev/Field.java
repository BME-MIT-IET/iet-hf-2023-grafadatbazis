package random_csapatnev;
import java.io.Serializable;
import java.util.ArrayList;

/**
 * A pályának egy alap építő eleme, 
 * a mezőkön keresztül juthatunk el a kívánt úticélhoz, 
 * kapcsolatban áll és tárolja a szomszédos mezőket, 
 * illetve a rajta álló virológusokat.
 */
public class Field implements Serializable
{
	int x;
	int y;
	public Field(int _x, int _y) {
		x = _x;
		y = _y;
	}
	/**
	 * A szomszédos mezők listája.
	 */
	ArrayList<Field> neighbours = new ArrayList<>();
	/**
	 * Az adott mezőn aktuálisan elhelyezkedő virológusok listája.
	 */
	ArrayList<Character> characters = new ArrayList<>();
	/**
	 * Megállapítja egy paraméterül kapott mezőről, hogy szomszédos-e.
	 * @param f A mező amiről ki kell deríteni hogy szomszédos-e?
	 * @return Ténylegesen szomszédos-e a mező.
	 */
	
	
	public Boolean IsNeighbour(Field f)
	{
		return neighbours.contains(f);
	}
	/**
	 * Eltávolítja a mezőről a paraméterül kapott virológust.
	 * @param v Eltávolítandó virológus.
	 */
	public void RemoveCharacter(Character c)
	{
		characters.remove(c);
	}
	/**
	 * Visszaadja, hogy egy adot karakter rajta áll-e a mezőn.
	 * @param c Az ellenőrizendő karakter
	 * @return Ezen a mezőn áll-e a v Virológus.
	 */
	public Boolean ContainsCharacter(Character c)
	{
		return characters.contains(c);
	}
	/**
	 * Az éppen itt álló c karakter interaktál a mezővel.
	 * @param c Az interaktáló karakter.
	 */
	public void Interact(Character c) {}
	public void BearInteract(Bear b){
		for(Character c : characters) {
			if(!c.name.startsWith("b")) {
				b.characterInteract(c);
			}		
		}
	}
	/**
	 * Átmozgatja a megadott mezőről a paraméterül kapott karaktert a jelenlegi mezőre.
	 * @param f A mező amiről el akar mozogni a Virológus.
	 * @param c A karakter aki mozogni kíván.
	 */
	public void MoveFrom(Field f, Character c)
	{
		this.characters.add(c);
		f.RemoveCharacter(c);
	}
	
	/**
	 * Visszaadja, a letárolt szomszédos mezők listáját.
	 * @return Szomszédos mezők listája.
	 */
	public ArrayList<Field> GetNeighbours()
	{
		return neighbours;
	}
	/**
	 * Visszaadja a letárolt a mezőn rajta álló karakterek listáját.
	 * @return A jelenlegi mezőn álló karakterek.
	 */
	public ArrayList<Character> GetCharacters()
	{
		return characters;
	}
}
