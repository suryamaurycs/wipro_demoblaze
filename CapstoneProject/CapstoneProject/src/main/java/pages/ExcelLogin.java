package pages;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class ExcelLogin {
    private WebDriver driver;
    private By loginLinkLocator = By.id("login2");
    private By usernameFieldLocator = By.id("loginusername");
    private By passwordFieldLocator = By.id("loginpassword");
    private By loginButtonLocator = By.xpath("//button[contains(text(),'Log in')]");
    private By logoutButtonLocator = By.xpath("//*[@id='logout2']");

    // Constructor
    public ExcelLogin(WebDriver driver) {
        this.driver = driver;
    }

    // Click on Login link to open login modal
    public void clickLoginLink() {
        driver.findElement(loginLinkLocator).click();
    }

    // Fill in the username on login modal
    public void fillUsername(String UserName) {
        WebElement usernameField = driver.findElement(usernameFieldLocator);
        usernameField.sendKeys(UserName);
    }

    // Fill in the password on login modal
    public void fillPassword(String PassWord) {
        WebElement passwordField = driver.findElement(passwordFieldLocator);
        passwordField.sendKeys(PassWord);
    }

    // Click the "Log in" button on login modal
    public void clickLoginButton() {
        WebElement loginButton = driver.findElement(loginButtonLocator);
        loginButton.click();
    }

    // Click the logout button on homepage
    public void clickLogoutButton() {
        WebElement logoutButton = driver.findElement(logoutButtonLocator);
        logoutButton.click();
    }
}