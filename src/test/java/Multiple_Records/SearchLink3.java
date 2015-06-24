package Multiple_Records;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.Test;

public class SearchLink3 {
  
	WebDriver driver;
	@Test
	 public void getSearchLink() throws IOException
	 {
		 String csv_path = "D:\\Records\\records.csv";
		 BufferedReader br = new BufferedReader(new FileReader(csv_path));
		 String line;
		 String Url;
		 int linenumber = 0;
		 
		/* driver = new FirefoxDriver();
		 driver.manage().window().maximize();
		 driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS); */
		 
			String main = "";
		    String fileName = "D:\\link3.csv";
			FileWriter writer = new FileWriter(fileName);
			String FILE_HEADER = "Street;Address;Suburb;State;Post Code;Local Authority;Land Use;Current Zoning;Up Zoning;Site Area;Description;Lot;Plan;Site type;Adopted;Height;Density;Owner;CMA;AVM;GMAP;Link1;Link2;Link3;";
			writer.append(FILE_HEADER);
			writer.append("\n"); 
			
			while ((line = br.readLine()) != null) {
				if(linenumber>0)
				 { 
				     String[] cols = line.split(";");
				     String c = cols[2];
				    // String d = cols[1];
				    // String e = cols[2];
				    // String d = cols[3];
				    // String e = cols[4];
				     
				   /*  driver.get("http://www.realcommercial.com.au/");
				      System.out.println(driver.findElements(By.cssSelector("li.token-input-token")).size());
				     if(driver.findElements(By.cssSelector(".token-input-delete-token")).size() > 0)
				     {
				    	 driver.findElement(By.cssSelector(".token-input-delete-token")).click(); 
				     } 
				     
				     driver.findElement(By.id("token-input-where")).clear();
				     driver.findElement(By.id("token-input-where")).sendKeys(c);
				     driver.findElement(By.id("searchBtn")).click(); */
				     Url = "http://www.realcommercial.com.au/for-sale/in-"+c+"/list-1?nearbySuburb=false&autoSuggest=false";

				     //Url = driver.getCurrentUrl()+";";
				     //System.out.println(Url);
				   /*  String value = driver.findElement(By.id("resBar")).getText();
				     if(value.contains("No exact matches found"))
				     {
				    	 driver.get("http://www.realcommercial.com.au/");
				    	 driver.findElement(By.id("token-input-where")).clear();
				    	 driver.findElement(By.id("token-input-where")).sendKeys(c);
				    	 driver.findElement(By.id("searchBtn")).click();
				    	 Url = driver.getCurrentUrl()+";";
				     }
				     else
				     {
				    	 Url = driver.getCurrentUrl()+";";
				     } */
				     
					 main="";
					 for(int j=0; j<cols.length; j++)
					 {
						 main += cols[j]+';';
					 } 
					
					try {    
					    writer.append(main+';'+';'+';'+Url);
					    writer.append("\n");
				     }	
				     catch (IOException ex) {
					    ex.printStackTrace();    
					 }
					
					//driver.findElement(By.id("token-input-where")).clear();
				 }
				 linenumber++;
			}		 
			
		    writer.flush();
	        writer.close();
	 }
	
}
