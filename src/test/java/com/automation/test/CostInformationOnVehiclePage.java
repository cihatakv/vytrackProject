package com.automation.test;

import com.automation.utilities.BrowserUtils;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class CostInformationOnVehiclePage {


    // Verify that store manager or Sales manager should be able to see all car's cost information on on the Vehicle Costs page
    // https://qa2.vytrack.com/user/login
    //
    // Truck Drivers		user45 , user46 , user47								UserUser123
    // Store Manager		storemanager81 , storemanager82							UserUser123
    // Sales Manager		salesmanager146 , salesmanager147 , salesmanager148		UserUser123

    private WebDriver driver;
    private String URL = "https://qa2.vytrack.com/user/login";

    private By usernameBy = By.id("prependedInput");
    private By passwordBy = By.id("prependedInput2");
    private By submitBy = By.id("_submit");

    private String truckDriverUsername = "user45";
    private String truckDriverPassword = "UserUser123";

    private String storeManagerUsername = "storemanager81";
    private String storeManagerPassword = "UserUser123";

    private String salesManagerUsername = "salesmanager146";
    private String salesManagerPassword = "UserUser123";

    private By fleetBy = By.xpath("//span[@class='title title-level-1' and contains(text(),'Fleet')]");
    private By vehicleCostBy = By.xpath("//span[@class='title title-level-2' and contains(text(),'Vehicle Cost')]");

    private Actions actions;

    @Test(description = "Verify that store manager or Sales manager should be able to see all car's cost information on on the Vehicle Costs page")
    public void verifyStoreMangerVehicleCostPage (){
        actions = new Actions(driver);
        driver.findElement(usernameBy).sendKeys(storeManagerUsername);
        driver.findElement(passwordBy).sendKeys(storeManagerPassword);
        driver.findElement(submitBy).click();
        BrowserUtils.wait(2);
        actions.moveToElement(driver.findElement(fleetBy)).perform();
        BrowserUtils.wait(1);
        driver.findElement(By.xpath("//span[@class='title title-level-2' and contains(text(), 'Vehicle Cost')]")).click();
        BrowserUtils.wait(2);
        List<WebElement> table = driver.findElements(By.xpath("//tbody//tr//td[@class='number-cell grid-cell grid-body-cell grid-body-cell-TotalPrice']"));
        System.out.println("List<WebElement> table = driver.findElements(By.xpath(\"//tbody//tr//td[@class='number-cell grid-cell grid-body-cell grid-body-cell-TotalPrice']\"));");
        boolean isTableHas$ = false;

        for (WebElement webElement : table) {
            System.out.println("webElement.getText()");
            if (webElement.getText().contains("$")){
                isTableHas$ = true;
            }
        }
        Assert.assertTrue(isTableHas$);
    }

    @BeforeMethod
    public void setup() {
        WebDriverManager.chromedriver().version("79").setup();
        driver = new ChromeDriver();
        driver.get(URL);
//        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        BrowserUtils.wait(2);
        //driver.findElement(registration_formBy).click();
        BrowserUtils.wait(2);
    }

    @AfterMethod
    public void teardown() {
        // if webDriver object is alive
        if (driver != null) {
            // close the browser, close session
             driver.quit();
            // destroy webDriver object for sure
            driver = null;
        }
    }
}
