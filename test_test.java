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

	//@Test
	public void ChangeNumOfItemsInCart() throws Exception {
			//Add MacBook to cart
					driver.findElement(By.linkText("MacBook")).click();
					driver.findElement(By.cssSelector("#button-cart")).click();
					System.out.println("McBook was added to cart...");
					// Thread.sleep(2000); // For demonstration
			// Check if user see correct message about adding good to cart
					Assert.assertTrue(driver.findElement(By.cssSelector(".alert-success"))
							.getText().contains("Success: You have added MacBook to your shopping cart!"));
					System.out.println("User see correct message, good was added...");
					// Thread.sleep(2000); // For demonstration
			//Check if cart button contain correct data
					Assert.assertEquals(driver.findElement(By.cssSelector("#cart")).getText(), "1 item(s) - $602.00");
			//Open cart
					driver.manage().window().maximize();
					driver.findElement(By.linkText("Shopping Cart")).click();
					System.out.println("Shopping Cart page was opened...");
					// Thread.sleep(2000); // For demonstration
			//Edit quantity
					driver.findElement(By.xpath("//*[@id=\"content\"]/form/div/table/tbody/tr/td[4]/div/input")).clear();
					driver.findElement(By.xpath("//*[@id=\"content\"]/form/div/table/tbody/tr/td[4]/div/input")).sendKeys("5");
					driver.findElement(By.cssSelector(".fa-refresh")).click();
			//Check if user see correct message about modified cart
					Assert.assertTrue(driver.findElement(By.cssSelector(".alert-success"))
							.getText().contains("Success: You have modified your shopping cart!"));
					System.out.println("User see correct message, cart was modified...");
			//Check if quantity in cart display correctly
					driver.findElement(By.cssSelector("#cart")).click();
					Assert.assertEquals(driver
							.findElement(By.cssSelector(
									"#cart > ul > li:nth-child(2) > div > table > tbody > tr:nth-child(2) > td:nth-child(2)")).getText(), "$10.00");// eco tax
					System.out.println("Quantity in cart display correct...");
					System.out.println("Positiv quantity editing worc correctly");
	}

	@Test
				public void ErrorMessageChangeNumOfItemsInCart() throws Exception {
					//Add MacBook to cart
					driver.findElement(By.linkText("MacBook")).click();
					driver.findElement(By.cssSelector("#button-cart")).click();
					System.out.println("McBook was added to cart...");
					// Thread.sleep(2000); // For demonstration
			// Check if user see correct message about adding good to cart
					Assert.assertTrue(driver.findElement(By.cssSelector(".alert-success"))
							.getText().contains("Success: You have added MacBook to your shopping cart!"));
					System.out.println("User see correct message, good was added...");
					// Thread.sleep(2000); // For demonstration
			//Check if cart button contain correct data
					Assert.assertEquals(driver.findElement(By.cssSelector("#cart")).getText(), "1 item(s) - $602.00");
			//Open cart
					driver.manage().window().maximize();
					driver.findElement(By.linkText("Shopping Cart")).click();
					System.out.println("Shopping Cart page was opened...");
					// Thread.sleep(2000); // For demonstration
			//Edit quantity
					driver.findElement(By.xpath("//*[@id=\"content\"]/form/div/table/tbody/tr/td[4]/div/input")).clear();
					driver.findElement(By.xpath("//*[@id=\"content\"]/form/div/table/tbody/tr/td[4]/div/input")).sendKeys("test");
			//Check Error message
					Assert.assertTrue(driver.findElement(By.cssSelector(".alert-success"))
							.getText().contains("Warning: Input valid data!"));
	}
}
