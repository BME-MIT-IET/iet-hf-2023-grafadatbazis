package random_csapatnev;

/**
 * Agent osztályból származik, felelőssége, kifejteni a karakterre azt a hatást,
 * amitől elfelejti a már megtanult ágenseket.
 */
public class AmnesiaVirus extends Agent {
	public AmnesiaVirus() {
		name = "AmnesiaVirus";
	}

	/**
	 * Felülírja az Agent Round metódusát,
	 * ez hívódik meg amikor egy karakter aktív ágensei között el van tárolva,
	 * a karakter Round függvénye hívja meg.
	 */
	@Override
	public void round() {
		expireDate--;
		if (expireDate == 0) {
			ownerCharacter.craftedAgents.remove(this);
		}
	}

	/**
	 * Felülírja az Agent Effect metódusát, ez a függvény hívódik meg amikor kifejti
	 * a hatását karakterre.
	 */
	@Override
	public void affect(Character c) {
		boolean hasProtection = false;
		for (Agent a : c.activeAgents) {
			if (StringLiterals.AMN_VACCINE.equals(a.name) || StringLiterals.PROT_VACCINE.equals(a.name)) {
				hasProtection = true;
			}
		}
		if (!hasProtection) {
			c.knownAgents.clear();
			c.activeAgents.add(this);
			this.currCharacter = c;
		}
	}

	/**
	 * Felülírja az Agent Expire metódusát, ez a függvény hívódik meg amikor lejár
	 * vírus hatása.
	 */
	@Override
	public void expire() {
		currCharacter.activeAgents.remove(this);
	}

	@Override
	public Agent createNew() {
		return new AmnesiaVirus();
	}
}
