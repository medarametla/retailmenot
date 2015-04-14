package com.retailmenot.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class HomePage extends WebPage{

	//TODO - Move the elements to an external files like properties or xml if you want to localize etc.
	private By couponsLink=By.linkText("Coupons");
	private By productDealsLink=By.linkText("Product Deals");
	
	public HomePage(WebDriver driver, String url) 
	{
		this.driver= driver;
		driver.get(url);
	
			
	}
	public HotProductDealsPage browseProductDeals()
	{
		driver.findElement(couponsLink).click();
		driver.findElement(productDealsLink).click();	     
	    return new HotProductDealsPage(driver);

	}
	 

}
