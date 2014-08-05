import static org.junit.Assert.*;

import org.junit.Test;


public class CalendarTest {
	
	CalendarData test = new CalendarData();

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
