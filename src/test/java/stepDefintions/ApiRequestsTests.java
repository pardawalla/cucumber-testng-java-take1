package StepDefintions;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

import org.apache.hc.client5.http.fluent.Response;
import org.apache.hc.core5.http.ClassicHttpResponse;
import org.apache.hc.core5.http.ParseException;
import org.apache.hc.core5.http.io.entity.EntityUtils;
import org.testng.Assert;

import MyUtils.SysOutPrintlnColored;
import MyUtils.RestUtils;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class ApiRequestsTests {

    SysOutPrintlnColored printToConsole = new SysOutPrintlnColored();
    static RestUtils myRequest = new RestUtils();
    String apiEndpoint = null;
    String respString = null;
    Response respResponse;
    int statusCode;

    @Given("the API endpoint {string}")
    public void theApiEndpoint(String url) {
        apiEndpoint = url;
    }

    @When("I make a GET request")
    public void iMakeAGerRequest() {
        respString = RestUtils.getRequest(apiEndpoint);
        printToConsole.purplePrint(respString);
    }

    @Then("the response contains {string}")
    public void theResponseContains(String expectedValue) {
        // We're intereted if the charity exists. If the charity is there, it'll have to
        // be in the format `,"Description":"{Charity name}",`. So we search for the following
        // substring. This way we can make sure the value is from the `Description` key/value pair, and not from the
        // Tagline, ImageSource etc, key/value pair.
        String expectedCharityDescription = ",\"Description\":\"" + expectedValue + "\",";
        Assert.assertTrue(respString.contains(expectedCharityDescription));
    }

    @When("I make a POST request using the JSON data {string}")
    public void iMakeAPostRequestUsingTheJsonData(String jsonData) throws IOException, ParseException{
        printToConsole.purplePrint(jsonData);
        respResponse = RestUtils.postRequest(apiEndpoint, jsonData);
        ClassicHttpResponse httpResp = (ClassicHttpResponse) respResponse.returnResponse();
        statusCode = httpResp.getCode();
        String statusReasonPhrase = httpResp.getReasonPhrase();
        String responseBody = EntityUtils.toString(httpResp.getEntity(), StandardCharsets.UTF_8);
        String myStr = "\nStatus Code:" + statusCode + "\nreason phrase:" + statusReasonPhrase + "\n\n body:" + responseBody;
        printToConsole.purplePrint(myStr);
    }
    
    @Then("the POST request returns with the status code {string}")
    public void thePostRequestReturns(String expectedValue){
        String statusCodeStr = ""+statusCode;
        Assert.assertEquals(statusCodeStr, expectedValue);
    }

}
