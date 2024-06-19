package storeBlazeLoginPage;
import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BlazeLoginPage {
    public static void main(String[] args) {
        WebDriver driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        try {
            // Open the Demoblaze website
            driver.get("https://www.demoblaze.com/index.html");
            driver.manage().window().maximize();

            // Log in with an existing user
            loginUser(driver, "wipro", "wipro123");

            // Optionally, wait for the user to observe the result
            Thread.sleep(3000);

        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            // Close the browser
            driver.quit();
        }
    }

    private static void loginUser(WebDriver driver, String username, String password) {
        try {
            // Click on the "Log in" button
            WebElement loginButton = driver.findElement(By.id("login2"));
            loginButton.click();

            // Wait for the login modal to appear
            WebDriverWait wait = new WebDriverWait(driver, null);
            WebElement usernameField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("loginusername")));

            // Fill in the username
            usernameField.sendKeys(username);

            // Fill in the password
            WebElement passwordField = driver.findElement(By.id("loginpassword"));
            passwordField.sendKeys(password);

            // Click the "Log in" button in the modal
            WebElement submitButton = driver.findElement(By.xpath("//button[contains(text(),'Log in')]"));
            submitButton.click();

            // Optionally, wait for the login process to complete and validate
            wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("loginusername")));
            
            // Example validation after login (check for logout button or user icon)
            // WebElement logoutButton = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("logoutButton")));
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

