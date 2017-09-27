package main.java.university;

import java.util.HashSet;
import java.util.Set;

import main.java.dao.Identified;
import main.java.university.person.Student;


public class Group implements Identified<Integer>{
	
	private Integer id;
	private String name;
	
	private Set<Student> students;
	private Set<Course> courses;
	
	
	public Group() {
		this.students = new HashSet<Student>();
		this.courses = new HashSet<Course>();
	}
	
	public Group(Integer id, String name) {
		this.id = id;
		this.name = name;
		this.students = new HashSet<Student>();
		this.courses = new HashSet<Course>();
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
	
	public Set<Student> getStudents() {
		return students;
	}
	
	public void setStudents(Set<Student> students) {
		this.students = students;
	}
	
	public void addStudent(Student student){
		if (student!=null)
			students.add(student);		
	}
	
	public void addCourse(Course course) {
		if (course!=null)
			courses.add(course);		
	}
	
	public Set<Course> getCourses() {
		return courses;
	}
	public void setCourses(Set<Course> courses) {
		this.courses = courses;
	}

	public Student getStudent(Student student) {
		for (Student s: students){
			if (s.equals(student))
				return s;
		}
		return null;
	}
	
	
	@Override
	public String toString() {
		return "Group" + id + " [name=" + name + ", Students=" + students.size()
				+ ", Courses=" + courses.size() + "]";
	}
	
	

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Group other = (Group) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}

	public void removeStudent(Student student) {
		if (students.contains(student)) {
			//student.getGroup().getStudents().remove(student);
			students.remove(student);
		}
	}	
	
	public void removeCourse(Course course) {
		if(courses.contains(course)){
			for (Group g : course.getGroups()) {
				g.getCourses().remove(course);
			}
			courses.remove(course);
		}
	}

	public Course getCourse(Course course) {
		for (Course c: courses){
			if (c.equals(course))
				return c;
		}
		return null;
	}

	public void removeGroupFromGroupStudents() {
		for (Student s: students){
			s.setGroup(null);
		}		
	}

	public void removeGroupFromGroupCourses(Group group) {
		for (Course c: courses){
			c.getGroups().remove(group);
		}
		
	}
}
