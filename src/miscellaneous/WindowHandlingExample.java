package miscellaneous;

import java.time.Duration;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class WindowHandlingExample {

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
		
		System.setProperty("webdriver.chrome.driver", "D:\\driver\\chromedriver-win64\\chromedriver-win64\\chromedriver.exe");
		WebDriver driver=new ChromeDriver();
		
		driver.navigate().to("https://www.flipkart.com/");
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		driver.manage().window().maximize();
		
		WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(20000));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[text()='Two Wheelers']")));
		
		String mainWinndow=driver.getWindowHandle();
		
		WebElement searchTextbox=driver.findElement(By.xpath("//input[@name='q']"));
		searchTextbox.sendKeys("Pixel");
		searchTextbox.sendKeys(Keys.ENTER);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[text()='Newest First']")));
		
		WebElement pixel7aSea128GB=driver.findElement(By.xpath("//div[text()='Google Pixel 7a (Sea, 128 GB)']"));
		pixel7aSea128GB.click();
		
		Set<String> allWindowHandles=driver.getWindowHandles();
		
		for(String handle:allWindowHandles) {
			if(!handle.equals(mainWinndow)) {
				driver.switchTo().window(handle);
				Thread.sleep(8000);
				String price=driver.findElement(By.xpath("(//div[contains(text(),'₹')])[2]")).getText();
				System.out.println("The price is "+price);
			}
			driver.switchTo().window(mainWinndow);
		}
		
		((org.openqa.selenium.JavascriptExecutor) driver).executeScript("window.open()");
		driver.close();
		
//		driver.switchTo().defaultContent();
		
//		driver.quit();

	}

}
