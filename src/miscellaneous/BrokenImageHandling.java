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

public class BrokenImageHandling {
	
	public static boolean isImageBroken(String imgURL) {
		try {
			URL url=new URL(imgURL);
			HttpURLConnection connection=(HttpURLConnection)url.openConnection();
			connection.setRequestMethod("HEAD");
			connection.setConnectTimeout(4000);
			connection.connect();
			
			int responseCode=connection.getResponseCode();
			return responseCode>=400;
		}catch(Exception e) {
			System.out.println("Error checking image "+imgURL+" - "+e.getMessage());
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
		
		List<WebElement> imageList=driver.findElements(By.xpath("//img"));
		
		System.out.println("Total images found "+imageList.size());
		
		for(WebElement img:imageList) {
			String imgURL=img.getAttribute("src");
			
			if(imgURL==null||imgURL.isEmpty()) {
				System.out.println("Image missing 'src' attribute");
				continue;
			}
			
			if(isImageBroken(imgURL)) {
				System.out.println("Broken image "+imgURL);
			}else {
				System.out.println("Valid image "+imgURL);
			}
		}
		
		driver.quit();

	}

}
