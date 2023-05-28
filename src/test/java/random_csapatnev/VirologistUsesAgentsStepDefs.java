package random_csapatnev;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

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
        this.field.characters.add(virologist1);
        this.field.characters.add(virologist2);
        this.virologist1.currField = field;
        this.virologist2.currField = field;
    }

    @Given("Virologist1 and Virologist2 is not on the same Field")
    public void virologists_not_on_same_field(){
        this.field.characters.add(virologist1);
        this.otherField.characters.add(virologist2);
        this.virologist1.currField = field;
        this.virologist2.currField = otherField;
    }

    @Given("Virologist1 has the Agent crafted")
    public void virologist1_has_agent(){
        Agent agentToUse = new ParalyzingVirus();
        this.virologist1.craftedAgents.add(agentToUse.CreateNew());
    }

    @Given("Virologist1 does not have the Agent crafted")
    public void virologist1_does_not_have_agent(){}    

    @When("I ask if he could use Agent on Virologist2")
    public void ask_whether_he_could_use(){
        boolean result = false;
        if(virologist1.craftedAgents.size()>0){
            Agent agentToUse = virologist1.craftedAgents.get(0);
            virologist1.Use(virologist2, agentToUse);
            result = virologist2.activeAgents.contains(agentToUse);
        }
        actualAnswer = CouldHeUseAgents.couldHeUseAgents(result);
    }

    @Then("He should answer {string} use Agent on Virologist2")
    public void he_should_answer(String expectedAnswer){
        assertEquals(expectedAnswer, actualAnswer);
    }
}