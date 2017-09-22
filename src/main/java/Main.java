package main.java;

import java.util.ArrayList;
import java.util.List;

import main.java.schedule.CourseSchedule;
import main.java.schedule.DailyScheduleForProfessor;
import main.java.schedule.DailyScheduleForStudent;
import main.java.schedule.MonthlyScheduleForProfessor;
import main.java.schedule.MonthlyScheduleForStudent;
import main.java.schedule.RoomSchedule;
import main.java.schedule.Schedule;
import main.java.schedule.ScheduleSlot;
import main.java.schedule.TimeUnit;
import main.java.university.Course;
import main.java.university.Group;
import main.java.university.Room;
import main.java.university.University;
import main.java.university.person.Professor;
import main.java.university.person.Student;

public class Main {

	public static void main(String[] args) {
		
		University university = new University(1);
		
		
		Group gr1 = new Group(1, "FIOT");
		Group gr2 = new Group(2, "QA");
		university.addGroup(gr1);
		
		Student st1 = new Student(1, "Andrey Petrov");
		Student st2 = new Student(2, "Dmitro Shevchenko");
		Student st3 = new Student(3, "Tatiana Sapunova");
		Student st4 = new Student(4, "noname");
		university.addStudent(st1, gr1);
		//univer.addStudent(st1, gr2);
		System.out.println(university.toString()+"\n");
		
		/*
		univer.addStudent(st2, gr2);
		univer.addStudent(st3, gr1);
		
		*/
		Room room1 = new Room(1,5,"Kyiv");
		university.addRoom(room1);
		
		Professor prMath = new Professor(1, "Nikolay Ivanovich");
		university.addProfessor(prMath);
		
		Course math = new Course(1, "Math", 4);
		university.addCourse(math);
		university.addProfessorToCourse(prMath, math);
		university.addCourseToGroup(math, gr1);
		
		System.out.println(university.toString()+"\n");
		///////////////////////////////////////////////////
		/*
		Course java = new Course(2, "Java", 6);
		univer.addCourseToGroup(java, gr1);
		univer.addCourseToGroup(java, gr2);
		univer.addProfessorToCourse(prMath, java);
		System.out.println(univer.toString()+"\n");
		
		
		/*
		univer.removeProfessor(prMath);
		System.out.println(univer.toString()+"\n");
		
		
		univer.removeStudent(st2);
		univer.removeStudent(st4);
		System.out.println(univer.toString()+"\n");		
		
		Course cSharp = new Course(1, "C#", 4);
		univer.removeCourse(java);
		univer.removeCourse(cSharp);
		System.out.println(univer.toString()+"\n");
	*/
		/*
		university.addStudent(st1, gr1);
		university.addCourseToGroup(math, gr1);
		university.addProfessorToCourse(prMath, math);
		*/
		List<ScheduleSlot> sheduleSlots = new ArrayList<ScheduleSlot>();
		int i=0;
		for (int d=1; d<31; d++){
			for (int t=9; t<21; t++){
				sheduleSlots.add(new ScheduleSlot(i++, room1, new TimeUnit(d,t)));
			}
		}
		System.out.println(sheduleSlots.toString()+"\n");
		
		//sheduleSlots.get(300).setCourse(math);
		//sheduleSlots.get(3).setCourse(math);
		sheduleSlots.get(353).setCourse(math);
		sheduleSlots.get(353).addGroup(gr1);
		//sheduleSlots.get(353).addGroup(gr2);
		sheduleSlots.get(352).addGroup(gr1);
		//sheduleSlots.get(353).setProfessor(prMath);
		
		System.out.println(sheduleSlots.toString()+"\n");
		
		Schedule schedule = new Schedule(1, sheduleSlots);
		System.out.println(schedule.toString()+"\n");
		
		university.addSchedule(schedule);
				
		
		Schedule dayProfessorSchedule = new DailyScheduleForProfessor(2, 1, prMath);
		for (ScheduleSlot ss: sheduleSlots){
			if(ss.getProfessor().equals(prMath)){
				dayProfessorSchedule.addSchedulerSlot(ss);
			}
		}
		university.addSchedule(dayProfessorSchedule);
				
		Schedule dayStudentSchedule = new DailyScheduleForStudent(3, 1, st1);
		for (ScheduleSlot ss: sheduleSlots){
			if(ss.getGroups().contains(st1.getGroup())){
				dayStudentSchedule.addSchedulerSlot(ss);
			}
		}
		
		System.out.println(dayStudentSchedule.getClass()+"\n");
		System.out.println(dayStudentSchedule.toString()+"\n");
		university.addSchedule(dayStudentSchedule);
		Student s = ((DailyScheduleForStudent) dayStudentSchedule).getStudent();
		System.out.println(s.toString()+"\n");
		
		
		MonthlyScheduleForStudent monthStudentSchedule = new MonthlyScheduleForStudent(4, 1, st1);
		monthStudentSchedule.addDailySchedule((DailyScheduleForStudent) dayStudentSchedule);
		university.addSchedule(monthStudentSchedule);
		
		MonthlyScheduleForProfessor monthProfessorSchedule = new MonthlyScheduleForProfessor(5, 1, prMath);
		monthProfessorSchedule.addDailySchedule((DailyScheduleForProfessor) dayProfessorSchedule);
		university.addSchedule(monthProfessorSchedule);
		
		
		RoomSchedule roomSchedule = new RoomSchedule(6, room1);
		for (ScheduleSlot ss: sheduleSlots){
			if(ss.getRoom().equals(room1)){
				roomSchedule.addSchedulerSlot(ss);
			}
		}
		university.addSchedule(roomSchedule);
		
		
		CourseSchedule courseSchedule = new CourseSchedule(7, math);
		for (ScheduleSlot ss: sheduleSlots){
			if(ss.getCourse().equals(math)){
				courseSchedule.addSchedulerSlot(ss);
			}
		}
		university.addSchedule(courseSchedule);
		
		System.out.println(university.toString()+"\n");
		
		university.removeCourse(math);
		System.out.println(university.toString()+"\n");
		
		int c = ((DailyScheduleForStudent)university.getSchedule(dayStudentSchedule))
				.getScheduleSlot(sheduleSlots.get(353)).getProfessor().getCourses().size();
	}	
}
