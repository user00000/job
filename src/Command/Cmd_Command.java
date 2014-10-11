package Command;


import java.io.BufferedReader;
import java.io.InputStreamReader;
import Logs.FileLog;

public class Cmd_Command {
	
	
	public static String  cmd(String command){
		
		
		StringBuffer output = new StringBuffer();
		 
		Process p;
		
		try {
			 Runtime rt = Runtime.getRuntime();
			// p = rt.exec("cmd /c "+command);
			 p = rt.exec(command);
			
			
			
			BufferedReader reader = 
                            new BufferedReader(new InputStreamReader(p.getInputStream()));
 
            String line = "";			
			while ((line = reader.readLine())!= null) {
				output.append(line + "\n");
			}
			
 
		} catch (Exception e) {
			 FileLog.log("ошибка выполнения cmd комманды.");	
		}
 
		return output.toString();
 
	}
		
	
	}


