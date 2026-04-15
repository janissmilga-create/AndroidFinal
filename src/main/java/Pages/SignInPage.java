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

public class SignInPage {
    protected AndroidDriver driver;
    private final Helpers helpers = new Helpers();


    @AndroidFindBy(uiAutomator = "new UiSelector().description(\"Sign in\")")
    protected RemoteWebElement signInButton;
    @AndroidFindBy(uiAutomator = "new UiSelector().resourceId(\"com.booking:id/facet_profile_header_sign_in_cta\")")
    protected RemoteWebElement buttonOnSignUpPage;
    @AndroidFindBy(uiAutomator = "new UiSelector().text(\"Airport taxis\")")
    protected RemoteWebElement taxiPageButton;

    public SignInPage(AndroidDriver driver) {
        this.driver = driver;
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
    }
    public void goToSignIn(){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.elementToBeClickable(signInButton)).click();
    }
    public boolean signInPageLoaded() {
        return new WebDriverWait(driver, GlobalVariables.globalTimeout).until(ExpectedConditions.visibilityOf(buttonOnSignUpPage)).isDisplayed();
    }
    public void goToTaxis(){
    helpers.scrollToElementByDescription(driver, "Airport taxis");
    taxiPageButton.click();
    }
}
