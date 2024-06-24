package TestSuite;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class main {

    WebDriver driver;

    @BeforeMethod
    public void setUp() {

        System.setProperty("webdriver.chrome.driver", "E:\\Software Testing\\chromedriver-win64\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.get("https://www.saucedemo.com/");
    }

    @AfterMethod(timeOut = 3000)
    public void tearDown() throws InterruptedException{
        Thread.sleep(2000);
        if (driver != null) {
            driver.quit();
        }
    }

    @Test
    public void testaLoginPageElements()  {
        WebElement usernameField = driver.findElement(By.id("user-name"));
        WebElement passwordField = driver.findElement(By.id("password"));
        WebElement loginButton = driver.findElement(By.id("login-button"));

        Assert.assertTrue(usernameField.isDisplayed(), "Username field is not displayed");
        Assert.assertTrue(passwordField.isDisplayed(), "Password field is not displayed");
        Assert.assertTrue(loginButton.isDisplayed(), "Login button is not displayed");


    }

    @Test
    public void testbValidLogin() {
        driver.findElement(By.id("user-name")).sendKeys("standard_user");
        driver.findElement(By.id("password")).sendKeys("secret_sauce");
        driver.findElement(By.id("login-button")).click();

        WebElement swagLabsDiv = driver.findElement(By.xpath("//*[@id=\"header_container\"]/div[1]/div[2]/div"));
        Assert.assertTrue(swagLabsDiv.isDisplayed(), "Login was not successful");
    }

    @Test
    public void testcInvalidLogin() {
        driver.findElement(By.id("user-name")).sendKeys("invalid_user");
        driver.findElement(By.id("password")).sendKeys("invalid_password");
        driver.findElement(By.id("login-button")).click();

        WebElement errorMessage = driver.findElement(By.cssSelector("#login_button_container > div > form > div.error-message-container.error"));
        Assert.assertTrue(errorMessage.isDisplayed(), "Error message is not displayed");
        Assert.assertTrue(errorMessage.getText().contains("Epic sadface: Username and password do not match any user in this service"), "Error message text is incorrect");
    }

    @Test
    public void testdEmptyUsername() {
        driver.findElement(By.id("password")).sendKeys("some_password");
        driver.findElement(By.id("login-button")).click();

        WebElement errorMessage = driver.findElement(By.cssSelector("div.error-message-container.error"));
        Assert.assertTrue(errorMessage.isDisplayed(), "Error message is not displayed");
        Assert.assertTrue(errorMessage.getText().contains("Epic sadface: Username is required"), "Error message text is incorrect");
    }

    @Test
    public void testeEmptyPassword() {
        driver.findElement(By.id("user-name")).sendKeys("some_user");
        driver.findElement(By.id("login-button")).click();

        WebElement errorMessage = driver.findElement(By.cssSelector("div.error-message-container.error"));
        Assert.assertTrue(errorMessage.isDisplayed(), "Error message is not displayed");
        Assert.assertTrue(errorMessage.getText().contains("Epic sadface: Password is required"), "Error message text is incorrect");
    }
}



