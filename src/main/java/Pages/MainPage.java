package Pages;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

import org.openqa.selenium.remote.RemoteWebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import util.GlobalVariables;
import util.Helpers;

import java.time.Duration;

public class MainPage {
    private final Helpers helpers = new Helpers();
    protected AndroidDriver driver;

    @AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.view.View\").instance(7)")
    protected RemoteWebElement mainPage;
    @AndroidFindBy(id="com.booking:id/bt_accept")
    protected RemoteWebElement acceptCookies;
    @AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.view.View\").instance(2)")
    protected RemoteWebElement declineNotifications;
    @AndroidFindBy(accessibility = "Navigate up")
    protected RemoteWebElement noSignIn;
    @AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.view.View\").instance(3)")
    protected RemoteWebElement signInPopup;

    public MainPage(AndroidDriver driver) {
        this.driver = driver;
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
    }
    public boolean mainPageLoaded() {
        return new WebDriverWait(driver, GlobalVariables.globalTimeout).until(ExpectedConditions.visibilityOf(mainPage)).isDisplayed();
    }
    public void skipPopups() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.elementToBeClickable(acceptCookies)).click();
        wait.until(ExpectedConditions.elementToBeClickable(declineNotifications)).click();
        noSignIn.click();
        wait.until(ExpectedConditions.visibilityOf(signInPopup));
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        helpers.tapByCoordinates(driver, 550, 450);

    }

}
