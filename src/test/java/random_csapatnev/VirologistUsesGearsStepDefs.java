package random_csapatnev;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import random_csapatnev.modelclasses.*;

import static org.junit.Assert.assertEquals;

class CouldHeUseGears{
    static String couldHeUseGears(boolean usageSuccess) {
        return usageSuccess ? "Yes I could" : "No I couldn't";
    }
}

public class VirologistUsesGearsStepDefs{
    private final Virologist virologist = new Virologist("Virologist");
    private Gear gear;
    private String actualAnswer;
    private String actualGear;

    @Given("Virologist has Gloves")
    public void virologist_has_gloves(){
        gear = new Gloves();
        actualGear = gear.getName().toString();
        virologist.addGear(gear);
    }

    @Given("Virologist has a Cloak")
    public void virologist_has_a_cloak(){
        gear = new Cloak();
        actualGear = gear.getName().toString();
        virologist.addGear(gear);
    }

    @Given("Virologist has an Axe")
    public void virologist_has_an_axe(){
        gear = new Axe();
        actualGear = gear.getName().toString();
        virologist.addGear(gear);
    }

    @Given("Virologist has a Sack")
    public void virologist_has_a_sack(){
        gear = new Sack();
        actualGear = gear.getName().toString();
        virologist.addGear(gear);
    }

    @Given("Virologist does not have Gloves")
    public void virologist_does_not_have_gloves(){
        actualGear = "GLOVES";
    }

    @Given("Virologist has less than 3 Gears equipped")
    public void virologist_has_less_then_3_gears_equipped(){}

    @Given("Virologist has more than 3 Gears equipped")
    public void virologist_has_more_then_3_gears_equipped(){
        Gear gear1 = new Cloak();
        Gear gear2 = new Cloak();
        Gear gear3 = new Cloak();
        virologist.addGear(gear1);
        virologist.addGear(gear2);
        virologist.addGear(gear3);
        virologist.equipGear(GearEnum.CLOAK);
        virologist.equipGear(GearEnum.CLOAK);
        virologist.equipGear(GearEnum.CLOAK);
    }

    @When("I ask if he could equip Gloves")
    public void ask_whether_he_could_use(){
        virologist.equipGear(GearEnum.GLOVES);
        boolean result = false;
        if(virologist.getActiveGears().contains(gear) && virologist.getIsGloved()){
            result = true;
        }

        actualAnswer = CouldHeUseGears.couldHeUseGears(result);
    }

    @When("I ask if he could equip Cloak")
    public void ask_whether_he_could_use_cloak(){
        virologist.equipGear(GearEnum.CLOAK);
        boolean result = false;
        if(virologist.getActiveGears().contains(gear) && virologist.getIsCloaked()){
            result = true;
        }
        
        actualAnswer = CouldHeUseGears.couldHeUseGears(result);
    }

    @When("I ask if he could equip Axe")
    public void ask_whether_he_could_use_axe(){
        virologist.equipGear(GearEnum.AXE);
        boolean result = false;
        if(virologist.getActiveGears().contains(gear)){
            result = true;
        }
        
        actualAnswer = CouldHeUseGears.couldHeUseGears(result);
    }

    @When("I ask if he could equip Sack")
    public void ask_whether_he_could_use_sack(){
        virologist.equipGear(GearEnum.SACK);
        boolean result = false;
        if(virologist.getActiveGears().contains(gear)){
            result = true;
        }
        
        actualAnswer = CouldHeUseGears.couldHeUseGears(result);
    }

    @Then("He should answer {string} equip {string}")
    public void he_should_answer(String expectedAnswer, String expectedGear){
        assertEquals(expectedAnswer, actualAnswer);
        assertEquals(expectedGear, actualGear);
    }
}