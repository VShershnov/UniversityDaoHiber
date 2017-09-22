package main.java.schedule;

import java.util.HashSet;
import java.util.Set;

import main.java.university.Course;
import main.java.university.Room;
import main.java.university.person.Professor;
import main.java.university.Group;

public class ScheduleSlot {
	
	private Integer id;
	
	private Room room;
	
	private Course course;
	
	private TimeUnit time;
	
	private Professor professor;
	
	private Set<Group> groups;
	
	
	public ScheduleSlot(Integer id, Room room, TimeUnit time) {
		this.id = id;
		this.room = room;
		this.time = time;
		this.groups = new HashSet<Group>();
		this.professor = new Professor();
		this.course = new Course();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Room getRoom() {
		return room;
	}

	public void setRoom(Room room) {
		this.room = room;
	}

	public Course getCourse() {
		return course;
	}

	public void setCourse(Course course) {
		this.course = course;
	}

	public TimeUnit getTime() {
		return time;
	}

	public void setTime(TimeUnit time) {
		this.time = time;
	}

	public Professor getProfessor() {
		return professor;
	}

	public void setProfessor(Professor professor) {
		this.professor = professor;
	}

	public Set<Group> getGroups() {
		return groups;
	}

	public void setGroups(Set<Group> groups) {
		this.groups = groups;
	}
	
	public void addGroup(Group group){
		if (group!=null)
			groups.add(group);
	}
	
	public Group getGroup(Group group) {
		for (Group g: groups){
			if (g.equals(group))
				return g;
		}
		return null;
	}
	
	public void removeGroup(Group group){
		if(group!=null)
			groups.remove(group);
	}

	@Override
	public String toString() {
		return "ScheduleSlot" + id + " [time=" + time + ", room" + room.getId() + ", course="
				+ course.getName() + ", professor=" + professor.getFullName()
				+ ", groups=" + groups.size() + "]\n";
	}
		
}
