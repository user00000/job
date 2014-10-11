package Logs;


import java.io.*;
import java.util.logging.*;
 
public class FileLog {
   
 
   
   public static void log(String log)  {
   
	   final Logger logger = Logger.getLogger("LOG");
	   
	  // Construct a default FileHandler.
      Handler fh;
	try {
		
		fh = new FileHandler("LogFile.log", true);
		fh.setFormatter(new SimpleFormatter());  // Set the log format
	      // Add the FileHander to the logger.
	      //logger.addHandler(fh);
	      // Set the logger level to produce logs at this level and above.
	      //logger.setLevel(Level.FINE);
	 
	      // Redirecting System.out and System.err
	    //!!!!!!!!!!!!!!!!!!!!!!! 
		/*
		PrintStream outPS =
	      new PrintStream(
	         new BufferedOutputStream(
	            new FileOutputStream("LogFile.log", true)));  // append is true
	      System.setErr(outPS);    // redirect System.err
	      System.setOut(outPS);
	 */
	  
	      
	      logger.info(log);
	
	
	} catch (SecurityException e) {
		
		e.printStackTrace();
	} catch (IOException e) {
		
		e.printStackTrace();
	}  // append is true
      
      
   }
}