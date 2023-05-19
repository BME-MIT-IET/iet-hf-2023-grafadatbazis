package random_csapatnev;

import java.io.Serializable;
import java.util.ArrayList;

import javax.swing.JPanel;

/**
 * 
 * MVC modellből a Model-t kezelő rész.
 *
 */
public class Model implements Serializable 
{
	int N;
	int M;
	
	Field[][] fields;
	ArrayList<Character> characters = new ArrayList<Character>();
	ArrayList<Agent> agents = new ArrayList<Agent>();
	ArrayList<Gear> gears = new ArrayList<Gear>();
	ArrayList<Material> materials = new ArrayList<Material>();

	ArrayList<GraphicsCharacter> graphicsCharacter = new ArrayList<GraphicsCharacter>();
	GraphicsFieldBase[][] graphicsFields;
	ArrayList<GraphicsGear> graphicsGear = new ArrayList<GraphicsGear>();
	ArrayList<GraphicsMaterial> graphicsMaterial = new ArrayList<GraphicsMaterial>();
	
	public Model() 
	{
		
	}
	
	public void setFields(Field[][] input) { fields = input; }
	public void setCharacters(ArrayList<Character> input) { characters = input; }
	public void setAgents(ArrayList<Agent> input) { agents = input; }
	public void setGears(ArrayList<Gear> input) { gears = input; }
	public void setMaterials(ArrayList<Material> input) { materials = input; }
	public void setDim(int x, int y) { N = x; M = y; }
	
	public Field[][] getFields() { return fields; }
	public ArrayList<Character> getCharacters() { return characters; }
	public ArrayList<Agent> getAgents() { return agents; }
	public ArrayList<Gear> getGears() { return gears; }
	public ArrayList<Material> getMaterials() { return materials; }
	public ArrayList<GraphicsCharacter> getGraphicsCharacter() 
	{
		return graphicsCharacter;
	}
	public void setGraphicsCharacter(ArrayList<GraphicsCharacter> graphicsCharacter) {
		this.graphicsCharacter = graphicsCharacter;
	}
	public GraphicsFieldBase[][] getGraphicsField() {
		return graphicsFields;
	}
	public void setGraphicsField(GraphicsFieldBase[][] graphicsField) {
		this.graphicsFields = graphicsField;
	}
	public ArrayList<GraphicsGear> getGraphicsGear() {
		return graphicsGear;
	}
	public void setGraphicsGear(ArrayList<GraphicsGear> graphicsGear) {
		this.graphicsGear = graphicsGear;
	}
	public ArrayList<GraphicsMaterial> getGraphicsMaterial() {
		return graphicsMaterial;
	}
	public void setGraphicsMaterial(ArrayList<GraphicsMaterial> graphicsMaterial) {
		this.graphicsMaterial = graphicsMaterial;
	}
	
	public void createGraphicsObjects() {}
	public void clearData() {}
	public void repaint() {}
}
