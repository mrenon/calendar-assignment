
public class CalendarData {
	
	String month;
	int day;
	
	public CalendarData(){
		
		this.month = "";
		this.day = -1;
		
	}
	
	public void setMonth(String month){
		
		this.month = month;
		
	}
	
	public String getMonth(){
		
		return month;
		
	}
	
	public void setDay(int day){
		
		if(getMonth().equals("February") && day == 30){
			
			this.day = 0;
			
		}
		
		else if((getMonth().equals("April") || getMonth().equals("June") || getMonth().equals("August") || getMonth().equals("September") ||
				getMonth().equals("November")) && (day == 31)){
			
			this.day = 0;
			
		}
		
		else{
		
			this.day = day;
		
		}
		
	}
	
	public int getDay(){
		
		return day;
		
	}

}
