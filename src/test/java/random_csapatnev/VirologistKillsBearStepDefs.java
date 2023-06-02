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

class CouldHeKillBear{
    static String couldHeKillBear(boolean usageSuccess) {
        return usageSuccess ? "Yes I could" : "No I couldn't";
    }
}

public class VirologistKillsBearStepDefs{
    private Virologist virologist = new Virologist("Virologist");
    private Bear bear = new Bear("Bear");
    private Field field = new Field(0,0);
    private Field otherField = new Field(3,3);
    private String actualAnswer;

    @Given("Virologist and Bear is on the same Field")
    public void virologist_and_bear_on_same_field(){
        this.field.getCharacters().add(virologist);
        this.field.getCharacters().add(bear);
        this.virologist.setCurrField(field);
        this.bear.setCurrField(field);
    }

    @Given("Virologist and Bear are on different fields")
    public void virologist_and_bear_on_different_fields(){
        this.field.getCharacters().add(virologist);
        this.otherField.getCharacters().add(bear);
        this.virologist.setCurrField(field);
        this.bear.setCurrField(otherField);
    }

    @Given("Virologist has Axe")
    public void virologist_has_axe(){
        Gear gear = new Axe();
        gear.pickUp(virologist);
    }

    @Given("Virologist does not have Axe")
    public void virologist_does_not_have_axe(){}

    @When("I ask if he could kill Bear")
    public void ask_whether_he_could_kill(){
        MainFrame.Instance = mock(MainFrame.class);
        Model m = mock(Model.class);
        when(MainFrame.Instance.getModel()).thenReturn(m);
        
        ArrayList<GraphicsCharacter> gc = new ArrayList<>();
        ArrayList<Character> c = new ArrayList<>();
        when(m.getGraphicsCharacter()).thenReturn(gc);
        when(m.getCharacters()).thenReturn(c);

        
        boolean result = true;
        virologist.bearInteract(bear);
        if(virologist.getCurrField().containsCharacter(bear) || otherField.containsCharacter(bear)){
            result = false;
        }
        actualAnswer = CouldHeKillBear.couldHeKillBear(result);
    }

    @Then("He should answer {string} kill Bear")
    public void he_should_answer(String expectedAnswer){
        assertEquals(expectedAnswer, actualAnswer);
    }
}