package main.java.schedule;

import main.java.university.person.Student;

public class MonthlyScheduleForStudent extends MonthlySchedule {

	private Student student;
	
	public MonthlyScheduleForStudent(Integer id, Integer month, Student student) {
		super(id, month);
		this.student=student;
	}
			
	public Student getStudent() {
		return student;
	}

	public void setStudent(Student student) {
		this.student = student;
	}
	
	public void addDailySchedule(DailyScheduleForStudent dailySchedule) {
		student = dailySchedule.getStudent();
		super.addDailySchedule(dailySchedule);
	}

	
	
	@Override
	public String toString() {
		return "MonthlySchedule"+ getId() + "[Month=" + getMonth() 
				+ ", student=" + student.getFullName()
				+ ", DailySchedules="
				+ getDailySchedules().size() + "]";
	}		
}
