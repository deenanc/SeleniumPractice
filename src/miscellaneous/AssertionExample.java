package miscellaneous;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;


public class AssertionExample {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
//		System.setProperty("webdriver.chrome.driver", "D:\\driver\\chromedriver-win64\\chromedriver-win64\\chromedriver.exe");
//		WebDriver driver=new ChromeDriver();
		
		System.setProperty("webdriver.edge.driver", "D:\\driver\\edgedriver_win64\\msedgedriver.exe");
		WebDriver driver=new EdgeDriver();
		
		driver.navigate().to("https://www.flipkart.com/");
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		driver.manage().window().maximize();
		
		WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(20000));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[text()='Two Wheelers']")));
		
		String expectedTitle="Online Shopping Site for Mobiles, Electronics, Furniture, Grocery, Lifestyle, Books & More. Best Offers!";
		String actualTitle=driver.getTitle();
		

		   try {
	            Assert.assertEquals(actualTitle, expectedTitle, "Title does not match!");
	            System.out.println("Test Passed: Title matches.");
	        } catch (AssertionError e) {
	            System.out.println("Test Failed: " + e.getMessage());
	        }
		   
		
		driver.quit();

	}

}
