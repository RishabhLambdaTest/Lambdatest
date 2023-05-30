package steps;

import net.thucydides.core.annotations.Steps;
import net.thucydides.core.steps.ScenarioSteps;
import pages.GooglePage;

public class GoogleSteps extends ScenarioSteps {

    @Steps
    GooglePage googlePage;

    //static Logger log = LogManager.getLogger(GoogleSteps.class);

    public void navigateToLoginPage() {
        googlePage.open();
        getDriver().manage().window().maximize();
    }

    public String getTitle(){
        return getDriver().getTitle();
    }
}
