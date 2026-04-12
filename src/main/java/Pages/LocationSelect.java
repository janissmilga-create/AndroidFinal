package Pages;

import io.appium.java_client.AppiumBy;
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
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class LocationSelect {
    protected AndroidDriver driver;
    private final Helpers helpers = new Helpers();


    @AndroidFindBy(uiAutomator = "new UiSelector().description(\"Enter your destination\")")
    protected RemoteWebElement locationSearchButton;
    @AndroidFindBy(uiAutomator = "new UiSelector().resourceId(\"com.booking:id/facet_with_bui_free_search_booking_header_toolbar_content\")")
    protected RemoteWebElement locationSearchField;
    @AndroidFindBy(uiAutomator = "new UiSelector().text(\"City in North Macedonia\")")
    protected RemoteWebElement selectCity;
    @AndroidFindBy(uiAutomator = "new UiSelector().resourceId(\"com.booking:id/facet_date_picker_confirm\")")
    protected RemoteWebElement confirmDates;
    @AndroidFindBy(accessibility = "1 room · 2 adults · 0 children")
    protected RemoteWebElement changeRoomSize;
    @AndroidFindBy(uiAutomator = "new UiSelector().resourceId(\"com.booking:id/bui_input_stepper_add_button\").instance(0)")
    protected RemoteWebElement addRooms;
    @AndroidFindBy(uiAutomator = "new UiSelector().resourceId(\"com.booking:id/bui_input_stepper_add_button\").instance(1)")
    protected RemoteWebElement addAdults;
    @AndroidFindBy(uiAutomator = "new UiSelector().resourceId(\"com.booking:id/group_config_apply_button\")")
    protected RemoteWebElement confirmRoom;
    @AndroidFindBy(uiAutomator = "new UiSelector().text(\"Search\").instance(0)")
    protected RemoteWebElement searchButton;
    @AndroidFindBy(uiAutomator = "new UiSelector().resourceId(\"com.booking:id/searchbox_destination\").text(\"Skopje\")")
    protected RemoteWebElement destinationShown;
    @AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.ImageView\").instance(3)")
    protected RemoteWebElement openFirstResult;
    @AndroidFindBy(accessibility = "Add to list")
    protected RemoteWebElement heartProperty;
    @AndroidFindBy(accessibility = "Navigate up")
    protected RemoteWebElement backButton;
    @AndroidFindBy(uiAutomator = "new UiSelector().description(\"Saved\")")
    protected RemoteWebElement savedTab;
    @AndroidFindBy(uiAutomator = "new UiSelector().resourceId(\"com.booking:id/wishlist_properties_number\")")
    protected RemoteWebElement propertyIsSaved;

    public LocationSelect(AndroidDriver driver) {
        this.driver = driver;
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
    }
    public void goToSearch(){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.elementToBeClickable(locationSearchButton)).click();
    }
    public void searchSkopje(){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.elementToBeClickable(locationSearchField)).sendKeys("Skopje");
        wait.until(ExpectedConditions.elementToBeClickable(selectCity)).click();
    }
    public void chooseDates(String start, String end){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));

        RemoteWebElement startDate = (RemoteWebElement) driver.findElement(
                AppiumBy.androidUIAutomator("new UiSelector().description(\"" + start + "\")")
        );

        RemoteWebElement endDate = (RemoteWebElement) driver.findElement(
                AppiumBy.androidUIAutomator("new UiSelector().description(\"" + end + "\")")
        );

        wait.until(ExpectedConditions.elementToBeClickable(startDate)).click();
        wait.until(ExpectedConditions.elementToBeClickable(endDate)).click();
        wait.until(ExpectedConditions.elementToBeClickable(confirmDates)).click();
    }
    public void adjustRooms(){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.elementToBeClickable(changeRoomSize)).click();
        wait.until(ExpectedConditions.elementToBeClickable(addRooms)).click();
        addAdults.click();
        confirmRoom.click();
        wait.until(ExpectedConditions.elementToBeClickable(searchButton)).click();
    }
    public boolean datesAndLocation(String expectedDates) {
        return new WebDriverWait(driver, GlobalVariables.globalTimeout).until(driver ->
                destinationShown.isDisplayed() && driver.findElement(AppiumBy.androidUIAutomator("new UiSelector().text(\"" + expectedDates + "\")")).isDisplayed());
    }
    public void saveProperty(){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.elementToBeClickable(openFirstResult)).click();
        wait.until(ExpectedConditions.elementToBeClickable(heartProperty)).click();
        wait.until(ExpectedConditions.elementToBeClickable(backButton)).click();
        wait.until(ExpectedConditions.elementToBeClickable(backButton)).click();
        wait.until(ExpectedConditions.elementToBeClickable(savedTab)).click();

    }

    public boolean isPropertySaved() {
        return new WebDriverWait(driver, GlobalVariables.globalTimeout).until(ExpectedConditions.visibilityOf(propertyIsSaved)).isDisplayed();
    }
    public String formatDateRange(String start, String end) {
        DateTimeFormatter inputFormat = DateTimeFormatter.ofPattern("d MMMM yyyy", Locale.ENGLISH);
        DateTimeFormatter outputFormat = DateTimeFormatter.ofPattern("MMM d", Locale.ENGLISH);

        LocalDate startDate = LocalDate.parse(start, inputFormat);
        LocalDate endDate = LocalDate.parse(end, inputFormat);

        return outputFormat.format(startDate) + " - " + outputFormat.format(endDate);
    }
}
