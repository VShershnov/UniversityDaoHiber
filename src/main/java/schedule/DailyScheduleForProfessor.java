package main.java.schedule;

import main.java.university.person.Professor;

public class DailyScheduleForProfessor extends DailySchedule {

	private Professor professor;
	
	public DailyScheduleForProfessor(Integer id, Integer day, Professor professor) {
		super(id, day);
		this.professor=professor;
	}

	public Professor getProfessor() {
		return professor;
	}

	public void setProfessor(Professor professor) {
		this.professor = professor;
	}

	@Override
	public String toString() {
		return super.toString() + " professor=" + professor.getFullName()+"]";
	}
}
