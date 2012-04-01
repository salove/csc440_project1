package common;
import java.util.Calendar;
import java.sql.Date;

public class C_Date {
	Calendar calendar;
	
	public C_Date() {
		calendar=Calendar.getInstance();
	}
	
	public C_Date(int month, int date, int year) {
		this();
		calendar.clear();
		calendar.set(year, month, date);
	}
	
	public C_Date(Date d){
		this();
		calendar.clear();
		calendar.setTime(d);
	}
}
