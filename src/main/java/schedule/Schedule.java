package main.java.schedule;

import java.util.ArrayList;
import java.util.List;

import main.java.university.Course;
import main.java.university.Group;
import main.java.university.Room;
import main.java.university.person.Professor;
import main.java.university.person.Student;

public class Schedule {

	private Integer id;
	private List<ScheduleSlot> scheduleSlots;
	
	public Schedule() {
		this.scheduleSlots = new ArrayList<ScheduleSlot>();
	}
	
	public Schedule(Integer id) {
		this();
		this.id = id;
	}

	public Schedule(Integer id, List<ScheduleSlot> scheduleSlots) {
		this.scheduleSlots = scheduleSlots;
		this.id = id;
	}
		
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public void addSchedulerSlot (ScheduleSlot scheduleSlot){
		scheduleSlots.add(scheduleSlot);
	}
	
	public void removeSchedulerSlot (ScheduleSlot scheduleSlot){
		scheduleSlots.remove(scheduleSlot);
	}
	
	public List<ScheduleSlot> getScheduleSlots() {
		return scheduleSlots;
	}

	public void setScheduleSlots(List<ScheduleSlot> scheduleSlots) {
		this.scheduleSlots = scheduleSlots;
	}

	public ScheduleSlot getScheduleSlot(ScheduleSlot scheduleSlot) {
		for (ScheduleSlot ss: scheduleSlots){
			if (ss.equals(scheduleSlot))
				return ss;
		}
		return null;
	}

	@Override
	public String toString() {
		return "Schedule" + id + " scheduleSlots=" + scheduleSlots.size();
	}

	public void removeGroupfromScheduleSlots(Group group) {
		for(ScheduleSlot ss: scheduleSlots){			
			ss.removeGroup(group);			
		}		
	}

	public void removeCoursefromScheduleSlots(Course course) {
		for(ScheduleSlot ss: scheduleSlots){			
			if(ss.getCourse()!=null && ss.getCourse().equals(course))
				ss.setCourse(null);			
		}		
	}

	public void removeProfessorfromScheduleSlots(Professor prof) {
		for(ScheduleSlot ss: scheduleSlots){			
			if(ss.getProfessor()!=null && ss.getProfessor().equals(prof))
				ss.setProfessor(null);			
		}		
	}

	public void removeRoomfromScheduleSlots(Room room) {
		for(ScheduleSlot ss: scheduleSlots){
			if(ss.getRoom()!=null && ss.getRoom().equals(room))
				ss.setRoom(null);				
		}		
	}		
}
