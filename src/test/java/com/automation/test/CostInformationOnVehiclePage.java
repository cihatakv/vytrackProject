package com.automation.test;

import com.automation.utilities.BrowserUtils;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

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








    @BeforeMethod
    public void setup() {
        WebDriverManager.chromedriver().version("79").setup();
        driver = new ChromeDriver();
        driver.get(URL);
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
