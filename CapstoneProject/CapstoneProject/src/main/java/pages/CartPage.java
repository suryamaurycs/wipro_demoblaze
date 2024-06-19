package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class CartPage extends BasePage {

    @FindBy(xpath = "//tr[@class='success']")
    private WebElement cartItem;

    @FindBy(xpath = "//a[contains(text(), 'Delete')]")
    private WebElement deleteButton;

    public CartPage(WebDriver driver) {
        super(driver);
    }

    public boolean isItemInCart() {
        return wait.until(ExpectedConditions.visibilityOf(cartItem)).isDisplayed();
    }

    public void deleteItemFromCart() {
        wait.until(ExpectedConditions.elementToBeClickable(deleteButton)).click();
    }
}
