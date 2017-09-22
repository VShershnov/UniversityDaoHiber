package main.java.university;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import main.java.schedule.CourseSchedule;
import main.java.schedule.DailyScheduleForProfessor;
import main.java.schedule.DailyScheduleForStudent;
import main.java.schedule.MonthlyScheduleForProfessor;
import main.java.schedule.MonthlyScheduleForStudent;
import main.java.schedule.RoomSchedule;
import main.java.schedule.Schedule;
import main.java.university.person.Professor;
import main.java.university.person.Student;

public class University {
	
	private Integer id;
	
	private Set<Group> allGroups;
	
	private Set<Professor> allProfessors;
	
	private Set<Student> allStudents;
	
	private Set<Course> allCourses;
	
	private Set<Room> allRooms;
	
	private Set<Schedule> allSchedules;
		
	public University(){
		this.allGroups = new HashSet<Group>();
		this.allProfessors = new HashSet<Professor>();
		this.allStudents = new HashSet<Student>();
		this.allCourses = new HashSet<Course>();
		this.allRooms = new HashSet<Room>();
		this.allSchedules = new HashSet<Schedule>();
	}
	
	public University(Integer id){
		this();
		this.id = id;
	}

	
	public void addGroup(Group group) {
		if (group!=null)
			allGroups.add(group);		
	}

	public void addRoom(Room room){
		if (room!=null)
			allRooms.add(room);
	}
	
	public void addProfessor(Professor prof) {
		if (prof!=null)
			allProfessors.add(prof);
	}
	
	public void addCourse(Course course) {
		if (course!=null)
			allCourses.add(course);
	}
	
	public void addStudent(Student student) {
		if (student!=null)
			allStudents.add(student);
	}
	
	public void addSchedule(Schedule schedule) {
		if (schedule!=null)
			allSchedules.add(schedule);
	}
	
	public void addStudent(Student student, Group group) {
		
		if(student!=null && group!=null){
			if (!allGroups.contains(group)){
				allGroups.add(group);
				System.out.println("To University #" + id + " added NEW " + group + "\n");
			}		
			
			if(student.getGroup()!=null)
				getGroup(getStudent(student).getGroup()).removeStudent(student);
			
			student.setGroup(group);
			
			if (!allStudents.contains(student)){
				allStudents.add(student);
				System.out.println("To University #" + id + " added NEW " + student + "\n");
			}
			
			getGroup(group).addStudent(student);	
		}
	}
	
	
	public void addCourseToGroup(Course course, Group group) {

		if(course!=null && group!=null){
			if (!allGroups.contains(group)) {
				allGroups.add(group);
				System.out.println("To University #" + id + " added NEW " + group
						+ "\n");
			}
	
			if (!allCourses.contains(course)) {
				allCourses.add(course);
				System.out.println("To University #" + id + " added NEW " + course
						+ "\n");
			}
	
			getGroup(group).addCourse(course);
			getCourse(course).addGroup(group);
		}
	}
	
	public void addProfessorToCourse(Professor prof, Course course) {

		if(course!=null && prof!=null){
			if (!allProfessors.contains(prof) && prof!=null) {
				allProfessors.add(prof);
				System.out.println("To University #" + id + " added NEW " + prof
						+ "\n");
			}
	
			if (!allCourses.contains(course) && course!=null) {
				allCourses.add(course);
				System.out.println("To University #" + id + " added NEW " + course
						+ "\n");
			}
	
			getProfessor(prof).addCourse(course);
			getCourse(course).addProfessor(prof);
		}
	}
		
	public void setStudentGroup(Student student, Group group){
		addStudent(student, group);
	}
	
	//remove conditions
	public void removeGroup(Group group) {
		if(group!=null){
			removeGroupFromSchedules(group);
			group.removeGroupFromGroupCourses(group);
			group.removeGroupFromGroupStudents();
			allGroups.remove(group);
		}				
	}
		 	
	public void removeProfessor(Professor prof){
		if(prof!=null){
			removeProfessorFromSchedules(prof);
			prof.removeProfessorFromProfessorCourses(prof);
			allProfessors.remove(prof);		
		}		
	}
	
	public void removeCourse(Course course) {
		if(course!=null){
			removeCourseFromSchedules(course);			
			course.removeCourseFromCourseGroups(course);
			course.removeCourseFromCourseProfessors(course);
			allCourses.remove(course);		
		}
	}
	
	public void removeStudent(Student student) {
		if(student!=null){
			removeStudentFromSchedules(student);
			student.removeStudentFromStudentGroup(student);			
			allStudents.remove(student);		
		}
	}
	
	public void removeRoom(Room room) {
		if(room!=null){
			removeRoomFromSchedules(room);			
			allRooms.remove(room);		
		}		
	}
	
	public void removeSchedule(Schedule schedule) {
		if(schedule!=null)					
			allSchedules.remove(schedule);				
	}
		
	public Set<Schedule> getAllSchedules() {
		return allSchedules;
	}

	public void setAllSchedules(Set<Schedule> allSchedules) {
		this.allSchedules = allSchedules;
	}

	public Set<Course> getAllCourses() {
		return allCourses;
	}

	public void setAllCourses(Set<Course> allCourses) {
		this.allCourses = allCourses;
	}
			
	public Set<Room> getAllRooms() {
		return allRooms;
	}

	public void setAllRooms(Set<Room> allRooms) {
		this.allRooms = allRooms;
	}

	public Set<Group> getAllGroups() {
		return allGroups;
	}
	
	public void setAllGroups(Set<Group> allGroups) {
		this.allGroups = allGroups;
	}
	
	public Set<Professor> getAllProfessors() {
		return allProfessors;
	}
	
	public void setAllProfessors(Set<Professor> allProfessors) {
		this.allProfessors = allProfessors;
	}
	
	public Set<Student> getAllStudents() {
		return allStudents;
	}

	public void setAllStudents(Set<Student> allStudents) {
		this.allStudents = allStudents;
	}

	@Override
	public String toString() {
		return "University" + id + "\n"
				+ "allGroups=" + allGroups + "\n"
				+ "allProfessors=" + allProfessors + "\n"
				+ "allStudents="+ allStudents + "\n"
				+ "allCourses=" + allCourses + "\n"
				+ "allRooms="	+ allRooms + "\n"
				+ "allSchedule=" + allSchedules;		
	}
	
	public Group getGroup(Group group) {
		for (Group g: allGroups){
			if (g.equals(group))
				return g;
		}
		return null;
	}
	
	public Professor getProfessor(Professor prof) {
		for (Professor p: allProfessors){
			if (p.equals(prof))
				return p;
		}
		return null;
	}

	public Course getCourse(Course course) {
		for (Course c: allCourses){
			if (c.equals(course))
				return c;
		}
		return null;
	}
	
	public Student getStudent(Student student) {
		for (Student s: allStudents){
			if (s.equals(student))
				return s;
		}
		return null;
	}

	public Schedule getSchedule(Schedule schedule) {
		for (Schedule s: allSchedules){
			if (s.equals(schedule))
				return s;
		}
		return null;
	}

	private boolean isScheduleAreStudentSchedule(Schedule s, Student student) {
		if ((s instanceof DailyScheduleForStudent) && ((DailyScheduleForStudent) s).getStudent().equals(student))
			return true;
		if ((s instanceof MonthlyScheduleForStudent) && ((MonthlyScheduleForStudent) s).getStudent().equals(student))
			return true;
		return false;
	}

	private boolean isScheduleAreStudentGroupSchedule(Schedule s, Group group) {
		if ((s instanceof DailyScheduleForStudent) && ((DailyScheduleForStudent) s).getStudent().getGroup().equals(group))
			return true;
		if ((s instanceof MonthlyScheduleForStudent) && ((MonthlyScheduleForStudent) s).getStudent().getGroup().equals(group))
			return true;
		return false;
	}

	private boolean isScheduleAreProfessorSchedule(Schedule s, Professor prof) {
		if ((s instanceof DailyScheduleForProfessor) && ((DailyScheduleForProfessor) s).getProfessor().equals(prof))
			return true;
		if ((s instanceof MonthlyScheduleForProfessor) && ((MonthlyScheduleForProfessor) s).getProfessor().equals(prof))
			return true;
		return false;		
	}

	private boolean isScheduleAreCourseSchedule(Schedule s, Course course) {
		if ((s instanceof CourseSchedule) && ((CourseSchedule) s).getCourse().equals(course))
			return true;
		return false;		
	}

	private boolean isScheduleAreRoomSchedule(Schedule s, Room room) {
		if ((s instanceof RoomSchedule) && ((RoomSchedule) s).getRoom().equals(room))
			return true;
		return false;		
	}

	private void removeStudentFromSchedules(Student student) {
		for(Iterator<Schedule> i = allSchedules.iterator(); i.hasNext();){
			Schedule s = i.next();
			if(isScheduleAreStudentSchedule(s, student))
				i.remove();								
		}		
	}

	private void removeCourseFromSchedules(Course course) {
		for(Iterator<Schedule> i = allSchedules.iterator(); i.hasNext();){
			Schedule s = i.next();
			if(isScheduleAreCourseSchedule(s, course))
				i.remove();
			else {
				s.removeCoursefromScheduleSlots(course);
			}						
		}		
	}

	private void removeProfessorFromSchedules(Professor prof) {
		for(Iterator<Schedule> i = allSchedules.iterator(); i.hasNext();){
			Schedule s = i.next();
			if(isScheduleAreProfessorSchedule(s, prof))
				i.remove();
			else {
				s.removeProfessorfromScheduleSlots(prof);
			}						
		}		
	}

	private void removeGroupFromSchedules(Group group) {
		
		for(Iterator<Schedule> i = allSchedules.iterator(); i.hasNext();){
			Schedule s = i.next();
			if(isScheduleAreStudentGroupSchedule(s, group))
				i.remove();
			else {
				s.removeGroupfromScheduleSlots(group);
			}						
		}	
	}

	private void removeRoomFromSchedules(Room room) {
		for(Iterator<Schedule> i = allSchedules.iterator(); i.hasNext();){
			Schedule s = i.next();
			if(isScheduleAreRoomSchedule(s, room))
				i.remove();
			else {
				s.removeRoomfromScheduleSlots(room);
			}						
		}		
	}
}
