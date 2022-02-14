package com.vytrack.step_definitions;

import com.vytrack.pages.DashBoardPage;
import com.vytrack.pages.LoginPage;
import com.vytrack.utilities.BrowserUtils;
import com.vytrack.utilities.ConfigurationReader;
import com.vytrack.utilities.Driver;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.util.Map;

public class ContactsStepDefs {
    @Given("the user logged in as {string}")
    public void the_user_logged_in_as(String userType) {
        Driver.get().get(ConfigurationReader.get("url"));
        LoginPage loginPage = new LoginPage();
        String username = null;
        String password = null;

        if (userType.equals("driver")) {
            username = ConfigurationReader.get("driver_username");
            password = ConfigurationReader.get("driver_password");
        } else if (userType.equals("sales manager")) {
            username = ConfigurationReader.get("sales_manager_username");
            password = ConfigurationReader.get("sales_manager_password");
        } else if (userType.equals("store manager")) {
            username = ConfigurationReader.get("store_manager_username");
            password = ConfigurationReader.get("store_manager_password");
        }
        loginPage.login(username, password);
    }
    @Then("the user should see following options")
    public void the_user_should_see_following_options(List<String> menuOptions) {
        new DashBoardPage().waitUntilLoaderScreenDisappear();
        List<String> actualOptions = BrowserUtils.getElementsText(new DashBoardPage().menuOptions);
        Assert.assertEquals(menuOptions, actualOptions);
    }
    @When("the user loggs in using following credentials")
    public void the_user_loggs_in_using_following_credentials(Map<String,String> userInfo) {
    //use map information to login and also verify firstname and lastname
        String username = userInfo.get("username");
        String password = userInfo.get("password");
        new LoginPage().login(username, password);
        String actualName = new DashBoardPage().userName.getText();
        String expectedName = userInfo.get("firstname") + " " + userInfo.get("lastname");
        Assert.assertEquals(expectedName, actualName);
    }
}
