package simpleTesting;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import myBaseClass.BaseClass;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class DemoBlazeLoginFromDataProperties extends BaseClass {
    public static void main(String[] args) throws IOException, InterruptedException {
        // Load properties file
        Properties prop = new Properties();
        FileInputStream fis = new FileInputStream("D:\\ProjectWork\\Pom_3\\Pom_3\\src\\test\\java\\simpleData\\Data.properties");
        prop.load(fis);

        // Read properties
        String url = prop.getProperty("url");
        String username = prop.getProperty("username");
        String password = prop.getProperty("password");

     
         invokeBrowser("Chrome");

        // Navigate to the website
        driver.get(url);
        driver.findElement(By.id("login2")).click();
       
       Thread.sleep(2000);
        // Find username and password fields and login button
        driver.findElement(By.id("loginusername")).sendKeys(username);
       driver.findElement(By.id("loginpassword")).sendKeys(password);
       
        driver.findElement(By.xpath("//button[contains(text(),'Log in')]")).click();
        Thread.sleep(4000);
        screenShot(driver);
        WebElement logoutLink = driver.findElement(By.xpath("//a[contains(text(),'Log out')]"));
        if (logoutLink.isDisplayed()) {
            System.out.println("Login successful!");
        } else {
            System.out.println("Login failed!");
        }

        // Close the browser
        driver.quit();
    }
}
