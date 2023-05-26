package random_csapatnev;

import java.util.Objects;

/**
 * Agent osztályból származik, feladata hogy a bénító hatást kifejtése az adott karakterre.
 */
public class ParalyzingVirus extends Agent 
{
	public ParalyzingVirus() {
		name = "ParalyzingVirus";
	}
	/**
	 * Felülírja az Agent Round metódusát, 
	 * ez hívódik meg amikor egy karakter aktív ágensei között el van tárolva, 
	 * a karakter Round függvénye hívja meg.
	 */
	@Override
	public void Round() 
	{
		if(currCharacter != null) {
			currCharacter.SetIsParalyzed(true);
			activeTime++;
			if(Objects.equals(activeTime, effectTime)) {
				Expire();
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
	 * Felülírja az Agent Effect metódusát, ez a függvény hívódik meg amikor kifejti a hatását karakterre
	 */
	@Override
	public void Effect(Character c) 
	{
		boolean hasProtection = false;
		for(Agent a : c.activeAgents) {
			if("ParalyzingVaccine".equals(a.name) || "ProtectiveVaccine".equals(a.name)) {
				hasProtection = true;
			}
		}
		if(!hasProtection) {
			this.currCharacter = c;
			currCharacter.activeAgents.add(this);
			currCharacter.SetIsParalyzed(true);
		}
	}

	/**
	 * Felülírja az Agent Expire metódusát, ez a függvény hívódik meg amikor lejár vírus hatása.
	 */
	@Override
	public void Expire() 
	{
		currCharacter.SetIsParalyzed(false);
		currCharacter.activeAgents.remove(this);
	}

	@Override
	public Agent CreateNew() 
	{
		return new ParalyzingVirus();
	}

}
