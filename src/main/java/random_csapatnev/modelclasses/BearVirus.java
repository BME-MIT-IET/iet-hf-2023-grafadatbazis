package random_csapatnev.modelclasses;

import random_csapatnev.*;
import random_csapatnev.MainFrame;
import random_csapatnev.viewClasses.GraphicsBear;
import random_csapatnev.viewClasses.GraphicsCharacter;

import java.util.ArrayList;
import java.util.Random;

/**
 *
 * Agent osztályból származik, felülírja ennek a némelyik metódusát.
 * Felelőssége, hogy reprezentálja és működésében ellássa, a medve vírussal
 * megfertőződött virológusok szerepét.
 */
public class BearVirus extends Agent {
	private Random rand = new Random();

	public BearVirus() {
		name = StringLiterals.BEAR_VIRUS;
	}

	@Override
	public void round() {
		/*
		 * BearVirus hatása nem tud lejárni, ezért a Round implementációja
		 * üres, LSP miatt azonban nem dobhat kivételt.
		 */
	}

	/**
	 * Felülírja az Agent Effect metódusát, ez a függvény hívódik meg amikor kifejti
	 * a hatását virológusra.
	 */
	@Override
	public void affect(Character c) {
		boolean hasProtection = false;
		for (Agent a : c.activeAgents) {
			if (StringLiterals.PROT_VACCINE.equals(a.name) || (c.isCloaked && rand.nextInt(1000) <= 823)) {
				hasProtection = true;
			}
		}
		if (!hasProtection) {
			c.currField.removeCharacter(c);

			ArrayList<GraphicsCharacter> tempList = new ArrayList<>(MainFrame.Instance.getModel().getGraphicsCharacter());
			for (GraphicsCharacter e : tempList) {
				if (e.getC() == c) {
					e.remove();
					MainFrame.Instance.getModel().getGraphicsCharacter().remove(e);
					MainFrame.Instance.refreshView();
				}
			}

			MainFrame.Instance.getModel().getCharacters().remove(c);

			if (c.name.equals("v0")) {
				Main.endGame(false);
			}

			Bear b = new Bear(c, "b" + c.name.substring(1));
			MainFrame.Instance.getModel().getCharacters().add(b);
			MainFrame.Instance.getModel().getGraphicsCharacter().add(new GraphicsBear(b));
		}
	}

	@Override
	public void expire() {
		currCharacter.activeAgents.remove(this);
	}

	@Override
	public Agent createNew() {
		return new BearVirus();
	}

}
