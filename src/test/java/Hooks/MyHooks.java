package Hooks;

import java.time.Duration;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.cucumber.java.After;
import io.cucumber.java.AfterStep;
import io.cucumber.java.Before;
import io.cucumber.java.BeforeStep;
import io.cucumber.java.Scenario;


public class MyHooks extends DriverInstance {


    private String workingDir = System.getProperty("user.dir");
    private String myChromeDriverPath = workingDir + "/src/test/resources/drivers/chromedriver";
    private Duration timeoutInSeconds = Duration.ofSeconds(60);
    
    
    @Before("@webUI")
    public void beforeScenario(Scenario scenario) {
        System.setProperty("webdriver.chrome.driver", myChromeDriverPath);
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        wait = new WebDriverWait(driver, timeoutInSeconds);
    }

    @After("@webUI")
    public void afterScenario(Scenario scenario) {
        driver.quit();
    }

    @BeforeStep
    public void beforeStep(Scenario scenario) {

    }

    @AfterStep
    public void afterStep(Scenario scenario) {

    }

}
