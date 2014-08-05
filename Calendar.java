import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

import javax.swing.JOptionPane;

import org.eclipse.swt.widgets.Display; 
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.List;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Label;
import org.eclipse.wb.swt.SWTResourceManager;
import org.eclipse.swt.widgets.Button;

import biweekly.Biweekly;
import biweekly.ICalendar;
import biweekly.component.VEvent;
import biweekly.property.DateStart;
import biweekly.property.Description;
import biweekly.property.Summary;
import biweekly.util.DateTimeComponents;
import biweekly.util.Duration;


public class Calendar {

	protected Shell CalendarGUI;
	private Text descripBox;
	private Text eventNameBox;
	private List monthList;
	private List dayList;
	private List yearList;
	private List startHourList;
	private List startMinList;
	private List endHourList;
	private List endMinList;

	/**
	 * Launch the application.
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			Calendar window = new Calendar();
			window.open();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Open the window.
	 */
	public void open() {
		Display display = Display.getDefault();
		createContents();
		CalendarGUI.open();
		CalendarGUI.layout();
		while (!CalendarGUI.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
	}

	/**
	 * Create contents of the window.
	 */
	protected void createContents() {
		CalendarGUI = new Shell();
		CalendarGUI.setTouchEnabled(true);
		CalendarGUI.setBackground(SWTResourceManager.getColor(SWT.COLOR_TITLE_BACKGROUND));
		CalendarGUI.setText("Calendar Interface");
		CalendarGUI.setSize(530, 450);
		CalendarGUI.setEnabled(true);
		
		//creates the event label
		Label eventNameLabel = new Label(CalendarGUI, SWT.BORDER | SWT.SHADOW_IN);
		eventNameLabel.setAlignment(SWT.CENTER);
		eventNameLabel.setFont(SWTResourceManager.getFont("Segoe UI", 12, SWT.NORMAL));
		eventNameLabel.setBounds(10, 36, 94, 26);
		eventNameLabel.setText("Event Name:");
		
		//creates a text field where you can enter the event's name
		eventNameBox = new Text(CalendarGUI, SWT.BORDER);
		eventNameBox.setBounds(154, 36, 230, 49);
		
		//month label
		Label monthLabel = new Label(CalendarGUI, SWT.BORDER);
		monthLabel.setAlignment(SWT.CENTER);
		monthLabel.setBounds(41, 107, 44, 15);
		monthLabel.setText("Month");
		
		//makes a list of the months that you can select from
		monthList = new List(CalendarGUI, SWT.BORDER | SWT.V_SCROLL); //creates a list
		monthList.setForeground(SWTResourceManager.getColor(SWT.COLOR_BLACK)); //sets the font color
		monthList.setTouchEnabled(true); 
		monthList.setBackground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_LIGHT_SHADOW)); //background color
		monthList.setItems(new String[] {"January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"}); //entries in the list
		monthList.setBounds(10, 128, 111, 93); //position it is on the frame
		
		//day label
		Label dayLabel = new Label(CalendarGUI, SWT.BORDER | SWT.SHADOW_IN);
		dayLabel.setAlignment(SWT.CENTER);
		dayLabel.setBounds(154, 107, 31, 15);
		dayLabel.setText("Day");
		
		//makes a list of the days that you can select from
		dayList = new List(CalendarGUI, SWT.BORDER | SWT.V_SCROLL); //creates a list
		dayList.setForeground(SWTResourceManager.getColor(SWT.COLOR_BLACK));
		dayList.setBackground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_LIGHT_SHADOW)); //background color
		dayList.setItems(new String[] {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", 
				"15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31"}); //entries in the list
		dayList.setBounds(154, 128, 44, 93); //position it is on the frame
		
		//year label
		Label yearLabel = new Label(CalendarGUI, SWT.BORDER | SWT.SHADOW_IN);
		yearLabel.setAlignment(SWT.CENTER);
		yearLabel.setBounds(233, 107, 31, 15);
		yearLabel.setText("Year");
		
		//makes a list of the years that you can select from
		yearList = new List(CalendarGUI, SWT.BORDER | SWT.V_SCROLL); //creates a list
		yearList.setForeground(SWTResourceManager.getColor(SWT.COLOR_BLACK)); //font color
		yearList.setBackground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_LIGHT_SHADOW)); //background color
		yearList.setItems(new String[] {"2014", "2015", "2016", "2017", "2018", "2019", "2020", "2021", "2022", "2023", 
				"2024", "2025", "2026", "2027", "2028", "2029", "2030"}); //entries
		yearList.setBounds(223, 128, 57, 93); //position it is on the frame
		
		//start time label
		Label startTimeLabel = new Label(CalendarGUI, SWT.BORDER | SWT.SHADOW_IN); 
		startTimeLabel.setAlignment(SWT.CENTER);
		startTimeLabel.setBounds(297, 131, 66, 15);
		startTimeLabel.setText("Start Time");
		
		
		//hour label for start time
		Label sHourLabel = new Label(CalendarGUI, SWT.BORDER | SWT.SHADOW_IN);
		sHourLabel.setBounds(377, 107, 31, 15);
		sHourLabel.setText("Hour");
		
		//creates the list for the start time's hour
		startHourList = new List(CalendarGUI, SWT.BORDER | SWT.V_SCROLL); //creates the list
		startHourList.setBackground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_LIGHT_SHADOW)); //background color
		startHourList.setItems(new String[] {"01", "02", "03", "04", "05", "06", "07", "08", "09",
				"10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24"}); //entries based on 24-hours
		startHourList.setBounds(369, 128, 46, 37); //position
		
		//minutes label for start time
		Label sMinLabel = new Label(CalendarGUI, SWT.BORDER | SWT.SHADOW_IN);
		sMinLabel.setBounds(438, 107, 44, 15);
		sMinLabel.setText("Minutes");
		
		//creates the list for the start time's minutes
		startMinList = new List(CalendarGUI, SWT.BORDER | SWT.V_SCROLL); //creates the list
		startMinList.setBackground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_LIGHT_SHADOW)); //background color
		startMinList.setItems(new String[] {"00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", 
				"14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31", "32", 
				"33", "34", "35", "36", "37", "38", "39", "40", "41", "42", "43", "44", "45", "46", "47", "48", "49", "50", "51", 
				"52", "53", "54", "55", "56", "57", "58", "59"}); //entries
		startMinList.setBounds(438, 128, 44, 37); //position
		
		//end time label
		Label endTimeLabel = new Label(CalendarGUI, SWT.BORDER | SWT.SHADOW_IN);
		endTimeLabel.setText("End Time");
		endTimeLabel.setAlignment(SWT.CENTER);
		endTimeLabel.setBounds(297, 206, 66, 15);
		
		//end time's hour label
		Label eHourLabel = new Label(CalendarGUI, SWT.BORDER | SWT.SHADOW_IN);
		eHourLabel.setText("Hour");
		eHourLabel.setBounds(377, 182, 31, 15);
		
		//creates the list for the end time's hours
		endHourList = new List(CalendarGUI, SWT.BORDER | SWT.V_SCROLL); //creates the list
		endHourList.setBackground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_LIGHT_SHADOW));  //background color
		endHourList.setItems(new String[] {"01", "02", "03", "04", "05", "06", "07", "08", "09", 
				"10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24"}); //entries
		endHourList.setBounds(369, 203, 46, 37); //position
		
		//end time's minutes label
		Label eMinLabel = new Label(CalendarGUI, SWT.BORDER | SWT.SHADOW_IN);
		eMinLabel.setText("Minutes");
		eMinLabel.setBounds(438, 182, 44, 15);
		
		//creates the end time's minute list
		endMinList = new List(CalendarGUI, SWT.BORDER | SWT.V_SCROLL); //creates the list
		endMinList.setBackground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_LIGHT_SHADOW)); //background color
		endMinList.setItems(new String[] {"00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13",
				"14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31", "32",
				"33", "34", "35", "36", "37", "38", "39", "40", "41", "42", "43", "44", "45", "46", "47", "48", "49", "50", "51", 
				"52", "53", "54", "55", "56", "57", "58", "59"}); //entries 
		endMinList.setBounds(438, 203, 44, 37); //position
		
		//description label
		Label descripLabel = new Label(CalendarGUI, SWT.NONE);
		descripLabel.setBounds(214, 249, 66, 15);
		descripLabel.setText("Description");
		
		//text field where you can enter a description for the event
		descripBox = new Text(CalendarGUI, SWT.BORDER);
		descripBox.setBounds(21, 270, 440, 93);
		
		Listener openerListener = new Listener() {
		      public void handleEvent(Event event) {
		        createEvent();
		      }
		    };
		// creates a push button to submit
		Button pushButton = new Button (CalendarGUI, SWT.PUSH);
		pushButton.setLocation(180, 380);
		pushButton.setText("Create event");
		pushButton.addListener(SWT.Selection, openerListener);
		pushButton.pack();		

	}
	
	public void createEvent() {
        int month = monthList.getSelectionIndex();  // 0 to 11, or -1 if not selected
        int day = dayList.getSelectionIndex();   // 0 to 30, or -1 if not selected
        int year = -1;
        if (yearList.getSelectionIndex() >= 0)
            year = Integer.parseInt(yearList.getSelection()[0]);
        int shour = -1, smin = -1, ehour = -1, emin = -1;
        if (startHourList.getSelectionIndex() >= 0)
            shour = Integer.parseInt(startHourList.getSelection()[0]);
        if (startMinList.getSelectionIndex() >= 0)
            smin = Integer.parseInt(startMinList.getSelection()[0]);
        if (endHourList.getSelectionIndex() >= 0)
            ehour = Integer.parseInt(endHourList.getSelection()[0]);
        if (endMinList.getSelectionIndex() >= 0)
            emin = Integer.parseInt(endMinList.getSelection()[0]);

        
        System.out.println("Event name: " + eventNameBox.getText().trim());
        System.out.println("Description: " + descripBox.getText().trim());
        System.out.println("Date: " + year + "-" + (month+1) + "-" + (day+1));
        System.out.println("Start Time: " + shour + ":" + smin);
        System.out.println("End Time: " + ehour + ":" + emin);
        
        try
        {
            //New calendar object
            
            ICalendar calendar = new ICalendar();
            
            //New event object
            
            VEvent event = new VEvent();
            
            //creates the title of the event and sets the language to English
            
            Summary summary = new Summary(eventNameBox.getText().trim());
            event.setSummary(summary);
            
            Description description = new Description(descripBox.getText().trim());
            event.setDescription(description);
            
            summary.setLanguage("en-us");
            
            TimeZone currentZone = TimeZone.getTimeZone("America/Hawaii");
            Locale currentLocale = Locale.ENGLISH;
            
            DateTimeComponents components = new DateTimeComponents(2014, 07, 28, 15, 0, 0, false);
            
            //time in milliseconds used to generate the time
            if (year > 0 && month >= 0 && day >= 0 && shour >= 0 && smin >= 0)
            {
                @SuppressWarnings("deprecation")
				Date eventTime = new Date(year-1900, month, day+1, shour %24, smin);
                event.setDateStart(eventTime);        
            }
            if (year > 0 && month >= 0 && day >= 0 && ehour >= 0 && emin >= 0)
            {
                @SuppressWarnings("deprecation")
				Date eventTime = new Date(year-1900, month, day+1, ehour %24, emin);
                event.setDateEnd(eventTime);        
            }
            
            DateStart startDate = new DateStart(null, false);
            startDate = new DateStart(components);
                
            
            //adds the duration of the event
            
            event.setDuration(new Duration.Builder().minutes(30).build());
            
            calendar.addEvent(event);
            
            //outputs all information to a .ics file
            
            File file = new File("event.ics");
            
            Biweekly.write(calendar).go(file);
            
            System.out.println("Hello");
            JOptionPane.showMessageDialog(null, "Event Created");
        } catch (IOException ex)
        {
            
        }
        
	}
}
