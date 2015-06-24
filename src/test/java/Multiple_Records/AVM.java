package Multiple_Records;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.dnd.peer.DropTargetPeer;
import java.awt.event.KeyEvent;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class AVM {
	
	String csv_path = "D:\\Records\\records_151-250.csv";
	WebDriver driver;
	
	
	@BeforeTest
	public void login()
	{
		driver = new FirefoxDriver();
	    driver.manage().window().maximize();
	    driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
	    driver.get("https://rpp.rpdata.com/rpp/login.html;jsessionid=be1fa1cf813920fb895339384d1e?login_error=5");
	  
	    //login here
	  
	    driver.findElement(By.id("j_username")).sendKeys("mpadisetti");
	    driver.findElement(By.id("j_password")).sendKeys("roshan2013");
	    driver.findElement(By.xpath("//input[@value='Login']")).click();
	}

	 @Test
	 public void getAvmReport() throws IOException, InterruptedException, AWTException
	 {
		 BufferedReader br = new BufferedReader(new FileReader(csv_path));
		 String line;
		 int linenumber = 0;
		 driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
			String main = "";
		    String fileName = "d:\\avm_records.csv";
			FileWriter writer = new FileWriter(fileName);
			String FILE_HEADER = "Street;Address;Suburb;State;Post Code;Local Authority;Land Use;Current Zoning;Up Zoning;Site Area;Description;Lot;Plan;Site type;Adopted;Height;Density;AVM;";
			writer.append(FILE_HEADER);
			writer.append("\n"); 
			
			while ((line = br.readLine()) != null) {
				
				 if(linenumber>1380)
				 { 
				     String[] cols = line.split(";");
				     String a2 = cols[0];
				     String a = a2.replace(" ","-");
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
						 driver.findElement(By.cssSelector(".taskToolbar UL LI.avm span label")).click();
					   }
				       else
				       {
				    	 driver.findElement(By.cssSelector(".taskToolbar UL LI.avm span label")).click();
				       }
					
					String winHandleBefore = driver.getWindowHandle(); 
					 
					for (String winHandle : driver.getWindowHandles()) {
					    driver.switchTo().window(winHandle); 
					}
					
					Select sel = new Select(driver.findElement(By.id("avmPropertyDataUpdateForm:propertyType")));
					sel.selectByVisibleText("House");
					
					Select sel1 = new Select(driver.findElement(By.id("avmPropertyDataUpdateForm:updateType")));
					sel1.selectByVisibleText("Updates are current and accurate");
					driver.findElement(By.id("avmPropertyDataUpdateForm:generateAVM")).click();
					
					driver.findElement(By.id("agreementsForm:doNotDisplay")).click();
					driver.findElement(By.xpath("//input[@value='I Agree']")).click();
					if(driver.getPageSource().contains("Sorry, the Valuation confidence level is too low."))
					{
						
					}
					else
					{	
					Thread.sleep(5000);
					Robot rb =new Robot();
					rb.keyPress(KeyEvent.VK_ENTER);
					
					String a1 = a.replace(" ", "_");
					String b1 = b.replace(" ", "_");
					String c1 = c.replace(" ", "_");
					String d1 = d.replace(" ", "_");
					String e1 = e.replace(" ", "_");
					
					
					String getpdf = "C:\\Users\\attune60\\Downloads\\AVM_Pdf\\"+a1+"_"+b1+"_"+c1+"_"+d1+"_"+e1+".pdf;";
					
					main="";
					for(int j=0; j<cols.length; j++)
					{
							
						 main += cols[j]+';';
						 
					}
					
					try {    
					    writer.append(main+';'+';'+';'+';'+getpdf);
					    writer.append("\n");
				     }	
				     catch (IOException ex) {
					    ex.printStackTrace();    
					 }
					
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
