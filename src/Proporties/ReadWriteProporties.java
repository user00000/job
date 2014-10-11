package Proporties;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import Logs.FileLog;

public class ReadWriteProporties {

	public static void WriteProporties(String nameProportiesFile, Map map){
		
		
		try {
			Properties properties = new Properties();
			
			//save into proporties file
			for(Object key : map.keySet()) {
			     
			   properties.setProperty((String)key, (String)map.get(key));
			    
			}
			
			
			File file = new File(nameProportiesFile+".proporties");
			FileOutputStream fileOut = new FileOutputStream(file);
			properties.store(fileOut,nameProportiesFile );
			fileOut.close();
			
		} catch (FileNotFoundException e) {
			
			//System.out.println("Файл proporties не найден");
			FileLog.log("Файл proporties не найден");
			
		} catch (IOException e) {
		
			//System.out.println("Ошибка ввода вывода. Proporties");
			FileLog.log("Ошибка ввода вывода. Proporties");
		}
		
	}
	
	
	public static Map ReadProporties(String nameProportiesFile){
		
		Map mapA = new HashMap();
		
		try {
			File file = new File(nameProportiesFile+".proporties");
			FileInputStream fileInput = new FileInputStream(file);
			Properties properties = new Properties();
			properties.load(fileInput);
			fileInput.close();

			Enumeration enuKeys = properties.keys();
			
			String key;
			
			//вывод списка параметров
			while (enuKeys.hasMoreElements()) {
				
				key = (String) enuKeys.nextElement();
				
				mapA.put(key,(String)properties.getProperty(key));
				
			}
			
		} catch (FileNotFoundException e) {
			
			//System.out.println("Файл proporties не найден");
			FileLog.log("Файл proporties не найден");
			
		} catch (IOException e) {

			//System.out.println("Ошибка ввода вывода. Proporties");
			FileLog.log("Ошибка ввода вывода. Proporties");
		}
		
		return mapA;
		
	}
	
}
