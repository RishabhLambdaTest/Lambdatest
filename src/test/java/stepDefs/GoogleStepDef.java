package stepDefs;

import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.thucydides.core.annotations.Steps;
import org.junit.Assert;
import org.openqa.selenium.JavascriptExecutor;
import steps.GoogleSteps;

public class GoogleStepDef {

    Scenario scenario;

    @Steps
    GoogleSteps googleSteps;

    @Before
    public void before(Scenario scenario) {
        this.scenario = scenario;
        System.out.println("-----------****************--------"+scenario.getName());
    }

    @Given("user launch browser")
    public void user_launch_browser() {
        System.out.println("Launched");
        googleSteps.navigateToLoginPage();
        ((JavascriptExecutor)googleSteps.getDriver()).executeScript("lambda-name="+scenario.getName());
    }

    @When("user enter url")
    public void user_enter_url() {
        System.out.println("URL entered");
    }

    @Then("verify title")
    public void verify_title() {
        System.out.println("Title verified");
        String title = googleSteps.getTitle();
        Assert.assertEquals("Google",title);

    }

}