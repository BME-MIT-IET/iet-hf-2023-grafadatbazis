package random_csapatnev;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import static org.junit.Assert.assertEquals;

class CouldHeUseGears{
    static String couldHeUseGears(boolean usageSuccess) {
        return usageSuccess ? "Yes I could" : "No I couldn't";
    }
}

public class VirologistUsesGearsStepDefs{
    private Virologist virologist = new Virologist("Virologist");
    Gear gear = new Gloves();
    private String actualAnswer;

    @Given("Virologist has the Gear")
    public void virologist_has_gear(){
        virologist.gears.add(gear);
    }

    @Given("Virologist does not have the Gear")
    public void virologist_does_not_have_gear(){}

    @Given("Virologist has less then 3 Gears equipped")
    public void virologist_has_less_then_3_gears_equipped(){}

    @Given("Virologist has more then 3 Gears equipped")
    public void virologist_has_more_then_3_gears_equipped(){
        Gear gear1 = new Cloak();
        Gear gear2 = new Cloak();
        Gear gear3 = new Cloak();
        virologist.gears.add(gear1);
        virologist.gears.add(gear2);
        virologist.gears.add(gear3);
        virologist.equipGear(GearEnum.CLOAK);
        virologist.equipGear(GearEnum.CLOAK);
        virologist.equipGear(GearEnum.CLOAK);
    }

    @When("I ask if he could equip Gear")
    public void ask_whether_he_could_use(){
        virologist.equipGear(GearEnum.GLOVES);
        boolean result = false;
        if(virologist.activeGears.contains(gear) && virologist.isGloved == true){
            result = true;
        }
        
        actualAnswer = CouldHeUseGears.couldHeUseGears(result);
    }

    @Then("He should answer {string} equip Gear")
    public void he_should_answer(String expectedAnswer){
        assertEquals(expectedAnswer, actualAnswer);
    }
}