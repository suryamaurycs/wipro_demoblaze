package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class ProductPage extends BasePage {

    private By productLink = By.linkText("Samsung galaxy s6");
    private By addToCartButton = By.xpath("//a[contains(text(), 'Add to cart')]");

    public ProductPage(WebDriver driver) {
        super(driver);
    }

    public void selectProduct() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement product = wait.until(ExpectedConditions.elementToBeClickable(productLink));
        product.click();
    }

    public void addToCart(int n) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement addToCart = wait.until(ExpectedConditions.elementToBeClickable(addToCartButton));
        addToCart.click();
        wait.until(ExpectedConditions.alertIsPresent()).accept();
    }
}
