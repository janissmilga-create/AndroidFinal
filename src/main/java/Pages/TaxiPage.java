package Pages;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import util.GlobalVariables;
import util.Helpers;

import java.util.Arrays;
import java.util.List;

public class TaxiPage {
    protected AndroidDriver driver;
    private final Helpers helpers = new Helpers();
    public TaxiPage(AndroidDriver driver) {
        this.driver = driver;
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
    }
    @AndroidFindBy(uiAutomator = "new UiSelector().text(\"Trusted taxis around the world\")")
    protected RemoteWebElement firstSwipe;
    @AndroidFindBy(uiAutomator = "new UiSelector().text(\"Hygiene measures for a safer ride\")")
    protected RemoteWebElement secondSwipe;
    @AndroidFindBy(uiAutomator = "new UiSelector().text(\"Free cancellation for total flexibility\")")
    protected RemoteWebElement thirdSwipe;
    @AndroidFindBy(uiAutomator = "new UiSelector().text(\"Live flight tracking for airport pick-ups\")")
    protected RemoteWebElement fourthSwipe;
    @AndroidFindBy(uiAutomator = "new UiSelector().resourceId(\"com.booking:id/got_it_button\")")
    protected RemoteWebElement gotItButton;
    @AndroidFindBy(uiAutomator = "new UiSelector().text(\"Where can we take you?\")")
    protected RemoteWebElement searchTaxis;


    public boolean taxiPageLoaded() {
        return new WebDriverWait(driver, GlobalVariables.globalTimeout).until(ExpectedConditions.visibilityOf(firstSwipe)).isDisplayed();
    }
    public boolean secondSwipeShown(){
        return new WebDriverWait(driver, GlobalVariables.globalTimeout).until(ExpectedConditions.visibilityOf(secondSwipe)).isDisplayed();
    }
    public boolean thirdSwipeShown(){
        return new WebDriverWait(driver, GlobalVariables.globalTimeout).until(ExpectedConditions.visibilityOf(thirdSwipe)).isDisplayed();
    }
    public boolean fourthSwipeShown(){
        return new WebDriverWait(driver, GlobalVariables.globalTimeout).until(ExpectedConditions.visibilityOf(fourthSwipe)).isDisplayed();
    }
    public void clickGotIt(){
        gotItButton.click();
    }
    public boolean searchTaxisShown(){
        return new WebDriverWait(driver, GlobalVariables.globalTimeout).until(ExpectedConditions.visibilityOf(searchTaxis)).isDisplayed();
    }

    public void swipeTaxis(){
        Dimension size = driver.manage().window().getSize();

        int startX = (int) (size.width * 0.9);
        int endX   = (int) (size.width * 0.1);
        int startY = size.height / 2;
        int endY   = startY;

        helpers.swipe(driver, startX, startY, endX, endY);
    }

}
