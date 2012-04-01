package common;

import java.util.Calendar;

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

	@SuppressWarnings("deprecation")
	public C_Date(java.sql.Date date) {
		
		this(date.getMonth(),date.getDate(),date.getYear());
	}
}
