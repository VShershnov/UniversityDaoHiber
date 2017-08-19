package main.schedule;

import main.university.Course;

public class CourseSchedule extends Schedule {

	private Course course;
	
	public CourseSchedule(Integer id, Course course) {
		super(id);
		this.course = course;
	}

	public Course getCourse() {
		return course;
	}

	public void setCourse(Course course) {
		this.course = course;
	}

	@Override
	public String toString() {
		return "CourseSchedule"+ getId() + "[course=" + course.getName()
				+ ", ScheduleSlots=" + getScheduleSlots().size() + "]";
	}
}
