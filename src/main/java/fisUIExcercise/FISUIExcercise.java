package fisUIExcercise;

import java.util.Set;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;
import listeners.ListenersTestNG;
import pageObject.BooksPage;

@Listeners(ListenersTestNG.class)
public class FISUIExcercise {
	
	WebDriver driver;
	String mainWindow;
	
	
	
	@BeforeTest
	public void setUp() {
		WebDriverManager.edgedriver().setup();
		driver = new EdgeDriver();
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
		driver.get("https://www.ebay.com");
		System.out.println("Main Window Page Title: "+driver.getTitle());
		
	}
	
	@Test
	public void eBayTest() {
		
		String expectedText = "Buy It Now";
		BooksPage booksPage = PageFactory.initElements(driver, BooksPage.class);
		
		booksPage.search_box.sendKeys("Book");
		booksPage.search_box.sendKeys(Keys.ENTER);
		mainWindow = driver.getWindowHandle();
		booksPage.item_list.get(0).click();
		Set<String> windows = driver.getWindowHandles();
		System.out.println("Number of windows count: "+windows.size());
		for(String s : windows) {
			if(s.equals(mainWindow)) {}
			else {driver.switchTo().window(s);}
		}
		
		Assert.assertEquals(booksPage.buy_it_btn.getText(), expectedText);
		
	}
	
	@Test(dependsOnMethods = "eBayTest")
	public void subWindowTest() {
		BooksPage booksPage = PageFactory.initElements(driver, BooksPage.class);
		booksPage.add_cart_button.click();
		WebDriverWait wait = new WebDriverWait(driver , 5);
		WebElement cartCount = wait.until(ExpectedConditions.visibilityOf(booksPage.add_cart_items_count));
		
		int cartItemsCount = Integer.parseInt(cartCount.getText());
		Assert.assertEquals(cartItemsCount , 1);
		
		driver.close();
		driver.switchTo().window(mainWindow);
		
	}
	
	@AfterTest
	public void tearDown() {
		driver.close();
		driver.quit();
	}
	
	

}
