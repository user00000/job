package Command;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import Logs.FileLog;

public class SQL_Command {

	
public static void cmd_shell_SQL(Connection connect,String command)   {
		
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
	
	
}
