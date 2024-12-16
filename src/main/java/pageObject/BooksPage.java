package pageObject;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class BooksPage {
	
	@FindBy(xpath = "//div[@id='gh-ac-box2']/input[1]")
	public WebElement search_box;

	@FindBy(xpath = "//ul//li[starts-with(@id,'item')]//img") 
	public List<WebElement> item_list;
	
	@FindBy(xpath = "//a[contains(@id,'atcBtn_btn')]") 
	public WebElement add_cart_button;
	
	@FindBy(xpath = "//li[@id='gh-minicart-hover']//*[@id='gh-cart-n']") 
	public WebElement add_cart_items_count;
	
	@FindBy(xpath = "//a[contains(@id,'binBtn_btn')]") 
	public WebElement buy_it_btn;
	
	
}
