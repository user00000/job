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
						
						//System.out.println("������� SQL �� ����������. �������� ��� ���� �� ������ xp_cmdshell");	
						FileLog.log("������� SQL �� ����������. �������� ��� ���� �� ������ xp_cmdshell");
					}
				
			
		}else
		{
		   //System.out.println("�� ������� ��������� ��������� cmd_shell_SQL. Connection is null");
			FileLog.log("�� ������� ��������� ��������� cmd_shell_SQL. Connection is null");
		}
		
	}
	
	
}
