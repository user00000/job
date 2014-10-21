/**
 * 
 * ѕерибираютс€ все даты в верном формате в данном файле
 * и отсылаютс€ на формировани€ списка дл€ восстановлени€.
 * 
 * ѕосле всего прочтени€ файл удал€етс€.
 * 
 */

package ReadWriteFile;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;

import Connection.Connect_to_Storage;
import Validation.Validate_Date_Format;


public class Read_from_DateList_start_Recover  {
	
	Connect_to_Storage connect_to_storage;

    private String pathFile_with_date_to_recover;	

    
    
    public Read_from_DateList_start_Recover(String pathFile_with_date_to_recover) {
		
    	
    	this.pathFile_with_date_to_recover = pathFile_with_date_to_recover;
    	
	}
    
    
    
    
    
    
    public void FileReadFunction(){
    	
        
    	Connection connect_to_nice = connect_to_storage.getConnect();
    	Recover recover = new Recover();
        
    	try{
    
    		File file = new File(pathFile_with_date_to_recover);
    		FileReader fileReader = new FileReader(file);
    		BufferedReader buffeReader = new BufferedReader(fileReader);
	        String line ="";
	        
    		while((line = buffeReader.readLine() ) != null) {
    			
    			//System.out.println(Validate_Date_Format.check_date_format(line)+" "+line);
    			
    			if( Validate_Date_Format.check_date_format(line) == true ){
    			
    			
    				
    				//System.out.println(line);
                    line = Validate_Date_Format.convertFormat_102(line);
				   
    				recover.start_recover(line, connect_to_nice);
    			
    			
    			
    			}
    			
    		}
    		
    		buffeReader.close();
    		fileReader.close();
    		//file.delete();
    		
    		
    		
    		
    	} catch(IOException e){
		
		System.out.println("Error 1. Cannot read file");

    	}
    	
    
    	connect_to_storage.closeConnect(connect_to_nice);
    
    	
    	
    }
    
    
    

}

