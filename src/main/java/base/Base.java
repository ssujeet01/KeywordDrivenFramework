package base;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class Base 
{

	public WebDriver driver;
	public Properties prop;
	
		public WebDriver init_driver(String browserName)
		{
			if(browserName.equalsIgnoreCase("chrome"))
			{
				System.setProperty("webdriver.chrome.driver", "chromedriver.exe");
				if(prop.getProperty("headless").equalsIgnoreCase("yes"))
				{
					ChromeOptions options = new ChromeOptions();
					options.addArguments("--headless");
				}
				else
				{
					driver= new ChromeDriver();
				}
			}
			
			return driver;
		}
	
		public Properties init_properties()
		{
			prop = new Properties();
			try 
			{
				FileInputStream fis = new FileInputStream("C:\\Sujeet\\codebase\\HubSpotFramework\\src\\main\\java\\appconfig\\Config.properties");
				prop.load(fis);
			} catch (FileNotFoundException e) 
			{
				e.printStackTrace();
			} catch (IOException e) {
				
				e.printStackTrace();
			}
			return prop;
		}
}
