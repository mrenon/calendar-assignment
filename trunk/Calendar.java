import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

import org.eclipse.swt.widgets.Display; 
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.MessageBox;
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
import biweekly.component.VTimezone;
import biweekly.property.Classification;
import biweekly.property.DateEnd;
import biweekly.property.DateStart;
import biweekly.property.Description;
import biweekly.property.Geo;
import biweekly.property.ProductId;
import biweekly.property.Summary;
import biweekly.property.Version;
import biweekly.util.DateTimeComponents;
import biweekly.util.Duration;
import biweekly.util.Recurrence;
import biweekly.util.Recurrence.Frequency;

import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;

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
        private List priorityList;
        private Composite startGroupButton;
        private Composite endGroupButton;
        private Button sAM;
        private Button sPM;
        private Button eAM;
        private Button ePM;
        private Composite setStatusButtonGroup;
        private Button privateButton;
        private Button publicButton;
        private Button confidentialButton;
        private Label priorityDescriptionLabel;
        private boolean dayNumValid;
        private boolean dayValid;
        private boolean monthValid;
        private boolean yearValid;
        private boolean timeValid;
        private boolean textValid;
        private Label coordLabel;
        private Text latitudeText;
        private Text longitudeText;
        private Composite recureEventButGrp;
        private Button yesRecurrButton;
        private Button noRecurrButton;
        private List recureList;
        
        

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
                CalendarGUI.setSize(743, 625);
                CalendarGUI.setEnabled(true);
                
                //creates the event label
                Label eventNameLabel = new Label(CalendarGUI, SWT.SHADOW_IN);
                eventNameLabel.setBackground(SWTResourceManager.getColor(SWT.COLOR_TITLE_BACKGROUND));
                eventNameLabel.setAlignment(SWT.CENTER);
                eventNameLabel.setFont(SWTResourceManager.getFont("Franklin Gothic Medium", 15, SWT.BOLD));
                eventNameLabel.setBounds(306, 10, 125, 22);
                eventNameLabel.setText("Event Name");
                
                //creates a text field where you can enter the event's name
                eventNameBox = new Text(CalendarGUI, SWT.BORDER);
                eventNameBox.setBackground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_LIGHT_SHADOW));
                eventNameBox.setBounds(233, 38, 274, 49);
                
                //status of the event label
                Label eventStatusLabel = new Label(CalendarGUI, SWT.NONE);
                eventStatusLabel.setBackground(SWTResourceManager.getColor(SWT.COLOR_TITLE_BACKGROUND));
                eventStatusLabel.setBounds(584, 116, 76, 15);
                eventStatusLabel.setText("Event Status");
                
                //holds the private, public, and confidential buttons in a group
                setStatusButtonGroup = new Composite(CalendarGUI, SWT.NONE);
                setStatusButtonGroup.setBackground(SWTResourceManager.getColor(SWT.COLOR_TITLE_BACKGROUND));
                setStatusButtonGroup.setBounds(569, 145, 100, 65);
                
                // buttons for private, public, and confidential
                publicButton = new Button(setStatusButtonGroup, SWT.RADIO);
                publicButton.addSelectionListener(new SelectionAdapter() {
                        @Override
                        public void widgetSelected(SelectionEvent e) {
                                //action listener saying that PUBLIC was selected
                                //put code here 
                        }
                });
                publicButton.setBounds(0, 0, 91, 18);
                publicButton.setSelection(true);
                publicButton.setText("Public");
                
                privateButton = new Button(setStatusButtonGroup, SWT.RADIO);
                privateButton.addSelectionListener(new SelectionAdapter() {
                        @Override
                        public void widgetSelected(SelectionEvent e) {
                                //action listener saying that PRIVATE was selected
                                //put code here 
                        }
                });
                privateButton.setBounds(0, 23, 91, 18);
                privateButton.setText("Private");
                
                confidentialButton = new Button(setStatusButtonGroup, SWT.RADIO);
                confidentialButton.addSelectionListener(new SelectionAdapter() {
                        @Override
                        public void widgetSelected(SelectionEvent e) {
                                //action listener saying that CONFIDENTIAL was selected
                                //put code here 
                        }
                });
                confidentialButton.setBounds(0, 47, 103, 18);
                confidentialButton.setText("Confidential");
                
                // start date label
                Label lblStartDate = new Label(CalendarGUI, SWT.NONE);
                lblStartDate.setBackground(SWTResourceManager.getColor(SWT.COLOR_TITLE_BACKGROUND));
                lblStartDate.setFont(SWTResourceManager.getFont("Franklin Gothic Medium", 15, SWT.BOLD));
                lblStartDate.setBounds(10, 90, 111, 22);
                lblStartDate.setText("Start Date");
                
                //start month label
                Label monthLabel = new Label(CalendarGUI, SWT.NONE);
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
                Label dayLabel = new Label(CalendarGUI, SWT.SHADOW_IN);
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
                Label yearLabel = new Label(CalendarGUI, SWT.SHADOW_IN);
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
                Label sHourLabel = new Label(CalendarGUI, SWT.SHADOW_IN);
                sHourLabel.setBackground(SWTResourceManager.getColor(SWT.COLOR_TITLE_BACKGROUND));
                sHourLabel.setBounds(323, 152, 31, 15);
                sHourLabel.setText("Hour");
                
                //creates the list for the start time's hour
                startHourList = new List(CalendarGUI, SWT.BORDER | SWT.V_SCROLL); //creates the list
                startHourList.setBackground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_LIGHT_SHADOW)); //background color
                startHourList.setItems(new String[] {"01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12"}); //entries based on 24-hours
                startHourList.setBounds(323, 173, 46, 37); //position
                
                //minutes label for start time
                Label sMinLabel = new Label(CalendarGUI, SWT.SHADOW_IN);
                sMinLabel.setBackground(SWTResourceManager.getColor(SWT.COLOR_TITLE_BACKGROUND));
                sMinLabel.setBounds(387, 152, 44, 15);
                sMinLabel.setText("Minutes");
                
                //creates the list for the start time's minutes
                startMinList = new List(CalendarGUI, SWT.BORDER | SWT.V_SCROLL); //creates the list
                startMinList.setBackground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_LIGHT_SHADOW)); //background color
                startMinList.setItems(new String[] {"00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", 
                                "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31", "32", 
                                "33", "34", "35", "36", "37", "38", "39", "40", "41", "42", "43", "44", "45", "46", "47", "48", "49", "50", "51", 
                                "52", "53", "54", "55", "56", "57", "58", "59"}); //entries
                startMinList.setBounds(397, 173, 44, 37);
                
                startGroupButton = new Composite(CalendarGUI, SWT.NONE);
                startGroupButton.setBackground(SWTResourceManager.getColor(SWT.COLOR_TITLE_BACKGROUND));
                startGroupButton.setBounds(452, 173, 49, 42);
                
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
                lblEndDate.setBounds(10, 247, 100, 28);
                lblEndDate.setText("End Date");
                
                // makes a list of months for END date
                endMonthList = new List(CalendarGUI, SWT.BORDER | SWT.V_SCROLL);
                endMonthList.setBackground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_LIGHT_SHADOW));
                endMonthList.setItems(new String[] {"January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"});
                endMonthList.setBounds(10, 300, 111, 101);
                
                // makes a list of days for END date
                endDayList = new List(CalendarGUI, SWT.BORDER | SWT.V_SCROLL);
                endDayList.setBackground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_LIGHT_SHADOW));
                endDayList.setItems(new String[] {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14",
                                "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31"});
                endDayList.setBounds(156, 300, 42, 101);
                
                // makes a list of years for END date
                endYearList = new List(CalendarGUI, SWT.BORDER | SWT.V_SCROLL);
                endYearList.setBackground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_LIGHT_SHADOW));
                endYearList.setItems(new String[] {"2014", "2015", "2016", "2017", "2018", "2019", "2020", "2021", "2022", "2023"
                                , "2024", "2025", "2026", "2027", "2028", "2029", "2030"});
                endYearList.setBounds(223, 300, 57, 100);
                
                // end month label
                Label endMonthLabel = new Label(CalendarGUI, SWT.NONE);
                endMonthLabel.setBackground(SWTResourceManager.getColor(SWT.COLOR_TITLE_BACKGROUND));
                endMonthLabel.setBounds(41, 281, 59, 15);
                endMonthLabel.setText("Month");
                
                // end day label
                Label endDayLabel = new Label(CalendarGUI, SWT.NONE);
                endDayLabel.setBackground(SWTResourceManager.getColor(SWT.COLOR_TITLE_BACKGROUND));
                endDayLabel.setBounds(165, 281, 20, 14);
                endDayLabel.setText("Day");
                
                //end year label
                Label endYearLabel = new Label(CalendarGUI, SWT.NONE);
                endYearLabel.setBackground(SWTResourceManager.getColor(SWT.COLOR_TITLE_BACKGROUND));
                endYearLabel.setBounds(228, 281, 36, 14);
                endYearLabel.setText("Year");
                
               
                //end time label
                Label endTimeLabel = new Label(CalendarGUI, SWT.BORDER | SWT.SHADOW_IN);
                endTimeLabel.setBackground(SWTResourceManager.getColor(SWT.COLOR_TITLE_BACKGROUND));
                endTimeLabel.setText("End Time");
                endTimeLabel.setAlignment(SWT.CENTER);
                endTimeLabel.setBounds(286, 293, 66, 15);
                
                //end time's hour label
                Label eHourLabel = new Label(CalendarGUI, SWT.SHADOW_IN);
                eHourLabel.setBackground(SWTResourceManager.getColor(SWT.COLOR_TITLE_BACKGROUND));
                eHourLabel.setText("Hour");
                eHourLabel.setBounds(323, 314, 31, 15);
                
                //creates the list for the end time's hours
                endHourList = new List(CalendarGUI, SWT.BORDER | SWT.V_SCROLL); //creates the list
                endHourList.setBackground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_LIGHT_SHADOW));  //background color
                endHourList.setItems(new String[] {"01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12"}); //entries
                endHourList.setBounds(323, 335, 46, 37); //position
                
                //end time's minutes label
                Label eMinLabel = new Label(CalendarGUI, SWT.SHADOW_IN);
                eMinLabel.setBackground(SWTResourceManager.getColor(SWT.COLOR_TITLE_BACKGROUND));
                eMinLabel.setText("Minutes");
                eMinLabel.setBounds(387, 314, 44, 22);
                
                //creates the end time's minute list
                endMinList = new List(CalendarGUI, SWT.BORDER | SWT.V_SCROLL); //creates the list
                endMinList.setBackground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_LIGHT_SHADOW)); //background color
                endMinList.setItems(new String[] {"00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13",
                                "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31", "32",
                                "33", "34", "35", "36", "37", "38", "39", "40", "41", "42", "43", "44", "45", "46", "47", "48", "49", "50", "51", 
                                "52", "53", "54", "55", "56", "57", "58", "59"}); //entries 
                endMinList.setBounds(398, 335, 44, 37);
                
                //description label
                Label descripLabel = new Label(CalendarGUI, SWT.NONE);
                descripLabel.setFont(SWTResourceManager.getFont("Franklin Gothic Medium", 15, SWT.BOLD));
                descripLabel.setBackground(SWTResourceManager.getColor(SWT.COLOR_TITLE_BACKGROUND));
                descripLabel.setBounds(10, 423, 129, 22);
                descripLabel.setText("Description");
                
                //text field where you can enter a description for the event
                descripBox = new Text(CalendarGUI, SWT.BORDER);
                descripBox.setBackground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_LIGHT_SHADOW));
                descripBox.setBounds(20, 451, 248, 73);
                
                //priority label 
                Label priorityLabel = new Label(CalendarGUI, SWT.NONE);
                priorityLabel.setBackground(SWTResourceManager.getColor(SWT.COLOR_TITLE_BACKGROUND));
                priorityLabel.setFont(SWTResourceManager.getFont("Franklin Gothic Medium", 15, SWT.BOLD));
                priorityLabel.setBounds(306, 423, 148, 30);
                priorityLabel.setText("Priority Level");
                
                //description for priority level
                priorityDescriptionLabel = new Label(CalendarGUI, SWT.NONE);
                priorityDescriptionLabel.setFont(SWTResourceManager.getFont("Lucida Grande", 11, SWT.ITALIC));
                priorityDescriptionLabel.setBackground(SWTResourceManager.getColor(SWT.COLOR_TITLE_BACKGROUND));
                priorityDescriptionLabel.setBounds(305, 454, 196, 18);
                priorityDescriptionLabel.setText("\"0- none, 1- highest, 9- lowest\"");
                
                //priority list where user can choose desired priority level
                priorityList = new List(CalendarGUI, SWT.BORDER | SWT.V_SCROLL);
                priorityList.setItems(new String[] {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9"});
                priorityList.setBackground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_LIGHT_SHADOW));
                priorityList.setBounds(323, 475, 159, 49);
                
                Listener openerListener = new Listener() {
                      public void handleEvent(Event event) {
                        createEvent();
                      }
                    };
                    
                // creates a push button to submit
                Button pushButton = new Button (CalendarGUI, SWT.BORDER);
                pushButton.setSize(161, 29);
                pushButton.setLocation(306, 565);
                pushButton.setText("Create Event");
                pushButton.addListener(SWT.Selection, openerListener);
                pushButton.pack();
                
                //button group to hold end dates AM and PM button
                endGroupButton = new Composite(CalendarGUI, SWT.NONE);
                endGroupButton.setBackground(SWTResourceManager.getColor(SWT.COLOR_TITLE_BACKGROUND));
                endGroupButton.setBounds(448, 330, 49, 42);
                
                ePM = new Button(endGroupButton, SWT.RADIO);
                ePM.setBounds(0, 26, 49, 16);
                ePM.setSelection(true);
                ePM.setText("PM");
                
                eAM = new Button(endGroupButton, SWT.RADIO);
                eAM.setBounds(0, 4, 49, 16);
                eAM.setText("AM");
                
                //Recurrence event label
                Label recureLabel = new Label(CalendarGUI, SWT.NONE);
                recureLabel.setBackground(SWTResourceManager.getColor(SWT.COLOR_TITLE_BACKGROUND));
                recureLabel.setBounds(519, 451, 91, 15);
                recureLabel.setText("Recurring");
                
                coordLabel = new Label(CalendarGUI, SWT.NONE);
                coordLabel.setBackground(SWTResourceManager.getColor(SWT.COLOR_TITLE_BACKGROUND));
                coordLabel.setBounds(546, 281, 142, 22);
                coordLabel.setText("Geographic Coordinates");
                
                latitudeText = new Text(CalendarGUI, SWT.BORDER);
                latitudeText.setText("-74.006605");
                latitudeText.setBounds(584, 342, 127, 21);
                
                longitudeText = new Text(CalendarGUI, SWT.BORDER);
                longitudeText.setText("40.714623");
                longitudeText.setBounds(584, 311, 127, 21);
                
                Label longitudeLabel = new Label(CalendarGUI, SWT.NONE);
                longitudeLabel.setBackground(SWTResourceManager.getColor(SWT.COLOR_TITLE_BACKGROUND));
                longitudeLabel.setBounds(521, 314, 57, 15);
                longitudeLabel.setText("Longitude");
                
                Label latitudeLabel = new Label(CalendarGUI, SWT.NONE);
                latitudeLabel.setBackground(SWTResourceManager.getColor(SWT.COLOR_TITLE_BACKGROUND));
                latitudeLabel.setText("Latitude");
                latitudeLabel.setBounds(519, 345, 57, 15);
                
                recureList = new List(CalendarGUI, SWT.BORDER | SWT.V_SCROLL);
                recureList.setEnabled(false);  
                recureList.setBackground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_LIGHT_SHADOW));
                recureList.setItems(new String[] {"Daily", "Weekly", "Monthly", "Yearly"});
                recureList.setBounds(599, 475, 100, 49);
                
                recureEventButGrp = new Composite(CalendarGUI, SWT.NONE);
                recureEventButGrp.setBackground(SWTResourceManager.getColor(SWT.COLOR_TITLE_BACKGROUND));
                recureEventButGrp.setBounds(519, 475, 74, 41);
                
                yesRecurrButton = new Button(recureEventButGrp, SWT.RADIO);
                yesRecurrButton.addSelectionListener(new SelectionAdapter() {
                        @Override
                        public void widgetSelected(SelectionEvent e) {
                                recureList.setEnabled(true);
                                //recure button was SELECTED add code here for 
                                //recurrence event ..i think
                                
                        }
                });
                yesRecurrButton.setBounds(0, 0, 74, 16);
                yesRecurrButton.setText("YES");
                
                noRecurrButton = new Button(recureEventButGrp, SWT.RADIO);
                noRecurrButton.addSelectionListener(new SelectionAdapter() {
                        @Override
                        public void widgetSelected(SelectionEvent e) {
                                recureList.setEnabled(false);
                                //recure button was NOT SELECTED 
                        }
                });
                noRecurrButton.setBounds(0, 25, 74, 16);
                noRecurrButton.setSelection(true);
                noRecurrButton.setText("NO");
        }

        public void createEvent() {
        int startMonth = monthList.getSelectionIndex();  // 0 to 11, or -1 if not selected
        int startDay = dayList.getSelectionIndex();   // 0 to 30, or -1 if not selected
        int startYear = -1;
        
        int endMonth = endMonthList.getSelectionIndex();
        int endDay = endDayList.getSelectionIndex();
        int endYear = -1;
        
        //just a easier way to store AM and PM times
        int tempStartTime= 0; 
        int tempEndTime = 0;
        
        // variable to store priority selection
        int priorityIndex = priorityList.getSelectionIndex();
        
        //variable to store the recurrence selection
        int recureIndex = recureList.getSelectionIndex();
        
        if (yearList.getSelectionIndex() >= 0)
            startYear = Integer.parseInt(yearList.getSelection()[0]);
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
        System.out.println("Start Date: " + startYear + "-" + (startMonth+1) + "-" + (startDay+1));
        
        System.out.println("Priority level: " + priorityIndex);
        
        if (privateButton.getSelection())
        {
        	System.out.println("Classification: Private");
        }
        else if (publicButton.getSelection())
        {
        	System.out.println("Classification: Public");
        }
        else if (confidentialButton.getSelection())
        {
        	System.out.println("Classification: Confidential");
        }
        
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
                
            dayValid = false;
            monthValid = false;
            yearValid = false;
            timeValid = false;
            textValid = false;  
                dayNumValid = false;
                
                //temp variables to check if event name and description are empty fields
                String checkSummary = eventNameBox.getText().trim();
                String checkDescrip = descripBox.getText().trim();
                
                
                /**********************************************
                 * if statements to check if data was entered
                 *********************************************/
                if(checkSummary.isEmpty() || checkDescrip.isEmpty()){ 
                	
                     MessageBox emptyDescrip = new MessageBox(CalendarGUI, SWT.ICON_INFORMATION | SWT.OK);                 
                     emptyDescrip.setText("Error");
                     emptyDescrip.setMessage("Please Enter All Data Fields");
                     emptyDescrip.open();
                }
                else if(startMonth == -1 || startDay == -1 || startYear == -1 || shour == -1 || smin == -1){ //check if there is nothing selected in the start month, day, year, and time

                    MessageBox emptyStart = new MessageBox(CalendarGUI, SWT.ICON_INFORMATION | SWT.OK);                   
                    emptyStart.setText("Error");
                    emptyStart.setMessage("Please Enter All Data Fields");
                    emptyStart.open();
                }
                else if(endMonth == -1 || endDay == -1 || endYear == -1 || ehour == -1 || emin == -1){ //check if there is nothing selected in the end month, day, year, and time
                    MessageBox emptyEnd = new MessageBox(CalendarGUI, SWT.ICON_INFORMATION | SWT.OK);                   
                    emptyEnd.setText("Error");
                    emptyEnd.setMessage("Please Enter All Data Fields");
                    emptyEnd.open();
                }
                else{
                        textValid = true;
                }
                
                /************************************************************************************************
                 * if statements to check if the start time (hour, min) is before the end time (hour, min) 
                 ************************************************************************************************/
                if(tempEndTime < tempStartTime) //check if end time is before start time
                {
                    MessageBox badHour = new MessageBox(CalendarGUI, SWT.ICON_INFORMATION | SWT.OK);                   
                    badHour.setText("Error");
                    badHour.setMessage("Bad Hour Input");
                    badHour.open();
                }
                else if(tempEndTime > tempStartTime && emin < smin)//create .ics file 
                {
                    timeValid = true;
                }
                else if(emin < smin) //check if end time min is before start time min
                {
                    MessageBox badMinute = new MessageBox(CalendarGUI, SWT.ICON_INFORMATION | SWT.OK);                   
                    badMinute.setText("Error");
                    badMinute.setMessage("Bad Minute Input");
                    badMinute.open();
                }
                else{
                        timeValid = true;
                }
                
                /************************************************************************************************
                 * if statements to check if the start year is before the end year 
                 ************************************************************************************************/
                if(startYear <= endYear){ //2014 < 2015
                        yearValid = true;
                }
                else{
                    MessageBox badYear = new MessageBox(CalendarGUI, SWT.ICON_INFORMATION | SWT.OK);                   
                    badYear.setText("Error");
                    badYear.setMessage("Bad Year Input");
                    badYear.open();
                }
                
                /************************************************************************************************
                 * if statements to check if the start day is before the end day 
                 ************************************************************************************************/
                if(yearValid == true){ //1 < 2
                        if(startMonth <= endMonth && startDay <= endDay)
                                dayValid = true;
                        else if(startMonth <= endMonth && startDay >= endDay)//possibility of bad time or year input
                                dayValid = true;
                        else if(startMonth >= endMonth && startDay <= endDay)
                                dayValid = true;
                        else if(startMonth >= endMonth && startDay >= endDay && startYear < endYear)
                                dayValid = true;
                }
                else{
                    MessageBox badDay = new MessageBox(CalendarGUI, SWT.ICON_INFORMATION | SWT.OK);                   
                    badDay.setText("Error");
                    badDay.setMessage("Bad Day Input");
                    badDay.open();
                }
                
                /************************************************************************************************
                 * if statements to check if the start month is before the end month 
                 ************************************************************************************************/
                if(yearValid == true && dayValid == true){ //jan < feb
                        if(startMonth <= endMonth)
                                monthValid = true;
                        else if(startMonth >= endMonth && endYear > startYear)
                                monthValid = true;
                        else
                        {
                            MessageBox badMonth = new MessageBox(CalendarGUI, SWT.ICON_INFORMATION | SWT.OK);                   
                            badMonth.setText("Error");
                            badMonth.setMessage("Bad Month Input");
                            badMonth.open();
                        }
                }
                else
                {
                    MessageBox badMonth = new MessageBox(CalendarGUI, SWT.ICON_INFORMATION | SWT.OK);                   
                    badMonth.setText("Error");
                    badMonth.setMessage("Bad Month Input");
                    badMonth.open();
                }
                
                /**************************************************************
                 * if statements to check if the month is correct
                 * also to check that the start month is before the end month
                 **************************************************************/
                //checks for February
                if(startMonth ==  1 &&  startYear %4 == 0){
                        if(startDay > 28)//it is a leap year for the start date
                        {
                        MessageBox startLeapYear = new MessageBox(CalendarGUI, SWT.ICON_INFORMATION | SWT.OK);                   
                        startLeapYear.setText("Error");
                        startLeapYear.setMessage("Start Date Bad Input: It is a leap year, but you cannot enter 30 or 31 on February");
                        startLeapYear.open();
                        }
                                
                        else
                                dayNumValid = true;
                }
                else if(endMonth == 1 && endYear %4 == 0){//it is a leap year for the end date
                        if(endDay > 28)
                        {
                            MessageBox endLeapYear = new MessageBox(CalendarGUI, SWT.ICON_INFORMATION | SWT.OK);                   
                            endLeapYear.setText("Error");
                            endLeapYear.setMessage("Start Date Bad Input: It is a leap year, but you cannot enter 30 or 31 on February");
                            endLeapYear.open();
                        }
                        else
                                dayNumValid = true;
                }
                else if(startDay >= 28 && startMonth == 1 || endMonth == 1 && endDay >= 28) //regular february
                {
                    MessageBox febError = new MessageBox(CalendarGUI, SWT.ICON_INFORMATION | SWT.OK);                   
                    febError.setText("Error");
                    febError.setMessage("Bad Input: It is February, cannot enter 29, 30, 31");
                    febError.open();    
                }
                
                //checks for months that dont have 31 days
                else if(startDay == 30 && startMonth == 3)
                {
                    MessageBox sAprilError = new MessageBox(CalendarGUI, SWT.ICON_INFORMATION | SWT.OK);                   
                    sAprilError.setText("Error");
                    sAprilError.setMessage("Start Date Bad Input: April does not have 31 days");
                    sAprilError.open();
                }
                else if(startDay == 30 && startMonth == 5)
                {
                    MessageBox sJuneError = new MessageBox(CalendarGUI, SWT.ICON_INFORMATION | SWT.OK);                   
                    sJuneError.setText("Error");
                    sJuneError.setMessage("Start Date Bad Input: June does not have 31 days");
                    sJuneError.open();    
                }
                else if(startDay == 30 && startMonth == 8)
                {
                    MessageBox sSeptError = new MessageBox(CalendarGUI, SWT.ICON_INFORMATION | SWT.OK);                   
                    sSeptError.setText("Error");
                    sSeptError.setMessage("Start Date Bad Input: September does not have 31 days");
                    sSeptError.open();
                }
                else if(startDay == 30 && startMonth == 10)
                {
                    MessageBox sNovemError = new MessageBox(CalendarGUI, SWT.ICON_INFORMATION | SWT.OK);                   
                    sNovemError.setText("Error");
                    sNovemError.setMessage("Start Date Bad Input: November does not have 31 days");
                    sNovemError.open();
                }
                else if(endDay == 30 && endMonth == 3)
                {
                    MessageBox eAprilError = new MessageBox(CalendarGUI, SWT.ICON_INFORMATION | SWT.OK);                   
                    eAprilError.setText("Error");
                    eAprilError.setMessage("End Date Bad Input: April does not have 31 days");
                    eAprilError.open();
                }
                else if(endDay == 30 && endMonth == 5)
                {
                    MessageBox eJuneError = new MessageBox(CalendarGUI, SWT.ICON_INFORMATION | SWT.OK);                   
                    eJuneError.setText("Error");
                    eJuneError.setMessage("End Date Bad Input: June does not have 31 days");
                    eJuneError.open();
                }
                else if(endDay == 30 && endMonth == 8)
                {
                    MessageBox eSeptError = new MessageBox(CalendarGUI, SWT.ICON_INFORMATION | SWT.OK);                   
                    eSeptError.setText("Error");
                    eSeptError.setMessage("End Date Bad Input: September does not have 31 days");
                    eSeptError.open();
                }
                else if(endDay == 30 && endMonth == 10)
                {
                    MessageBox eNovemError = new MessageBox(CalendarGUI, SWT.ICON_INFORMATION | SWT.OK);                   
                    eNovemError.setText("Error");
                    eNovemError.setMessage("End Date Bad Input: November does not have 31 days");
                    eNovemError.open();
                }
                
                
                //passed all the tests ready for .ics file creation
                else
                    dayNumValid = true;

                /**********************
                 * if passed all the valid checks
                 * then create the .ics file
                 **********************/
                    if(dayNumValid == true && dayValid == true && monthValid == true && yearValid == true && timeValid == true && textValid ==true){
                        //New calendar object
            
                        ICalendar calendar = new ICalendar();
            
                        //New event object
                        
                        VEvent event = new VEvent();
            
                        //creates the title of the event and sets the language to English
            
                        Summary summary = new Summary(eventNameBox.getText().trim());
                        event.setSummary(summary);
                        Description description = new Description(descripBox.getText().trim());
                        event.setDescription(description);
                
                        TimeZone currentZone = TimeZone.getTimeZone("America/Hawaii");
                        Locale currentLocale = Locale.ENGLISH;
                        
                        //sets the classification of the event
                        
                        if (privateButton.getSelection())
                        {
                        	event.setClassification(Classification.private_());
                        }
                        else if (publicButton.getSelection())
                        {
                        	event.setClassification(Classification.public_());
                        }
                        else if (confidentialButton.getSelection())
                        {
                        	event.setClassification(Classification.confidential());
                        }
                        
                        //sets the event priority
                        
                        event.setPriority(priorityIndex);
                        
                        //sets the geographic location
                        
                        Geo location = new Geo(40.714623,-74.006605);                
                        
                        event.setGeo(location);
                        
                        //sets the timezone
                        
                        VTimezone timezone = new VTimezone("America/Hawaii");
                        
                        calendar.addTimezone(timezone);
            
                        DateTimeComponents components = new DateTimeComponents(2014, 07, 28, 15, 0, 0, false);
            
                        //time in milliseconds used to generate the time
                        if (startYear > 0 && startMonth >= 0 && startDay >= 0 && tempStartTime >= 0 && smin >= 0)
                        {
                                @SuppressWarnings("deprecation")
                                Date eventTime = new Date(startYear-1900, startMonth, startDay+1, tempStartTime %24, smin);
                                event.setDateStart(eventTime);        
                        }
                        if (endYear > 0 && endMonth >= 0 && endDay >= 0 && tempEndTime >= 0 && emin >= 0)
                        {
                                @SuppressWarnings("deprecation")
                                Date eventTime = new Date(startYear-1900, endMonth, endDay+1, tempEndTime %24, emin);
                                event.setDateEnd(eventTime);        
                        }
            
                        DateStart startDate = new DateStart(null, false);
                        startDate = new DateStart(components);
                        
                        DateEnd endDate = new DateEnd(null, false);
                        endDate = new DateEnd(components);
                        
                        //creates the recurrence rule if the user specifies for the event to occur
            
                        if(recureIndex == 0){
                        	
                        	Recurrence recur = new Recurrence.Builder(Frequency.DAILY).build();
                        	event.setRecurrenceRule(recur);
                        	
                        }
                        
                        else if(recureIndex == 1){
                        	
                        	Recurrence recur = new Recurrence.Builder(Frequency.WEEKLY).build();
                        	event.setRecurrenceRule(recur);
                        	
                        }
                        
                        else if(recureIndex == 2){
                        	
                        	Recurrence recur = new Recurrence.Builder(Frequency.MONTHLY).build();
                        	event.setRecurrenceRule(recur);
                        	
                        }
            
                        calendar.addEvent(event);
                        
                        //changes the product ID
                        
                        ProductId prodid = new ProductId("-//Team Euphrates//ICS 314 Calendar//EN");
                        calendar.setProductId(prodid);
            
                        //outputs all information to a .ics file
                        
                        File file = new File("event.ics");
            
                        Biweekly.write(calendar).go(file);
                        
                        int style = SWT.ICON_INFORMATION | SWT.OK;
                        MessageBox dialog = new MessageBox(CalendarGUI, style);
                     
                        dialog.setText("Confirmation");
                        dialog.setMessage("Event Created");
                        dialog.open();
            
                        System.exit(0);
                        
                }
        } catch (IOException ex)

        {
            
        }
        
        }
}