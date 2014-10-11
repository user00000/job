package Connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JOptionPane;

import Logs.FileLog;
import Proporties.ConnectParameters;
import Proporties.ReadWriteProporties;

public class Connect_to_Storage {

	public static Connection connect;
	
	private final static String nameProportiesFile = "ConnectionParams";
    static String server_IP;
	static String port;
	static String dataBase;
	static String table_str;
	static String user;
	static String password;
	

	
	private static  void setParameters(){
		
		
		/*
		server_IP = "192.168.2.118";
		port      = "1433";
		dataBase  = "nice_storage_center";
		user      = "Nice_test";
		password  = "!Qqwe123";
		*/
		
		Map mapL = new HashMap();
		mapL = ReadWriteProporties.ReadProporties(nameProportiesFile);
		
		server_IP = (String)mapL.get(ConnectParameters.Parameters.server_IP.getValue());
		port      = (String)mapL.get(ConnectParameters.Parameters.port.getValue());
		dataBase  = (String)mapL.get(ConnectParameters.Parameters.dataBase.getValue());
		user      = (String)mapL.get(ConnectParameters.Parameters.user.getValue());
		password  = (String)mapL.get(ConnectParameters.Parameters.password.getValue());
		
		
		
		
	}
	
	
	
	public static Connection getConnect(){
		
		setParameters();
		
		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
	       //   connect = DriverManager.getConnection("jdbc:sqlserver://192.168.2.118:1433;databaseName=nice_storage_center;user=Nice_test;password=!Q@W3e4r");
	       //   connect = DriverManager.getConnection("jdbc:sqlserver://192.168.2.118;instance=DISNIMLABSRV01;databaseName=nice_storage_center;user=Nice_test;password=!Qqwe123");
		   	connect = DriverManager.getConnection("jdbc:sqlserver://"+server_IP+":"+port+";databaseName="+dataBase,user,password);
		   //	connect = DriverManager.getConnection("jdbc:sqlserver://"+server_IP+":"+port+";databaseName="+dataBase + ";integratedSecurity=true");
			
		} catch (ClassNotFoundException | SQLException e) {
			
			//System.out.println("Can not connect");
			FileLog.log("Can not connect");
			
			JOptionPane.showMessageDialog(null, "Can not connect","Error", JOptionPane.PLAIN_MESSAGE);
			
		}
		
		return connect;
		
	}
	
	
	public static void closeConnect(Connection connect){
		if(connect != null){
			
			try {
				connect.close();
			} catch (SQLException e) {
				
				//System.out.println("Ошибка закрытия коннекта");
				FileLog.log("Ошибка закрытия коннекта");
				
			}
		}
		
	}
	
}
