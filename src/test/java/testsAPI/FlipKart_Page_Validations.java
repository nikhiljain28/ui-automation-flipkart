package testsAPI;

import java.time.Duration;
import java.util.List;
import java.util.NoSuchElementException;

import org.junit.AfterClass;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.Test;
import org.openqa.selenium.By;


public class FlipKart_Page_Validations {
	
	public static WebDriver driver;
	public static WebDriverWait mywait;
	public static WebElement LoginCancelButton;
	public static WebElement frameOne;
	public static List<WebElement> Mobiles;
	public static List<WebElement> allTheCategories;
	
	@Test (priority =1)
	public void flipkart_getClickedAllTheCategories() {
		driver = new ChromeDriver();
		mywait = new WebDriverWait(driver,Duration.ofSeconds(40));
		driver.get("https://www.flipkart.com/");
		try {
		    LoginCancelButton = mywait.until(ExpectedConditions
		        .elementToBeClickable(By.cssSelector(".b3wTlE"))); // wait + find together
		    LoginCancelButton.click();
		    System.out.println("Login popup closed");
		}
		catch (TimeoutException e) {
			System.out.println("Popup did not appear: " + e.getMessage());
		}
		catch (NoSuchElementException e) {
	        System.out.println("Element not found: " + e.getMessage());
	    }
	}
	
	@Test (priority =2, dependsOnMethods = "flipkart_getClickedAllTheCategories")
	public void flipKart_getAllCategories() {
		allTheCategories = driver.findElements(By.xpath("//*[contains(@class,'css-175oi2r r-18u37iz')]/div[@class='css-175oi2r']"));
		for(WebElement x: allTheCategories) {
			System.out.println(x.getText());
		}
	}
	
	@Test(priority =3, dependsOnMethods = "flipKart_getAllCategories")
	public void flipkart_getMobiles() {
		Mobiles = driver.findElements(By.xpath("//*[text()='Mobiles']"));
		if(Mobiles.size()>0) {
			Mobiles.get(0).click();
		}
		else {
			System.out.println("Nothing is stored in this webelement list");
		}
	}
	
	@AfterTest
	public void closingBrowser() {
		driver.quit();
	}
}
