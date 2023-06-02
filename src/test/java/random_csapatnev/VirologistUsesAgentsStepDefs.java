package random_csapatnev;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import random_csapatnev.modelclasses.Agent;
import random_csapatnev.modelclasses.Field;
import random_csapatnev.modelclasses.ParalyzingVirus;
import random_csapatnev.modelclasses.Virologist;

import static org.junit.Assert.assertEquals;

class CouldHeUseAgents{
    static String couldHeUseAgents(boolean usageSuccess) {
        return usageSuccess ? "Yes I could" : "No I couldn't";
    }
}

public class VirologistUsesAgentsStepDefs{
    private Virologist virologist1 = new Virologist("Virologist1");
    private Virologist virologist2 = new Virologist("Virologist2");
    private Field field = new Field(0,0);
    private Field otherField = new Field(3,3);
    private String actualAnswer;

    @Given("Virologist1 and Virologist2 stands on the same Field")
    public void virologists_on_same_field(){
        this.field.getCharacters().add(virologist1);
        this.field.getCharacters().add(virologist2);
        this.virologist1.setCurrField(field);
        this.virologist2.setCurrField(field);
    }

    @Given("Virologist1 and Virologist2 is not on the same Field")
    public void virologists_not_on_same_field(){
        this.field.getCharacters().add(virologist1);
        this.otherField.getCharacters().add(virologist2);
        this.virologist1.setCurrField(field);
        this.virologist2.setCurrField(otherField);
    }

    @Given("Virologist1 has the Agent crafted")
    public void virologist1_has_agent(){
        Agent agentToUse = new ParalyzingVirus();
        this.virologist1.addCraftedAgent(agentToUse.createNew());
    }

    @Given("Virologist1 does not have the Agent crafted")
    public void virologist1_does_not_have_agent(){}    

    @When("I ask if he could use Agent on Virologist2")
    public void ask_whether_he_could_use(){
        boolean result = false;
        if(virologist1.getCraftedAgents().size()>0){
            Agent agentToUse = virologist1.getCraftedAgents().get(0);
            virologist1.use(virologist2, agentToUse);
            result = virologist2.getActiveAgents().contains(agentToUse);
        }
        actualAnswer = CouldHeUseAgents.couldHeUseAgents(result);
    }

    @Then("He should answer {string} use Agent on Virologist2")
    public void he_should_answer(String expectedAnswer){
        assertEquals(expectedAnswer, actualAnswer);
    }
}