package Multiple_Records;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class GMAP {
	
	String csv_path = "D:\\Records\\records_256-359.csv";
	WebDriver driver;
	
	@BeforeTest
	public void login()
	{
		//System.setProperty("webdriver.chrome.driver", "D:\\chromedriver.exe");
		driver = new FirefoxDriver();
		//driver = new ChromeDriver();
	    driver.manage().window().maximize();
	    driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
	    driver.get("https://rpp.rpdata.com/rpp/login.html;jsessionid=be1fa1cf813920fb895339384d1e?login_error=5");
	  
	    //login here
	  
	    driver.findElement(By.id("j_username")).sendKeys("mpadisetti");
	    driver.findElement(By.id("j_password")).sendKeys("roshan2013");
	    driver.findElement(By.xpath("//input[@value='Login']")).click();
	}

	 @Test
	 public void getAvmReport() throws IOException, InterruptedException
	 {
		 BufferedReader br = new BufferedReader(new FileReader(csv_path));
		 String line;
		 int linenumber = 0;
		 driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
			String main = "";
		    String fileName = "D:\\gmap_records.csv";
			FileWriter writer = new FileWriter(fileName);
			String FILE_HEADER = "Street;Address;Suburb;State;Post Code;Local Authority;Land Use;Current Zoning;Up Zoning;Site Area;Description;Lot;Plan;Site type;Adopted;Height;Density;Owner;GMAP";
			writer.append(FILE_HEADER);
			writer.append("\n"); 
			
			while ((line = br.readLine()) != null) {
			  if(linenumber>1496)
			  { 
				     String[] cols = line.split(";");
				     String a = cols[0];
				     String b = cols[1];
				     String c = cols[2];
				     String d = cols[3];
				     String e = cols[4];
				    
					driver.findElement(By.xpath("//div[@id='searchAddressSimple']/input")).sendKeys(a+' '+b+' '+c+' '+d);
					driver.findElement(By.cssSelector("a.gradientSiteColor span")).click();
					
					if(driver.getPageSource().contains("No properties with this address can be located."))
					{
						driver.get("https://rpp.rpdata.com/rpp/dashboard.html?execution=e1s1");
					}
					else
					{
						
					  if(driver.getPageSource().contains("To begin an activity, select one or more properties from the View All list."))
					  {	 
						   driver.findElement(By.cssSelector(".cellData .floatLeft .thumbnail a")).click();
						   driver.findElement(By.linkText("Google Map")).click();
					  }
				      else
				      {
				    	  driver.findElement(By.linkText("Google Map")).click();
				      }
						
						  String winHandleBefore = driver.getWindowHandle();
						  for (String winHandle : driver.getWindowHandles()) {
						    driver.switchTo().window(winHandle); 
						  }
						
						main="";
						for(int j=0; j<cols.length; j++)
						{
								
							 main += cols[j]+';';
							 
						}
						System.out.println(driver.getCurrentUrl());
						try {    
						    writer.append(main+driver.getCurrentUrl());
						    writer.append("\n");
					     }	
					     catch (IOException ex) {
						    ex.printStackTrace();    
						 }
						
						driver.close();
						
						driver.switchTo().window(winHandleBefore);
						
						driver.get("https://rpp.rpdata.com/rpp/dashboard.html?execution=e1s1");

					  }
				  }
				 linenumber++;
			}		 
			
		    writer.flush();
	        writer.close();
	 }
} 
