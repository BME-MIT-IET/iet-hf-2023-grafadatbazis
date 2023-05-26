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
	Random rand = new Random();

	public Virologist(String _name) {
		super(_name);
	}

	/**
	 *A jelenlegi virológus átmozog a paraméterként megkapott mezőre.
	 */
	@Override
	public void Move(Field f)
	{
		Field fTemp = f;
		if(Boolean.TRUE.equals(isParalyzed)) { return; }
		if(Boolean.TRUE.equals(isVitus)) { fTemp = currField.GetNeighbours().get(rand.nextInt(currField.GetNeighbours().size())); }
		
		if(Boolean.TRUE.equals(currField.IsNeighbour(f)))
		{
			fTemp.MoveFrom(currField, this);
			currField = fTemp;
		}
	}
	
	@Override
	public void FieldInteract() 
	{
		if(currField != null) {
			currField.Interact(this);
		}
	}
	
	/**
	 * A virológus leveszi a megadott felszerelést.
	 * @param g Annak a felszerelésnek az enum-ja amit le szeretne venni.
	 */
	public void UnequipGear(GearEnum g)
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
	public void CraftAgent(Agent a)
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
				if(a.cost.GetContainer().get(curEnum) > currMaterial.GetContainer().get(curEnum))
				{
					canCraft = false;
					break;
				}
			}
			if(Boolean.TRUE.equals(canCraft))
			{
				for(MatEnum curEnum: MatEnum.values())
				{
					currMaterial.GetContainer().put(curEnum, currMaterial.GetContainer().get(curEnum) - a.cost.GetContainer().get(curEnum));  
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
	public void AgentUsedOnHim(Agent a, Character c)
	{
		if(Boolean.TRUE.equals(isGloved))
		{
			c.AgentUsedOnHim(a, c);
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
	public void EquipGear(GearEnum g)
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
	public void BearInteract(Character c)
	{
		if(Boolean.TRUE.equals(currField.ContainsCharacter(c)))
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
	public void StealMaterialInteract(Character c)
	{
		if(currField.ContainsCharacter(c) && c.isParalyzed)
		{
			Material maxSteal = new Material(0,0);
			for(MatEnum curEnum: MatEnum.values())
			{
				if(currMaterial.GetContainer().get(curEnum) < maxMaterial.GetContainer().get(curEnum))
				{
					maxSteal.container.put(curEnum, maxMaterial.GetContainer().get(curEnum) - currMaterial.GetContainer().get(curEnum));
				}
			}
			Material stolenMat = c.StealMaterial(maxSteal);
			for(MatEnum curEnum: MatEnum.values())
			{
				currMaterial.GetContainer().put(curEnum, stolenMat.GetContainer().get(curEnum) + currMaterial.GetContainer().get(curEnum));
			}	
		}
	}
	/**
	 * Az az interakció amikor a jelenlegi virológus el akar lopni felszerelést egy másik virológustól.
	 * @param v A virológus akitől a felszerelést szeretné ellopni a jelenlegi virológus.
	 */
	public void StealGearInteract(Character c)
	{
		if(Boolean.TRUE.equals(currField.ContainsCharacter(c)))
		{
			if(Boolean.TRUE.equals(c.isParalyzed))
			{
				Gear stolenGear = c.StealGear();
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
	public void Use(Character c, Agent a)
	{
		if(Boolean.TRUE.equals(currField.ContainsCharacter(c)))
		{
			if(craftedAgents.contains(a))
			{
				c.AgentUsedOnHim(a, c);
				craftedAgents.remove(a);
			}
		}
	}
	/**
	 * Ez hívódik meg amikor a GameManager a kört lépteti.
	 */
	@Override
	public void Round()
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
			ArrayList<Field> neighFields = currField.GetNeighbours();
			int randomint = rand.nextInt(neighFields.size());
			Move(neighFields.get(randomint));
			FieldInteract();
		}
	}
}
