package random_csapatnev;

import java.io.Serializable;

/**
 * Indikálja, hogy a Material container-ben milyen típusú anyagok vannak letárolva, 
 * illetve ez alapján lehet majd kikérni, 
 * hogy melyik anyag típusból mennyi van.
 */
public enum MatEnum implements Serializable
{
	AMINOACID,
	NUCLEOTIDE
}
