package main.university;

import java.util.HashSet;
import java.util.Set;

import main.university.person.Professor;

public class Course {

	//Course id and duration in hours
	private Integer id;
	private Integer duration;
	
	private String name;
		
	private Set<Group> groups;
	private Set<Professor> professors;
			
	public Course(Integer id, String name, Integer duration) {
		this.id = id;
		this.name = name;
		this.duration = duration;
		this.groups = new HashSet<Group>();
		this.professors = new HashSet<Professor>();
	}
	
	public Course() {
		this.groups = new HashSet<Group>();
		this.professors = new HashSet<Professor>();
	}

	public Integer getId() {
		return id;
	}
	
	public void setId(Integer id) {
		this.id = id;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getDuration() {
		return duration;
	}
	public void setDuration(Integer duration) {
		this.duration = duration;
	}
	public Set<Group> getGroups() {
		return groups;
	}
	
	public Group getGroup(Group group) {
		for (Group g: groups){
			if (g.equals(group))
				return g;
		}
		return null;
	}
	
	public void setGroups(Set<Group> groups) {
		this.groups = groups;
	}
	public Set<Professor> getProfessors() {
		return professors;
	}
	public void setProfessors(Set<Professor> professors) {
		this.professors = professors;
	}
		
	public void addProfessor(Professor prof) {
		if (prof!=null)
			professors.add(prof);
	}
	
	public void removeProfessor(Professor prof) {
		professors.remove(prof);
	}
	
	public void addGroup(Group group){
		if (group!=null)
			groups.add(group);
	}
	
	public void removeGroup(Group group){
		groups.remove(group);
	}
	
	@Override
	public String toString() {
		return "Course" + id + " [name=" + name + ", duration=" + duration
				+ ", groups=" + groups.size() + ", professors=" + professors.size() + "]";
	}

	public void removeCourseFromCourseGroups(Course course) {
		for (Group g: groups){
			g.getCourses().remove(course);
		}		
	}

	public void removeCourseFromCourseProfessors(Course course) {
		for (Professor p: professors){
			p.getCourses().remove(course);
		}		
	}
}
