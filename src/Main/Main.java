package Main;


/**
 *  При запуске программы вводится полный путь к файлу,
 *  где хранятся даты для восстановления в формате YYYY-MM-DD HH:mm
 *  
 *  По умолчанию будет путь: D:\Nice_Storage\List_data_to_Recover\dates.txt
 * 
 *  Так же меняется конфигурация SQl сервера, разрешающая делать cmd команды из sql сервера.
 * 
 */





import Connection.ReconfigureCmdShell;
import ReadWriteFile.Read_from_DateList_start_Recover;


public class Main {

	
	public static void main(String agv[]){
		
		
		
       
        String pathFile_with_date_to_recover = "";
        
        
        if (agv.length == 0) {
        	pathFile_with_date_to_recover = "D:\\Nice_Storage\\List_data_to_Recover\\dates.txt";
        } else {
        	pathFile_with_date_to_recover = agv[0];	
        }
        
        
        ReconfigureCmdShell.reconfigure_cmd();
         
		
		Read_from_DateList_start_Recover readWrite = new Read_from_DateList_start_Recover(pathFile_with_date_to_recover);
	    readWrite.FileReadFunction();
	
	    
	}
	
	
}
