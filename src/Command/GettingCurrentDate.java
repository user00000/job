package Command;



	import java.util.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

	public class GettingCurrentDate {
	  
		private static DateFormat  df;
		
		public GettingCurrentDate(int formate_type){
	
			switch (formate_type){
			
			case 1: df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			break;

			case 2: df = new SimpleDateFormat("dd/MM/yy HH:mm:ss");
			break;
			
			}
		}
		
		public static String current_date() {
	       //getting current date and time using Date class
		  
	       Date dateobj = new Date();
	       return df.format(dateobj);
		}
	       /*getting current date time using calendar class 
	        * An Alternative of above*/
	      public static String current_date_calendar(){
	       Calendar calobj = Calendar.getInstance();
	       return df.format(calobj.getTime());
	    }
	      
	      
	}
	

