import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.List;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Label;
import org.eclipse.wb.swt.SWTResourceManager;


public class Calendar {

	protected Shell CalendarGUI;
	private Text descripBox;
	private Text eventNameBox;

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
		CalendarGUI.setSize(530, 418);
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
		List monthList = new List(CalendarGUI, SWT.BORDER | SWT.V_SCROLL); //creates a list
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
		List dayList = new List(CalendarGUI, SWT.BORDER | SWT.V_SCROLL); //creates a list
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
		List yearList = new List(CalendarGUI, SWT.BORDER | SWT.V_SCROLL); //creates a list
		yearList.setForeground(SWTResourceManager.getColor(SWT.COLOR_BLACK)); //font color
		yearList.setBackground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_LIGHT_SHADOW)); //background color
		yearList.setItems(new String[] {"2014", "2015", "2016", "2017", "2018", "2019", "2020", "2021", "2022", "2023", 
				"2024", "2025", "2026", "2027", "2028", "2029", "2030"}); //entries
		yearList.setBounds(223, 128, 57, 93); //postion it is on the frame
		
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
		List startHourList = new List(CalendarGUI, SWT.BORDER | SWT.V_SCROLL); //creates the list
		startHourList.setBackground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_LIGHT_SHADOW)); //background color
		startHourList.setItems(new String[] {"01", "02", "03", "04", "05", "06", "07", "08", "09",
				"10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24"}); //entries based on 24-hours
		startHourList.setBounds(369, 128, 46, 37); //position
		
		//minutes label for start time
		Label sMinLabel = new Label(CalendarGUI, SWT.BORDER | SWT.SHADOW_IN);
		sMinLabel.setBounds(438, 107, 44, 15);
		sMinLabel.setText("Minutes");
		
		//creates the list for the start time's minutes
		List startMinList = new List(CalendarGUI, SWT.BORDER | SWT.V_SCROLL); //creates the list
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
		List endHourList = new List(CalendarGUI, SWT.BORDER | SWT.V_SCROLL); //creates the list
		endHourList.setBackground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_LIGHT_SHADOW));  //background color
		endHourList.setItems(new String[] {"01", "02", "03", "04", "05", "06", "07", "08", "09", 
				"10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24"}); //entries
		endHourList.setBounds(369, 203, 46, 37); //position
		
		//end time's minutes label
		Label eMinLabel = new Label(CalendarGUI, SWT.BORDER | SWT.SHADOW_IN);
		eMinLabel.setText("Minutes");
		eMinLabel.setBounds(438, 182, 44, 15);
		
		//creates the end time's minute list
		List endMinList = new List(CalendarGUI, SWT.BORDER | SWT.V_SCROLL); //creates the list
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

	}
}
