package util;

import Pages.LocationSelect;
import Pages.MainPage;
import Pages.SignInPage;
import Pages.TaxiPage;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.reporters.jq.Main;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;

public class DriverSetup {

    protected AndroidDriver driver;
    protected final AppiumServerManager appiumServerManager = new AppiumServerManager();

    protected MainPage mainPage;
    protected SignInPage signInPage;
    protected TaxiPage taxiPage;
    protected LocationSelect locationSelect;

    @BeforeSuite
    public void startAppiumServer() {
        appiumServerManager.startAppiumServer();
    }

    @BeforeMethod
    public void setUp() {

        UiAutomator2Options options = new UiAutomator2Options();
        options.setAvd(ConfigReader.getProperty("avd"))
                .setApp(ConfigReader.getProperty("app"))
                .setAppPackage(ConfigReader.getProperty("appPackage"))
                //.setAppActivity("appActivity")
                .setNoReset(false)
                .setFullReset(true);

        try {
            driver = new AndroidDriver(new URI(GlobalVariables.appiumLocalUrl).toURL(), options);
        } catch (MalformedURLException | URISyntaxException e) {
            throw new RuntimeException(e);
        }

        mainPage = new MainPage(driver);
        signInPage = new SignInPage(driver);
        taxiPage = new TaxiPage(driver);
        locationSelect = new LocationSelect(driver);

    }

    @AfterMethod(alwaysRun = true)
    public void tearDown() {
        if (driver != null)
            driver.quit();
    }

    @AfterSuite(alwaysRun = true)
    public void stopAppiumServer() {
        appiumServerManager.stopAppiumServer();
    }
}
