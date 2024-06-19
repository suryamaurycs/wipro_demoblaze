package tests;


import java.time.Duration;

import org.testng.Assert;
import org.testng.annotations.Test;
import pages.*;
import testbase.BaseTest;

public class MainTestAutomation extends BaseTest {
    
    @Test(priority = 0)
    public void loginFuctionality() {
        try {
            // Login Test
            LoginPage loginPage = new LoginPage(driver);
            loginPage.login(configReader.getProperty("username"), configReader.getProperty("password"));
            Thread.sleep(5000);
            String pageTitle = driver.getTitle();
            Assert.assertEquals(pageTitle, "STORE", "Page title is match");
            System.out.println("Login successful");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Test(priority = 1)
    public void categoriesDisplayed() {
        try {
            HomePage homePage = new HomePage(driver);
            Assert.assertTrue(homePage.areCategoriesDisplayed(), "Categories are displayed");
            Thread.sleep(5000);
            System.out.println("Categories Displayed");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Test(priority = 2)
    public void navigateToNextPage() {
        try {
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
            e.printStackTrace();
        }
    }

    @Test(priority = 3)
    public void contactDemo() {
        ContactPage contactPage = new ContactPage(driver);
        contactPage.fillContactForm(
                configReader.getProperty("contactEmail"),
                configReader.getProperty("contactName"),
                configReader.getProperty("contactMessage")
        );
    }

    @Test(priority = 4)
    public void addCartDemo() {
    	 try {
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
    	        e.printStackTrace();
    	    }
    }

    @Test(priority = 5)
    public void deleteItemFromCart() {
        try {
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
            CartPage cartPage = new CartPage(driver);
            Thread.sleep(2000);
            cartPage.deleteItemFromCart();
            Thread.sleep(1000);
            System.out.println("Item deleted from cart successfully");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Test(priority = 6)
    public void checkoutProcess() {
        try {
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
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
