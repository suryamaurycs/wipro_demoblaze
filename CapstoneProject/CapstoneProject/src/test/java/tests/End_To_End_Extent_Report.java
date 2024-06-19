
package tests;

import static io.restassured.RestAssured.given;
import java.io.IOException;
import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import io.restassured.response.Response;
import myBaseClass.ExtentManager;
import pages.*;
import utils.ConfigReader;
import utils.Excel_Read;

public class End_To_End_Extent_Report {
	
	protected WebDriver driver;
    protected ConfigReader configReader;
    private ExtentReports extent;
	private ExtentTest test;
	
	@BeforeSuite
    public void setUp() {
        configReader = new ConfigReader();
        ChromeOptions options = new ChromeOptions();
        driver = new ChromeDriver(options);
        driver.manage().window().maximize();
        driver.get(configReader.getProperty("url"));
        extent = ExtentManager.getInstance();
	}
	   
    @Test(priority = 0)
    public void loginFuctionality() {
        try {
            // Login Test
        	 test = ExtentManager.createTest(" login");
            LoginPage loginPage = new LoginPage(driver);
            loginPage.login(configReader.getProperty("username"), configReader.getProperty("password"));
            Thread.sleep(5000);
            String pageTitle = driver.getTitle();
            Assert.assertEquals(pageTitle, "STORE", "Page title is match");
            System.out.println("Login successful");
        } catch (InterruptedException e) {
        	test.log(Status.FAIL, "An error occurred: " + e.getMessage());
            e.printStackTrace();
        }
    }

    @Test(priority = 1)
    public void categoriesDisplayed() {
        try {
        	 test = ExtentManager.createTest("cat");
            HomePage homePage = new HomePage(driver);
            Assert.assertTrue(homePage.areCategoriesDisplayed(), "Categories are displayed");
            Thread.sleep(5000);
            System.out.println("Categories Displayed");
        } catch (InterruptedException e) {
        	test.log(Status.FAIL, "An error occurred: " + e.getMessage());
            e.printStackTrace();
        }
    }

    @Test(priority = 2)
    public void navigateToNextPage() {
        try {
        	 test = ExtentManager.createTest("navigate");
            HomePage homePage = new HomePage(driver);
            homePage.navigateToNextPage();
            Thread.sleep(5000);
            homePage.waitForNextPageLoad();
            Thread.sleep(5000);
            String currentUrl = driver.getCurrentUrl();
            System.out.println("Current URL after navigation: " + currentUrl);
            System.out.println("Navigated to next page");
            Thread.sleep(5000);
            Assert.assertFalse(currentUrl.contains("page=2"), "Navigate to the next page");
        } catch (InterruptedException e) {
        	test.log(Status.FAIL, "An error occurred: " + e.getMessage());
            e.printStackTrace();
        }
    }

    @Test(priority = 3)
    public void contactDemo() {
        try {
            test = ExtentManager.createTest("Contact Demo");
            ContactPage contactPage = new ContactPage(driver);
            Thread.sleep(5000);
            contactPage.fillContactForm(
                configReader.getProperty("contactEmail"),
                configReader.getProperty("contactName"),
                configReader.getProperty("contactMessage")
            );
            test.log(Status.PASS, "Contact form submitted successfully");
        } catch (Exception e) {
            test.log(Status.FAIL, "An error occurred: " + e.getMessage());
            e.printStackTrace();
        }
    }


    @Test(priority = 4)
    public void addCartDemo() {
    	 try {   test = ExtentManager.createTest("Add to cart ");
    	        ProductPage productPage = new ProductPage(driver);
    	        HomePage homePage = new HomePage(driver);
    	        Thread.sleep(5000);
    	        productPage.selectProduct();
    	       // Thread.sleep(5000);
    	        // Modify to add only one item to the cart
    	        productPage.addToCart(1); // Assuming addToCart method accepts quantity as parameter
    	       // Thread.sleep(5000);
    	        homePage.openCart();
    	        CartPage cartPage = new CartPage(driver);
    	        Thread.sleep(5000);
    	        Assert.assertTrue(cartPage.isItemInCart(), "Item is in the cart");
    	        Thread.sleep(5000);
    	        System.out.println("Item added to cart successfully");
    	    } catch (InterruptedException e) {
    	    	test.log(Status.FAIL, "An error occurred: " + e.getMessage());
    	        e.printStackTrace();
    	    }
    }

    @Test(priority = 5)
    public void deleteItemFromCart() {
        try {
        	 test = ExtentManager.createTest("delete");
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
            CartPage cartPage = new CartPage(driver);
            Thread.sleep(2000);
            cartPage.deleteItemFromCart();
            Thread.sleep(1000);
            System.out.println("Item deleted from cart successfully");
        } catch (InterruptedException e) {
        	test.log(Status.FAIL, "An error occurred: " + e.getMessage());
            e.printStackTrace();
        }
    }

    @Test(priority = 6)
    public void checkoutProcess() {
        try {
        	 test = ExtentManager.createTest("checkout");
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
            ProductPage productPage = new ProductPage(driver);
            HomePage homePage = new HomePage(driver);
            driver.navigate().back();
            driver.navigate().refresh();
            Thread.sleep(2000);
            homePage.navigateToHomePage();
            System.out.println("Navigated to Homepage");
            Thread.sleep(5000);
            productPage.selectProduct();
            Thread.sleep(5000);
            productPage.addToCart(1);
            homePage.openCart();
            Thread.sleep(5000);
            CheckoutPage checkoutPage = new CheckoutPage(driver);
            Thread.sleep(5000);
            checkoutPage.fillCheckoutForm(
                    configReader.getProperty("name"),
                    configReader.getProperty("country"),
                    configReader.getProperty("city"),
                    configReader.getProperty("creditCard"),
                    configReader.getProperty("month"),
                    configReader.getProperty("year")
            );
             
            Assert.assertTrue(checkoutPage.isThankYouMessageDisplayed(), "Thank you message displayed");
            System.out.println("Thank you for purchasing");
            homePage.navigateToHomePage();
            Thread.sleep(5000);
            homePage.clickLogoutButton();
            driver.navigate().refresh();
        } catch (InterruptedException e) {
        	test.log(Status.FAIL, "An error occurred: " + e.getMessage());
            e.printStackTrace();
        }
    }
    @Test(priority = 7)
    public void testInvalidLogin() throws InterruptedException {
        try {
        	ExcelLogin    newloginPage = new ExcelLogin(driver);
            // Navigate to the URL
        	 test = ExtentManager.createTest("Login with InvalidCredentials");
            driver.get("https://www.demoblaze.com/index.html");
            Thread.sleep(5000);
            WebElement loginButton = driver.findElement(By.id("login2"));
            loginButton.click();

            System.out.println("Enter Invalid Creadential");
            // Instantiate the LoginPages
           // ExcelLogin newloginPage = new ExcelLogin(driver);

            // Read credentials from Excel
            String excelPath = "C:\\Users\\Naresh\\OneDrive\\Desktop\\final project\\AutomationInvaliddetails.xlsx";
            String sheetName = "Sheet1";
            Excel_Read.openExcel(excelPath, sheetName);

            String UserName = Excel_Read.getCellValue(1, 0); // Assuming row 1 (0-indexed) is for credentials
            String PassWord = Excel_Read.getCellValue(1, 1); // Column 0 is for username, column 1 is for password

            Excel_Read.closeExcel();

            // Perform login
          //  newloginPage.clickLoginLink();
            newloginPage.fillUsername(UserName);
            newloginPage.fillPassword(PassWord);
            newloginPage.clickLoginButton();
            System.out.println("Login Failed ");
            // Optionally, add assertions to verify login failure
        } catch (IOException e) {
        	test.log(Status.FAIL, "An error occurred: " + e.getMessage());
            e.printStackTrace();
            // Handle the exception (e.g., log it, fail the test, etc.)
        }
    }
    
    @Test(priority = 8)
    public void testGetStatusCode() {
        
        test = extent.createTest("Check API Status Code");
        
        String baseUri = configReader.getProperty("BaseUrl");
        String endpoint = "/cart.html"; 
        
        test.info("Sending GET request to " + baseUri + endpoint);
        
        Response response = given()
                                .baseUri(baseUri)
                            .when()
                                .get(endpoint)
                            .then()
                                .extract().response();

       
        int statusCode = response.getStatusCode();

          if (statusCode == 200) {
            test.pass("API responded with status code 200");
        } else {
            test.fail("API responded with status code " + statusCode);
        }
    }
    
       
    
    @AfterSuite
    public void tearDown() {
        if (driver != null) {
        	
            driver.quit();
            extent.flush();
        }
    }
}
