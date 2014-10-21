
/**
 * 
 * ѕроверка даты на соответствие формату DD.MM.YYYY hh:mm:ss
 * 
 */

package Validation;

import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class Validate_Date_Format {

	
	public static boolean check_date_format(String for_check){
		
		    String year_mask  = "[1-9][0-9][0-9][0-9]";
		    
		    String time_mask = "(0|[1-9]|[1][0-9]|[2][0-3]):[0-5][0-9]:[0-5][0-9]";
		    
		    String day_month_mask = "";
		 
		    String check_year = for_check.substring(6,10) ; //first 6-10 symbols;
		    
		    
		    
		    
		 
		    if( check_year.matches(year_mask) != true ) {  // not digit 
		    	return false;
		    }
		    	
		      
		    if ( Integer.parseInt(check_year)%4 == 0  ){  //29 days in february
		    	
	    		day_month_mask = "((0[1-9]|[1-2][0-9]|3[0-1])\\.(01|03|05|07|08|10|12)|(0[1-9]|[1-2][0-9]|30)\\.(04|06|09|11)|(0[1-9]|[1-2][0-9])\\.02)";      //29 days in february
	    	}else{
	    	    day_month_mask = "((0[1-9]|[1-2][0-9]|3[0-1])\\.(01|03|05|07|08|10|12)|(0[1-9]|[1-2][0-9]|30)\\.(04|06|09|11)|(0[1-9]|1[0-9]|2[0-8])\\.02)";
	    	}
		    
		    String mask =  day_month_mask + "."+ year_mask + " " + time_mask ;	
		
			return  for_check.matches(mask) ;
			
		
		
	}
	
	
public static String convertFormat_102 (String date){
    	
		String pattern = "(\\d{2})(?:\\.)(\\d{2})(?:\\.)(\\d{4})(?:\\s)(\\d{1,2})(?:\\:)(\\d{2})";  // month group 2
	
		Pattern r = Pattern.compile(pattern);
		Matcher m = r.matcher(date);
		
		String date_m = null;
		
    	if (m.find( )) {
    		String year   = m.group(3).toString();
    	    String month  = m.group(2).toString();
    		String day    = m.group(1).toString();
    		String hour   = m.group(4).toString();
    		String minute = m.group(5).toString();
    		
    		
    		if (hour.length() == 1){
    			hour = "0"+hour;
    		}
    		
    		
    		date_m =  year+"-"+month+"-"+day+" "+hour+":"+minute;
    		 
    		
    		
    		} else {
    		
    		
            System.out.println("NO MATCH");
            System.exit(1); 
            
    	}
    	
    	return date_m;
    	
    }
	
	
	
}
