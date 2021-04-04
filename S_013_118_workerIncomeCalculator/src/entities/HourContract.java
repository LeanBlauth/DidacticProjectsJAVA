package entities;
import 	java.util.Date;

public class HourContract {
	
	private Date 	date; 
	private Double 	valuePerHour;
	private int 	hours;
	
	public HourContract(Date date, Double valuePerHour, int hours) {
		this.date 			= date;
		this.valuePerHour 	= valuePerHour;
		this.hours 			= hours;
	}
	
	public void setDate(Date date) {
		this.date = date;
	}
	
	public void setValuePerHour(Double valuePerHour) {
		this.valuePerHour = valuePerHour;
	}
	
	public void setHours(int hours) {
		this.hours = hours;
	}
	
	public Date getDate() {
		return date;
	}
	
	public Double getValuePerHour() {
		return valuePerHour;
	}
	
	public int getHours() {
		return hours;
	}
	
	public Double totalReturn() {
		return valuePerHour*hours;
	}
	
}
