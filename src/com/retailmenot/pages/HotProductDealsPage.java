package com.retailmenot.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class HotProductDealsPage extends WebPage {

	private By hotDealsLink = By.xpath("//h1[text()='Hot Product Deals']");

	// TODO This can be fetched from a external prop or xml file also.
	private By getCountXPath(String category, String type) {
		return By.xpath("//section[@id='" + category+ "']/div/div/div/span[contains(text(),'" + type + "')]");
	}

	// TODO This can be fetched from a external prop or xml file also.
	private By getOfferCodeXPath(String category) {
		return By.xpath("//section[@id='" + category+ "']//span[contains(text(),'Coupon')]/a");
	}

	public HotProductDealsPage(WebDriver driver) {
		this.driver = driver;
	}

	public boolean verifyText(String expected) {
		String actual = driver.findElement(hotDealsLink).getText();
		boolean b = false;
		if (actual.equals(expected)) {
			b = true;
		}
		return b;

	}

	public int getProductDealsCount(String dealType) {
		WebElement findElement = driver.findElement(By.linkText(dealType));
		if (findElement == null) {
			return 0;
		}
		findElement.click();

		String dealsParentXpath = null;
		String dealsChildXpath = null;
		if (dealType.contains("Home")) {
			dealsParentXpath = "//section[@id='homekitchen']/div";
			dealsChildXpath = "//section[@id='homekitchen']/div/div";
		} else if (dealType.contains("Electronics")) {

			dealsParentXpath = "//section[@id='Electronics']/div";
			dealsChildXpath = "//section[@id='Electronics']/div/div";
		} else if (dealType.contains("Accessories")) {
			dealsParentXpath = "//section[@id='Accessories']/div";
			dealsChildXpath = "//section[@id='Accessories']/div/div";
		}

		System.out.println("Deals xpath " + dealsParentXpath);
		System.out.println("Deals xpath " + dealsChildXpath);

		List<WebElement> productList = null;

		List<WebElement> rows = driver.findElements(By.xpath(dealsParentXpath));
		int count = rows.size();

		for (int i = 0; i <= count; i++) {
			productList = driver.findElements(By.xpath(dealsChildXpath));

		}

		return productList.size();

	}

	public int getCouponCount(String dealType) {
		String dealsParentXpath = null;
		By dealsChildXpath = null;
		if (dealType.contains("Home")) {
			dealsParentXpath = "//section[@id='homekitchen']/div";
			dealsChildXpath = getCountXPath("homekitchen", "Coupon");
		} else if (dealType.contains("Electronics")) {
			dealsParentXpath = "//section[@id='Electronics']/div";
			dealsChildXpath = getCountXPath("Electronics", "Coupon");
		} else if (dealType.contains("Accessories")) {
			dealsParentXpath = "//section[@id='Accessories']/div";
			dealsChildXpath = getCountXPath("Accessories", "Coupon");
		}

		List<WebElement> couponList = null;

		List<WebElement> rows = driver.findElements(By.xpath(dealsParentXpath));
		int count = rows != null ? rows.size() : 0;

		couponList = count > 0 ? driver.findElements(dealsChildXpath) : null;

		return couponList != null ? couponList.size() : 0;
	}

	public int getSaleCount(String dealType) {
		String dealsParentXpath = null;
		By dealsChildXpath = null;
		if (dealType.contains("Home")) {
			dealsParentXpath = "//section[@id='homekitchen']/div";
			dealsChildXpath = getCountXPath("homekitchen", "Sale");
		} else if (dealType.contains("Electronics")) {
			dealsParentXpath = "//section[@id='Electronics']/div";
			dealsChildXpath = getCountXPath("Electronics", "Sale");
		} else if (dealType.contains("Accessories")) {
			dealsParentXpath = "//section[@id='Accessories']/div";
			dealsChildXpath = getCountXPath("Accessories", "Sale");
		}

		List<WebElement> saleList = null;

		List<WebElement> rows = driver.findElements(By.xpath(dealsParentXpath));
		int count = rows.size();

		for (int i = 0; i <= count; i++) {
			saleList = driver.findElements(dealsChildXpath);

		}

		return saleList.size();
	}

	public void seeOfferAndCode(String dealType) {
		By clickCouponXpath = null;
		if (dealType.contains("Home")) {
			clickCouponXpath = getOfferCodeXPath("homekitchen");
		} else if (dealType.contains("Electronics")) {
			clickCouponXpath = getOfferCodeXPath("Electronics");
		} else if (dealType.contains("Accessories")) {
			clickCouponXpath = getOfferCodeXPath("Accessories");
		}

		if(clickCouponXpath != null){
			System.out.println("I am in seeOffer and code " + clickCouponXpath);
			WebElement findElement = driver.findElement(clickCouponXpath);
			if (findElement != null && findElement.isEnabled()) {
				findElement.click();
			}
		}
	}

	public String getOffer() {
		String offer = null;
		WebElement findElement = driver.findElement(By.xpath("//div[@id='couponModal']/div/div[2]/div/div//p"));
		if (!findElement.isDisplayed()) {
			offer = findElement.getAttribute("innerHTML");
		} else {
			offer = findElement.getText();
		}
		return offer;
	}

	public String getCouponCode() {
		String couponCode = null;
		WebElement findElement = driver.findElement(By.xpath("//div[@id='couponModal']/div/div[2]/div/section/div/span"));
		if (!findElement.isDisplayed()) {
			couponCode = findElement.getAttribute("innerHTML");
		} else {
			couponCode = findElement.getText();
		}
		return couponCode;
	}
	
	

}
