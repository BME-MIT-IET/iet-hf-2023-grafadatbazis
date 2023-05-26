package random_csapatnev;

import java.util.ArrayList;

/**
 * Character osztályból származik, felülírja ennek a némelyik metódusát. Felelőssége, hogy reprezentálja és működésében ellássa, a medve vírussal megfertőződött virológusok szerepét.
 */
public class Bear extends Character
{
	public Bear(String inputName) { 
		super(inputName);
	}
	public Bear(Character c, String inputName){
		super(c, inputName);
		c.isParalyzed = true;
		isParalyzed = false;
		name = inputName;
	}
	/**
	 * A jelenlegi medve átmozog a paraméterként megkapott mezőre.
	 */
	@Override
	public void move(Field f)
	{
		if(Boolean.TRUE.equals(currField.isNeighbour(f))) {
			f.moveFrom(currField, this);
			currField = f;
		}
	}
	/**
	 *A jelenlegi medve interaktál azzal a mezővel amin jelenleg áll.
	 */
	@Override
	public void fieldInteract()
	{
		currField.bearInteract(this);
	}
	/**
	 * A jelenlegi medve interaktál a paraméterül kapott karakterrel.
	 */
	@Override
	public void characterInteract(Character c)
	{
		use(c, new BearVirus());
	}
	/**
	 *Felülírja a Character Use függvényét, a megadott karakteren használja a megadott ágenst. (Ez a medve esetében a medve vírus.)
	 */
	@Override
	public void use(Character c, Agent a)
	{
		if(Boolean.TRUE.equals(currField.containsCharacter(c))) {
			c.agentUsedOnHim(a, c);
		}
	}
	/**
	 *  Ez hívódik meg amikor a GameManager a köröket lépteti.
	 */
	@Override
	public void round()
	{
	}
	/**
	 * Ez a függvény hívódik meg amikor a jelenlegi medve meghal.
	 */
	@Override
	public void die()
	{
		Character c = new Character(this, "c" + this.name.substring(1));
		MainFrame.Instance.model.characters.add(c);
		MainFrame.Instance.model.graphicsCharacter.add(new GraphicsDeadCharacter(c));
		
		this.currField.characters.add(c);
		this.currField.characters.remove(this);
		
		ArrayList<GraphicsCharacter> tempList = new ArrayList<GraphicsCharacter>(MainFrame.Instance.model.graphicsCharacter.size());
		for(GraphicsCharacter e : MainFrame.Instance.model.graphicsCharacter) {
			tempList.add(e);
		}
		for(GraphicsCharacter e : tempList) {
			if(e.c == this) {
				e.Remove();
				MainFrame.Instance.model.graphicsCharacter.remove(e);
				MainFrame.Instance.refreshView();
			}
		}
		MainFrame.Instance.model.characters.remove(this);
	}
}
