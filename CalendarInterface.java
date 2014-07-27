/**
 * Calendar file creation program
 * @author Chase Matayoshi, Michelle-Lei Pinlac, Mauricio Renon
 * @verion 1.0
 * @since 7/27/2014
 * 
 */

import java.io.File;
import java.io.IOException;
import java.sql.Date;
import java.util.Locale;
import java.util.TimeZone;

import biweekly.Biweekly;
import biweekly.ICalendar;
import biweekly.component.VEvent;
import biweekly.property.DateStart;
import biweekly.property.Summary;
import biweekly.util.DateTimeComponents;
import biweekly.util.Duration;


public class CalendarInterface {

	public static void main(String args[]) throws IOException {
		
		//New calendar object
		
		ICalendar calendar = new ICalendar();
		
		//New event object
		
		VEvent event = new VEvent();
		
		//creates the title of the event and sets the language to English
		
		Summary summary = new Summary("Meeting with TA");
		event.setSummary(summary);
		
		summary.setLanguage("en-us");
		
		//TimeZone currentZone = TimeZone.getTimeZone("America/Hawaii");
		//Locale currentLocale = Locale.ENGLISH;
		
		//DateTimeComponents components = new DateTimeComponents(2014, 07, 28, 15, 0, 0, false);
		
		//time in milliseconds used to generate the time
		
		long time = 1406595600000L;
		
		Date eventTime = new Date(time);
		
		//DateStart startDate = new DateStart(null, false);
		//startDate = new DateStart(components);
		
		event.setDateStart(eventTime);
		
		//adds the duration of the event
		
		event.setDuration(new Duration.Builder().minutes(30).build());
		
		calendar.addEvent(event);
		
		//outputs all information to a .ics file
		
		File file = new File("meeting.ics");
		
		Biweekly.write(calendar).go(file);
		
		
	}

}
