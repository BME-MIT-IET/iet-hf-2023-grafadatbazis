package random_csapatnev;

import java.util.ArrayList;
import java.util.Objects;
import java.util.Random;

/**
 * Ez az osztály tartalmazza a játékhoz szükséges adatokat és 
 * többi mezővel (Field), 
 * felszereléssel(Gear), 
 * ágenssel(Agent), 
 * virológussal (Virologist) interaktáló metódusokat. 
 * Továbbá tartalmazza a megtanult ágenseket (Agent), a jelenleg aktív ágenseket, 
 * megszerzett felszereléseket (Gear), a jelenleg felvett felszereléseket, 
 * a jelenlegi mezőt amin éppen áll (Field), a már megszerzett anyagokat (Material), 
 * a maximum kapacitást ami azt mutatja, 
 * hogy egyszerre mennyi anyagot képes magánál tartani.
 */
public class Virologist extends Character
{
	public Virologist(String inputName) {
		super(inputName);
	}

	/**
	 *A jelenlegi virológus átmozog a paraméterként megkapott mezőre.
	 */
	@Override
	public void move(Field f)
	{
		Field fTemp = f;
		if(Boolean.TRUE.equals(isParalyzed)) { return; }
		if(Boolean.TRUE.equals(isVitus)) { fTemp = currField.getNeighbours().get(rand.nextInt(currField.getNeighbours().size())); }
		
		if(Boolean.TRUE.equals(currField.isNeighbour(f)))
		{
			fTemp.moveFrom(currField, this);
			currField = fTemp;
		}
	}
	
	@Override
	public void fieldInteract() 
	{
		if(currField != null) {
			currField.interact(this);
		}
	}
	
	/**
	 * A virológus leveszi a megadott felszerelést.
	 * @param g Annak a felszerelésnek az enum-ja amit le szeretne venni.
	 */
	public void unequipGear(GearEnum g)
	{
		for(int i = activeGears.size() - 1; i >= 0; --i)
		{
			if(activeGears.get(i).getName() == g)
			{
				activeGears.get(i).Remove(this);
				break;
			}
		}
	}

	/**
	 * Elkészíti a megadott ágenst.
	 * @param a Az elkészítendő ágens.
	 */
	public void craftAgent(Agent a)
	{
		Agent curAgent = null;
		for(Agent curKnown: knownAgents)
		{
			if(Objects.equals(curKnown.name, a.name))
			{
				curAgent = curKnown;
				break;
			}
		}
		if(curAgent != null)
		{
			Boolean canCraft = true;
			for(MatEnum curEnum: MatEnum.values())
			{
				if(a.cost.getContainer().get(curEnum) > currMaterial.getContainer().get(curEnum))
				{
					canCraft = false;
					break;
				}
			}
			if(Boolean.TRUE.equals(canCraft))
			{
				for(MatEnum curEnum: MatEnum.values())
				{
					currMaterial.getContainer().put(curEnum, currMaterial.getContainer().get(curEnum) - a.cost.getContainer().get(curEnum));  
				}
				craftedAgents.add(curAgent.CreateNew());
			}
		}
	}
	/**
	 * A jelenlegi virológuson használják a megadott ágenst.
	 * @param a Az adott ágens amit a jelenlegi virológuson használnak.
	 * @param c Az a virológus aki a jelenlegi virológuson használja az ágenst..
	 */
	@Override
	public void agentUsedOnHim(Agent a, Character c)
	{
		if(Boolean.TRUE.equals(isGloved))
		{
			c.agentUsedOnHim(a, c);
			for(Gear curGear: activeGears)
			{
				if(curGear.name == GearEnum.GLOVES)
				{
					curGear.Deteriorate(this);
				}
			}
			return;
		}
		else if(Boolean.TRUE.equals(isCloaked))
		{
			if(rand.nextInt(1000) < 823)
			{
				return;
			}
		}
		for(Agent curAgent: activeAgents)
		{
			if(Objects.equals(curAgent.name, a.name))
			{
				return;
			}
		}
		a.Effect(c);
	}

	/**
	 * A virológus felszereli a megadott Enum-ú felszerelést.
	 * @param g
	 */
	public void equipGear(GearEnum g)
	{
		for(int i = gears.size() - 1; i >= 0; --i)
		{
			if(gears.get(i).getName() == g)
			{
				if(activeGears.size() < 3)
				{
					Gear curGear = gears.remove(i);
					curGear.Effect(this);
					activeGears.add(curGear);
					break;
				}
			}
		}
	}
	/**
	 * Az a függvény, amikor a jelenlegi virológus interaktol egy karakterrel, és használja rajta a baltát
	 * @param c Karakter akivel interaktol
	 */
	@Override
	public void bearInteract(Character c)
	{
		if(Boolean.TRUE.equals(currField.containsCharacter(c)))
		{
			for(int i = activeGears.size() - 1; i <= 0; --i)
			{
				if(activeGears.get(i).getName() == GearEnum.AXE)
				{
					activeGears.get(i).Effect(c);
					activeGears.remove(i);
					return;
				}
			}
		}
	}
	/**
	 * Az az interakció amikor a jelenlegi virológus el akar lopni anyagot egy másik virológustól.
	 * @param c A karakter akitől az anyagot szertné ellopni a jelenlegi virológus
	 */
	public void stealMaterialInteract(Character c)
	{
		if(currField.containsCharacter(c) && c.isParalyzed)
		{
			Material maxSteal = new Material(0,0);
			for(MatEnum curEnum: MatEnum.values())
			{
				if(currMaterial.getContainer().get(curEnum) < maxMaterial.getContainer().get(curEnum))
				{
					maxSteal.container.put(curEnum, maxMaterial.getContainer().get(curEnum) - currMaterial.getContainer().get(curEnum));
				}
			}
			Material stolenMat = c.stealMaterial(maxSteal);
			for(MatEnum curEnum: MatEnum.values())
			{
				currMaterial.getContainer().put(curEnum, stolenMat.getContainer().get(curEnum) + currMaterial.getContainer().get(curEnum));
			}	
		}
	}
	/**
	 * Az az interakció amikor a jelenlegi virológus el akar lopni felszerelést egy másik virológustól.
	 * @param v A virológus akitől a felszerelést szeretné ellopni a jelenlegi virológus.
	 */
	public void stealGearInteract(Character c)
	{
		if(Boolean.TRUE.equals(currField.containsCharacter(c)))
		{
			if(Boolean.TRUE.equals(c.isParalyzed))
			{
				Gear stolenGear = c.stealGear();
				if(stolenGear != null)
				{
					gears.add(stolenGear);
				}
			}
		}
	}
	/**
	 *Felülírja a Character Use függvényét, a megadott karakteren használja a megadott ágenst.
	 */
	@Override
	public void use(Character c, Agent a)
	{
		if(Boolean.TRUE.equals(currField.containsCharacter(c)))
		{
			if(craftedAgents.contains(a))
			{
				c.agentUsedOnHim(a, c);
				craftedAgents.remove(a);
			}
		}
	}
	/**
	 * Ez hívódik meg amikor a GameManager a kört lépteti.
	 */
	@Override
	public void round()
	{
		ArrayList<Agent> tempList = new ArrayList<Agent>(activeAgents.size());
		for(Agent curActive: activeAgents) {
			tempList.add(curActive);
		}
		
		for(Agent curActive: tempList)
		{
			curActive.Round();
		}
		
		if(Boolean.TRUE.equals(isVitus)) {
			ArrayList<Field> neighFields = currField.getNeighbours();
			int randomint = rand.nextInt(neighFields.size());
			move(neighFields.get(randomint));
			fieldInteract();
		}
	}
}
