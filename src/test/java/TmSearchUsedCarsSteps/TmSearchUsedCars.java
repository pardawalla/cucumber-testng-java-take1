package TmSearchUsedCarsSteps;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import Hooks.DriverInstance;
import io.cucumber.java.en.Given;
import MyUtils.SysOutPrintlnColored;

public class TmSearchUsedCars extends DriverInstance{
    
    SysOutPrintlnColored printToConsole = new SysOutPrintlnColored();

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

}
