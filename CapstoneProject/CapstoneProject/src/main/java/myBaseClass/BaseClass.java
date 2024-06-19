package myBaseClass;


import java.io.File;
import java.io.IOException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import com.google.common.io.Files;
public class BaseClass {
	protected static WebDriver driver;
	
	public static void invokeBrowser(String s) {
		if(s.equalsIgnoreCase("chrome")){
			 driver=new ChromeDriver();
			 driver.manage().window().maximize();
			 }
		else if(s.equalsIgnoreCase("firefox")){
			 driver=new FirefoxDriver();
			 driver.manage().window().maximize();
		}
		else if(s.equalsIgnoreCase("edge")) {
			 driver=new EdgeDriver();
			 driver.manage().window().maximize();
			}
		else
			System.out.println("Browser not available");
		}
	public static void screenShot(WebDriver driver) throws IOException {
		File src=null;
		TakesScreenshot tr=(TakesScreenshot)driver;
		src=tr.getScreenshotAs(OutputType.FILE);
		Files.copy(src,new File( "C:\\Users\\Naresh\\OneDrive\\Desktop\\final project\\CapstoneProject\\CapstoneProject\\ScreenShots\\ScreenShot"+"Page-"+System.currentTimeMillis()+".png"));
		
	}
	public static void defectscreenShot(WebDriver driver) throws IOException {
		File src=null;
		TakesScreenshot tr=(TakesScreenshot)driver;
		src=tr.getScreenshotAs(OutputType.FILE);
		Files.copy(src,new File( "C:\\Users\\Naresh\\OneDrive\\Desktop\\final project\\CapstoneProject\\CapstoneProject\\ScreenShots\\DefectScreenShot"+"Page-"+System.currentTimeMillis()+".png"));
		
	}
	public static void closeBrowser(WebDriver driver) throws InterruptedException {
		Thread.sleep(2000);
		driver.quit();
		
	}
}
