package stepDefintions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class example {
    @Given("today is Sunday")
    public void todayIsSunday() {
        System.out.println("Given step successfully mapped!");
    }

    @When("I ask whether it's Friday yet")
    public void iAskWhetherItIsFridayYet() {
        System.out.println("When step successfully mapped!");
    }

    @Then("I should be told {string}")
    public void iShouldBeTold(String string) {
        System.out.println("When step successfully mapped!");
    }
}
