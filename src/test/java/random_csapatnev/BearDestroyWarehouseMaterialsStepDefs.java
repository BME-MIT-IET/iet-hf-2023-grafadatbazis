package random_csapatnev;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import random_csapatnev.modelclasses.*;

import static org.junit.Assert.assertEquals;

import java.util.Map;

class CouldHeDestoryMaterials{
    static String couldHeDestoryMaterials(boolean success) {
        return success ? "Yes I could" : "No I couldn't";
    }
}

public class BearDestroyWarehouseMaterialsStepDefs{
    private Bear bear = new Bear("Bear");
    private Field field = new Field(0,0);
    private Warehouse warehouse = new Warehouse(0,0);
    private String actualAnswer;

    @Given("Bear is on a Warehouse")
    public void bear_is_on_a_warehouse(){
        bear.setCurrField(warehouse);
    }

    @Given("Bear is not on a Warehouse")
    public void bear_is_not_on_a_warehouse(){
        bear.setCurrField(field);
    }

    @When("I ask if he could destroy the materials")
    public void ask_whether_he_could_destroy(){
        boolean result = false;
        bear.fieldInteract();

        Map<MatEnum, Integer> mat = warehouse.getMaterial().getContainer();
        if(mat.get(MatEnum.AMINOACID) == 0 && mat.get(MatEnum.NUCLEOTIDE) == 0)
        {
            result = true;
        }
        actualAnswer = CouldHeDestoryMaterials.couldHeDestoryMaterials(result);
    }

    @Then("He should answer {string} destroy the materials")
    public void he_should_answer(String expectedAnswer){
        assertEquals(expectedAnswer, actualAnswer);
    }
}