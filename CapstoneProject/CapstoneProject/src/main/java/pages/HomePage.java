package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.function.Function;

public class HomePage extends BasePage {

    private By nextPageButton = By.id("next2");
    private By cartLink = By.id("cartur");
 //   private By homeLink = By.linkText("Home");

    public HomePage(WebDriver driver) {
        super(driver);
    }

    public boolean areCategoriesDisplayed() {
        WebElement phonesCategory = driver.findElement(By.linkText("Phones"));
        WebElement laptopsCategory = driver.findElement(By.linkText("Laptops"));
        WebElement monitorsCategory = driver.findElement(By.linkText("Monitors"));

        return phonesCategory.isDisplayed() && laptopsCategory.isDisplayed() && monitorsCategory.isDisplayed();
    }

    public void navigateToNextPage() {
        retryingFindClick(nextPageButton);
    }

    public void waitForNextPageLoad() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(".card-block")));
    }

    public void openCart() {
        retryingFindClick(cartLink);
    }

    public void navigateToHomePage() {
      // retryingFindClick(homeLink);
      driver.navigate().to("https://www.demoblaze.com/index.html");
    }
    public void clickLogoutButton() {
        WebElement logoutButton = driver.findElement(By.xpath(" //*[@id= 'logout2']"));
        logoutButton.click();
    }


    private void retryingFindClick(By by) {
        int attempts = 0;
        while (attempts < 1) {
            try {
                WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
                WebElement element = wait.until(ExpectedConditions.elementToBeClickable(by));
                element.click();
                break;
            } catch (StaleElementReferenceException e) {
                attempts++;
            }
        }
    }
}
