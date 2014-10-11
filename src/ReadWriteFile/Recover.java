package ReadWriteFile;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import Command.Cmd_Command;
import Command.RemoteFileDemo;
import Command.SQL_Command;
import Command.WriteInFile;
import Connection.Connect_to_Storage;
import Logs.FileLog;
import Proporties.ConnectParameters;
import Proporties.ReadWriteProporties;

public class Recover extends Select_from_tables {

	
	
	 
	public void start_recover(String date_to_recover,Connection connect_to_storage){
		
	//	Connection connect_to_storage =  Connect_to_Storage.getConnect();
		
		Select_path_to_file_by_date(date_to_recover, connect_to_storage);
		
	//	Connect_to_Storage.closeConnect(connect_to_storage);
	
	}
	

	
	public void Select_path_to_file_by_date(String date_to_restore, Connection connect_to_nice) {
		
		//connect_to_nice = Connect_to_Storage.getConnect();
		
		if(connect_to_nice != null){
			
			
			
		    //------------LOAD PARAMETERS---------
			final String nameProportiesFile = "ConnectionParams";
			Map mapL = new HashMap();
			mapL = ReadWriteProporties.ReadProporties(nameProportiesFile);
			//String path_to_restore_folder_other_server = (String)mapL.get(ConnectParameters.Parameters.restoreList.getValue());
			//String path_to_restore_list_other_server =path_to_restore_folder_other_server + "\\restore_list.txt";
			String path_to_restore_list_folder = (String)mapL.get(ConnectParameters.Parameters.restoreList.getValue());       // "\\\\"+Get_IP.get_ip()+path_to_restore_folder_other_server.replace(":", "$");
			String path_to_restore_list        = path_to_restore_list_folder + "\\restore_list.txt";
			String path_to_log                 = (String)mapL.get(ConnectParameters.Parameters.restoreLog.getValue());
			String where_field                 = "dtRecordingGMTStartTime";
			String select_field                = "vcArchivePath";
			String folderID_field              = "siUnitID";
			String folder_field                = "nvcUnitPath";
			String table0                      = "tblStorageCenter"; 
			
			String path_to_Veritas_util    = (String) mapL.get(ConnectParameters.Parameters.pathUtil.getValue());
			String path_to_remote_tools    = (String) mapL.get(ConnectParameters.Parameters.remote_tools.getValue());
			String remote_user             = (String)mapL.get(ConnectParameters.Parameters.remote_user.getValue());
			String remote_password         = (String)mapL.get(ConnectParameters.Parameters.remote_password.getValue());
			String remote_disk             = (String)mapL.get(ConnectParameters.Parameters.remote_disk.getValue());
			//---load parameters-------------------------
			
			
			//-----------SERVER PATHS-------------------
			
			ResultSet rs_server_path = null;
			
			
				
				try {
					
					 rs_server_path = select_from_table_server_path(connect_to_nice);
				
					 
					
					  int loop = 0;
					 
					  String remote_store_folder_path = "";
					  String remote_server="";
					  String store_folder ="";
				      while(rs_server_path.next()){
						
				    	  remote_store_folder_path = rs_server_path.getString(folder_field);
				    	  remote_server            = Parse_store_folder.remote_server(remote_store_folder_path);
				    	  store_folder             = Parse_store_folder.local_folder(remote_store_folder_path);
				    	  
				    	 
				    	//Statement  stat = null;
					    ResultSet  rs = null;
				    	  
						rs = select_local_path_to_file(connect_to_nice,rs_server_path.getString(folderID_field),table0,folderID_field,select_field,where_field,date_to_restore);
				    	
						
						
				    	if(rs.isBeforeFirst() !=  false){
				    		 
				    		  SQL_Command.cmd_shell_SQL(connect_to_nice, "del " + path_to_restore_list);  
				    		
				    	  while(rs.next()){
				    	  
				    		  
				    		  /*Создание списка для восстановления*/ 
				    		  
								SQL_Command.cmd_shell_SQL(connect_to_nice, "echo " + remote_disk + "\\" + store_folder + "\\" + rs.getString(select_field)+">>"+path_to_restore_list);  
				    		  
				    	  }
				    	  
				    	  
                            	
				    	  
				    	   /*-----Создание файла с командой восстановления */  
				    	  Command_in_batfile batCommand = new Command_in_batfile();
				          
				    	  batCommand.setPath_to_Veritas_util(path_to_Veritas_util);
				    	  batCommand.setDate_to_restore(date_to_restore);
				    	  batCommand.setPath_to_log(path_to_log);
				    	  batCommand.setPath_to_restore_list(path_to_restore_list);
				    	  batCommand.setCommand("bprestore.exe");
				    	  
				    	  String command_to_file = batCommand.comand_to_file();
				    	  
				    	  System.out.println(batCommand.comand_to_file());
				    	 
				    	 
				    	  
				    	  WriteInFile input = new WriteInFile(path_to_restore_list_folder + "\\" + "restore.bat");
				    	  input.writeTextFile(command_to_file);
				    	  
				    	  RemoteFileDemo rfd = new RemoteFileDemo();
				    	  try {
							rfd.copyToRemote();
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
				    	  
				    	  /*-----------------------*/
				    	  
				    	//!!!!COPY TO REMOTE MACHINE
				    	  
				    	  
				    	   
							String remote_path_login =  remote_server + " -u " + remote_user + " -p " +remote_password+" ";
						   
						    String command_to_start_restore =   path_to_remote_tools + " " + remote_path_login 
						    		                            + "\"" + path_to_restore_list_folder + "\\" + "restore.bat" + "\"";
							
							
							Cmd_Command.cmd(command_to_start_restore);

							//cmd_shell_SQL(connect_to_nice, path_to_util + "\\bprestore.exe -L " + path_to_log + "\\" + date_to_restore.replace("-", "").replace(" ", "_").replace(":", "_") + "_logs.txt" + " -f " + path_to_restore_list);

			    		  
				    	  
				    	  
				    	  
				    	}
						
				    	//stat.close();
		                rs.close();
		               
						
						loop++;
				      }
				      
				      if (loop == 0) {
				    	  FileLog.log("Пустой результат в таблице сетевых путей");	  
				      }
						
					
				
				      rs_server_path.close();
				    //  stat_server_path.close();
				      
				
				
				} catch (SQLException e) {
					
					FileLog.log("Пустой результат в таблице сетевых путей");
				}
				
				
				
					
					//FileLog.log("Пустой результат в таблице сетевых путей");
					
				
			
			
			
			//------server paths--------------------------
			
			
		}	
			
	}
	
	/*
	
private void cmd_shell_SQL(Connection connect,String command)   {
		
		if(connect != null){
			
			PreparedStatement pStatSQL;
			
			
			
				
					try {
						pStatSQL = connect.prepareStatement("EXEC master..xp_cmdshell ?");
						pStatSQL.setString(1, command);
						pStatSQL.executeQuery();
					} catch (SQLException e) {
						
						//System.out.println("Команда SQL не отработала. Возможно нет прав на запуск xp_cmdshell");	
						FileLog.log("Команда SQL не отработала. Возможно нет прав на запуск xp_cmdshell");
					}
				
			
		}else
		{
		   //System.out.println("Не удалось выполнить процедуру cmd_shell_SQL. Connection is null");
			FileLog.log("Не удалось выполнить процедуру cmd_shell_SQL. Connection is null");
		}
		
	}

	*/
	
	
	
	
	
}
