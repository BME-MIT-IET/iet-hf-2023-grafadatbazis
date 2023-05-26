package random_csapatnev;

import java.util.Objects;

/**
 * Agent osztályból származik, feladata, hogy a a ParalyzingVirus hatását megakadályozása.
 */
public class ParalyzingVaccine extends Agent
{
	public ParalyzingVaccine() {
		name = "ParalyzingVaccine";
	}
	/**
	 * Felülírja az Agent Round metódusát, 
	 * ez hívódik meg amikor egy karakterre aktív ágensei között el van tárolva, 
	 * a karakterre Round függvénye hívja meg.
	 */
	@Override
	public void round() 
	{
		if(currCharacter != null) {
			currCharacter.setIsParalyzed(false);
			activeTime++;
			if(Objects.equals(activeTime, effectTime)) {
				expire();
			}
		}
		else {
			expireDate--;
			if(expireDate == 0) {
				ownerCharacter.craftedAgents.remove(this);
			}
		}
	}

	/**
	 * Felülírja az Agent Effect metódusát, 
	 * ez a függvény hívódik meg amikor kifejti a hatását karakterre
	 */
	@Override
	public void affect(Character c) 
	{
		c.activeAgents.add(this);
		this.currCharacter = c;
		currCharacter.setIsParalyzed(false);
	}

	/**
	 * Felülírja az Agent Expire metódusát, ez a függvény hívódik meg amikor lejár vakcina hatása.
	 */
	@Override
	public void expire() 
	{
		currCharacter.activeAgents.remove(this);
	}

	@Override
	public Agent createNew() 
	{
		return new ParalyzingVaccine();
	}
	
}
