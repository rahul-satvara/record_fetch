package Multiple_Records;

import java.awt.datatransfer.SystemFlavorMap;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.Comparator;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;

public class ListofFile {
	
	String csv_path = "D:\\Records\\records_151-250.csv";
	WebDriver driver;
	String line;

	@Test
	public void getFileName() throws IOException
	{
		File folder = new File("D:\\Records\\PDF\\AVM_150-250");
		File[] listOfFiles = folder.listFiles();
		
		Arrays.sort(listOfFiles, new Comparator<File>() { 
		    public int compare(File f1, File f2) {
		        return Long.valueOf(f1.lastModified()).compareTo(f2.lastModified());
		    }
		});
		
		String main = "";
		String fileName = "d:\\link1.csv";
		FileWriter writer = new FileWriter(fileName);
		String FILE_HEADER = "Street;Address;Suburb;State;Post Code;Local Authority;Land Use;Current Zoning;Up Zoning;Site Area;Description;Lot;Plan;Site type;Adopted;Height;Density;AVM;";
		writer.append(FILE_HEADER);
		writer.append("\n"); 
            System.out.println(listOfFiles.length);
		    for (int i = 0; i < listOfFiles.length; i++)
		    {
		      if (listOfFiles[i].isFile())
		      {
		          String FileName = listOfFiles[i].getName();
		         // System.out.println(FileName);
		    	  boolean found = false;
		          BufferedReader br = new BufferedReader(new FileReader(csv_path));
		          
		          while ((line = br.readLine()) != null)
		          {
		        	  
			          String[] cols = line.split(";");
			          String a = cols[0].toUpperCase();
				      String b = cols[1].toUpperCase();
				      String c = cols[2].toUpperCase();
				      b = b.replace(" " , "_");
				      c = c.replace(" " , "_");
				      String d = a+"_"+b+ "_" +c;
			          //System.out.println(d);
			        main="";
					for(int j=0; j<cols.length; j++)
					{
							
						 main += cols[j]+';';
						 
					}
			        
			        //System.out.println(d);
				    if(FileName.contains(d))
				    {
				    	found = true;
				    	break;
				    	  
				    }
		    	}  
		          
		          if(found)
		          {
		        	  FileName = FileName.replace("__", "_");
		        	  try {    
						    writer.append(main+';'+FileName);
						    writer.append("\n");
					     }	
					     catch (IOException ex) {
						    ex.printStackTrace();    
						 }
		          }
		          else
		          {
		        	  try {    
						    writer.append(main+';'+" ");
						    writer.append("\n");
					     }	
					     catch (IOException ex) {
						    ex.printStackTrace();    
						 }
		          }
		            
		    } 
		 }
		    writer.flush();
		    writer.close();
	  }
	
  }
