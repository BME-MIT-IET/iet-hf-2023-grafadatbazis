package random_csapatnev;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import random_csapatnev.modelclasses.*;
import random_csapatnev.modelclasses.Character;
import random_csapatnev.viewclasses.GraphicsCharacter;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.Map;

class CouldHeGainStuff {
    static String couldHeGainStuff(boolean usageSuccess) {
        return usageSuccess ? "Yes I gained" : "No I didn't gain";
    }
}

public class VirologistFieldInteractStepDefs {
    private Virologist virologist = new Virologist("Virologist");
    private Field field;
    private String actualAnswer;

    @Given("Virologist is on a Laboratory")
    public void virologist_is_on_laboratory() {
        MainFrame.Instance = mock(MainFrame.class);

        field = new Laboratory(0, 0);
        virologist.setCurrField(field);
    }

    @Given("Virologist is on a Warehouse")
    public void virologist_is_on_warehouse() {
        field = new Warehouse(0, 0);
        virologist.setCurrField(field);
    }

    @Given("Virologist is on a Safehouse")
    public void virologist_is_on_safehouse() {
        field = new Safehouse(0, 0);
        virologist.setCurrField(field);
    }

    @When("I ask if he learnt a new Agent")
    public void ask_whether_he_could_use() {
        boolean result = false;
        virologist.fieldInteract();
        if (virologist.getKnownAgents().size() > 0) {
            result = true;
        }
        actualAnswer = CouldHeGainStuff.couldHeGainStuff(result);
    }

    @When("I ask if he gained materials")
    public void ask_whether_he_gained_materials() {
        MainFrame.Instance = mock(MainFrame.class);
        Model m = mock(Model.class);
        when(MainFrame.Instance.getModel()).thenReturn(m);
        
        ArrayList<GraphicsCharacter> gc = new ArrayList<>();
        ArrayList<Character> c = new ArrayList<>();
        when(m.getGraphicsCharacter()).thenReturn(gc);
        when(m.getCharacters()).thenReturn(c);

        boolean result = false;
        virologist.fieldInteract();
        Map<MatEnum, Integer> material = virologist.getCurrMaterial().getContainer();
        if (material.get(MatEnum.AMINOACID) > 0 || material.get(MatEnum.NUCLEOTIDE) > 0) {
            result = true;
        }
        actualAnswer = CouldHeGainStuff.couldHeGainStuff(result);
    }

    @When("I ask if he gained a new Gear")
    public void ask_whether_he_gained_gear() {
        boolean result = false;
        virologist.fieldInteract();
        if (virologist.getGears().size() > 0 || virologist.getActiveGears().size() > 0) {
            result = true;
        }
        actualAnswer = CouldHeGainStuff.couldHeGainStuff(result);
    }

    @Then("He should answer {string} a new Agent")
    public void he_should_answer(String expectedAnswer) {
        assertEquals(expectedAnswer, actualAnswer);
    }

    @Then("He should answer {string} materials")
    public void he_should_answer_materials(String expectedAnswer) {
        assertEquals(expectedAnswer, actualAnswer);
    }

    @Then("He should answer {string} a new Gear")
    public void he_should_answer_gear(String expectedAnswer) {
        assertEquals(expectedAnswer, actualAnswer);
    }
}