package random_csapatnev;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import static org.junit.Assert.assertEquals;
import static random_csapatnev.CouldHeMove.couldHeMove;

class CouldHeMove{
    static String couldHeMove(boolean movementSuccess) {
        return movementSuccess ? "Yes I could" : "No I couldn't";
    }
}

public class VirologistMovesStepDefs {
    private Virologist virologist = new Virologist("test");
    private Agent activeAgent;
    private Field startingField = new Field(0,0);
    private Field neighbourField;
    private String actualAnswer;
    @Given("Virologist has no effects affecting their movement")
    public void no_effects(){}
    @Given("Virologist is paralyzed")
    public void paralyzed(){
        ParalyzingVirus paralyzingVirus = new ParalyzingVirus();
        paralyzingVirus.Effect(this.virologist);
    }
    @Given("Virologist stands on a Field with one neighbour")
    public void one_neighbour(){
        this.startingField.characters.add(virologist);
        this.virologist.currField = startingField;
        this.neighbourField = new Field(1,0);
        this.startingField.neighbours.add(neighbourField);
    }
    @When("I ask whether he could move to that field")
    public void ask_whether_he_could_move(){
        if(virologist.currField.GetNeighbours().size()>0){
            virologist.Move(virologist.currField.GetNeighbours().get(0));
        }

        actualAnswer = couldHeMove(neighbourField.characters.contains(virologist));
    }
    @Then("He should answer {string} move")
    public void he_should_answer(String expectedAnswer){
        assertEquals(expectedAnswer, actualAnswer);
    }
}
