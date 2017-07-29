package resources;

import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

public class JodaTimeExample {

    private static final String DATE_FORMAT = "dd-M-yyyy KK:mm:ss a";
    private static final String NEW_DATE_FORMAT = "yyyy-MM-dd HH:mm:ss";
    public String getTime(String dateServer,String toTimeZone)  throws ParseException
    {
    	String input = dateServer;
    	DateFormat inputFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    	DateFormat outputFormat = new SimpleDateFormat("dd-MM-yyyy KK:mm:ss a");
    	//System.out.println(outputFormat.format(inputFormat.parse(input)));
    	dateServer=outputFormat.format(inputFormat.parse(input));
    	System.out.println("Origi"+dateServer);
    	SimpleDateFormat formatter = new SimpleDateFormat(DATE_FORMAT);
    	String dateInString = dateServer;
	    Date date = formatter.parse(dateInString);
	    TimeZone tz = TimeZone.getDefault();
	    //String defaultTimeZone="America/New_York";
	    		
	    SimpleDateFormat sdfAmerica = new SimpleDateFormat(DATE_FORMAT);
        DateTime dt = new DateTime(date);
        DateTimeZone dtZone = DateTimeZone.forID(toTimeZone);
        DateTime dtus = dt.withZone(dtZone);
        TimeZone tzInAmerica = dtZone.toTimeZone();
        Date dateInAmerica = dtus.toLocalDateTime().toDate(); //Convert to LocalDateTime first

        sdfAmerica.setTimeZone(tzInAmerica);	
        String t=formatter.format(dateInAmerica);
	  //  System.out.println("Test="+formatter.format(dateInAmerica));
    	return t;
    }
    public String SaveTime(String dateServer,String toTimeZone)  throws ParseException
    {
    	String input = dateServer;
    	DateFormat inputFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    	DateFormat outputFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    	//System.out.println(outputFormat.format(inputFormat.parse(input)));
    	dateServer=outputFormat.format(inputFormat.parse(input));
    	//System.out.println("Origi"+dateServer);
    	SimpleDateFormat formatter = new SimpleDateFormat(NEW_DATE_FORMAT);
    	String dateInString = dateServer;
	    Date date = formatter.parse(dateInString);
	    TimeZone tz = TimeZone.getDefault();
	    //String defaultTimeZone="America/New_York";
	    		
	    SimpleDateFormat sdfAmerica = new SimpleDateFormat(NEW_DATE_FORMAT);
        DateTime dt = new DateTime(date);
        DateTimeZone dtZone = DateTimeZone.forID(toTimeZone);
        DateTime dtus = dt.withZone(dtZone);
        TimeZone tzInAmerica = dtZone.toTimeZone();
        Date dateInAmerica = dtus.toLocalDateTime().toDate(); //Convert to LocalDateTime first

        sdfAmerica.setTimeZone(tzInAmerica);	
        String t=formatter.format(dateInAmerica);
	  //  System.out.println("Test="+formatter.format(dateInAmerica));
    	return t;
    }
    public static void main(String[] args) throws ParseException {
    	
    	
    	JodaTimeExample jtx=new JodaTimeExample();
    	//jtx.getTime("2017-05-6 13:54:23", "America/New_York");
    	String a=jtx.SaveTime("2017-05-15 10:30:00", "America/New_York");
    	System.out.println(a);
       }

}