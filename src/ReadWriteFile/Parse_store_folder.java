package ReadWriteFile;

public class Parse_store_folder {

	
	public static String local_folder(String remote_path) {
		
		int index = remote_path.indexOf("\\",2);
		
		return  remote_path.substring(index+1);
		
	}
	
	
	
public static String remote_server(String remote_path) {
		
		int index = remote_path.indexOf("\\",2);
		
		return  remote_path.substring(0,index);
		
	}
	
}
