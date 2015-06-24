package Multiple_Records;


import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;



public class rec {
	 
	String csv_path = "D:\\Records\\records-11-50.csv";
	WebDriver driver;
	
	
	@BeforeTest
	public void login()
	{
		FirefoxProfile profile = new FirefoxProfile();
		profile.setPreference("webdriver.load.strategy", "unstable");
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
	 public void cmaDataRead() throws IOException, AWTException, InterruptedException
	 {
		
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS); 
		BufferedReader br = new BufferedReader(new FileReader(csv_path));
		String line;
		int linenumber = 0;
		
		String main = "";
	    String fileName = "d:\\records1.csv";
		FileWriter writer = new FileWriter(fileName);
		String FILE_HEADER = "Street;Address;Suburb;State;Post Code;Local Authority;Land Use;Current Zoning;Up Zoning;Site Area;Description;Lot;Plan;Site type;Adopted;Height;Density;Owner;CMA;";
		writer.append(FILE_HEADER);
		writer.append("\n");
		
		while ((line = br.readLine()) != null) {
			 if(linenumber>17)
			 { 
			     String[] cols = line.split(";");
			     String a = cols[0];
			     String b = cols[1];
			     String c = cols[2];
			     String d = cols[3];
			     String e = cols[4];
			 
			 

			     
				driver.findElement(By.xpath("//div[@id='searchAddressSimple']/input")).sendKeys(a+' '+b+' '+c+' '+d);
				driver.findElement(By.cssSelector("a.gradientSiteColor span")).click();
				//driver.findElement(By.linkText("Google Map")).click();
				//String Gmap = driver.getCurrentUrl();
				//System.out.println(Gmap);
				
				String winHandleBefore = driver.getWindowHandle();
				
				driver.findElement(By.cssSelector(".taskToolbar UL LI.cma span label")).click();
				for (String winHandle : driver.getWindowHandles()) {
				    driver.switchTo().window(winHandle); 
				}
				
				
				/*if(driver.getCurrentUrl() != "https://rpp.rpdata.com/rpp/flow/cma.html?execution=e2s3")
				{	
				driver.findElement(By.linkText("Start a new CMA for this property")).click();
				} */
			    Thread.sleep(15000);
				//driver.findElement(By.cssSelector("#containerWorkFlow #containerSteps li#selectCma a .mid")).click();
				//driver.findElement(By.id("myCmasForm:j_id494")).click();
				//System.out.println(driver.getCurrentUrl());
				//driver.findElement(By.linkText("Start a new CMA for this property")).click();
				driver.findElement(By.linkText(("Select all"))).click();
				driver.findElement(By.id(("multiLaunchpadForm:btnNext"))).click();
				driver.findElement(By.linkText(("Select all"))).click();
				driver.findElement(By.id(("multiLaunchpadForm:btnNext"))).click();
				driver.findElement(By.linkText(("Select all"))).click();
				driver.findElement(By.id(("multiLaunchpadForm:btnNext"))).click();
				driver.findElement(By.linkText(("Select all"))).click();
				//driver.findElement(By.id(("multiLaunchpadForm:btnNext"))).click();
				driver.findElement(By.id(("navigation:j_id97:7:btnNextPdf"))).click();
			    Thread.sleep(50000);
				Robot rb =new Robot();
				rb.keyPress(KeyEvent.VK_ENTER);
                driver.close();			
				String a1 = a.replace(" ", "_");
				String b1 = b.replace(" ", "_");
				String c1 = c.replace(" ", "_");
				String d1 = d.replace(" ", "_");
				String e1 = e.replace(" ", "_");
				
				String getpdf = "C:\\Users\\attune60\\Downloads\\CMA_Pdf\\cma_"+a1+"_"+b1+"_"+c1+"_"+d1+"_"+e1+".pdf;";
				
				main="";
				for(int j=0; j<cols.length; j++)
				{

					 main += cols[j]+';';

				}
				
				
				try {    
				    writer.append(main+';'+';'+';'+getpdf);
				    writer.append("\n");
			     }	
			     catch (IOException ex) {
				    ex.printStackTrace();    
				 }
                
				driver.switchTo().window(winHandleBefore);
				
				driver.get("https://rpp.rpdata.com/rpp/dashboard.html?execution=e1s1");
			 }	
		  linenumber++; 	
		}
		
	    writer.flush();
        writer.close();
	 }

}
