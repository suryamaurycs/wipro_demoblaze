package pages;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class ContactPage extends BasePage {

    @FindBy(xpath = "//a[contains(text(), 'Contact')]")
    private WebElement contactButton;

    @FindBy(id = "recipient-email")
    private WebElement contactEmail;

    @FindBy(id = "recipient-name")
    private WebElement contactName;

    @FindBy(id = "message-text")
    private WebElement contactMessage;

    @FindBy(xpath = "//button[contains(text(), 'Send message')]")
    private WebElement sendMessageButton;

    public ContactPage(WebDriver driver) {
        super(driver);
    }

    public void fillContactForm(String email, String name, String message) {
        wait.until(ExpectedConditions.elementToBeClickable(contactButton)).click();
        wait.until(ExpectedConditions.visibilityOf(contactEmail)).sendKeys(email);
        contactName.sendKeys(name);
        contactMessage.sendKeys(message);
        sendMessageButton.click();
        wait.until(ExpectedConditions.alertIsPresent()).accept();
    }
}
