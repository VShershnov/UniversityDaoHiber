package main.java.schedule;

import main.java.university.person.Student;

public class DailyScheduleForStudent extends DailySchedule {

	private Student student;
	
	public DailyScheduleForStudent(Integer id, Integer day, Student student) {
		super(id, day);
		this.student=student;
	}

	public Student getStudent() {
		return student;
	}

	public void setStudent(Student student) {
		this.student = student;
	}

	@Override
	public String toString() {
		return super.toString() + " student=" + student.getFullName()+"]";
	}
}
