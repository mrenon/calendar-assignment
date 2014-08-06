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
import biweekly.property.DateEnd;
import biweekly.property.DateStart;
import biweekly.property.Description;
import biweekly.property.Summary;
import biweekly.util.DateTimeComponents;
import biweekly.util.Duration;

import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.custom.CLabel;


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
        private List endMonthList;
        private List endYearList;
        private List endDayList;
        private Composite startGroupButton;
        private Composite endGroupButton;
        private Button sAM;
        private Button sPM;
        private Button eAM;
        private Button ePM;
        private boolean valid;

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
                CalendarGUI.setMinimumSize(new Point(69, 52));
                CalendarGUI.setTouchEnabled(true);
                CalendarGUI.setBackground(SWTResourceManager.getColor(SWT.COLOR_TITLE_BACKGROUND));
                CalendarGUI.setText("Calendar Interface");
                CalendarGUI.setSize(563, 600);
                CalendarGUI.setEnabled(true);
                
                //creates the event label
                Label eventNameLabel = new Label(CalendarGUI, SWT.BORDER | SWT.SHADOW_IN);
                eventNameLabel.setBackground(SWTResourceManager.getColor(SWT.COLOR_TITLE_BACKGROUND));
                eventNameLabel.setAlignment(SWT.CENTER);
                eventNameLabel.setFont(SWTResourceManager.getFont("Franklin Gothic Medium", 15, SWT.BOLD));
                eventNameLabel.setBounds(223, 10, 94, 22);
                eventNameLabel.setText("Event Name");
                
                //creates a text field where you can enter the event's name
                eventNameBox = new Text(CalendarGUI, SWT.BORDER);
                eventNameBox.setBackground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_LIGHT_SHADOW));
                eventNameBox.setBounds(154, 34, 230, 49);
                
                // start date label
                Label lblStartDate = new Label(CalendarGUI, SWT.NONE);
                lblStartDate.setBackground(SWTResourceManager.getColor(SWT.COLOR_TITLE_BACKGROUND));
                lblStartDate.setFont(SWTResourceManager.getFont("Franklin Gothic Medium", 15, SWT.BOLD));
                lblStartDate.setBounds(0, 88, 85, 22);
                lblStartDate.setText("Start Date");
                
                //start month label
                Label monthLabel = new Label(CalendarGUI, SWT.BORDER);
                monthLabel.setBackground(SWTResourceManager.getColor(SWT.COLOR_TITLE_BACKGROUND));
                monthLabel.setAlignment(SWT.CENTER);
                monthLabel.setBounds(41, 116, 44, 15);
                monthLabel.setText("Month");
                
                //makes a list of the months that you can select from for START date
                
                monthList = new List(CalendarGUI, SWT.BORDER | SWT.V_SCROLL); //creates a list
                monthList.setForeground(SWTResourceManager.getColor(SWT.COLOR_BLACK)); //sets the font color
                monthList.setTouchEnabled(true); 
                monthList.setBackground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_LIGHT_SHADOW)); //background color
                monthList.setItems(new String[] {"January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"}); //entries in the list
                monthList.setBounds(10, 137, 111, 93); //position it is on the frame
                
                //start day label
                Label dayLabel = new Label(CalendarGUI, SWT.BORDER | SWT.SHADOW_IN);
                dayLabel.setBackground(SWTResourceManager.getColor(SWT.COLOR_TITLE_BACKGROUND));
                dayLabel.setAlignment(SWT.CENTER);
                dayLabel.setBounds(154, 116, 31, 15);
                dayLabel.setText("Day");
                
                //makes a list of the days that you can select from for START date
                dayList = new List(CalendarGUI, SWT.BORDER | SWT.V_SCROLL); //creates a list
                dayList.setForeground(SWTResourceManager.getColor(SWT.COLOR_BLACK));
                dayList.setBackground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_LIGHT_SHADOW)); //background color
                dayList.setItems(new String[] {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", 
                                "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31"}); //entries in the list
                dayList.setBounds(154, 137, 44, 93); //position it is on the frame
                
                //start year label
                Label yearLabel = new Label(CalendarGUI, SWT.BORDER | SWT.SHADOW_IN);
                yearLabel.setBackground(SWTResourceManager.getColor(SWT.COLOR_TITLE_BACKGROUND));
                yearLabel.setAlignment(SWT.CENTER);
                yearLabel.setBounds(233, 116, 31, 15);
                yearLabel.setText("Year");
                
                //makes a list of the years that you can select from for START date
                yearList = new List(CalendarGUI, SWT.BORDER | SWT.V_SCROLL); //creates a list
                yearList.setForeground(SWTResourceManager.getColor(SWT.COLOR_BLACK)); //font color
                yearList.setBackground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_LIGHT_SHADOW)); //background color
                yearList.setItems(new String[] {"2014", "2015", "2016", "2017", "2018", "2019", "2020", "2021", "2022", "2023", 
                                "2024", "2025", "2026", "2027", "2028", "2029", "2030"}); //entries
                yearList.setBounds(223, 137, 57, 93); //position it is on the frame
                
                //start time label
                Label startTimeLabel = new Label(CalendarGUI, SWT.BORDER | SWT.SHADOW_IN); 
                startTimeLabel.setBackground(SWTResourceManager.getColor(SWT.COLOR_TITLE_BACKGROUND));
                startTimeLabel.setAlignment(SWT.CENTER);
                startTimeLabel.setBounds(286, 127, 66, 15);
                startTimeLabel.setText("Start Time");
                
                
                //hour label for start time
                Label sHourLabel = new Label(CalendarGUI, SWT.BORDER | SWT.SHADOW_IN);
                sHourLabel.setBackground(SWTResourceManager.getColor(SWT.COLOR_TITLE_BACKGROUND));
                sHourLabel.setBounds(356, 145, 31, 15);
                sHourLabel.setText("Hour");
                
                //creates the list for the start time's hour
                startHourList = new List(CalendarGUI, SWT.BORDER | SWT.V_SCROLL); //creates the list
                startHourList.setBackground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_LIGHT_SHADOW)); //background color
                startHourList.setItems(new String[] {"01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12"}); //entries based on 24-hours
                startHourList.setBounds(356, 166, 46, 37); //position
                
                //minutes label for start time
                Label sMinLabel = new Label(CalendarGUI, SWT.BORDER | SWT.SHADOW_IN);
                sMinLabel.setBackground(SWTResourceManager.getColor(SWT.COLOR_TITLE_BACKGROUND));
                sMinLabel.setBounds(422, 145, 44, 15);
                sMinLabel.setText("Minutes");
                
                //creates the list for the start time's minutes
                startMinList = new List(CalendarGUI, SWT.BORDER | SWT.V_SCROLL); //creates the list
                startMinList.setBackground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_LIGHT_SHADOW)); //background color
                startMinList.setItems(new String[] {"00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", 
                                "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31", "32", 
                                "33", "34", "35", "36", "37", "38", "39", "40", "41", "42", "43", "44", "45", "46", "47", "48", "49", "50", "51", 
                                "52", "53", "54", "55", "56", "57", "58", "59"}); //entries
                startMinList.setBounds(422, 166, 44, 37);
                
                startGroupButton = new Composite(CalendarGUI, SWT.NONE);
                startGroupButton.setBackground(SWTResourceManager.getColor(SWT.COLOR_TITLE_BACKGROUND));
                startGroupButton.setBounds(472, 166, 49, 42);
                
                sAM = new Button(startGroupButton, SWT.RADIO);
                sAM.setSelection(true);
                sAM.setBounds(0, 0, 49, 16);
                sAM.setText("AM");
                
                sPM = new Button(startGroupButton, SWT.RADIO);
                sPM.setBounds(0, 22, 49, 16);
                sPM.setText("PM");
                
               // end date label
                Label lblEndDate = new Label(CalendarGUI, SWT.NONE);
                lblEndDate.setFont(SWTResourceManager.getFont("Franklin Gothic Medium", 15, SWT.BOLD));
                lblEndDate.setBackground(SWTResourceManager.getColor(SWT.COLOR_TITLE_BACKGROUND));
                lblEndDate.setBounds(0, 247, 72, 28);
                lblEndDate.setText("End Date");
                
                // makes a list of months for END date
                endMonthList = new List(CalendarGUI, SWT.BORDER | SWT.V_SCROLL);
                endMonthList.setBackground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_LIGHT_SHADOW));
                endMonthList.setItems(new String[] {"January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"});
                endMonthList.setBounds(10, 300, 111, 101);
                
                // makes a list of days for END date
                endDayList = new List(CalendarGUI, SWT.BORDER | SWT.V_SCROLL);
                endDayList.setBackground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_LIGHT_SHADOW));
                endDayList.setItems(new String[] {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31"});
                endDayList.setBounds(156, 300, 42, 101);
                
                // makes a list of years for END date
                endYearList = new List(CalendarGUI, SWT.BORDER | SWT.V_SCROLL);
                endYearList.setBackground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_LIGHT_SHADOW));
                endYearList.setItems(new String[] {"2014", "2015", "2016", "2017", "2018", "2019", "2020", "2021", "2022", "2023", "2024", "2025", "2026", "2027", "2028", "2029", "2030"});
                endYearList.setBounds(223, 300, 57, 100);
                
                // end month label
                Label endMonthLabel = new Label(CalendarGUI, SWT.NONE);
                endMonthLabel.setBackground(SWTResourceManager.getColor(SWT.COLOR_TITLE_BACKGROUND));
                endMonthLabel.setBounds(41, 281, 59, 23);
                endMonthLabel.setText("Month");
                
                // end day label
                Label endDayLabel = new Label(CalendarGUI, SWT.NONE);
                endDayLabel.setBackground(SWTResourceManager.getColor(SWT.COLOR_TITLE_BACKGROUND));
                endDayLabel.setBounds(167, 281, 31, 14);
                endDayLabel.setText("Day");
                
               // end year label
                Label endYearLabel = new Label(CalendarGUI, SWT.NONE);
                endYearLabel.setBackground(SWTResourceManager.getColor(SWT.COLOR_TITLE_BACKGROUND));
                endYearLabel.setBounds(228, 281, 36, 14);
                endYearLabel.setText("Year");
                
               
                //end time label
                Label endTimeLabel = new Label(CalendarGUI, SWT.BORDER | SWT.SHADOW_IN);
                endTimeLabel.setBackground(SWTResourceManager.getColor(SWT.COLOR_TITLE_BACKGROUND));
                endTimeLabel.setText("End Time");
                endTimeLabel.setAlignment(SWT.CENTER);
                endTimeLabel.setBounds(286, 300, 66, 15);
                
                //end time's hour label
                Label eHourLabel = new Label(CalendarGUI, SWT.BORDER | SWT.SHADOW_IN);
                eHourLabel.setBackground(SWTResourceManager.getColor(SWT.COLOR_TITLE_BACKGROUND));
                eHourLabel.setText("Hour");
                eHourLabel.setBounds(342, 321, 31, 15);
                
                //creates the list for the end time's hours
                endHourList = new List(CalendarGUI, SWT.BORDER | SWT.V_SCROLL); //creates the list
                endHourList.setBackground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_LIGHT_SHADOW));  //background color
                endHourList.setItems(new String[] {"01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12"}); //entries
                endHourList.setBounds(342, 335, 46, 37); //position
                
                //end time's minutes label
                Label eMinLabel = new Label(CalendarGUI, SWT.BORDER | SWT.SHADOW_IN);
                eMinLabel.setBackground(SWTResourceManager.getColor(SWT.COLOR_TITLE_BACKGROUND));
                eMinLabel.setText("Minutes");
                eMinLabel.setBounds(411, 321, 44, 15);
                
                //creates the end time's minute list
                endMinList = new List(CalendarGUI, SWT.BORDER | SWT.V_SCROLL); //creates the list
                endMinList.setBackground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_LIGHT_SHADOW)); //background color
                endMinList.setItems(new String[] {"00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13",
                                "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31", "32",
                                "33", "34", "35", "36", "37", "38", "39", "40", "41", "42", "43", "44", "45", "46", "47", "48", "49", "50", "51", 
                                "52", "53", "54", "55", "56", "57", "58", "59"}); //entries 
                endMinList.setBounds(411, 335, 44, 37);
                
                //description label
                Label descripLabel = new Label(CalendarGUI, SWT.NONE);
                descripLabel.setFont(SWTResourceManager.getFont("Franklin Gothic Medium", 15, SWT.BOLD));
                descripLabel.setBackground(SWTResourceManager.getColor(SWT.COLOR_TITLE_BACKGROUND));
                descripLabel.setBounds(223, 423, 94, 22);
                descripLabel.setText("Description");
                
                //text field where you can enter a description for the event
                descripBox = new Text(CalendarGUI, SWT.BORDER);
                descripBox.setBackground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_LIGHT_SHADOW));
                descripBox.setBounds(53, 451, 440, 93);
                
                Listener openerListener = new Listener() {
                      public void handleEvent(Event event) {
                        createEvent();
                      }
                    };
                // creates a push button to submit
                Button pushButton = new Button (CalendarGUI, SWT.BORDER);
                pushButton.setLocation(218, 550);
                pushButton.setText("Create Event");
                pushButton.addListener(SWT.Selection, openerListener);
                pushButton.pack();
                
                endGroupButton = new Composite(CalendarGUI, SWT.NONE);
                endGroupButton.setBackground(SWTResourceManager.getColor(SWT.COLOR_TITLE_BACKGROUND));
                endGroupButton.setBounds(472, 330, 49, 42);
                
                eAM = new Button(endGroupButton, SWT.RADIO);
                eAM.setBounds(0, 26, 49, 16);
                eAM.setText("AM");
                
                ePM = new Button(endGroupButton, SWT.RADIO);
                ePM.setBounds(0, 4, 49, 16);
                ePM.setSelection(true);
                ePM.setText("PM");

        }

        public void createEvent() {
        int month = monthList.getSelectionIndex();  // 0 to 11, or -1 if not selected
        int day = dayList.getSelectionIndex();   // 0 to 30, or -1 if not selected
        int year = -1;
        
        int endMonth = endMonthList.getSelectionIndex();
        int endDay = endDayList.getSelectionIndex();
        int endYear = -1;
        
        //just a easier way to store AM and PM times
        int tempStartTime= 0; 
        int tempEndTime = 0;
        
        
        if (yearList.getSelectionIndex() >= 0)
            year = Integer.parseInt(yearList.getSelection()[0]);
        if (endYearList.getSelectionIndex() >= 0)
        	endYear = Integer.parseInt(endYearList.getSelection()[0]);
        int shour = -1, smin = -1, ehour = -1, emin = -1;
        if (startHourList.getSelectionIndex() >= 0)
            shour = Integer.parseInt(startHourList.getSelection()[0]);
        if (startMinList.getSelectionIndex() >= 0)
            smin = Integer.parseInt(startMinList.getSelection()[0]);
        if (endHourList.getSelectionIndex() >= 0)
            ehour = Integer.parseInt(endHourList.getSelection()[0]);
        if (endMinList.getSelectionIndex() >= 0)
            emin = Integer.parseInt(endMinList.getSelection()[0]);
        
        //checks if PM is selected and goes off the 24h system we previously implemented
        if (sPM.getSelection())
        	tempStartTime = shour + 12;
        else
        	tempStartTime = shour;
        if (ePM.getSelection())
        	tempEndTime = ehour + 12;
        else
        	tempEndTime = ehour;
        
        System.out.println("Event name: " + eventNameBox.getText().trim());
        System.out.println("Description: " + descripBox.getText().trim());
        System.out.println("Start Date: " + year + "-" + (month+1) + "-" + (day+1));
      
        //prints AM/PM
        if(sPM.getSelection())
        	System.out.println("Start Time: " + shour + ":" + smin + "PM");
        else
        	System.out.println("Start Time: " + shour + ":" + smin + "AM");
        
        System.out.println("End Date: " + endYear + "-" + (endMonth+1) + "-" + (endDay+1));
        
        //prints AM/PM
        if(ePM.getSelection())
        	System.out.println("End Time: " + ehour + ":" + emin + "PM");
        else
        	System.out.println("End Time: " + ehour + ":" + emin + "AM");
        
        
        
        
        try
        {
        	
        	valid = false;
        	
        	//temp variables to check if event name and description are empty fields
        	String checkSummary = eventNameBox.getText().trim();
        	String checkDescrip = descripBox.getText().trim();
        	
        	if(checkSummary.isEmpty() || checkDescrip.isEmpty()){ 
        		JOptionPane.showMessageDialog(null, "Please Enter All Data Fields");
        	}
        	else if(month == -1 || day == -1 || year == -1 || tempEndTime == -1 || tempStartTime == -1){ //check if there is nothing selected in month, day, year, start and endtime
        		JOptionPane.showMessageDialog(null, "Please Enter All Data Fields"); 
        	}
        	else if(tempEndTime < tempStartTime){ //check if end time is before start time
            	JOptionPane.showMessageDialog(null, "Bad Hour Input");
            }
            else if(tempEndTime > tempStartTime && emin < smin){//create .ics file 
            	valid = true;
            }
            else if(emin < smin){ //check if end time min is before start time min
            	JOptionPane.showMessageDialog(null, "Bad min Input");
            }
            else{
            	valid = true;
            }
        	
        	if(valid == true){
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
        		if (year > 0 && month >= 0 && day >= 0 && tempStartTime >= 0 && smin >= 0)
        		{
        			@SuppressWarnings("deprecation")
                                Date eventTime = new Date(year-1900, month, day+1, tempStartTime %24, smin);
        			event.setDateStart(eventTime);        
        		}
        		if (endYear > 0 && endMonth >= 0 && endDay >= 0 && tempEndTime >= 0 && emin >= 0)
        		{
        			@SuppressWarnings("deprecation")
                                Date eventTime = new Date(year-1900, endMonth, endDay+1, tempEndTime %24, emin);
        			event.setDateEnd(eventTime);        
        		}
            
        		DateStart startDate = new DateStart(null, false);
        		startDate = new DateStart(components);
        		
        		DateEnd endDate = new DateEnd(null, false);
        		endDate = new DateEnd(components);
            
            
        		//adds the duration of the event
            
        		event.setDuration(new Duration.Builder().minutes(30).build());
            
        		calendar.addEvent(event);
            
        		//outputs all information to a .ics file
        		
        		File file = new File("event.ics");
            
        		Biweekly.write(calendar).go(file);

        		JOptionPane.showMessageDialog(null, "Event Created");
            
        		System.exit(0);
        		
        	}
        } catch (IOException ex)

        {
            
        }
        
        }
}
