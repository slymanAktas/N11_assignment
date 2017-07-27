package utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
//import org.openqa.selenium.firefox.FirefoxDriver;
import java.io.File;
import java.util.concurrent.TimeUnit;

public class browserPage {
	
	public static WebDriver driver;
	
	public void launchBrowser() {

		File chrome_file = new File("/Users/suleymanaktas/Downloads/drivers/chromedriver");

		System.setProperty("webdriver.chrome.driver",chrome_file.getAbsolutePath() );
	    driver = new ChromeDriver();
      //System.setProperty("webdriver.gecko.driver", "/Users/suleymanaktas/Downloads/geckodriver");
	  //driver=new FirefoxDriver();
		driver.get("http://www.n11.com");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

	}
	
	public void closeBrowser(){

		driver.close();
	}

}
