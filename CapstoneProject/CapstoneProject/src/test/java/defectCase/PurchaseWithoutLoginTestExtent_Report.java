package defectCase;



import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import myBaseClass.BaseClass;
import java.time.Duration;
import myBaseClass.ExtentManager;
public class PurchaseWithoutLoginTestExtent_Report extends BaseClass {

    private WebDriver driver;
    private WebDriverWait wait;
    private ExtentReports extent;
	private ExtentTest test;

    @BeforeSuite
    public void setUp() {
        // Initialize WebDriver
    	//invokeBrowser("driver");
    	extent = ExtentManager.getInstance();
        ChromeOptions options = new ChromeOptions();
        driver = new ChromeDriver(options);
        driver.manage().window().maximize();
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    @Test(priority = 1)
    public void addProductToCartWithoutLogin__Test_Case_01() {
        try {
        	 test = ExtentManager.createTest("Add to cart without login");
            // Open Demoblaze website
            driver.get("https://www.demoblaze.com/index.html");

            // Select product and add to cart
            WebElement product = wait.until(ExpectedConditions.elementToBeClickable(By.linkText("Samsung galaxy s6")));
            product.click();
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h2[contains(text(),'Samsung galaxy s6')]")));

            WebElement addToCartButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[contains(text(), 'Add to cart')]")));
            addToCartButton.click();
            wait.until(ExpectedConditions.alertIsPresent()).accept();
            
           defectscreenShot(driver);


            // Go to cart
            WebElement cartLink = wait.until(ExpectedConditions.elementToBeClickable(By.id("cartur")));
            cartLink.click();
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//tr[@class='success']")));
            System.out.println("Defect:  Adding in cart without login(Pass)");
        } catch (Exception e) {
          //  Assert.fail("Failed to add product to cart: " + e.getMessage());
            test.log(Status.FAIL, "An error occurred: " + e.getMessage());
        }
    }

    @Test(priority = 2)
    public void checkoutWithoutLogin___Test_Case_02() {
        try {
        	 test = ExtentManager.createTest("Purchase without login");
            // Proceed to checkout
            WebElement checkoutButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[contains(text(),'Place Order')]")));
            checkoutButton.click();



            // Fill order details
            WebElement nameField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("name")));
            slowType(nameField, "Suneel Verma", 100);

            WebElement countryField = driver.findElement(By.id("country"));
            slowType(countryField, "India", 100);

            WebElement cityField = driver.findElement(By.id("city"));
            slowType(cityField, "Ayodhya", 100);

            WebElement creditCardField = driver.findElement(By.id("card"));
            slowType(creditCardField, "1111 2222 3333", 100);

            WebElement monthField = driver.findElement(By.id("month"));
            slowType(monthField, "06", 100);

            WebElement yearField = driver.findElement(By.id("year"));
            slowType(yearField, "2024", 100);

            // Click purchase button
            WebElement purchaseButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[contains(text(),'Purchase')]")));
            purchaseButton.click();

            // Verify thank you message
            WebElement thankYouMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h2[contains(text(), 'Thank you for your purchase!')]")));
            Assert.assertTrue(thankYouMessage.isDisplayed(), "Thank you message displayed after purchase");

            // Close the success dialog
            WebElement closeButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[contains(text(), 'OK')]")));
            closeButton.click();
            System.out.println("Defects:   Without loging purchase(Pass)");
            defectscreenShot(driver);
            
        } catch (Exception e) {
            //Assert.fail("Failed during checkout: " + e.getMessage());
        	test.log(Status.FAIL, "An error occurred: " + e.getMessage());
        }
    }

    @AfterSuite

    //@AfterSuite
	public void afterTest() throws InterruptedException {
		closeBrowser(driver);
		extent.flush();}

    private void slowType(WebElement element, String text, int delay) throws InterruptedException {
        // Slowly type text into an input field
        for (char c : text.toCharArray()) {
            element.sendKeys(Character.toString(c));
            Thread.sleep(delay);
        }
    }
}

