package random_csapatnev;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import random_csapatnev.modelclasses.Agent;
import random_csapatnev.modelclasses.Material;
import random_csapatnev.modelclasses.Virologist;
import random_csapatnev.modelclasses.VitusVirus;

import static org.junit.Assert.*;
import static random_csapatnev.CouldHeCraft.couldHeCraft;

class CouldHeCraft{
    static String couldHeCraft(boolean craftSuccess){
        return craftSuccess ? "Yes I could" : "No I couldn't";
    }
}

public class VirologistCraftsStepDefs {
    private Virologist virologist = new Virologist("test");
    private Agent agent = new VitusVirus();
    private String actualAnswer;

    @Given("Virologist knows Agent")
    public void knows_agent(){
        virologist.getKnownAgents().add(agent);
    }
    @Given("Virologist does not know Agent")
    public void does_not_know_agent(){}
    @Given("Virologist has {int} AminoAcid and {int} Nucleotid")
    public void hasNMaterial(int aminoAcid, int nucleotid){
        virologist.getCurrMaterial().addMaterial(new Material(aminoAcid, nucleotid));
    }
    @When("I ask whether he could craft Agent")
    public void ask_whether_he_could_craft(){
        if(virologist.getKnownAgents().size()>0)
            virologist.craftAgent(virologist.getKnownAgents().get(0));
        actualAnswer = couldHeCraft(virologist.getCraftedAgents().size()>0);
    }
    @Then("He should answer {string} craft")
    public void he_should_answer(String expectedAnswer){
        assertEquals(expectedAnswer, actualAnswer);
    }
}
