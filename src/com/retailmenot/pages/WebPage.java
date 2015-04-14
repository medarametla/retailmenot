package com.retailmenot.pages;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;

public class WebPage {
	WebDriver driver;

	public void launchApp(String url) {

		driver.get(url);
		driver.manage().deleteAllCookies();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);

	}

	public boolean verifyTitle(String expected) {
		String actual = driver.getTitle();
		boolean b = false;
		if (actual.equals(expected)) {
			b = true;
		}
		return b;
	}

}
