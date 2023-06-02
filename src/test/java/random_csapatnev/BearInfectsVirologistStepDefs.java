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

class CouldHeInfectVirologist {
    static String couldHeInfectVirologist(boolean success) {
        return success ? "Yes I could" : "No I couldn't";
    }
}

public class BearInfectsVirologistStepDefs {
    private Virologist virologist = new Virologist("Virologist");
    private Bear bear = new Bear("bBear");
    private Field field = new Field(0, 0);
    private Field field2 = new Field(0, 1);
    private String actualAnswer;

    @Given("Bear and Virologist are on the same Field")
    public void bear_and_virologist_are_on_the_same_field() {
        bear.setCurrField(field);
        this.field.getCharacters().add(bear);
        virologist.setCurrField(field);
        this.field.getCharacters().add(virologist);
    }

    @Given("Bear and Virologist are not on the same Field")
    public void bear_and_virologist_are_not_on_the_same_field() {
        bear.setCurrField(field);
        this.field.getCharacters().add(bear);
        virologist.setCurrField(field2);
        this.field2.getCharacters().add(virologist);
    }

    @Given("Virologist has protection")
    public void virologist_has_protection() {
        virologist.getActiveAgents().add(new ProtectiveVaccine());
    }

    @Given("Virologist has no protection")
    public void virologist_has_no_protection() {
        virologist.getActiveAgents().clear();
    }

    @When("I ask if he could infect Virologist")
    public void ask_whether_he_could_infect() {
        MainFrame.Instance = mock(MainFrame.class);
        Model m = mock(Model.class);
        when(MainFrame.Instance.getModel()).thenReturn(m);
        
        ArrayList<GraphicsCharacter> gc = new ArrayList<>();
        ArrayList<Character> c = new ArrayList<>();
        when(m.getGraphicsCharacter()).thenReturn(gc);
        when(m.getCharacters()).thenReturn(c);

        boolean result = false;
        bear.fieldInteract();

        if (bear.getCurrField().getCharacters().size() >= 2) {
            if (bear.getCurrField().getCharacters().get(0) instanceof Bear && bear.getCurrField().getCharacters().get(1) instanceof Bear) {
                result = true;
            }
        }
        actualAnswer = CouldHeInfectVirologist.couldHeInfectVirologist(result);
    }

    @Then("He should answer {string} infect Virologist")
    public void he_should_answer(String expectedAnswer) {
        assertEquals(expectedAnswer, actualAnswer);
    }
}