package com.retailmenot.tests;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.retailmenot.pages.HomePage;
import com.retailmenot.pages.HotProductDealsPage;
import com.retailmenot.util.AppLibrary;

public class RetailMeNotTests {

	AppLibrary appLib;
	WebDriver driver;
	HomePage hPage;
	HotProductDealsPage dealsPage;

	public WebDriver getDriverInstance(String browserType) {
		if (browserType.equals("FF")) {
			driver = new FirefoxDriver();
		} else if (browserType.equals("IE")) {
			driver = new InternetExplorerDriver();
		} else {
			driver = new ChromeDriver();
		}
		return driver;
	}

	@Parameters({ "browserType", "url" })
	@BeforeClass
	public void invokeBrowser(String browserType, String url) {
		appLib = new AppLibrary();
		driver = this.getDriverInstance(browserType);
		hPage = new HomePage(driver, url);
		dealsPage = new HotProductDealsPage(driver);
	}

	/**
	 * Read the InputData from Excel
	 * 
	 */
	@DataProvider(name = "hotProductDeals")
	public String[][] getExcelValues() throws Exception {
		String str[][] = appLib
				.readExcel("C:\\work\\workspace\\RetailMeNotProject\\RetailMeNotApp.xls");
		return str;
	}

	@Parameters({ "url" })
	@Test
	public void browseDeals(String url) {
		hPage.launchApp(url);
		dealsPage = hPage.browseProductDeals();
		boolean result = dealsPage.verifyText("Hot Product Deals");
		Assert.assertTrue(result);
	}

	@Test(dataProvider = "hotProductDeals", dependsOnMethods = "browseDeals")
	public void verifyDealResults(String hotProductDeals) {

		System.out.println("hotproductDeals" + hotProductDeals);
		int dealsCount = dealsPage.getProductDealsCount(hotProductDeals);

		Reporter.log("The Count of Hot Product Deal Item  " + hotProductDeals
				+ "is -->" + dealsCount);
		System.out.println("The Count of Hot Product Deal Item  "
				+ hotProductDeals + "is -->" + dealsCount);

		int couponCount = dealsPage.getCouponCount(hotProductDeals);
		int saleCount = dealsPage.getSaleCount(hotProductDeals);

		Reporter.log("Number of times the Coupon at is displayed  "
				+ hotProductDeals + "-->" + couponCount);
		System.out.println("Number of times the Coupon at is displayed  "
				+ hotProductDeals + "-->" + couponCount);

		Reporter.log("Number of times the Sale at is displayed  "
				+ hotProductDeals + "-->" + saleCount);
		System.out.println("Number of times the Sale at is displayed  "
				+ hotProductDeals + "-->" + saleCount);

		dealsPage.seeOfferAndCode(hotProductDeals);
		String offerCode = dealsPage.getOffer();
		String couponCode = dealsPage.getCouponCode();

		Reporter.log("The couponDetais are as -->" + offerCode);
		System.out.println("The couponDetais are as -->" + offerCode);

		Reporter.log("The couponCode are as-->" + couponCode);
		System.out.println("The couponCode are as-->" + couponCode);

	}
	@AfterClass
	public void postTest(){
		driver.close();
	}
}

