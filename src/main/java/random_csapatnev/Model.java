package random_csapatnev;

import random_csapatnev.modelclasses.Agent;
import random_csapatnev.modelclasses.Field;
import random_csapatnev.modelclasses.Gear;
import random_csapatnev.modelclasses.Material;
import random_csapatnev.viewClasses.GraphicsCharacter;
import random_csapatnev.viewClasses.GraphicsFieldBase;
import random_csapatnev.viewClasses.GraphicsGear;
import random_csapatnev.viewClasses.GraphicsMaterial;
import random_csapatnev.modelclasses.Character;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * 
 * MVC modellből a Model-t kezelő rész.
 *
 */
public class Model implements Serializable {
	int sizeN;
	int sizeM;

	Field[][] fields;
	private List<Character> characters = new ArrayList<>();
	private List<Agent> agents = new ArrayList<>();
	private List<Gear> gears = new ArrayList<>();
	private List<Material> materials = new ArrayList<>();

	private List<GraphicsCharacter> graphicsCharacter = new ArrayList<>();
	GraphicsFieldBase[][] graphicsFields;
	private List<GraphicsGear> graphicsGear = new ArrayList<>();
	private List<GraphicsMaterial> graphicsMaterial = new ArrayList<>();

	public void setFields(Field[][] input) {
		fields = input;
	}

	public void setCharacters(List<Character> input) {
		characters = input;
	}

	public void setAgents(List<Agent> input) {
		agents = input;
	}

	public void setGears(List<Gear> input) {
		gears = input;
	}

	public void setMaterials(List<Material> input) {
		materials = input;
	}

	public void setDim(int x, int y) {
		sizeN = x;
		sizeM = y;
	}

	public Field[][] getFields() {
		return fields;
	}

	public List<Character> getCharacters() {
		return characters;
	}

	public List<Agent> getAgents() {
		return agents;
	}

	public List<Gear> getGears() {
		return gears;
	}

	public List<Material> getMaterials() {
		return materials;
	}

	public List<GraphicsCharacter> getGraphicsCharacter() {
		return graphicsCharacter;
	}

	public void setGraphicsCharacter(List<GraphicsCharacter> graphicsCharacter) {
		this.graphicsCharacter = graphicsCharacter;
	}

	public GraphicsFieldBase[][] getGraphicsField() {
		return graphicsFields;
	}

	public void setGraphicsField(GraphicsFieldBase[][] graphicsField) {
		this.graphicsFields = graphicsField;
	}

	public List<GraphicsGear> getGraphicsGear() {
		return graphicsGear;
	}

	public void setGraphicsGear(List<GraphicsGear> graphicsGear) {
		this.graphicsGear = graphicsGear;
	}

	public List<GraphicsMaterial> getGraphicsMaterial() {
		return graphicsMaterial;
	}

	public void setGraphicsMaterial(List<GraphicsMaterial> graphicsMaterial) {
		this.graphicsMaterial = graphicsMaterial;
	}
}
