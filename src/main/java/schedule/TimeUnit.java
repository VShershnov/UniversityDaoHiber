package main.java.schedule;

public class TimeUnit {
	
	private Integer day;
	private Integer time;
			
	public TimeUnit(Integer day, Integer time) {
		this.day = day;
		this.time = time;
	}
	public Integer getDay() {
		return day;
	}
	public void setDay(Integer day) {
		this.day = day;
	}
	public Integer getTime() {
		return time;
	}
	public void setTime(Integer time) {
		this.time = time;
	}
	@Override
	public String toString() {
		return "TimeUnit" + day + " [time=" + time + "]";
	}	
}
