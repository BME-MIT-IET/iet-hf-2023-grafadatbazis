package random_csapatnev;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Random;

/**
 * A karakterek ősosztálya, itt vannak definiálva, az alap karakter működést ellátó függvények.
 */
public class Character implements Serializable
{
	Random rand = new Random();

	String name = "";
	/**
	 * Jelenleg a karakternél lévő anyagok.
	 */
	Material currMaterial = new Material(0, 0);
	/**
	 * Megadja, hogy maximum mennyi anyag lehet a karakternél.
	 */
	Material maxMaterial = new Material(100, 100);	
	/**
	 * A Már megtanult ágensek listája
	 */
	ArrayList<Agent> knownAgents = new ArrayList<>();
	/**
	 * A Jelenleg a karakterre ható ágensek listája.
	 */
	ArrayList<Agent> activeAgents = new ArrayList<>();
	/**
	 * Jelenlegi mező amin tartózkodik
	 */
	Field currField;
	/**
	 * A már megszerzett felszerelések listája.
	 */
	ArrayList<Gear> gears = new ArrayList<>();
	/**
	 * A jelenleg viselt felszerelések listája.
	 */
	ArrayList<Gear> activeGears = new ArrayList<>();
	
	/**
	 * Megadja, hogy a virológuson van-e kesztyű.
	 */
	Boolean isGloved = false;
	/**
	 *  Megadja, hogy a virológuson van-e Köpeny.
	 */
	Boolean isCloaked = false;
	/**
	 * Megadja, hogy a virológus bénító vírus hatása alatt van-e.
	 */
	Boolean isParalyzed = false;
	/**
	 * Megadja, hogy a virológus vitus táncot okozó vírus hatása alatt van-e.
	 */
	Boolean isVitus = false;
	/**
	 * Megadja, hogy a virológus jelenleg milyen elkészített ágensekkel rendelkezik. 
	 */
	ArrayList<Agent> craftedAgents = new ArrayList<>();
	
	public Character(String inputName) {
		name = inputName;
	}
	public Character(Character c, String inputName) {
		currMaterial = c.currMaterial;
		gears = c.gears;
		activeGears = c.activeGears;
		craftedAgents = c.craftedAgents;
		currField = c.currField;
		isParalyzed = true;
		name = inputName;
	}
	
	public void setCurrField(Field f) {
		currField = f;
	}
	public void setIsParalyzed(Boolean b) {
		isParalyzed = b;
	}
	public void setIsVitus(Boolean b) {
		isVitus = b;
	}
	
	/**
	 * A karaktert átlépteti a megadott mezőre.
	 * @param f A mező ahova a karakter lépni szeretne.
	 */
	public void move(Field f){}
	/**
	 * A karakter interaktál azzal a mezővel amin éppen áll(currField)
	 */
	public void fieldInteract(){}
	/** A karakter interaktál egy másik karakterrel
	 * @param c Karakter akivel interaktál.
	 */
	public void characterInteract(Character c){}
	public void bearInteract(Character c) {}
	
	/**
	 * A karakter használja a paraméterként megadott ágenst a paraméterként megkapott karakteren
	 * @param c Character, akin az ágenst kívánjuk használni
	 * @param a Az ágens amit használni kívánunk.
	 */
	public void use(Character c, Agent a){}
	/**
	 *  Ez hívódik meg amikor a GameManager a kört lépteti.
	 */
	public void agentUsedOnHim(Agent a, Character v){}
	public void round(){}
	/**
	 * Ez hívódik meg amikor a jelenlegi karakteren használják a baltát, ilyenkor a jelenlegi karakter meghal.
	 */
	public void die(){}
	/**
	 * A jelenlegi virológustól ellopják az anyagait(currMaterial)
	 * @param m A max anyagmennyiség amennyit el tudnak lopni.
	 * @return A ténylegesen ellopott anyagmennyiség.
	 */
	public Material stealMaterial(Material m)
	{
		Material stolenMat = new Material(0,0);
		for(MatEnum curEnum: MatEnum.values())
		{
			if(currMaterial.getContainer().get(curEnum) > m.getContainer().get(curEnum))
			{
				stolenMat.getContainer().put(curEnum, m.getContainer().get(curEnum));
				currMaterial.getContainer().put(curEnum, currMaterial.getContainer().get(curEnum) - m.getContainer().get(curEnum));
			}
			else
			{
				stolenMat.getContainer().put(curEnum, currMaterial.getContainer().get(curEnum));
				currMaterial.getContainer().put(curEnum, 0);
			}
		}
		return stolenMat;
	}
	/**
	 * A jelenlegi virológusnak ellopják a felszerelését.
	 * @return Az ellopott felszerelés.
	 */
	public Gear stealGear()
	{
		if(!gears.isEmpty())
		{
			return gears.remove(rand.nextInt(gears.size()));
		}
		else if(!activeGears.isEmpty())
		{
			return activeGears.remove(rand.nextInt(activeGears.size()));	
		}
		else
		{
			return null;
		}
	}
	
	/**
	 * Eldobja a megadott Geart arra a Fieldre amin jelenleg áll.
	 * @param gE Az eldobandó felszerelés.
	 */
	public void dropGear(GearEnum gE) {
		Gear droppedGear = null;
		for(Gear g : gears) {
			if(g.name == gE) {
				droppedGear = g;
			}
		}
		for(Gear g : activeGears) {
			if(g.name == gE) {
				droppedGear = g;
			}
		}
		if(droppedGear != null) {
			droppedGear.remove(this);
			gears.remove(droppedGear);
		}
	}
}
