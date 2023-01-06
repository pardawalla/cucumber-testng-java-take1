package stepDefintions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class example {

    // Reference https://stackoverflow.com/questions/5762491/how-to-print-color-in-console-using-system-out-println#:~:text=If%20the%20console%20support%20(e.g.,for%20one%20color%20and%20System.
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_BLACK = "\u001B[30m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_PURPLE = "\u001B[35m";
    public static final String ANSI_CYAN = "\u001B[36m";
    public static final String ANSI_WHITE = "\u001B[37m";

    @Given("today is Sunday")
    public void todayIsSunday() {
        System.out.println(ANSI_PURPLE + "\n\nGiven step successfully mapped!\n\n" + ANSI_RESET);
        // got the idea from https://stackoverflow.com/questions/3302060/system-out-println-not-functioning
        System.err.println("\n\n using System.err.Given step successfully mapped!\n\n");
    }

    @When("I ask whether it's Friday yet")
    public void iAskWhetherItIsFridayYet() {
        System.out.println("When step successfully mapped!");
    }

    @Then("I should be told {string}")
    public void iShouldBeTold(String string) {
        System.out.println(ANSI_PURPLE + "\n\nThen step successfully mapped!\n\n" + ANSI_RESET);
    }
}
