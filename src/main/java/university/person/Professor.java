package main.java.university.person;

import java.util.HashSet;
import java.util.Set;

import main.java.university.Course;

public class Professor extends UniversityPerson{

	//list of professor teaching courses
	private Set<Course> courses;
	
	public Professor() {
		this.courses = new HashSet<Course>();
	}
	
	public Professor(int id, String name) {
		super.setId(id);
		super.setFullName(name);
		this.courses = new HashSet<Course>();
	}
	
	public void addCourse(Course course){
		if (course!=null)
			courses.add(course);
	}
	
	public void removeCourse(Course course){
		courses.remove(course);
	}
	
	public Set<Course> getCourses() {
		return courses;
	}

	public String toString() {
		return "Professor" + getId() + " [FullName="
				+ getFullName() + ", Courses=" + courses.size() + "]";
	}

	public Course getCourse(Course course) {
		for (Course c: courses){
			if (c.equals(course))
				return c;
		}
		return null;
	}

	public void removeProfessorFromProfessorCourses(Professor prof) {
		for (Course c: courses){
			c.getProfessors().remove(prof);
		}		
	}
}
