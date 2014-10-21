/**
 * 
 * ѕроверка даты на соответствие формату YYYY-MM-DD hh:mm
 * 
 */

package Validation;


public class Validate_Date_Format_102 {

	
	public static boolean check_date_format(String for_check){
		
		    String year_mask  = "[1-9][0-9][0-9][0-9]";
		    
		    String time_mask = "([0-1][0-9]|[2][0-3]):[0-5][0-9]";
		    
		    String month_day_mask = "";
		 
		    String check_year = for_check.substring(0, Math.min(4,for_check.length()) ) ; //first 4 symbols;
            
		    if( check_year.matches(year_mask) != true ) {  // not digit 
		    	return false;
		    }
		    	
		      
		    if ( Integer.parseInt(check_year)%4 == 0  ){  //29 days in february
		    	
	    		month_day_mask = "((01|03|05|07|08|10|12)-(0[1-9]|[1-2][0-9]|3[0-1])|(04|06|09|11)-(0[1-9]|[1-2][0-9]|30)|02-(0[1-9]|[1-2][0-9]))";      //29 days in february
	    	}else{
	    		month_day_mask = "((01|03|05|07|08|10|12)-(0[1-9]|[1-2][0-9]|3[0-1])|(04|06|09|11)-(0[1-9]|[1-2][0-9]|30)|02-(0[1-9]|1[0-9]|2[0-8]))";
	    	}
		    
		    String mask = year_mask + "-" + month_day_mask + " " + time_mask ;	
		
			return  for_check.matches(mask) ;
			
		
		
	}
	
	
        
	
	
}
