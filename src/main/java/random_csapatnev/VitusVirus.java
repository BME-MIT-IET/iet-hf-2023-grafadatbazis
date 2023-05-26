package random_csapatnev;

import java.util.Objects;

/**
 * Agent osztályból származik, feladata hogy a megadott karakterre kifejtse a vitus tánc hatását.
 */
public class VitusVirus extends Agent
{
	private static final String VIT_VACCINE = "VitusVaccine";
	private static final String PROT_VACCINE = "ProtectiveVaccine";

	public VitusVirus() {
		name = "VitusVirus";
	}
	/**
	 * Felülírja az Agent Round metódusát, 
	 * ez hívódik meg amikor egy karakter aktív ágensei között el van tárolva, 
	 * a karakter Round függvénye hívja meg.
	 */
	@Override
	public void round() 
	{
		if(currCharacter != null) {
			currCharacter.setIsVitus(true);
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
	 * Felülírja az Agent Effect metódusát, ez a függvény hívódik meg amikor kifejti a hatását karakterre.
	 */
	@Override
	public void effect(Character c) 
	{
		boolean hasProtection = false;
		for(Agent a : c.activeAgents) {
			if(VIT_VACCINE.equals(a.name) || PROT_VACCINE.equals(a.name)) {
				hasProtection = true;
			}
		}
		if(!hasProtection) {
			this.currCharacter = c;
			currCharacter.activeAgents.add(this);
			currCharacter.setIsVitus(true);
		}
	}

	/**
	 * Felülírja az Agent Expire metódusát, ez a függvény hívódik meg amikor lejár vírus hatása.
	 */
	@Override
	public void expire() 
	{
		currCharacter.setIsVitus(false);
		currCharacter.activeAgents.remove(this);
	}

	@Override
	public Agent createNew() 
	{
		return new VitusVirus();
	}
}
