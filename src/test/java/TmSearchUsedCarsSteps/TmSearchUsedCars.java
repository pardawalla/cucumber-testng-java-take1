package TmSearchUsedCarsSteps;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import Hooks.DriverInstance;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import MyUtils.SysOutPrintlnColored;

public class TmSearchUsedCars extends DriverInstance {

    SysOutPrintlnColored printToConsole = new SysOutPrintlnColored();

    // helper methods
    private Duration timeoutInSeconds = Duration.ofSeconds(240);
    private Duration pollingTimeInMilliseconds = Duration.ofMillis(10);

    private void waitUntilSelectOptionsPopulated(final Select select) {
        new FluentWait<WebDriver>(driver)
                .withTimeout(timeoutInSeconds)
                .pollingEvery(pollingTimeInMilliseconds)
                .until(new Function<WebDriver, Boolean>() {
                    public Boolean apply(WebDriver d) {
                        return (select.getOptions().size() > 1);
                    }

                });
    }

    @Given("I am on the TradeMe Motors search page")
    public void iAmOnTheTradeMeMotorsSearchPage() {

        String trademeMotorsUrl = "https://www.trademe.co.nz/a/motors";
        String trademeMotorsSiteTitle = "Cars And Vehicles For Sale | Trade Me Motors";
        driver.get(trademeMotorsUrl);
        driver.findElement(By.linkText("Motors")).click();
        wait.until(ExpectedConditions.titleContains(trademeMotorsSiteTitle));
        String actualTitle = driver.getTitle();
        printToConsole.purplePrint(actualTitle);
        Assert.assertEquals(actualTitle, trademeMotorsSiteTitle);
    }

    @When("I search for used cars based on the criteria")
    public void iSearchForUsedCarsBasedOnTheCriteria(List<Map<String, String>> criteria) {

        // References:
        // https://stackoverflow.com/questions/8882295/how-to-get-the-first-element-of-the-list-or-set

        String keywordsText = criteria.get(0).get("Keywords");
        String makeText = criteria.get(0).get("Make");
        String modelText = criteria.get(0).get("Model");
        printToConsole.purplePrint("Keywords: " + keywordsText);
        printToConsole.purplePrint("Make: " + makeText);
        printToConsole.purplePrint("Model: " + modelText);
        String bodyStyleStr = criteria.get(0).get("Body style");
        printToConsole.purplePrint("Body style: " + bodyStyleStr);
        List<String> bodyStyleText = new ArrayList<String>(Arrays.asList(bodyStyleStr.split(",")));
        for (String s : bodyStyleText) {
            printToConsole.purplePrint(s.trim());
        }

        driver.findElement(By.linkText("Used")).click();
        // Enter blue for keywords
        WebElement keywords = driver.findElement(By.name("keyword"));
        keywords.sendKeys(keywordsText);
        // select Honda as the Make
        WebElement make = driver.findElement(By.name("selectedMake"));
        Select makeChosen = new Select(make);
        makeChosen.selectByVisibleText(makeText);

        // Select Civic as the Model
        WebElement model = driver.findElement(By.name("searchParams.model"));
        Select modelChosen = new Select(model);
        waitUntilSelectOptionsPopulated(modelChosen);
        modelChosen.selectByVisibleText(modelText);

        // Select Body Types
        WebElement bodyStyle = driver
                .findElement(By.className("tm-motors-search-bar__dropdown-multi-select-text"));
        // Expand the Body Style drop-down box
        bodyStyle.click();
        // Select all the options needed
        for (String tmpStr : bodyStyleText) {
            String xpathStr = "//span[text()=' " + tmpStr.trim() + " ']";
            printToConsole.purplePrint(xpathStr);
            driver.findElement(By.xpath(xpathStr)).click();
        }

        // Close the Body Style drop-down box
        bodyStyle.click();

        // Click the Search button
        driver.findElement(By.xpath("//button[@type='submit']")).click();

        wait.until(ExpectedConditions.presenceOfElementLocated(
                By.xpath("//h3[@class='tm-search-header-result-count__heading ng-star-inserted']")));
        String headerText = driver
                .findElement(By.xpath("//h3[@class='tm-search-header-result-count__heading ng-star-inserted']"))
                .getText();
        // Assumptions: 
        // 1. For Keywords number plate or a unique phrase was used, so that that only one search result is returned. As the
        // If multiple results are returned, it becomes harder to find the listing we are interested in as the listing order by the search
        // cannot be always guaranteed.
        // 2. The listing exists.
        // Thus we are confirming here that only 1 search result was returned. 
        Assert.assertTrue(headerText.contains("Showing 1 result"));
    }

           
    @Then("on viewing the search result I can see the following info")
    public void onViewingTheSearchResultICanSeeTheFollowingInfo(List<Map<String,String>> expectedValue) {
        // Click on the single search resutl returned
        driver.findElement(By.xpath("(//div[@class='o-card']//a)[1]")).click();
        // confirm that listing page has been loaded by verifying that the "Ask a question" button is displayed
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//a[@id='mtf_enquiry_click_through'])[2]")));

        String numberPlateExpected = expectedValue.get(0).get("Number plate");
        String kilometersExpected = expectedValue.get(0).get("Kilometers");
        String bodyExpected = expectedValue.get(0).get("Body style");
        String seatsExpected = expectedValue.get(0).get("Seats");
        
        WebElement numberPlate = driver.findElement(By.xpath(("(//span[@class='o-tag__content']//div)[7]")));
        String actualNumberPlate = numberPlate.getText().trim();
        printToConsole.purplePrint(actualNumberPlate);
        // Using assert true, as sometimes the text contain the string "Number plate"
        // and sometimes it doesn't :/
        Assert.assertTrue(actualNumberPlate.contains(numberPlateExpected));

        WebElement vehicleOdometer = driver.findElement(By.xpath(("(//span[@class='o-tag__content']//div)[1]")));
        String actualKilometers = vehicleOdometer.getText().trim();
        printToConsole.purplePrint(actualKilometers);
        // Assert.assertTrue(actualKilometers.contains(kilometersExpected));
        // The km text is always returned as a comma seperated number. When writing the test data, we can either
        // enter the expected value as `39,000` or `39000` km. The code below removes the `,` from the number 
        // string and than simply compares the number string without the `,` seperator.
        String actualKmNum = actualKilometers.replaceAll("[^0-9]", "");
        printToConsole.purplePrint("ACTUAL km number string: " + actualKmNum);
        String expectedKmNum = kilometersExpected.replaceAll("[^0-9]", "");
        printToConsole.purplePrint("EXPECTED km number string: " + expectedKmNum);
        Assert.assertEquals(actualKmNum, expectedKmNum);

        WebElement body = driver.findElement(By.xpath(("(//span[@class='o-tag__content']//div)[2]")));
        String actualBody = body.getText().trim();
        printToConsole.purplePrint(actualBody);
        Assert.assertTrue(actualBody.contains(bodyExpected));

        WebElement seats = driver.findElement(By.xpath(("(//span[@class='o-tag__content']//div)[3]")));
        String actualSeats = seats.getText().trim();
        printToConsole.purplePrint(actualSeats);
        Assert.assertTrue(actualSeats.contains(seatsExpected));
    }

}
