package random_csapatnev;

import java.util.ArrayList;
import java.util.Random;

/**
 *
 * Agent osztályból származik, felülírja ennek a némelyik metódusát. Felelőssége, hogy reprezentálja és működésében ellássa, a medve vírussal megfertőződött virológusok szerepét.
 */
public class BearVirus extends Agent 
{
	public  BearVirus(){
		name = "BearVirus";
	}
	@Override
	public void Round() {}

	/**
	 * Felülírja az Agent Effect metódusát, ez a függvény hívódik meg amikor kifejti a hatását virológusra.
	 */
	@Override
	public void Effect(Character c)
	{
		boolean hasProtection = false;
		for(Agent a : c.activeAgents) {
			if(a.name == "ProtectiveVaccine" || (c.isCloaked && new Random().nextInt(1000) <= 823)) {
				hasProtection = true;
			}
		}
		if(!hasProtection) {
			c.currField.RemoveCharacter(c);
			
			ArrayList<GraphicsCharacter> tempList = new ArrayList<GraphicsCharacter>(MainFrame.Instance.model.graphicsCharacter.size());
			for(GraphicsCharacter e : MainFrame.Instance.model.graphicsCharacter) {
				tempList.add(e);
			}
			for(GraphicsCharacter e : tempList) {
				if(e.c == c) {
					e.Remove();
					MainFrame.Instance.model.graphicsCharacter.remove(e);
					MainFrame.Instance.refreshView();
				}
			}
			
			MainFrame.Instance.model.characters.remove(c);
			
			if(c.name.equals("v0")) {
				Main.endGame(false);
			}
			
			Bear b = new Bear(c, "b" + c.name.substring(1));
			MainFrame.Instance.model.characters.add(b);
			MainFrame.Instance.model.graphicsCharacter.add(new GraphicsBear(b));
		}
	}

	@Override
	public void Expire() 
	{
		currCharacter.activeAgents.remove(this);
	}

	@Override
	public Agent CreateNew() 
	{
		return new BearVirus();
	}

}
