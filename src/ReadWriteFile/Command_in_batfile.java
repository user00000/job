package ReadWriteFile;

public class Command_in_batfile {

	
	private  String path_to_Veritas_util;
	private  String path_to_log;
	private  String date_to_restore;
	private  String path_to_restore_list;
	private  String command;
	private  String server;
	
	
	public  String comand_to_file() {
	
		String comand_to_file = "";
		
	//String remote_path_login =  remote_path + " -u " + remote_user + " -p " +remote_password+" ";  /*сетевой путь к утилите Veritas*/
	//String remote_path_login= "\\\\SRVCCNSTORE.rs.ru -u rs\\nise_bd -p n$Dyp3W "; 
    /*
    remote_path = "\\\\192.168.2.118" + " -u data-integration.ru\\niceuser -p aAd7eiFf " ; 
    String remote_tools_with_param  = path_to_remote_tools + " " + remote_path ;  //+"\\" + " -u data-integration.ru/niceuser -p aAd7eiFf " ;
    String command                  = "\"C:\\test\\test.bat\"";
    String command_to_start_restore = remote_tools_with_param + " " + command;
    */
    
  //  String remote_tools_with_param  = path_to_remote_tools + remote_path +"\\" + " -u data-integration.ru/niceuser -p aAd7eiFf " ;
  //  String command                  = path_to_Veritas_util + "\\" + " bprestore.exe ";
   
    
    //String remote_tools_with_param  = path_to_remote_tools + " " + path_to_Veritas_util ; 
   // String command                  = "bprestore.exe";
  
    //String parametr_log      = " -L " + path_to_log + "\\" + date_to_restore.replace("-", "").replace(" ", "_").replace(":", "_") + "_logs.txt";
    //String parametr_file     = " -f " +  path_to_restore_list;
    
    /*
    
    String command_to_start_restore =   path_to_remote_tools + " " + remote_path_login 
    		                            + "\"" + path_to_Veritas_util + "\\" + command 
    		                            + parametr_log 
    		                            + parametr_file + "\"";
	
    */
    
    String parametr_log      = " -L " + path_to_log + "\\" + date_to_restore.replace("-", "").replace(" ", "_").replace(":", "_") + "_" + server+ "_logs.txt";
    String parametr_file     = " -f " +  path_to_restore_list;
    
    comand_to_file = "\"" + path_to_Veritas_util + "\\" + command + "\"" +  parametr_log + parametr_file ;
    
    return comand_to_file;
    
	}



	public void setPath_to_log(String path_to_log) {
		this.path_to_log = path_to_log;
	}




	public void setDate_to_restore(String date_to_restore) {
		this.date_to_restore = date_to_restore;
	}




	public void setPath_to_restore_list(String path_to_restore_list) {
		this.path_to_restore_list = path_to_restore_list;
	}



	public void setCommand(String command ) {
		this.command = command;
	}



	public void setPath_to_Veritas_util(String path_to_Veritas_util) {
		this.path_to_Veritas_util = path_to_Veritas_util;
	}



	public void setServer(String server) {
		this.server = server;
	}


}
