package SauceLabs;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;

import graphql.Assert;


public class SauceLogin {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		System.setProperty("webdriver.chrome.driver", "D:\\driver\\chromedriver-win64\\chromedriver-win64\\chromedriver.exe");
		WebDriver driver=new ChromeDriver();
		
//		System.setProperty("webdriver.edge.driver", "D:\\driver\\edgedriver_win64\\msedgedriver.exe");
//		 WebDriver driver=new EdgeDriver();
		
		
		driver.navigate().to("https://www.saucedemo.com/v1/");
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		driver.manage().window().maximize();
		
		WebElement userName=driver.findElement(By.name("user-name"));
		WebElement password=driver.findElement(By.name("password"));
		WebElement loginButton=driver.findElement(By.id("login-button"));
		
		userName.sendKeys("standard_user");
		password.sendKeys("secret_sauce");
		loginButton.click();
		
	//	WebElement appLogoName=driver.findElement(By.xpath("//div[@class='app_logo']"));
		String actualTitle=driver.getTitle();
		String expectedTitle="Swag Labs";
		
		System.out.println("The name is "+actualTitle);
		
		Assert.assertTrue(actualTitle.equals(expectedTitle));
		
		driver.close();

	}

}
