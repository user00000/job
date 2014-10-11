package Proporties;

public class ConnectParameters {

	public static enum Parameters{ 
		server_IP("server_IP")
		,port("port")
		,dataBase("dataBase")
		,table_str("table_str")
		,user("user")
		,password("password")
		,field("field")
		,restoreList("restoreList")
		,restoreLog("restoreLog")
		,pathUtil("pathUtil")
		,remote_tools("Remote_tools")
		,remote_user("remote_user")
		,remote_password("remote_password")
		,remote_disk("remote_disk");
	
		
	
	private String param;
	Parameters(String p){
		param = p;
	}
	
	public String getValue(){
		return param;
	}
		
	}
	
}
