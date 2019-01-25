package com.jbk;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class TestNGGrid {
	public WebDriver driver;
	public String URL, Node;
	
	@Parameters("browser")
	@BeforeTest
	public void launchapp(String browser) throws MalformedURLException 
		{
		String URL = "file:///D:/Java%20study/Selenium%20Software/Offline%20Website/index.html";
	
	if (browser.equalsIgnoreCase("firefox")) 
		{
		System.out.println(" Executing on FireFox");
		String Node = "http://localhost:5555/wd/hub";
		DesiredCapabilities cap = DesiredCapabilities.firefox();
		cap.setBrowserName("firefox");
		driver = new RemoteWebDriver(new URL(Node), cap);
		// Puts an Implicit wait, Will wait for 10 seconds before throwing
		// exception
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		// Launch web site
		driver.navigate().to(URL);
		driver.manage().window().maximize();
		}
	else if (browser.equalsIgnoreCase("chrome")) 
			{
				try
				{
					System.out.println(" Executing on CHROME");
					DesiredCapabilities cap = DesiredCapabilities.chrome();
					cap.setBrowserName("chrome");
					String Node = "http://localhost:5557/wd/hub";
					driver = new RemoteWebDriver(new URL(Node), cap);
					driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
					// Launch website
					driver.navigate().to(URL);
					driver.manage().window().maximize();
				}
				catch (Exception e)
				{
				e.printStackTrace();
				}
			}
		else
			{
			throw new IllegalArgumentException("The Browser Type is Undefined");
			}
		}
	
	@Test(priority=1)
	public void testUnameBlankMessage() {
	// Maximize window
	driver.manage().window().maximize();
	// locate email element
	WebElement userEle = driver.findElement(By.id("email"));
	// enter uname into text box
	userEle.sendKeys("mangesh");
	// locate password element
	WebElement passwordEle = driver.findElement(By.id("password"));
	// enter password into text box
	passwordEle.sendKeys("1234");
	// locate signin button
	WebElement signInbuttonEle = driver.findElement(By.xpath(".//*[@id='form']/div[3]/div/button"));
	// click signin button
	signInbuttonEle.click();
	String unameErrorMessage = driver.findElement(By.id("email_error")).getText();
	String emailErr="Please enter email as kiran@gmail.com";
	Assert.assertEquals(emailErr, unameErrorMessage);
	
	}
	@Test(priority=2)
	public void verifyTitle() throws InterruptedException 
	{
		String title=driver.getTitle();
		Assert.assertEquals("AdminLTE 2 | Log in",title);
		
	}
	
	@Test(priority=3)
	public void teardown()
	{
	driver.close();
	}
	
}
