package main.java.schedule;

import main.java.university.person.Professor;

public class MonthlyScheduleForProfessor extends MonthlySchedule {

	private Professor professor;
	
	public MonthlyScheduleForProfessor(Integer id, Integer month, Professor professor) {
		super(id, month);
		this.professor=professor;
	}
			
	public Professor getProfessor() {
		return professor;
	}

	public void setProfessor(Professor professor) {
		this.professor = professor;
	}
	
	public void addDailySchedule(DailyScheduleForProfessor dailySchedule) {
		professor = dailySchedule.getProfessor();
		super.addDailySchedule(dailySchedule);
	}

	@Override
	public String toString() {
		return "MonthlySchedule"+ getId() + "[Month=" + getMonth() 
				+ ", Professor=" + professor.getFullName()
				+ ", DailySchedules="
				+ getDailySchedules().size() + "]";
	}		
}
