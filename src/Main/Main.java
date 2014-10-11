package Main;

import Connection.ReconfigureCmdShell;
import ReadWriteFile.Read_from_DateList_start_Recover;

public class Main {

	
	public static void main(String agv[]){
		
		//System.out.println(Math.min(1,2));
       
        String pathFile_with_date_to_recover = "";
        
        
        if (agv.length == 0) {
        	pathFile_with_date_to_recover = "D:\\Nice_Storage\\List_data_to_Recover\\dates.txt";
        } else {
        	pathFile_with_date_to_recover = agv[0];	
        }
        
        
        ReconfigureCmdShell.reconfigure_cmd();
        
        
        
		
        //System.out.println(pathFile_with_date_to_recover);
		 
		
		Read_from_DateList_start_Recover readWrite = new Read_from_DateList_start_Recover(pathFile_with_date_to_recover);
	    readWrite.FileReadFunction();
	
	}
	
	
}
