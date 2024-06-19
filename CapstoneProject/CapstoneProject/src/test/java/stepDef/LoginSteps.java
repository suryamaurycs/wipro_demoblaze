package stepDef;



import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;

public class LoginSteps {
    private WebDriver driver;
    private WebDriverWait wait;

    @Before
    public void setUp() {
        ChromeOptions options = new ChromeOptions();
        driver = new ChromeDriver(options);
        driver.manage().window().maximize();
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    @After
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    @Given("I navigate to the login page")
    public void i_navigate_to_the_login_page() {
        driver.get("https://www.demoblaze.com/index.html");
    }

    @When("I click on the sign in button")
    public void i_click_on_the_sign_in_button() {
        WebElement loginButton = wait.until(ExpectedConditions.elementToBeClickable(By.id("login2")));
        loginButton.click();
    }

    @When("I enter {string} and {string}")
    public void i_enter_username_and_password(String username, String password) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("loginusername"))).sendKeys(username);
        driver.findElement(By.id("loginpassword")).sendKeys(password);
    }

    @When("I click on the login button")
    public void i_click_on_the_login_button() {
        driver.findElement(By.xpath("//button[contains(text(), 'Log in')]")).click();
    }

    @Then("I should be logged in successfully")
    public void i_should_be_logged_in_successfully() {
        WebElement userElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("nameofuser")));
        assertTrue(userElement.isDisplayed());
    }

    @When("I click on the logout link")
    public void i_click_on_the_logout_link() {
        WebElement logoutButton = wait.until(ExpectedConditions.elementToBeClickable(By.id("logout2")));
        logoutButton.click();
    }

    @Then("I should be logged out successfully")
    public void i_should_be_logged_out_successfully() {
        WebElement loginButton = wait.until(ExpectedConditions.elementToBeClickable(By.id("login2")));
        assertTrue(loginButton.isDisplayed());
    }

    @Then("I should see an error message")
    public void i_should_see_an_error_message() {
        // Check for alert presence or error message on the page
        boolean alertPresent = false;
        try {
            wait.until(ExpectedConditions.alertIsPresent());
            alertPresent = true;
            driver.switchTo().alert().accept();
        } catch (Exception e) {
            alertPresent = false;
        }
        assertTrue(alertPresent); // If alert is present, login failed
    }
}

