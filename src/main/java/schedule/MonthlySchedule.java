package main.java.schedule;

import java.util.HashSet;
import java.util.Set;


public class MonthlySchedule extends Schedule {

	private Integer month;
	
	private Set<DailySchedule> dailySchedules;
	
	public MonthlySchedule(Integer id, Integer month) {
		super(id);
		this.month = month;
		this.dailySchedules = new HashSet<DailySchedule>();
	}
	
	public MonthlySchedule(Integer id, Integer month, Set<DailySchedule> dailySchedules) {
		this(id, month);
		this.dailySchedules = dailySchedules;
	}

	public Integer getMonth() {
		return month;
	}

	public void setMonth(Integer month) {
		this.month = month;
	}
	
	public void addDailySchedule(DailySchedule dailySchedule){
		dailySchedules.add(dailySchedule);
	}
	
	public void removeDailySchedule(DailySchedule dailySchedule){
		dailySchedules.remove(dailySchedule);
	}
	
	public Set<DailySchedule> getDailySchedules() {
		return dailySchedules;
	}

	public void setDailySchedules(Set<DailySchedule> dailySchedules) {
		this.dailySchedules = dailySchedules;
	}

	@Override
	public String toString() {
		return "MonthlySchedule" + getId() + " [Month="+ month
				+ ", ScheduleSlots()=" + getScheduleSlots().size() + "]";
	}
}
