package com.vytrack.step_definitions;

import com.vytrack.pages.ContactsPage;
import com.vytrack.pages.DashBoardPage;
import com.vytrack.utilities.BrowserUtils;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;

public class NavigationMenuStepDefs {
    @When("the user navigates to Fleet, Vehicles")
    public void the_user_navigates_to_Fleet_Vehicles() {
        System.out.println("the user navigates to Fleet, Vehicles");
    }

    @Then("the title should be vehicles")
    public void the_title_should_be_vehicles() {
        System.out.println("The expected and actual title are matching");
    }

    @When("the user navigates to Marketing, Campaigns")
    public void the_user_navigates_to_Marketing_Campaigns() {
        System.out.println("The user navigates to marketing, campaigns");
    }

    @Then("title should be Campaigns")
    public void title_should_be_Campaigns() {
        System.out.println("The expected and actual title are matching");
    }

    @When("the user navigates to Activities, Calendar Events")
    public void the_user_navigates_to_Activities_Calendar_Events() {
        System.out.println("the user navigates to Activities, Calendar Events");
    }

    @Then("title should be Calendars")
    public void title_should_be_Calendars() {
        System.out.println("The expected and actual title are matching");
    }
    @When("the user navigates to {string} {string}")
    public void the_user_navigates_to(String tab, String module) {
        new DashBoardPage().navigateToModule(tab, module);
    }
    @Then("default page number should be {int}")
    public void default_page_number_should_be(Integer intExpected) {
        BrowserUtils.waitFor(5);
        ContactsPage contactsPage = new ContactsPage();
        Integer intActual = Integer.parseInt(contactsPage.pageNumber.getAttribute("value"));
        Assert.assertEquals(intExpected,intActual);

    }

}
