/**
 * 
 * Перибираются все даты в верном формате в данном файле
 * и отсылаются на формирования списка для восстановления.
 * 
 * После всего прочтения файл удаляется.
 * 
 */

package ReadWriteFile;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.text.ParseException;
import java.util.HashMap;
import java.util.Map;

import Command.GettingCurrentDate;
import Connection.Connect_to_Storage;
import Logs.FileLog;
import Proporties.ConnectParameters;
import Proporties.ReadWriteProporties;
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
				   
                    try {
    					
   					 //------------LOAD HOUR DIFF VALUE---------
   					final String nameProportiesFile = "ConnectionParams";
   					Map mapFile = new HashMap();
   					mapFile = ReadWriteProporties.ReadProporties(nameProportiesFile);
   					String hour_diff  = (String)mapFile.get(ConnectParameters.Parameters.hour_diff.getValue());
   					//------------load hour diff value---------------
   			        
   					//--изменени времени на заданное кол-во часов------------
						line = GettingCurrentDate.dateAdd(line,  Integer.parseInt(hour_diff));
						 
						 
					} catch (ParseException e) {	
						//e.printStackTrace();
						FileLog.log("Формат даты на входе функции не совпадает с ожидвемой yyyy-MM-dd HH:mm");
					}
                    
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

