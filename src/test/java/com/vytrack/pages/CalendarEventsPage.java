package com.vytrack.pages;

import com.vytrack.utilities.BrowserUtils;
import com.vytrack.utilities.Driver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class CalendarEventsPage extends BasePage {

    public CalendarEventsPage() {
        PageFactory.initElements(Driver.get(), this);
    }

    @FindBy(css = "[title='Create Calendar event']")
    public WebElement createCalendarEvent;

    @FindBy(css = ".btn-group.actions-group>div")
    public WebElement options;

    @FindBy(css = "input[type = 'number']")
    public WebElement pageNumber;

    @FindBy(xpath = "(//div[@class = 'btn-group'])[2]")
    public WebElement viewPerPage;

    @FindBy(css = ".fa-chevron-right.hide-text")
    public  WebElement pageArrowRight;

    @FindBy(css = ".pagination.pagination-centered label:nth-of-type(3)")
    public WebElement totalOfRecordsText;

    @FindBy(css=".grid-body tr")
    public List<WebElement> table_rows;

    @FindBy(css = ".pagination.pagination-centered label:nth-of-type(2)")
    public WebElement totalNumberOfPages;

    @FindBy(xpath =  "//tr//td//input[@type='checkbox']")
    public List<WebElement> checkboxes;

    @FindBy(css = ".btn.btn-default.btn-small.dropdown-toggle")
    public WebElement checkbox;

    @FindBy(css = ".dropdown-menu.dropdown-menu__select-all-header-cell.detach.dropdown-menu__floating>li>a")
    public WebElement checkboxAll;

    @FindBy(css = ".responsive-block div.control-group.attribute-row")
    public List<WebElement> testersMeetingInfo;

    @FindBy(xpath = "//td[@data-column-label = 'Title'][text() = 'Testers meeting']")
    public WebElement testerMeetingLink;
    public int findNumberOfRows(){
        int pages = Integer.parseInt(totalNumberOfPages.getAttribute("innerHTML").split(" ")[1].split("&")[0]);
        int rows_number = 0;
        for(int i = 1; i<=pages; i++){
            rows_number+= table_rows.size();
            if( i != pages) {
                BrowserUtils.clickWithTimeOut(pageArrowRight, 5);
                BrowserUtils.waitFor(2);
            }

        }

        return  rows_number;
    }
    public boolean compareEvents(){
        int recordsNumber = Integer.parseInt(totalOfRecordsText.getAttribute("innerHTML").split(" ")[2]);
        boolean flag = false;
        if(recordsNumber == findNumberOfRows()){
            flag = true;
        }
        return flag;
    }
    public boolean checkBoxes(){
        BrowserUtils.clickWithTimeOut(checkbox, 5);
        BrowserUtils.clickWithTimeOut(checkboxAll, 5);
        boolean flag = true;
        for (WebElement checkbox : checkboxes) {
            if(!checkbox.isSelected()){
                flag = false;
            }
        }
        return flag;
    }
    public boolean testerMeeting(){
        testerMeetingLink.click();
        String[] info = {"Title\nTesters meeting", "Description\nThis is a a weekly testers meeting", "Start\nNov 27, 2019, 9:30 AM",
                "End\nNov 27, 2019, 10:30 AM", "All-Day Event\nNo", "Organizer\nJohn Doe",
                "Call Via Hangout\nNo"};
        boolean flag = true;
        for (int i = 0; i < info.length; i++) {
            String s = info[i];
            if(!s.equals(testersMeetingInfo.get(i).getText())){
                flag = false;
            }
        }
        return flag;
    }
}
