package main.java.schedule;

public class DailySchedule extends Schedule {

	private Integer day;
	
	public DailySchedule(Integer id, Integer day) {
		super(id);
		this.day = day;
	}

	public Integer getDay() {
		return day;
	}

	public void setDay(Integer day) {
		this.day = day;
	}

	@Override
	public String toString() {
		return "DailySchedule" + getId() + " [day="+ day
				+ ", ScheduleSlots=" + getScheduleSlots().size();
	}
}
