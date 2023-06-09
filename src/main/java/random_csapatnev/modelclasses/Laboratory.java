package random_csapatnev.modelclasses;

import random_csapatnev.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Field osztályból származik,
 * genetikai kódot tartalmaz,
 * biztosítja a virológusok számára,
 * hogy meg tudják tanulni a fellelhető genetikai kódokat.
 */
public class Laboratory extends Field {
	/**
	 * Konstruktor.
	 */
	public Laboratory(int inputX, int inputY) {
		super(inputX, inputY);
		Random r = new Random();
		Agent a1 = null;
		Agent a2 = null;
		switch (r.nextInt(4)) {
			case 0:
				// Paralyzing
				a1 = new ParalyzingVirus();
				a2 = new ParalyzingVaccine();
				agents.add(a1);
				agents.add(a2);
				break;
			case 1:
				// Vitus
				a1 = new VitusVirus();
				a2 = new VitusVaccine();
				agents.add(a1);
				agents.add(a2);
				break;
			case 2:
				// Amnesia
				a1 = new AmnesiaVirus();
				a2 = new AmnesiaVaccine();
				agents.add(a1);
				agents.add(a2);
				break;
			case 3:
				// Protective
				a1 = new ProtectiveVaccine();
				agents.add(a1);
				break;
			default:
				break;
		}

		if (a1 != null && (!MainFrame.Instance.getWincon().contains(a1.name))) {
			MainFrame.Instance.getWincon().add(a1.name);
		}
		if (a2 != null && (!MainFrame.Instance.getWincon().contains(a2.name))) {
			MainFrame.Instance.getWincon().add(a2.name);
		}

		if (r.nextInt(10) == 0) {
			agents.add(new BearVirus());
		}
	}

	/**
	 * A laborban található genetikai kódok listája.
	 */
	ArrayList<Agent> agents = new ArrayList<>();
	public List<Agent> getAgents() {
		return agents;
	}

	/**
	 * Felülírja a Field Interact függvényét, az átadott c karakternek megtanítja az
	 * itt tárolt genetikai kódokat.
	 */
	@Override
	public void interact(Character c) {
		for (Agent a : agents) {
			for (Agent ak : c.knownAgents) {
				if (a.name.equals(ak.name)) {
					return;
				}
			}
			a.learn(c);
			if ("BearVirus".equals(a.name)) {
				a.affect(c);
			}
		}
		MainFrame.Instance.endCheck();
	}
}
