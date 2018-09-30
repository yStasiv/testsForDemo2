package UITests;

import java.util.concurrent.TimeUnit;

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

import net.bytebuddy.dynamic.scaffold.MethodRegistry.Handler.ForAbstractMethod;

public class Tests {

	WebDriver driver = null;
	String ststus1 = "Running>> ";

	@BeforeMethod
	public void SetUp() throws Exception {
		System.setProperty("webdriver.chrome.driver",
				"C:\\Program Files (x86)\\Google\\Chrome\\Application\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		driver.get("http://atqc-shop.epizy.com");
	}

	@AfterMethod
	public void TearDown() throws Exception {
		driver.quit();
	}

	@Test(enabled = true)
	public void SmokeTestOpenCart() throws Exception {
		// Verify 1 text element
		// Thread.sleep(2000); // For demonstration
		Assert.assertEquals(driver.findElement(By.xpath("//*[@id='content']/h3")).getText(), "Featured");
		System.out.println(ststus1 + "Element on page was found...");
		// Verify 1 button
		// Thread.sleep(2000); // For demonstration
		driver.manage().deleteAllCookies();
		Assert.assertEquals(driver.findElement(By.cssSelector("#cart-total")).getText(), "0 item(s) - $0.00");
		System.out.println(ststus1 + "Button on page was found...");
		// Find and verify element at another page
		// Thread.sleep(2000); // For demonstration
		driver.findElement(By.linkText("Site Map")).click();
		Assert.assertEquals(driver.findElement(By.xpath("//*[@id='content']/h1")).getText(), "Site Map");
		System.out.println(ststus1 + "Element on enother page was found...");
		// Message about all is great
		System.out.println("All is great, you can continue.");
	}

	@Test(dependsOnMethods = { "SmokeTestOpenCart" }, enabled = true)
	public void AddItemToCart() throws Exception {
		// Add good to cart (McBook)
		// Check if it's McBook and add to cart
		Assert.assertEquals(
				driver.findElement(By.cssSelector("#content > div.row > div:nth-child(1) > div > div.caption > h4 > a")).getText(),"MacBook");
		driver.findElement(By.cssSelector("#content > div.row > div:nth-child(1) > div > div.button-group > button:nth-child(1)")).click();
		System.out.println(ststus1 + "Good was added to cart...");
		// Thread.sleep(2000); // For demonstration
		// Check if user see correct message about adding good to cart
		Assert.assertTrue(
				driver.findElement(By.cssSelector("body > div:nth-child(4) > div.alert.alert-success")).getText()
						.contains("Success: You have added "
								+ driver.findElement(By.cssSelector(
										"#content > div.row > div:nth-child(1) > div > div.caption > h4 > a")).getText()
								+ " to your shopping cart!"));
		System.out.println(ststus1 + "User see correct message, good was added...");
		// Thread.sleep(2000); // For demonstration
		// Save current price this good
		driver.findElement(By.cssSelector("#content > div.row > div:nth-child(1) > div > div.caption > h4 > a")).click();
		Assert.assertEquals(driver.findElement(By.cssSelector("#content > div > div.col-sm-4 > h1")).getText(),"MacBook");
		String mcBooktPrice = driver
				.findElement(By.cssSelector("#content > div > div.col-sm-4 > ul:nth-child(4) > li:nth-child(1) > h2")).getText();
		System.out.println(ststus1 + "Price was find and saved...");
		// Thread.sleep(2000); // For demonstration
		driver.get("http://atqc-shop.epizy.com");
		// Check if text about count and price on cart button is correct
		Assert.assertEquals(driver.findElement(By.cssSelector("#cart-total")).getText(), "1 item(s) - " + mcBooktPrice);
		System.out.println(ststus1 + "Goods price correct...");
		// Thread.sleep(2000); // For demonstration
		// Check if all sum is correct
		driver.findElement(By.cssSelector("#cart-total")).click();
		Assert.assertEquals(driver
				.findElement(By.cssSelector("#cart > ul > li:nth-child(1) > table > tbody > tr > td:nth-child(3)")).getText(), "x 1");// count
		System.out.println(ststus1 + "Count display correct...");
		Assert.assertEquals(driver
				.findElement(By.cssSelector("#cart > ul > li:nth-child(1) > table > tbody > tr > td:nth-child(4)")).getText(), mcBooktPrice);// price
		System.out.println(ststus1 + "Price display correct...");
		Assert.assertEquals(driver
				.findElement(By.cssSelector(
						"#cart > ul > li:nth-child(2) > div > table > tbody > tr:nth-child(2) > td:nth-child(2)")).getText(), "$2.00");// eco tax
		System.out.println(ststus1 + "Eco Tax display correct...");
		Assert.assertEquals(driver.findElement(
						By.cssSelector("#cart > ul > li:nth-child(1) > table > tbody > tr > td:nth-child(4)")).getText(),
				driver.findElement(
						By.cssSelector("#cart > ul > li:nth-child(2) > div > table > tbody > tr:nth-child(4) > td:nth-child(2)")).getText());// price2
		System.out.println(ststus1 + "All price display correct...");
		System.out.println("All data display correct.");
	}
	
	
	
	
	

	@Test(dependsOnMethods = { "SmokeTestOpenCart", "AddItemToCart" }, enabled = true)
	public void AddSingleItemToCart() throws Exception {
			//Check if cart is empty
					Assert.assertEquals(driver.findElement(By.cssSelector("#cart-total")).getText(), "0 item(s) - $0.00");
			//Add MacBook to cart
					driver.findElement(By.linkText("MacBook")).click();
					driver.findElement(By.cssSelector("#button-cart")).click();
					System.out.println(ststus1 + "McBook was added to cart...");
					//Thread.sleep(2000); // For demonstration
			// Check if user see correct message about adding good to cart
					Assert.assertTrue(
							driver.findElement(By.cssSelector("body > div:nth-child(4) > div.alert.alert-success")).getText()
									.contains("Success: You have added MacBook to your shopping cart!"));
					System.out.println(ststus1 + "User see correct message, good was added...");
					//Thread.sleep(2000); // For demonstration
			//Check if cart button contain correct data
					Assert.assertEquals(driver.findElement(By.cssSelector("#cart")).getText(), "1 item(s) - $602.00");
			//back to main page		
					driver.get("http://atqc-shop.epizy.com");
			//Add iPhone to cart		
					driver.findElement(By.linkText("iPhone")).click();
					driver.findElement(By.cssSelector("#button-cart")).click();
					System.out.println(ststus1 + "iPhone was added to cart...");
					//Thread.sleep(2000); // For demonstration
			// Check if user see correct message about adding good to cart
					Assert.assertTrue(
							driver.findElement(By.cssSelector("body > div:nth-child(4) > div.alert.alert-success")).getText()
									.contains("Success: You have added iPhone to your shopping cart!"));
					System.out.println(ststus1 + "User see correct message, good was added...");
					//Thread.sleep(2000); // For demonstration
			//Check if cart button contain correct data
					Assert.assertEquals(driver.findElement(By.cssSelector("#cart")).getText(), "2 item(s) - $725.20");
					driver.findElement(By.cssSelector("#cart")).click();
					//Thread.sleep(2000); // For demonstration
					Thread.sleep(500); //maybe Error
			//Check if the tax is counted correctly
					Assert.assertEquals(driver
							.findElement(By.cssSelector(
									"#cart > ul > li:nth-child(2) > div > table > tbody > tr:nth-child(2) > td:nth-child(2)")).getText(), "$4.00");// eco tax
					System.out.println(ststus1 + "Eco Tax display correct...");
					System.out.println("All data display correct.");		
	}
	
	
	@Test(dependsOnMethods = { "SmokeTestOpenCart", "AddItemToCart" }, enabled = true)
	public void ChangeNumOfItemsInCart() throws Exception {
			//Add MacBook to cart
					driver.findElement(By.linkText("MacBook")).click();
					driver.findElement(By.cssSelector("#button-cart")).click();
					System.out.println(ststus1 + "McBook was added to cart...");
					// Thread.sleep(2000); // For demonstration
			// Check if user see correct message about adding good to cart
					Assert.assertTrue(driver.findElement(By.cssSelector(".alert-success"))
							.getText().contains("Success: You have added MacBook to your shopping cart!"));
					System.out.println(ststus1 + "User see correct message, good was added...");
					// Thread.sleep(2000); // For demonstration
			//Check if cart button contain correct data
					Assert.assertEquals(driver.findElement(By.cssSelector("#cart")).getText(), "1 item(s) - $602.00");
			//Open cart
					driver.manage().window().maximize();
					driver.findElement(By.linkText("Shopping Cart")).click();
					System.out.println(ststus1 + "Shopping Cart page was opened...");
					// Thread.sleep(2000); // For demonstration
			//Edit quantity
					driver.findElement(By.xpath("//*[@id=\"content\"]/form/div/table/tbody/tr/td[4]/div/input")).clear();
					driver.findElement(By.xpath("//*[@id=\"content\"]/form/div/table/tbody/tr/td[4]/div/input")).sendKeys("5");
					driver.findElement(By.cssSelector(".fa-refresh")).click();
			//Check if user see correct message about modified cart
					Assert.assertTrue(driver.findElement(By.cssSelector(".alert-success"))
							.getText().contains("Success: You have modified your shopping cart!"));
					System.out.println(ststus1 + "User see correct message, cart was modified...");
			//Check if quantity in cart display correctly
					driver.findElement(By.cssSelector("#cart")).click();
					Assert.assertEquals(driver
							.findElement(By.cssSelector(
									"#cart > ul > li:nth-child(2) > div > table > tbody > tr:nth-child(2) > td:nth-child(2)")).getText(), "$10.00");// eco tax
					System.out.println(ststus1 + "Quantity in cart display correct...");
					System.out.println("Positiv quantity editing worc correctly");
	}
	@Test(dependsOnMethods = { "SmokeTestOpenCart" }, enabled = true)
	public void ErrorMessageChangeNumOfItemsInCart() throws Exception {
		//Add MacBook to cart
		driver.findElement(By.linkText("MacBook")).click();
		driver.findElement(By.cssSelector("#button-cart")).click();
		System.out.println(ststus1 + "McBook was added to cart...");
		// Thread.sleep(2000); // For demonstration
// Check if user see correct message about adding good to cart
		Assert.assertTrue(driver.findElement(By.cssSelector(".alert-success"))
				.getText().contains("Success: You have added MacBook to your shopping cart!"));
		System.out.println(ststus1 + "User see correct message, good was added...");
		// Thread.sleep(2000); // For demonstration
//Check if cart button contain correct data
		Assert.assertEquals(driver.findElement(By.cssSelector("#cart")).getText(), "1 item(s) - $602.00");
//Open cart
		driver.manage().window().maximize();
		driver.findElement(By.linkText("Shopping Cart")).click();
		System.out.println(ststus1 + "Shopping Cart page was opened...");
		// Thread.sleep(2000); // For demonstration
//Edit quantity
		driver.findElement(By.xpath("//*[@id=\"content\"]/form/div/table/tbody/tr/td[4]/div/input")).clear();
		driver.findElement(By.xpath("//*[@id=\"content\"]/form/div/table/tbody/tr/td[4]/div/input")).sendKeys("test");
		// Thread.sleep(2000); // For demonstration
//Check Error message
		Assert.assertTrue(driver.findElement(By.cssSelector(".alert-success"))
				.getText().contains("Warning: Input valid data!"));
		System.out.println("User see correct messageabout warning.");
	}
}
