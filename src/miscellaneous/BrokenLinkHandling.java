package miscellaneous;

import java.net.HttpURLConnection;
import java.net.URL;
import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BrokenLinkHandling {
	
	public static boolean isLinkBroken(String linkURL) {
		try {
		URL url=new URL(linkURL);
		HttpURLConnection connection=(HttpURLConnection)url.openConnection();
		connection.setRequestMethod("HEAD");
		connection.setConnectTimeout(4000);
		connection.connect();
		
		int responseCode=connection.getResponseCode();
		return responseCode>=400;
		}catch(Exception e) {
			System.out.println("Error checking link "+linkURL+" - "+e.getMessage());
			return true;
		}
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		System.setProperty("webdriver.chrome.driver", "D:\\driver\\chromedriver-win64\\chromedriver-win64\\chromedriver.exe");
		WebDriver driver=new ChromeDriver();
		
		driver.navigate().to("https://qa.cdpfnol.nprd.aig.com/cdp/nordics/no/en/ah");
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		driver.manage().window().maximize();
		
		WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(20000));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//img[@alt='linkedIn']")));
		
		List<WebElement> allLinks=driver.findElements(By.xpath("//a"));
		
		System.out.println("Total links are "+allLinks.size());
		
		for(WebElement link:allLinks) {
			String linkURL=link.getAttribute("href");
			
			if(linkURL==null||linkURL.isEmpty()) {
				System.out.println("Link is missing 'href' attribute");
				continue;
			}
			
			if(isLinkBroken(linkURL)) {
				System.out.println("Invalid Link "+linkURL);
			}else {
				System.out.println("Valid Link "+linkURL);
			}
		}
		driver.quit();
	}

}
