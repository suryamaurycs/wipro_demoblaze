package simpleTesting;

import java.io.IOException;
import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import myBaseClass.BaseClass;

public class MannualSimpleTesting extends BaseClass {
	public static void main (String[] args) throws InterruptedException, IOException {

		
        invokeBrowser("Chrome");
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.get("https://www.demoblaze.com/index.html");
        WebElement loginButton = driver.findElement(By.id("login2"));
        loginButton.click();
        Thread.sleep(2000);

        // Enter username and password
        WebElement usernameField = driver.findElement(By.id("loginusername"));
        usernameField.sendKeys("wipro");
        WebElement passwordField = driver.findElement(By.id("loginpassword"));
        passwordField.sendKeys("wipro123");

        // Click on the login button in the modal
        WebElement loginModalButton = driver.findElement(By.xpath("//button[contains(text(), 'Log in')]"));
        loginModalButton.click();
        Thread.sleep(5000);
		// Click on the "Contact" button
	    WebElement contactButton = driver.findElement(By.xpath("//a[contains(text(), 'Contact')]"));
	    contactButton.click();
	    Thread.sleep(2000);

	    // Fill out the contact form
	    WebElement contactEmail = driver.findElement(By.id("recipient-email"));
	    contactEmail.sendKeys("test@example.com");
	    WebElement contactName = driver.findElement(By.id("recipient-name"));
	    contactName.sendKeys("Test User");
	    WebElement contactMessage = driver.findElement(By.id("message-text"));
	    contactMessage.sendKeys("This is a test message.");

	    // Click the send message button
	    WebElement sendMessageButton = driver.findElement(By.xpath("//button[contains(text(), 'Send message')]"));
	    sendMessageButton.click();
	    Thread.sleep(5000);
	    screenShot(driver);
	    Thread.sleep(5000);
	    driver.switchTo().alert().accept();
	    driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		 driver.get("https://www.demoblaze.com/index.html");

	  WebElement product1 = driver.findElement(By.linkText("Samsung galaxy s6"));
	  product1.click();
	  Thread.sleep(2000);
	  WebElement addToCartButton1 = driver.findElement(By.xpath("//a[contains(text(), 'Add to cart')]"));
	  addToCartButton1.click();
	  Thread.sleep(2000);
	  driver.switchTo().alert().accept();
	  Thread.sleep(2000);

	  // Go to the cart
	  WebElement cartLink1 = driver.findElement(By.id("cartur"));
	  cartLink1.click();
	  Thread.sleep(2000);

	  // Delete the product from the cart
	  WebElement deleteButton = driver.findElement(By.xpath("//a[contains(text(), 'Delete')]"));
	  deleteButton.click();
	  Thread.sleep(2000);

	  // Navigate back to home page
	  WebElement homeLink = driver.findElement(By.xpath("//a[contains(text(), 'Home')]"));
	  homeLink.click();
	  Thread.sleep(2000);
	  driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));//using IMPLCTY for taking time to loading
	  // Open the URL
	//  driver.get("https://www.demoblaze.com/index.html");
	  // Click on a product
	  WebElement product = driver.findElement(By.xpath("//a[contains(text(),'Samsung galaxy s6')]"));
	  product.click();
	  Thread.sleep(2000);
	  // Adding product in cart
	  WebElement addToCartButton = driver.findElement(By.xpath("//a[contains(text(),'Add to cart')]"));
	  addToCartButton.click();
	  
	  Thread.sleep(2000);
	  
	  // Wait for the cart alert
	  driver.switchTo().alert().accept();//Accept pop-up button as pressing OK
	  
	  // Go to the cart
	  WebElement cartLink = driver.findElement(By.xpath("//a[contains(text(),'Cart')]"));
	  cartLink.click();
	  Thread.sleep(2000);
	  // Proceed to checkout
	  WebElement checkoutButton = driver.findElement(By.xpath("//button[contains(text(),'Place Order')]"));
	  checkoutButton.click();
	  Thread.sleep(2000);
	  // Fill in the checkout form
	  WebElement nameField = driver.findElement(By.id("name"));
	  nameField.sendKeys("Suneel Verma");

	  WebElement countryField = driver.findElement(By.id("country"));
	  countryField.sendKeys("India");

	  WebElement cityField = driver.findElement(By.id("city"));
	  cityField.sendKeys("Ayodhya");

	  WebElement creditCardField = driver.findElement(By.id("card"));
	  creditCardField.sendKeys("1111 2222 3333");

	  WebElement monthField = driver.findElement(By.id("month"));
	  monthField.sendKeys("06");

	  WebElement yearField = driver.findElement(By.id("year"));
	  yearField.sendKeys("2024");
	  Thread.sleep(2000);
	  // Complete the checkout
	  WebElement purchaseButton = driver.findElement(By.xpath("//button[contains(text(),'Purchase')]"));
	  purchaseButton.click();
	// Verify the thank you message
	  WebElement thankYouMessage = driver.findElement(By.xpath("//h2[contains(text(), 'Thank you for your purchase!')]"));
	  Assert.assertTrue(thankYouMessage.isDisplayed());
	  System.out.println("Thank you for your purchase!");
	// Close the confirmation dialog
	  WebElement closeButton = driver.findElement(By.xpath("//button[contains(text(), 'OK')]"));
	  closeButton.click();
	  WebElement close = driver.findElement(By.xpath("/html/body/div[3]/div/div/div[3]/button[1]"));
	  close.click();
	  
	  WebElement homeLink1 = driver.findElement(By.xpath("//a[contains(text(), 'Home')]"));
	  homeLink1.click();
	  driver.close();
	}

}
