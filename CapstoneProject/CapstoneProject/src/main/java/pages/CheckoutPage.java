package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class CheckoutPage extends BasePage {

    @FindBy(xpath = "//button[contains(text(),'Place Order')]")
    private WebElement checkoutButton;

    @FindBy(id = "name")
    private WebElement nameField;

    @FindBy(id = "country")
    private WebElement countryField;

    @FindBy(id = "city")
    private WebElement cityField;

    @FindBy(id = "card")
    private WebElement creditCardField;

    @FindBy(id = "month")
    private WebElement monthField;

    @FindBy(id = "year")
    private WebElement yearField;

    @FindBy(xpath = "//button[contains(text(),'Purchase')]")
    private WebElement purchaseButton;

    @FindBy(xpath = "//h2[contains(text(), 'Thank you for your purchase!')]")
    private WebElement thankYouMessage;

    public CheckoutPage(WebDriver driver) {
        super(driver);
    }

    public void fillCheckoutForm(String name, String country, String city, String creditCard, String month, String year) {
        wait.until(ExpectedConditions.elementToBeClickable(checkoutButton)).click();
        wait.until(ExpectedConditions.visibilityOf(nameField)).sendKeys(name);
        countryField.sendKeys(country);
        cityField.sendKeys(city);
        creditCardField.sendKeys(creditCard);
        monthField.sendKeys(month);
        yearField.sendKeys(year);
        purchaseButton.click();
    }

    public boolean isThankYouMessageDisplayed() {
        return wait.until(ExpectedConditions.visibilityOf(thankYouMessage)).isDisplayed();
    }
}
