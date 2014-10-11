package Command;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class WriteInFile {

	private String fileName;
	
	public WriteInFile(String fileName){
		
		this.fileName = fileName;
	}
	
	
	
	public void writeTextFile(String text) {
	    
		
		
		
	    try {
	    	 FileWriter fileWriter = new FileWriter(this.fileName);
	    	 BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
	    	 bufferedWriter.write(text);
	    	 bufferedWriter.close();
	    	 
	    } catch (Exception e) {
	     
	    	
	    } 

	  } 
	
}
