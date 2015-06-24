package Multiple_Records;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.Test;

public class Records {

	WebDriver driver;
	
	@Test
	public void getRecord() throws IOException
	{
		 
	  //open the site in firefox driver  
	  driver = new FirefoxDriver();
	  driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	  driver.get("http://www.blockbrief.com/reports");
	  driver.manage().window().maximize();
	  
	  //go to login page
	  driver.findElement(By.xpath("//input[@type='email']")).sendKeys("dominique@masterwealthcontrol.com.au");
	  driver.findElement(By.cssSelector("#signin_form center form input[name='password']")).sendKeys("coaho7");   
	  driver.findElement(By.xpath("//input[@value='Submit']")).click();
	    
	  // click on site menu
	  driver.findElement(By.linkText("Sites")).click();
	    
	  //click on pagignation dropdown  
      //Select record = new Select(driver.findElement(By.cssSelector("#page2 select")));
      //List<WebElement> allrecords = record.getOptions();
      List<WebElement> Datarecord = driver.findElements(By.cssSelector("#t_fill .table_row"));
	      
	  //create one csv file
      String fileName = "d:\\records.csv";
	  FileWriter writer = new FileWriter(fileName);
	  String FILE_HEADER = "Street;Address;Suburb;State;Post Code;Local Authority;Land Use;Current Zoning;Up Zoning;Site Area;Description;Lot;Plan;Site type;Adopted;Height;Density;Owner;";
	  writer.append(FILE_HEADER);
	  writer.append("\n"); 
	  
	     //click on pages one by one
	     for(int i=135; i<150; i++)
		 {
    	     Select record1 = new Select(driver.findElement(By.cssSelector("#page2 select")));
		     record1.getOptions().get(i).click();
		 
		    //fetch record from every pages
		    for(int j=0; j<Datarecord.size(); j++)
		    {
			   List<WebElement> Datarecord1 = driver.findElements(By.cssSelector("#t_fill .table_row"));
			   Datarecord1.get(j).click();
			   
			   List<WebElement> valuerec = driver.findElements(By.cssSelector("div.modal.fade.in div.modal-dialog div.modal-content div.modal-body div.bootbox-body tr td.light-pink-border"));	
			  // System.out.println(valuerec.size());
			   
			   for(int k=0; k<valuerec.size(); k++)
			   {
				   
				   String total_record = valuerec.get(k).getText();
				   String final_record = total_record.concat(";");
				   String final1 = final_record.replace("\n", ". ");  
				   String Final = trimDoubleQuotes(final1);
				      
				    try {    
					    writer.append(Final);
				     }	
				     catch (IOException ex) {
					    ex.printStackTrace();    
					 } 
			   }   
			   
			   //System.out.println("\n");
			   driver.findElement(By.cssSelector("div.modal.fade.in div.modal-dialog div.modal-header button.bootbox-close-button")).click();
			   writer.append("\n");		  
		   }		
		   
		  }
	            writer.flush();
		        writer.close();     
		} 
	
	 public static String trimDoubleQuotes(String text)
	 {
	    
		int textLength = text.length();
        
	    if(text.indexOf('"') == 0 && text.lastIndexOf('"') == textLength-1)
	    {
	    	String value1 =  text.substring(1, textLength-2);
	    	return value1;
	    }
	    
	    if(text.indexOf('"') == 0)
	    {
	    	String value1 =  text.substring(1, textLength-1);
	    	return value1;
	    }
	    
	    if(text.lastIndexOf('"') == textLength-1)
	    {
	    	String value1 =  text.substring(0, textLength-2);
	    	return value1;
	    }
	    
        return text; 
	  }
	 
	 
	}	
