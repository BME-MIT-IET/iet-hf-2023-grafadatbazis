package random_csapatnev;

import java.util.ArrayList;

/**
 * Character osztályból származik, felülírja ennek a némelyik metódusát. Felelőssége, hogy reprezentálja és működésében ellássa, a medve vírussal megfertőződött virológusok szerepét.
 */
public class Bear extends Character
{
	public Bear(String _name) { 
		super(_name);
	};
	public Bear(Character c, String _name){
		super(c, _name);
		c.isParalyzed = true;
		isParalyzed = false;
		name = _name;
	}
	/**
	 * A jelenlegi medve átmozog a paraméterként megkapott mezőre.
	 */
	@Override
	public void Move(Field f)
	{
		if(currField.IsNeighbour(f)) {
			f.MoveFrom(currField, this);
			currField = f;
		}
	}
	/**
	 *A jelenlegi medve interaktál azzal a mezővel amin jelenleg áll.
	 */
	@Override
	public void FieldInteract()
	{
		currField.BearInteract(this);
	}
	/**
	 * A jelenlegi medve interaktál a paraméterül kapott karakterrel.
	 */
	@Override
	public void CharacterInteract(Character c)
	{
		Use(c, new BearVirus());
	}
	/**
	 *Felülírja a Character Use függvényét, a megadott karakteren használja a megadott ágenst. (Ez a medve esetében a medve vírus.)
	 */
	@Override
	public void Use(Character c, Agent a)
	{
		if(currField.ContainsCharacter(c)) {
			c.AgentUsedOnHim(a, c);
		}
	}
	/**
	 *  Ez hívódik meg amikor a GameManager a köröket lépteti.
	 */
	@Override
	public void Round()
	{
		/*Field f = currField.GetNeighbours().get(new Random().nextInt(currField.GetNeighbours().size()));
		Move(f);
		FieldInteract();*/
	}
	/**
	 * Ez a függvény hívódik meg amikor a jelenlegi medve meghal.
	 */
	@Override
	public void Die()
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
