package engine;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import base.Base;

public class KeywordEngine  {
	WebDriver driver;
	Properties prop;
	public static Workbook book;
	public Sheet sheet;
	
	String locatorName=null, locatorValue=null, actionName=null, actionValue=null;
	WebElement element;
	
	public final String SCENARIO_SHEET = "C:\\Sujeet\\codebase\\HubSpotFramework\\src\\main\\java\\scenarios\\hubspot_scenarios.xlsx";
	
	Base base;
	
	public void startExecution(String sheetName)
	{
		FileInputStream file = null;
		try 
		{
			file = new FileInputStream(SCENARIO_SHEET);
		} catch (FileNotFoundException e) 
		{
			e.printStackTrace();
		}
		
		try {
			book=WorkbookFactory.create(file);
		} catch (EncryptedDocumentException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		sheet = book.getSheet(sheetName);
		
		
		
		for(int i=0;i<sheet.getLastRowNum(); i++)
		{
			int k=0;
			String locatorColValue = sheet.getRow(i+1).getCell(k+1).toString().trim();
			try {
			if(!locatorColValue.equalsIgnoreCase("na"))
			{
				locatorName = locatorColValue.split("=")[0].trim();
				locatorValue= locatorColValue.split("=")[1].trim();
			}else
			{
				locatorName = "NA";					
			}
			
				actionName=sheet.getRow(i+1).getCell(k+2).toString().trim();
				actionValue=sheet.getRow(i+1).getCell(k+3).toString().trim();
				
			switch(actionName) 
			{
			case "open browser":
				base = new Base();
				base.init_properties();
				if(actionValue.isEmpty() || actionValue.equalsIgnoreCase("na"))
				{
					driver = base.init_driver(prop.getProperty("browser"));
				}
				else
				{
					driver = base.init_driver(actionValue);
				}
				break;
			
			case "enter url":
				if(actionValue.isEmpty() || actionValue.equalsIgnoreCase("na"))
				{
					driver.get(prop.getProperty("url"));
					Thread.sleep(5000);
				}
				else
				{
					driver.get(actionValue);
					Thread.sleep(5000);
				}
				break;
			case "quit":
				driver.quit();
				break;
			
			default:
				break;
				
			}
			
			switch(locatorName)
			{
			case "id":
				element = driver.findElement(By.id(locatorValue));
				if(actionName.equalsIgnoreCase("sendkeys"))
				{
					element.sendKeys(actionValue);
					element.clear();
				} else if(actionName.equalsIgnoreCase("click"))
				{
					element.click();
				}
				locatorName=null;
				break;
			
			case "linkText":
				element=driver.findElement(By.linkText(locatorValue));
				element.click();
				locatorName=null;
				break;
			
			case "NA":
				continue;
				
			default:
				break;
				
			}
			}
		catch(Exception e)
		{
			e.printStackTrace();	
		}
				
			}
		}}
	

