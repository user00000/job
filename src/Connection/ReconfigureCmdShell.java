package Connection;

import java.sql.Connection;
import java.sql.SQLException;

import Logs.FileLog;

public class ReconfigureCmdShell {

	
	//To enable the feature cmdshell
	public static void reconfigure_cmd(){
		
		Connection connect = Connect_to_Storage.getConnect();	
		
		reconfigure_to_enable_cmdshell(connect);
		
		Connect_to_Storage.closeConnect(connect);	
	}
	
	
	
	private static void reconfigure_to_enable_cmdshell(Connection connect){
		
		if(connect != null){
			
			try {
				
				
				// To update the currently configured value for advanced options.
				connect.createStatement().execute("EXEC sp_configure 'show advanced options', 1");
				connect.createStatement().execute("RECONFIGURE");
				
				//To enable the feature.
				connect.createStatement().execute("EXEC sp_configure xp_cmdshell, 1");
				connect.createStatement().execute("RECONFIGURE");
				
				
				/*
				Statement sqlStat = connect.createStatement();
				
				// To update the currently configured value for advanced options.
				
				sqlCommand = "EXEC sp_configure 'show advanced options', 1";
				
				sqlStat.addBatch(sqlCommand);
				//sqlStat.executeBatch(); sqlStat.clearBatch();
				sqlCommand = "RECONFIGURE";
				sqlStat.addBatch(sqlCommand);
				sqlStat.executeBatch(); sqlStat.clearBatch();
				
				
				//To enable the feature.
				
				sqlCommand = "EXEC sp_configure xp_cmdshell, 1";
				sqlStat.addBatch(sqlCommand);
				//sqlStat.executeBatch(); sqlStat.clearBatch();
				sqlCommand = "RECONFIGURE";
				sqlStat.addBatch(sqlCommand);
				sqlStat.executeBatch(); sqlStat.clearBatch();
				*/
					
				
			} catch (SQLException e) {
				
				//System.out.println("Не удалось выполнить метод reconfigure_to_enable_cmdshell.execute error");
				
				FileLog.log("Не удалось выполнить метод reconfigure_to_enable_cmdshell.execute error");
			}
			
			
		
			
		}
		
		else {
			
			//System.out.println("Не удалось выполнить метод reconfigure_to_enable_cmdshell. Connection is null");
			FileLog.log("Не удалось выполнить метод reconfigure_to_enable_cmdshell.execute error");
		}
		
	}
	
}
