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




public class Record3 {

	WebDriver driver;
	int linenumber;
	
	
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
	    
	  // click on Zonning-alert menu
	  driver.findElement(By.linkText("Zoning Guide")).click();
	    
	  //click on pagignation dropdown  
      List<WebElement> Datarecord = driver.findElements(By.cssSelector("#fill .table_row td"));
          
	  //create one csv file
      String fileName = "d:\\records.csv";
	  FileWriter writer = new FileWriter(fileName);
	  String FILE_HEADER = "Date//Adopted//Post Code//Council//State//Zoning Plan";
	  writer.append(FILE_HEADER);
	  writer.append("\n"); 
	  
	     //click on pages one by one
	     for(int i=102; i<120; i++)
		 {
	    	 Select record1 = new Select(driver.findElement(By.cssSelector("#page select")));
		     record1.getOptions().get(i).click();
	         
	    	 for(int j=0; j<Datarecord.size(); j++)
			 {
	    	   List<WebElement> Datarecord1 = driver.findElements(By.cssSelector("#fill .table_row td"));
	    	   String total_record = Datarecord1.get(j).getText();
	    	       
	    	       String Final = total_record.concat("//"); 
				   //System.out.println(Final);
				      
				    try {    
					    writer.append(Final);
				     }	
				     catch (IOException ex) {
					    ex.printStackTrace();    
					 } 
	    	   
			     if(linenumber%6 == 0)
	    	     {
			       writer.append("\n");
		          }		
		       }
		     }
	      
	        writer.flush();
	        writer.close();
	   }	 
  }	
