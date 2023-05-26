package random_csapatnev;

import java.io.Serializable;
import java.util.HashMap;

/**
 * Reprezentálja, 
 * és tárolja a játékban gyűjthető anyagok számosságát (nukleotidok, aminosavak).
 */
public class Material implements Serializable
{
	/**
	 * HashMap<MatEnum,Integer>: Tárolja a nukleotidok és aminosavak mennyiségét.
	 */
	HashMap <MatEnum, Integer> container;
	
	/**
	 * Konstruktorok.
	 */
	public Material() { container = new HashMap<MatEnum, Integer>(); }
    public Material(final int a, final int b) {
    	container = new HashMap<MatEnum, Integer>(){{
    		put(MatEnum.AMINOACID, a);
    		put(MatEnum.NUCLEOTIDE, b);
    	}
    	};
    }
	
	/**
	 * A paraméterül kapott c karakter felveszi a jelenlegi anyagmennyiség.
	 * @param c Aki felveszi a jelenlegi anyagmennyiséget.
	 */
	public void pickUp(Character c)
	{
		
		MatEnum[] matArray = MatEnum.values();
		int[] valArray = new int[matArray.length];
		Material cMat = c.currMaterial;
		Material cMaxMat = c.maxMaterial;
		
		for(int i = 0; i < matArray.length; i++) {
			valArray[i] = container.get(matArray[i]);
		}
		
		for(int i = 0; i < matArray.length; i++) {
			if(cMaxMat.getContainer().get(matArray[i]) <= valArray[i] + cMat.getContainer().get(matArray[i])) {
				int deltaVal = (cMaxMat.getContainer().get(matArray[i]) - cMat.getContainer().get(matArray[i]));
				cMat.container.put(matArray[i], cMaxMat.getContainer().get(matArray[i]));
				this.container.put(matArray[i], valArray[i]- deltaVal);
			}
			else {
				cMat.container.put(matArray[i], valArray[i]);
				valArray[i] = 0;
				this.container.put(matArray[i], valArray[i]);
			}
		}
	}
	/**
	 * Visszatér a letárolt container-el, ebben van tárolva, hogy milyen anyagból mennyi van.
	 * @return Letárolt container.
	 */
	public HashMap<MatEnum, Integer> getContainer()
	{
		return container;
	}
	/**
	 * A jelenlegi anyagból kivonásra kerül a paraméterként megkapott anyagmennyiség
	 * @param m Az eltávolítandó anyagmennyiség.
	 * @return Visszatér az új anyagmennyiséggel.
	 */
	public Material remove(Material m)
	{
		MatEnum[] matArray = MatEnum.values();
		
		for(int i = 0; i < matArray.length; i++) {
			int newVal = container.get(matArray[i]) - m.getContainer().get(matArray[i]);
			
			if(newVal > 0) {
				container.put(matArray[i], newVal);
			}
			else {
				container.put(matArray[i], 0);
			}
		}
		return this;
	}
	/**
	 * A jelenlegi anyaghoz hozzáadásra kerül a paraméterként megkapott anyagmennyiség
	 * @param m A hozzáadandó anyagmennyiség.
	 */
	public void addMaterial(Material m)
	{
		MatEnum[] matArray = MatEnum.values();
		
		for(int i = 0; i < matArray.length; i++) {
			int newVal = container.get(matArray[i]) + m.getContainer().get(matArray[i]);
			
			container.put(matArray[i], newVal);
		}
	}
}
