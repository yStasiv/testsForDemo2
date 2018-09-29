package UITests;

import java.sql.Driver;
import java.util.concurrent.TimeUnit;

import org.openqa.grid.web.servlet.DriverServlet;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class test_test {

	WebDriver driver = null;

	@BeforeMethod
	public void SetUp() throws Exception {
		System.setProperty("webdriver.chrome.driver",
				"C:\\Program Files (x86)\\Google\\Chrome\\Application\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		driver.get("http://atqc-shop.epizy.com");
	}

	@AfterMethod
	public void TearDown() throws Exception {
		driver.quit();
	}

	

	@Test
	public void AddSingleItemToCart() throws Exception {
		//Check if cart is empty
				Assert.assertEquals(driver.findElement(By.cssSelector("#cart-total")).getText(), "0 item(s) - $0.00");
		//Add MacBook to cart
				driver.findElement(By.linkText("MacBook")).click();
				driver.findElement(By.cssSelector("#button-cart")).click();
				System.out.println("McBook was added to cart...");
				// Thread.sleep(2000); // For demonstration
		// Check if user see correct message about adding good to cart
				Assert.assertTrue(
						driver.findElement(By.cssSelector("body > div:nth-child(4) > div.alert.alert-success")).getText()
								.contains("Success: You have added MacBook to your shopping cart!"));
				System.out.println("User see correct message, good was added...");
		//Check if cart button contain correct data
				Assert.assertEquals(driver.findElement(By.cssSelector("#cart")).getText(), "1 item(s) - $602.00");
		//back to main page		
				driver.get("http://atqc-shop.epizy.com");
		//Add iPhone to cart		
				driver.findElement(By.linkText("iPhone")).click();
				driver.findElement(By.cssSelector("#button-cart")).click();
				System.out.println("iPhone was added to cart...");
				// Thread.sleep(2000); // For demonstration
		// Check if user see correct message about adding good to cart
				Assert.assertTrue(
						driver.findElement(By.cssSelector("body > div:nth-child(4) > div.alert.alert-success")).getText()
								.contains("Success: You have added iPhone to your shopping cart!"));
				System.out.println("User see correct message, good was added...");
		//Check if cart button contain correct data
				Assert.assertEquals(driver.findElement(By.cssSelector("#cart")).getText(), "2 item(s) - $725.20");
				driver.findElement(By.cssSelector("#cart")).click();
		//Check if the tax is counted correctly
				Assert.assertEquals(driver
						.findElement(By.cssSelector(
								"#cart > ul > li:nth-child(2) > div > table > tbody > tr:nth-child(2) > td:nth-child(2)")).getText(), "$2.00");// eco tax
				System.out.println("Eco Tax display correct...");			
//				
				
				
				
				
	}
}
