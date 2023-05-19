package random_csapatnev;

/**
 * Agent osztályból származik, feladata hogy a megadott karakterre kifejtse a vitus tánc hatását.
 */
public class VitusVirus extends Agent
{
	public VitusVirus() {
		name = "VitusVirus";
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
			currCharacter.SetIsVitus(true);
			activeTime++;
			if(activeTime == effectTime) {
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
	 * Felülírja az Agent Effect metódusát, ez a függvény hívódik meg amikor kifejti a hatását karakterre.
	 */
	@Override
	public void Effect(Character c) 
	{
		boolean hasProtection = false;
		for(Agent a : c.activeAgents) {
			if(a.name == "VitusVaccine" || a.name == "ProtectiveVaccine") {
				hasProtection = true;
			}
		}
		if(!hasProtection) {
			this.currCharacter = c;
			currCharacter.activeAgents.add(this);
			currCharacter.SetIsVitus(true);
		}
	}

	/**
	 * Felülírja az Agent Expire metódusát, ez a függvény hívódik meg amikor lejár vírus hatása.
	 */
	@Override
	public void Expire() 
	{
		currCharacter.SetIsVitus(false);
		currCharacter.activeAgents.remove(this);
	}

	@Override
	public Agent CreateNew() 
	{
		return new VitusVirus();
	}
}
