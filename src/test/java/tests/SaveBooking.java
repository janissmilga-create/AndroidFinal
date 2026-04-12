package tests;

import data.TestData;
import org.testng.Assert;
import org.testng.annotations.Test;
import util.DriverSetup;

public class SaveBooking extends DriverSetup {

    @Test(dataProviderClass = TestData.class, dataProvider ="bookingData", testName = "Saved booking test")
    public void saveBooking(String startDate, String endDate){
        mainPage.skipPopups();
        Assert.assertTrue(mainPage.mainPageLoaded(), "Main page is not loaded");
        locationSelect.goToSearch();
        locationSelect.searchSkopje();
        locationSelect.chooseDates(startDate,endDate);
        locationSelect.adjustRooms();
        String expectedDates = locationSelect.formatDateRange(startDate, endDate);
        Assert.assertTrue(locationSelect.datesAndLocation(expectedDates), "Correct date or locations is not shown");
        locationSelect.saveProperty();
        Assert.assertTrue(locationSelect.isPropertySaved(), "No property has been saved");

    }


}
