package tests;

import Pages.TaxiPage;
import org.testng.Assert;
import org.testng.annotations.Test;
import util.DriverSetup;

public class AirportTaxi extends DriverSetup {

    @Test(testName = "Airport taxis test")
    public void airPortTaxi() {
        mainPage.skipPopups();
        Assert.assertTrue(mainPage.mainPageLoaded(), "Main page is not loaded");
        signInPage.goToSignIn();
        Assert.assertTrue(signInPage.signInPageLoaded(), "Sign in page is not loaded");
        signInPage.goToTaxis();
        Assert.assertTrue(taxiPage.taxiPageLoaded(), "Taxi Page not loaded");
        taxiPage.swipeTaxis();
        Assert.assertTrue(taxiPage.secondSwipeShown(), "Second swipe is not loaded");
        taxiPage.swipeTaxis();
        Assert.assertTrue(taxiPage.thirdSwipeShown(), "Third swipe is not shown");
        taxiPage.swipeTaxis();
        Assert.assertTrue(taxiPage.fourthSwipeShown(), "Fourth swipe is not shown");
        taxiPage.clickGotIt();
        taxiPage.searchTaxisShown();

    }
}
