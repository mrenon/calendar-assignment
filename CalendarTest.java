import static org.junit.Assert.*;

import org.junit.Test;

import biweekly.Biweekly;
import biweekly.ICalendar;

import java.util.*;
import java.io.File;
import java.io.IOException;
import java.text.*;


public class CalendarTest {
	 
	CalendarData test = new CalendarData();
	Calendar jUnit = new Calendar(); // new Calendar.java object for testing 
	ICalendar calendar = new ICalendar();
	
	/* asserts that test object is not null */
	@Test
	public void test_test()
	{
		assertNotNull(test);
	}
	
  /* asserts that junit object is not null */
	public void test_jUnit()
	{
		assertNotNull(jUnit);
	}
   
	/* testing the create event method inside calendar.java. For testing purposes, tested only event name, description, month, 
	 * day, and year. Want to test if fields go in desired spot
	 */
	public void test_event() throws IOException
	{
		String testContent = null;
		String testFileNameString = "test_Filname.ics";
		String eventNameBox = "Test title";
		String descrip = "Test Description";
		String testYear = "2015";
		String testDay = "4";
		String testMonth = "9";
		
		assertEquals("must be month", testMonth);
		assertEquals("must be day", testDay);
		assertEquals("must be year", testYear);
		assertEquals("must be test description", descrip);
		assertEquals("must be test title", eventNameBox);
		
		File file = new File(testFileNameString);
        
		Biweekly.write(calendar).go(file);
	}

	@Test
	public void testMonthandDay() {
		
		test.setMonth("February");
		
		test.setDay(30);
		
		assertEquals(0, test.getDay());
		
		test.setMonth("April");
		
		test.setDay(31);
		
		assertEquals(0, test.getDay());
		
	}

}
