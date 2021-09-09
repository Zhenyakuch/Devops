package testng;
import java.io.File;
import java.io.IOException;
import java.net.URL;

import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.google.common.io.Files;

import mail_sending.SendMaill;

public class Tests extends SendMaill {		
	    private WebDriver driver;		
		
	    @Test				
	    public void testEasy() throws IOException, InterruptedException {
	    	
	    	try{	    	
				driver.get("https://10.103.1.212"); 
				
				WebDriverWait wait112 = new WebDriverWait(driver, 10);
				WebElement element112 = wait112.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@id='details-button']")));
				element112.click();
				
				WebDriverWait wait134 = new WebDriverWait(driver, 10);
				WebElement element134 = wait134.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[@id='proceed-link']")));
				element134.click();
				
				WebDriverWait wait1 = new WebDriverWait(driver, 10);
				WebElement element1 = wait1.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@id='username']")));
				element1.sendKeys("admin");		
				
				WebDriverWait wait3 = new WebDriverWait(driver, 10);
				WebElement element3 = wait3.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@id='password']")));
				element3.sendKeys("west666");
				 
					
				Select select = new Select(driver.findElement(By.tagName("select")));
		        select.selectByVisibleText(" Локальная проверка подлинности ");
		           
		        
		      
				 WebDriverWait wait55 = new WebDriverWait(driver, 15);
				 WebElement element55 = wait55.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@value='Вход']")));
				 element55.click();
				 
				 driver.get("https://10.103.1.212/SetUpWizard.do?forwardTo=LDAP");  
				 
				
				 

		    	}
		    	catch (Exception e) {
			       	   File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
			  	    	   Files.copy(scrFile, new File("C:\\Users\\seluser\\screenshot.png"));
			  	    	   SendMaill.email("HelpDesk","HelpDesk@nces.by","autotest@nces.by",e);
			          }
	    	
	    	try {
			    	WebElement temp = driver.findElement(By.xpath("//*[@id=\"LDAPDomainsView_TABLE\"]/tbody/tr[4]/td[7]/a/img"));
					temp.click();
	    	}catch (Exception e1)
	    	{ System.out.println(" исключение чтобы не слать на почту");}
			 
			 
		    }
			
		@BeforeTest
		public void beforeTest() {	
			 try { 	 
				ChromeOptions capability = new ChromeOptions();
				capability.setCapability("ignoreZoomSetting", true);
				driver=new RemoteWebDriver(new URL("http://localhost:5558/wd/hub"), capability);
				driver.manage().window().maximize();
			        } catch (Exception e) {
			            throw new IllegalStateException("Can't start Web Driver", e);
			        }
			  
			    } 
			
		@AfterTest
		public void afterTest() {
			
			driver.quit();
		}	
}	

