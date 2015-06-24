package Multiple_Records;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class SearchLink2 {
	
	   
	   WebDriver driver;
		@Test
		 public void getSearchLink() throws IOException
		 {
			 String csv_path = "D:\\Records\\records.csv";
			 BufferedReader br = new BufferedReader(new FileReader(csv_path));
			 String line;
			 String Url;
			 int linenumber = 0;
			 
			// driver = new FirefoxDriver();
			// driver.manage().window().maximize();
			// driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
			 
				String main = "";
			    String fileName = "D:\\link2.csv";
				FileWriter writer = new FileWriter(fileName);
				String FILE_HEADER = "Street;Address;Suburb;State;Post Code;Local Authority;Land Use;Current Zoning;Up Zoning;Site Area;Description;Lot;Plan;Site type;Adopted;Height;Density;Owner;CMA;AVM;GMAP;Link1;Link2;";
				writer.append(FILE_HEADER);
				writer.append("\n"); 
				
				while ((line = br.readLine()) != null) {
					if(linenumber>0)
					 { 
					     String[] cols = line.split(";");
					     String c = cols[0];
					     String d = cols[1];
					     String e = cols[2];
					   // String d = cols[3];
					   //  String e = cols[4]; 
					     //System.out.println(a);
				
					   /*  driver.get("http://www.realestate.com.au/");
					     driver.findElement(By.id("where")).clear();
					     driver.findElement(By.id("where")).sendKeys(c+' '+d+' '+e);
					     driver.findElement(By.cssSelector(".rui-search-button")).click();
					     //String value = driver.findElement(By.cssSelector("#searchResultsTbl .h1Wrapper")).getText();
					     
					      Url = driver.getCurrentUrl()+";";
					      System.out.println(Url); */
					     Url= "http://www.realestate.com.au/buy/in-"+c+"+"+d+"+"+e+"/list-1";

					     
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
						//driver.findElement(By.id("where")).clear();
						
					 }
					 linenumber++;
				}		 
				
			    writer.flush();
		        writer.close();
		 }

	}


