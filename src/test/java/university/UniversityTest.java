package test.java.university;

import static org.junit.Assert.*;

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

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class UniversityTest {
	
	private University university;

	private Group gr1;
	private Group gr2;
	
	private Student st1;
	private Student st2;
	private Student st3;
	
	private static Room room1;
	
	private static Professor prMath;
	private Professor prInfo;
	
	private Course math;
	private Course java;		
	
	private Schedule schedule;
	
	private List<ScheduleSlot> sheduleSlots;
	
		
	@Before
	public void setUpBefore(){		
		university = new University(1);
		
		gr1 = new Group(1, "FIOT");
		gr2 = new Group(2, "QA");
		
		st1 = new Student(1, "Andrey Petrov");
		st2 = new Student(2, "Dmitro Shevchenko");
		st3 = new Student(3, "Tatiana Sapunova");
		
		room1 = new Room(1,5,"Kyiv");
		
		prMath = new Professor(1, "Nikolay Ivanovich");
		prInfo = new Professor(2, "Lidiya Stepanovna");
		
		math = new Course(1, "Math", 4);
		java = new Course(2, "Java", 6);
		
		sheduleSlots = new ArrayList<ScheduleSlot>();
		int i=0;
		for (int d=1; d<31; d++){
			for (int t=9; t<21; t++){
				sheduleSlots.add(new ScheduleSlot(i++, room1, new TimeUnit(d,t)));
			}
		}
		schedule = new Schedule(1, sheduleSlots);
	}
	
	@After
	public void tearDown(){
		university = null;
		gr1 = null;
		gr2 = null;
		st1 = null;
		st2 = null;
		st3 = null;
		room1 = null;
		prMath = null;
		prInfo = null;
		math = null;
		java = null;
		sheduleSlots = null;		
	}
	
	
	@Test
	public void testAddGroup01() {
		university.addGroup(gr1);
		assertEquals(1, university.getAllGroups().size());
	}

	@Test
	public void testAddGroup02() {
		university.addGroup(null);
		assertEquals(0, university.getAllGroups().size());
	}
	
	@Test
	public void testAddGroup03() {
		university.addGroup(gr1);
		university.addGroup(gr1);
		assertEquals(1, university.getAllGroups().size());
	}
	
	@Test
	public void testAddRoom01() {
		university.addRoom(room1);
		assertEquals(1, university.getAllRooms().size());
	}

	@Test
	public void testAddRoom02() {
		university.addRoom(null);
		assertEquals(0, university.getAllRooms().size());
	}
	
	@Test
	public void testAddRoom03() {
		university.addRoom(room1);
		university.addRoom(room1);
		assertEquals(1, university.getAllRooms().size());
	}
	
	@Test
	public void testAddProfessor01() {
		university.addProfessor(prMath);
		assertEquals(1, university.getAllProfessors().size());
	}

	@Test
	public void testAddProfessor02() {
		university.addProfessor(null);
		assertEquals(0, university.getAllProfessors().size());
	}
	
	@Test
	public void testAddProfessor03() {
		university.addProfessor(prMath);
		university.addProfessor(prMath);
		assertEquals(1, university.getAllProfessors().size());
	}
	
	@Test
	public void testAddCourse01() {
		university.addCourse(math);
		assertEquals(1, university.getAllCourses().size());
	}

	@Test
	public void testAddCourse02() {
		university.addCourse(null);
		assertEquals(0, university.getAllCourses().size());
	}
	
	@Test
	public void testAddCourse03() {
		university.addCourse(math);
		university.addCourse(math);
		assertEquals(1, university.getAllCourses().size());
	}

	@Test
	public void testAddStudentStudent01() {
		university.addStudent(st1);
		assertEquals(1, university.getAllStudents().size());
	}

	@Test
	public void testAddStudentStudent02() {
		university.addStudent(null);
		assertEquals(0, university.getAllStudents().size());
	}

	@Test
	public void testAddSchedule01() {
		university.addSchedule(schedule);
		assertEquals(1, university.getAllSchedules().size());
	}

	@Test
	public void testAddSchedule02() {
		university.addSchedule(null);
		assertEquals(0, university.getAllSchedules().size());
	}

	
	@Test
	public void testAddStudentStudentGroup01() {
		university.addGroup(gr1);
		university.addStudent(st1, gr1);
		
		assertEquals(1, university.getAllStudents().size());
		assertEquals(1, university.getAllGroups().size());
		assertEquals(1, university.getGroup(gr1).getStudents().size());
		assertEquals(gr1, university.getGroup(gr1).getStudent(st1).getGroup());
		assertEquals(gr1, university.getStudent(st1).getGroup());
	}
	
	@Test
	public void testAddStudentStudentGroup02() {
		university.addStudent(null, null);
		
		assertEquals(0, university.getAllStudents().size());
		assertEquals(0, university.getAllGroups().size());
	}
	
	@Test
	public void testAddStudentStudentGroup03() {
		university.addGroup(gr1);
		university.addStudent(st1, gr1);
		university.addStudent(st2, gr2);
	
		assertEquals(2, university.getAllStudents().size());
		assertEquals(2, university.getAllGroups().size());
		assertEquals(1, university.getGroup(gr1).getStudents().size());
		assertEquals(1, university.getGroup(gr2).getStudents().size());
		assertEquals(gr1, university.getGroup(gr1).getStudent(st1).getGroup());
		assertEquals(gr1, university.getStudent(st1).getGroup());
		assertEquals(gr2, university.getGroup(gr2).getStudent(st2).getGroup());
		assertEquals(gr2, university.getStudent(st2).getGroup());
	}
	
	@Test
	public void testAddStudentStudentGroup04() {
		university.addGroup(gr1);
		university.addStudent(st1, gr1);
		university.addStudent(st3, gr1);
		
		assertEquals(2, university.getAllStudents().size());
		assertEquals(1, university.getAllGroups().size());
		assertEquals(2, university.getGroup(gr1).getStudents().size());
		assertEquals(gr1, university.getGroup(gr1).getStudent(st1).getGroup());
		assertEquals(gr1, university.getStudent(st1).getGroup());
		assertEquals(gr1, university.getGroup(gr1).getStudent(st3).getGroup());
		assertEquals(gr1, university.getStudent(st3).getGroup());
	}
	
	@Test
	public void testAddStudentStudentGroup05() {
		university.addGroup(gr1);
		university.addStudent(st1, gr1);
		university.addStudent(st1, gr2);
		
		assertEquals(1, university.getAllStudents().size());
		assertEquals(2, university.getAllGroups().size());
		assertEquals(0, university.getGroup(gr1).getStudents().size());
		assertEquals(1, university.getGroup(gr2).getStudents().size());
		assertEquals(null, university.getGroup(gr1).getStudent(st1));
		assertEquals(gr2, university.getStudent(st1).getGroup());
		assertEquals(gr2, university.getGroup(gr2).getStudent(st1).getGroup());		
	}
	
	@Test
	public void testAddStudentStudentGroup06() {
		university.addGroup(gr1);
		university.addStudent(st1, gr1);
		university.addStudent(st1, gr1);
		
		assertEquals(1, university.getAllStudents().size());
		assertEquals(1, university.getAllGroups().size());
		assertEquals(1, university.getGroup(gr1).getStudents().size());
		assertEquals(gr1, university.getGroup(gr1).getStudent(st1).getGroup());
		assertEquals(gr1, university.getStudent(st1).getGroup());		
	}

	

	@Test
	public void testAddCourseToGroup01() {
		university.addCourse(math);
		university.addGroup(gr1);
		university.addCourseToGroup(math, gr1);
	
		assertEquals(1, university.getCourse(math).getGroups().size());
		assertEquals(1, university.getGroup(gr1).getCourses().size());
		assertEquals(1, university.getGroup(gr1).getCourse(math).getGroups().size());
	}
	
	@Test
	public void testAddCourseToGroup02() {
		university.addCourseToGroup(math, gr1);
		
		assertEquals(1, university.getAllCourses().size());
		assertEquals(1, university.getAllGroups().size());
		assertEquals(1, university.getGroup(gr1).getCourses().size());
		assertEquals(1, university.getCourse(math).getGroups().size());
	}
	
	
		
	@Test
	public void testAddCourseToGroup03() {
		university.addCourseToGroup(null, null);
		
		assertEquals(0, university.getAllCourses().size());
		assertEquals(0, university.getAllGroups().size());		
	}
	
	@Test
	public void testAddCourseToGroup04() {
		university.addCourseToGroup(math, gr1);
		university.addCourseToGroup(math, gr2);
	
		assertEquals(1, university.getAllCourses().size());
		assertEquals(2, university.getAllGroups().size());
		assertEquals(1, university.getGroup(gr1).getCourses().size());
		assertEquals(1, university.getGroup(gr2).getCourses().size());
		assertEquals(2, university.getCourse(math).getGroups().size());
	}
	
		
	@Test
	public void testAddCourseToGroup05() {			
		university.addCourseToGroup(math, gr1);
		university.addCourseToGroup(java, gr1);
				
		assertEquals(2, university.getAllCourses().size());
		assertEquals(1, university.getAllGroups().size());
		assertEquals(2, university.getGroup(gr1).getCourses().size());
		assertEquals(1, university.getCourse(math).getGroups().size());
		assertEquals(1, university.getCourse(java).getGroups().size());		
	}
	
	@Test
	public void testAddCourseToGroup06() {		
		university.addCourseToGroup(math, gr1);
		university.addCourseToGroup(math, gr1);
		
		assertEquals(1, university.getAllCourses().size());
		assertEquals(1, university.getAllGroups().size());
		assertEquals(1, university.getGroup(gr1).getCourses().size());
		assertEquals(1, university.getCourse(math).getGroups().size());		
	}
	
	
		
	@Test
	public void testAddProfessorToCourse01() {
		university.addCourse(math);
		university.addProfessor(prMath);
		university.addProfessorToCourse(prMath, math);
	
		assertEquals(1, university.getCourse(math).getProfessors().size());
		assertEquals(1, university.getProfessor(prMath).getCourses().size());
	}
	
	@Test
	public void testAddProfessorToCourse02() {
		university.addProfessorToCourse(prMath, math);
		
		assertEquals(1, university.getAllCourses().size());
		assertEquals(1, university.getAllProfessors().size());
		assertEquals(1, university.getCourse(math).getProfessors().size());
		assertEquals(1, university.getProfessor(prMath).getCourses().size());
	}
	
	
		
	@Test
	public void testAddProfessorToCourse03() {
		university.addProfessorToCourse(null, null);
		
		assertEquals(0, university.getAllCourses().size());
		assertEquals(0, university.getAllProfessors().size());		
	}
	
	@Test
	public void testAddProfessorToCourse04() {
		university.addProfessorToCourse(prMath,math);
		university.addProfessorToCourse(prInfo,math);
	
		assertEquals(1, university.getAllCourses().size());
		assertEquals(2, university.getAllProfessors().size());
		assertEquals(1, university.getProfessor(prMath).getCourses().size());
		assertEquals(1, university.getProfessor(prInfo).getCourses().size());
		assertEquals(2, university.getCourse(math).getProfessors().size());
	}
	
		
	@Test
	public void testAddProfessorToCourse05() {	
		university.addProfessorToCourse(prMath,math);
		university.addProfessorToCourse(prMath,java);
						
		assertEquals(2, university.getAllCourses().size());
		assertEquals(1, university.getAllProfessors().size());
		assertEquals(2, university.getProfessor(prMath).getCourses().size());
		assertEquals(1, university.getCourse(math).getProfessors().size());	
		assertEquals(1, university.getCourse(java).getProfessors().size());	
	}
	
	@Test
	public void testAddProfessorToCourse06() {		
		university.addProfessorToCourse(prMath,java);
		university.addProfessorToCourse(prMath,java);
		
		assertEquals(1, university.getAllCourses().size());
		assertEquals(1, university.getAllProfessors().size());
		assertEquals(1, university.getProfessor(prMath).getCourses().size());
		assertEquals(1, university.getCourse(java).getProfessors().size());	
	}
	

	@Test
	public void testRemoveGroup() {
		university.addStudent(st1, gr1);
		university.addCourseToGroup(math, gr1);
		
		sheduleSlots.get(353).addGroup(gr1);
		sheduleSlots.get(352).addGroup(gr1);
		sheduleSlots.get(353).setCourse(math);
			
		Schedule dayStudentSchedule = new DailyScheduleForStudent(3, 1, st1);
		for (ScheduleSlot ss: sheduleSlots){
			if(ss.getGroups().contains(st1.getGroup())){
				dayStudentSchedule.addSchedulerSlot(ss);
			}
		}
		university.addSchedule(dayStudentSchedule);
		
		MonthlyScheduleForStudent monthStudentSchedule = new MonthlyScheduleForStudent(4, 1, st1);
		monthStudentSchedule.addDailySchedule((DailyScheduleForStudent) dayStudentSchedule);
		university.addSchedule(monthStudentSchedule);
		
		CourseSchedule courseSchedule = new CourseSchedule(7, math);
		for (ScheduleSlot ss: sheduleSlots){
			if(ss.getCourse().equals(math)){
				courseSchedule.addSchedulerSlot(ss);
			}
		}
		university.addSchedule(courseSchedule);
		
		
		university.removeGroup(gr1);
		
		assertEquals(0, university.getAllGroups().size());
		assertEquals(null, university.getGroup(gr1));
		assertEquals(null, university.getStudent(st1).getGroup());
		assertEquals(0, university.getCourse(math).getGroups().size());
		
		assertEquals(null, university.getSchedule(dayStudentSchedule));
		assertEquals(null, university.getSchedule(monthStudentSchedule));		
		assertEquals(0, ((CourseSchedule)university.getSchedule(courseSchedule)).getCourse().getGroups().size());
		assertEquals(0, ((CourseSchedule)university.getSchedule(courseSchedule)).getScheduleSlot(sheduleSlots.get(353)).getCourse().getGroups().size());				
	}
	
	@Test
	public void testRemoveGroupAsRemoveEmptyGroup() {
		university.addStudent(st1, gr1);
		university.addCourseToGroup(math, gr1);
		
		sheduleSlots.get(353).addGroup(gr1);
		sheduleSlots.get(352).addGroup(gr1);
		sheduleSlots.get(353).setCourse(math);
			
		Schedule dayStudentSchedule = new DailyScheduleForStudent(3, 1, st1);
		for (ScheduleSlot ss: sheduleSlots){
			if(ss.getGroups().contains(st1.getGroup())){
				dayStudentSchedule.addSchedulerSlot(ss);
			}
		}
		university.addSchedule(dayStudentSchedule);
		
		MonthlyScheduleForStudent monthStudentSchedule = new MonthlyScheduleForStudent(4, 1, st1);
		monthStudentSchedule.addDailySchedule((DailyScheduleForStudent) dayStudentSchedule);
		university.addSchedule(monthStudentSchedule);
		
		CourseSchedule courseSchedule = new CourseSchedule(7, math);
		for (ScheduleSlot ss: sheduleSlots){
			if(ss.getCourse().equals(math)){
				courseSchedule.addSchedulerSlot(ss);
			}
		}
		university.addSchedule(courseSchedule);
		
		
		university.removeGroup(gr2);
		
		assertEquals(1, university.getAllGroups().size());
		assertEquals(gr1, university.getGroup(gr1));
		assertEquals(1, university.getCourse(math).getGroups().size());
		assertEquals(gr1, university.getStudent(st1).getGroup());
		
		assertEquals(dayStudentSchedule, university.getSchedule(dayStudentSchedule));
		assertEquals(monthStudentSchedule, university.getSchedule(monthStudentSchedule));		
		assertEquals(1, ((CourseSchedule)university.getSchedule(courseSchedule)).getCourse().getGroups().size());
		assertEquals(1, ((CourseSchedule)university.getSchedule(courseSchedule)).getScheduleSlot(sheduleSlots.get(353)).getCourse().getGroups().size());				
	}
	
	@Test
	public void testRemoveProfessor() {
		university.addStudent(st1, gr1);
		university.addProfessorToCourse(prMath,math);
		university.addCourseToGroup(math, gr1);
		
		sheduleSlots.get(353).setProfessor(prMath);
		sheduleSlots.get(353).addGroup(gr1);
		sheduleSlots.get(353).setCourse(math);
		
		Schedule dayProfessorSchedule = new DailyScheduleForProfessor(2, 1, prMath);
		for (ScheduleSlot ss: sheduleSlots){
			if(ss.getProfessor().equals(prMath)){
				dayProfessorSchedule.addSchedulerSlot(ss);
			}
		}
		university.addSchedule(dayProfessorSchedule);
		
		MonthlyScheduleForProfessor monthProfessorSchedule = new MonthlyScheduleForProfessor(5, 1, prMath);
		monthProfessorSchedule.addDailySchedule((DailyScheduleForProfessor) dayProfessorSchedule);
		university.addSchedule(monthProfessorSchedule);
		
		CourseSchedule courseSchedule = new CourseSchedule(7, math);
		for (ScheduleSlot ss: sheduleSlots){
			if(ss.getCourse().equals(math)){
				courseSchedule.addSchedulerSlot(ss);
			}
		}
		university.addSchedule(courseSchedule);
		
		
		university.removeProfessor(prMath);
		
		assertEquals(null, university.getProfessor(prMath));
		assertEquals(0, university.getGroup(gr1).getCourse(math).getProfessors().size());
		assertEquals(0, university.getCourse(math).getProfessors().size());
		assertEquals(0, university.getStudent(st1).getGroup().getCourse(math).getProfessors().size());
		
		assertEquals(null, university.getSchedule(monthProfessorSchedule));
		assertEquals(null, university.getSchedule(dayProfessorSchedule));
		
		assertEquals(null, ((CourseSchedule)university.getSchedule(courseSchedule))
				.getScheduleSlot(sheduleSlots.get(353)).getProfessor());
		
		assertEquals(0, ((CourseSchedule)university.getSchedule(courseSchedule))
				.getCourse().getProfessors().size());
		assertEquals(0, ((CourseSchedule)university.getSchedule(courseSchedule))
				.getScheduleSlot(sheduleSlots.get(353)).getCourse().getProfessors().size());	
	}

		
	@Test
	public void testRemoveProfessorAsRemoveSecondProfessor() {
		university.addStudent(st1, gr1);
		university.addProfessorToCourse(prMath,math);		
		university.addProfessorToCourse(prInfo,math);
		university.addCourseToGroup(math, gr1);
				
		sheduleSlots.get(353).setProfessor(prMath);
		sheduleSlots.get(353).addGroup(gr1);
		sheduleSlots.get(353).setCourse(math);
		sheduleSlots.get(352).setProfessor(prInfo);
		sheduleSlots.get(352).addGroup(gr1);
		sheduleSlots.get(352).setCourse(math);
		
		Schedule dayProfessorSchedule1 = new DailyScheduleForProfessor(2, 1, prMath);
		for (ScheduleSlot ss: sheduleSlots){
			if(ss.getProfessor().equals(prMath)){
				dayProfessorSchedule1.addSchedulerSlot(ss);
			}
		}
		university.addSchedule(dayProfessorSchedule1);
		
		Schedule dayProfessorSchedule2 = new DailyScheduleForProfessor(2, 1, prInfo);
		for (ScheduleSlot ss: sheduleSlots){
			if(ss.getProfessor().equals(prInfo)){
				dayProfessorSchedule2.addSchedulerSlot(ss);
			}
		}
		university.addSchedule(dayProfessorSchedule2);
		
		MonthlyScheduleForProfessor monthProfessorSchedule1 = new MonthlyScheduleForProfessor(5, 1, prMath);
		monthProfessorSchedule1.addDailySchedule((DailyScheduleForProfessor) dayProfessorSchedule1);
		university.addSchedule(monthProfessorSchedule1);
		
		CourseSchedule courseSchedule = new CourseSchedule(7, math);
		for (ScheduleSlot ss: sheduleSlots){
			if(ss.getCourse().equals(math)){
				courseSchedule.addSchedulerSlot(ss);
			}
		}
		university.addSchedule(courseSchedule);
		
		MonthlyScheduleForProfessor monthProfessorSchedule2 = new MonthlyScheduleForProfessor(5, 1, prInfo);
		monthProfessorSchedule2.addDailySchedule((DailyScheduleForProfessor) dayProfessorSchedule2);
		university.addSchedule(monthProfessorSchedule2);
				
		
		university.removeProfessor(prInfo);
		
		assertEquals(1, university.getAllProfessors().size());
		assertEquals(null, university.getProfessor(prInfo));
		assertEquals(1, university.getGroup(gr1).getCourse(math).getProfessors().size());
		assertEquals(1, university.getCourse(math).getProfessors().size());
		assertEquals(1, university.getStudent(st1).getGroup().getCourse(math).getProfessors().size());
		
		assertEquals(monthProfessorSchedule1, university.getSchedule(monthProfessorSchedule1));
		assertEquals(prMath, ((MonthlyScheduleForProfessor)university.getSchedule(monthProfessorSchedule1)).getProfessor());
		assertEquals(null, university.getSchedule(monthProfessorSchedule2));
		
		assertEquals(dayProfessorSchedule1, university.getSchedule(dayProfessorSchedule1));
		assertEquals(prMath, ((DailyScheduleForProfessor)university.getSchedule(dayProfessorSchedule1)).getProfessor());
		assertEquals(null, university.getSchedule(dayProfessorSchedule2));
		
		assertEquals(prMath, ((CourseSchedule)university.getSchedule(courseSchedule))
				.getScheduleSlot(sheduleSlots.get(353)).getProfessor());
		assertEquals(null, ((CourseSchedule)university.getSchedule(courseSchedule))
				.getScheduleSlot(sheduleSlots.get(352)).getProfessor());
		
		assertEquals(1, ((CourseSchedule)university.getSchedule(courseSchedule))
				.getCourse().getProfessors().size());
		assertEquals(1, ((CourseSchedule)university.getSchedule(courseSchedule))
				.getScheduleSlot(sheduleSlots.get(353)).getCourse().getProfessors().size());	
	}

	@Test
	public void testRemoveProfessorasRemoveEmptyProfessor03() {
		university.addStudent(st1, gr1);
		university.addProfessorToCourse(prMath,math);
		university.addCourseToGroup(math, gr1);
		
		sheduleSlots.get(353).setProfessor(prMath);
		sheduleSlots.get(353).addGroup(gr1);
		sheduleSlots.get(353).setCourse(math);
		
		
		Schedule dayProfessorSchedule = new DailyScheduleForProfessor(2, 1, prMath);
		for (ScheduleSlot ss: sheduleSlots){
			if(ss.getProfessor().equals(prMath)){
				dayProfessorSchedule.addSchedulerSlot(ss);
			}
		}
		university.addSchedule(dayProfessorSchedule);
		
		MonthlyScheduleForProfessor monthProfessorSchedule = new MonthlyScheduleForProfessor(5, 1, prMath);
		monthProfessorSchedule.addDailySchedule((DailyScheduleForProfessor) dayProfessorSchedule);
		university.addSchedule(monthProfessorSchedule);
		
		CourseSchedule courseSchedule = new CourseSchedule(7, math);
		for (ScheduleSlot ss: sheduleSlots){
			if(ss.getCourse().equals(math)){
				courseSchedule.addSchedulerSlot(ss);
			}
		}
		university.addSchedule(courseSchedule);
		
		
		university.removeProfessor(prInfo);
		
		assertEquals(prMath, university.getProfessor(prMath));
		assertEquals(1, university.getGroup(gr1).getCourse(math).getProfessors().size());
		assertEquals(1, university.getCourse(math).getProfessors().size());
		assertEquals(1, university.getStudent(st1).getGroup().getCourse(math).getProfessors().size());
		
		assertEquals(monthProfessorSchedule, university.getSchedule(monthProfessorSchedule));
		assertEquals(prMath, ((MonthlyScheduleForProfessor)university.getSchedule(monthProfessorSchedule)).getProfessor());
		assertEquals(dayProfessorSchedule, university.getSchedule(dayProfessorSchedule));
		assertEquals(prMath, ((DailyScheduleForProfessor)university.getSchedule(dayProfessorSchedule)).getProfessor());
		
		assertEquals(prMath, ((CourseSchedule)university.getSchedule(courseSchedule))
				.getScheduleSlot(sheduleSlots.get(353)).getProfessor());
		
		assertEquals(1, ((CourseSchedule)university.getSchedule(courseSchedule))
				.getCourse().getProfessors().size());
		assertEquals(1, ((CourseSchedule)university.getSchedule(courseSchedule))
				.getScheduleSlot(sheduleSlots.get(353)).getCourse().getProfessors().size());	
	}
	
	@Test
	public void testRemoveCourse() {
		university.addStudent(st1, gr1);
		university.addCourseToGroup(math, gr1);
		university.addProfessorToCourse(prMath, math);
				
		sheduleSlots.get(353).addGroup(gr1);
		sheduleSlots.get(352).addGroup(gr1);
		sheduleSlots.get(353).setCourse(math);
		sheduleSlots.get(352).setCourse(java);
			
		Schedule dayStudentSchedule = new DailyScheduleForStudent(3, 1, st1);
		for (ScheduleSlot ss: sheduleSlots){
			if(ss.getGroups().contains(st1.getGroup())){
				dayStudentSchedule.addSchedulerSlot(ss);
			}
		}
		university.addSchedule(dayStudentSchedule);
		
		MonthlyScheduleForStudent monthStudentSchedule = new MonthlyScheduleForStudent(4, 1, st1);
		monthStudentSchedule.addDailySchedule((DailyScheduleForStudent) dayStudentSchedule);
		university.addSchedule(monthStudentSchedule);
		
		CourseSchedule courseSchedule = new CourseSchedule(7, math);
		for (ScheduleSlot ss: sheduleSlots){
			if(ss.getCourse().equals(math)){
				courseSchedule.addSchedulerSlot(ss);
			}
		}
		university.addSchedule(courseSchedule);
		
		
		university.removeCourse(math);
		
		assertEquals(null, university.getCourse(math));
		assertEquals(null, university.getGroup(gr1).getCourse(math));
		assertEquals(null, university.getProfessor(prMath).getCourse(math));
		assertEquals(null, university.getStudent(st1).getGroup().getCourse(math));
		
		assertEquals(null, university.getSchedule(courseSchedule));
		assertEquals(0, ((DailyScheduleForStudent)university.getSchedule(dayStudentSchedule))
				.getStudent().getGroup().getCourses().size());
		assertEquals(0, ((MonthlyScheduleForStudent)university.getSchedule(monthStudentSchedule))
				.getStudent().getGroup().getCourses().size());
		
		
		assertEquals(null, ((DailyScheduleForStudent)university.getSchedule(dayStudentSchedule))
				.getScheduleSlot(sheduleSlots.get(353)).getCourse());
		assertEquals(0, ((DailyScheduleForStudent)university.getSchedule(dayStudentSchedule))
				.getScheduleSlot(sheduleSlots.get(353)).getProfessor().getCourses().size());		
	}

	@Test
	public void testRemoveCourseAsSecondCourse() {
		university.addStudent(st1, gr1);
		university.addCourseToGroup(math, gr1);
		university.addCourseToGroup(java, gr1);
		university.addProfessorToCourse(prMath, math);
		university.addProfessorToCourse(prMath, java);
				
		sheduleSlots.get(352).addGroup(gr1);
		sheduleSlots.get(352).setCourse(java);
		sheduleSlots.get(352).setProfessor(prMath);
		sheduleSlots.get(353).addGroup(gr1);
		sheduleSlots.get(353).setCourse(math);
		sheduleSlots.get(353).setProfessor(prMath);
					
		Schedule dayStudentSchedule = new DailyScheduleForStudent(3, 1, st1);
		for (ScheduleSlot ss: sheduleSlots){
			if(ss.getGroups().contains(st1.getGroup())){
				dayStudentSchedule.addSchedulerSlot(ss);
			}
		}
		university.addSchedule(dayStudentSchedule);
		
		MonthlyScheduleForStudent monthStudentSchedule = new MonthlyScheduleForStudent(4, 1, st1);
		monthStudentSchedule.addDailySchedule((DailyScheduleForStudent) dayStudentSchedule);
		university.addSchedule(monthStudentSchedule);
		
		CourseSchedule courseSchedule1 = new CourseSchedule(7, math);
		for (ScheduleSlot ss: sheduleSlots){
			if(ss.getCourse().equals(math)){
				courseSchedule1.addSchedulerSlot(ss);
			}
		}
		university.addSchedule(courseSchedule1);
		
		CourseSchedule courseSchedule2 = new CourseSchedule(7, java);
		for (ScheduleSlot ss: sheduleSlots){
			if(ss.getCourse().equals(java)){
				courseSchedule2.addSchedulerSlot(ss);
			}
		}
		university.addSchedule(courseSchedule2);
				
		university.removeCourse(java);
		
		assertEquals(math, university.getCourse(math));
		assertEquals(math, university.getGroup(gr1).getCourse(math));
		assertEquals(math, university.getProfessor(prMath).getCourse(math));
		assertEquals(math, university.getStudent(st1).getGroup().getCourse(math));
		assertEquals(1, university.getStudent(st1).getGroup().getCourses().size());
		
		assertEquals(courseSchedule1, university.getSchedule(courseSchedule1));
		assertEquals(null, university.getSchedule(courseSchedule2));
		
		assertEquals(1, ((DailyScheduleForStudent)university.getSchedule(dayStudentSchedule))
				.getStudent().getGroup().getCourses().size());
		assertEquals(1, ((MonthlyScheduleForStudent)university.getSchedule(monthStudentSchedule))
				.getStudent().getGroup().getCourses().size());
		
		assertEquals(null, ((DailyScheduleForStudent)university.getSchedule(dayStudentSchedule)).getScheduleSlot(sheduleSlots.get(352)).getCourse());	
		assertEquals(math, ((DailyScheduleForStudent)university.getSchedule(dayStudentSchedule)).getScheduleSlot(sheduleSlots.get(353)).getCourse());		
						
		assertEquals(1, ((DailyScheduleForStudent)university.getSchedule(dayStudentSchedule))
				.getScheduleSlot(sheduleSlots.get(353)).getProfessor().getCourses().size());
	}
	
	
	@Test
	public void testRemoveStudent() {
		university.addStudent(st1, gr1);
		university.addStudent(st2, gr1);
		university.addProfessorToCourse(prMath,math);
		university.addCourseToGroup(math, gr1);
		
		sheduleSlots.get(353).setProfessor(prMath);
		sheduleSlots.get(353).addGroup(gr1);
		sheduleSlots.get(353).setCourse(math);
				
		Schedule dayStudentSchedule1 = new DailyScheduleForStudent(1, 1, st1);
		for (ScheduleSlot ss: sheduleSlots){
			if(ss.getGroups().contains(st1.getGroup())){
				dayStudentSchedule1.addSchedulerSlot(ss);
			}
		}
		university.addSchedule(dayStudentSchedule1);
		
		Schedule dayStudentSchedule2 = new DailyScheduleForStudent(2, 1, st2);
		for (ScheduleSlot ss: sheduleSlots){
			if(ss.getGroups().contains(st1.getGroup())){
				dayStudentSchedule2.addSchedulerSlot(ss);
			}
		}
		university.addSchedule(dayStudentSchedule2);
		
		MonthlyScheduleForStudent monthStudentSchedule1 = new MonthlyScheduleForStudent(4, 1, st1);
		monthStudentSchedule1.addDailySchedule((DailyScheduleForStudent) dayStudentSchedule1);
		university.addSchedule(monthStudentSchedule1);
		
		MonthlyScheduleForStudent monthStudentSchedule2 = new MonthlyScheduleForStudent(4, 1, st1);
		monthStudentSchedule2.addDailySchedule((DailyScheduleForStudent) dayStudentSchedule2);
		university.addSchedule(monthStudentSchedule2);
		
		CourseSchedule courseSchedule = new CourseSchedule(7, math);
		for (ScheduleSlot ss: sheduleSlots){
			if(ss.getCourse().equals(math)){
				courseSchedule.addSchedulerSlot(ss);
			}
		}
		university.addSchedule(courseSchedule);		
		
		university.removeStudent(st1);
		
		assertEquals(null, university.getStudent(st1));
		assertEquals(null, university.getGroup(gr1).getStudent(st1));
		assertEquals(null, university.getGroup(gr1).getCourse(math).getGroup(gr1).getStudent(st1));
		assertEquals(null, university.getCourse(math).getGroup(gr1).getStudent(st1));		
		
		assertEquals(null, university.getSchedule(monthStudentSchedule1));
		assertEquals(null, university.getSchedule(dayStudentSchedule1));
		assertEquals(dayStudentSchedule2, university.getSchedule(dayStudentSchedule2));
		
		assertEquals(null, ((CourseSchedule)university.getSchedule(courseSchedule))
				.getScheduleSlot(sheduleSlots.get(353)).getGroup(gr1).getStudent(st1));		
		assertEquals(st2, ((CourseSchedule)university.getSchedule(courseSchedule))
				.getScheduleSlot(sheduleSlots.get(353)).getGroup(gr1).getStudent(st2));
		
		assertEquals(null, ((CourseSchedule)university.getSchedule(courseSchedule))
				.getCourse().getGroup(gr1).getStudent(st1));
		assertEquals(st2, ((CourseSchedule)university.getSchedule(courseSchedule))
				.getCourse().getGroup(gr1).getStudent(st2));
		
		assertEquals(null, ((CourseSchedule)university.getSchedule(courseSchedule))
				.getScheduleSlot(sheduleSlots.get(353)).getGroup(gr1).getStudent(st1));
		assertEquals(st2, ((CourseSchedule)university.getSchedule(courseSchedule))
				.getScheduleSlot(sheduleSlots.get(353)).getGroup(gr1).getStudent(st2));
	}

	@Test
	public void testRemoveRoom() {
		university.addRoom(room1);
		university.addStudent(st1, gr1);				
		sheduleSlots.get(353).addGroup(gr1);		
			
		Schedule dayStudentSchedule = new DailyScheduleForStudent(3, 1, st1);
		for (ScheduleSlot ss: sheduleSlots){
			if(ss.getGroups().contains(st1.getGroup())){
				dayStudentSchedule.addSchedulerSlot(ss);
			}
		}
		university.addSchedule(dayStudentSchedule);		
		
				
		RoomSchedule roomSchedule = new RoomSchedule(6, room1);
		for (ScheduleSlot ss: sheduleSlots){
			if(ss.getRoom().equals(room1)){
				roomSchedule.addSchedulerSlot(ss);
			}
		}
		university.addSchedule(roomSchedule);		
		
		university.removeRoom(room1);
		
		assertEquals(0, university.getAllRooms().size());				
		assertEquals(null, university.getSchedule(roomSchedule));
				
		assertEquals(null, ((DailyScheduleForStudent)university.getSchedule(dayStudentSchedule))
				.getScheduleSlot(sheduleSlots.get(353)).getRoom());		
	}

	@Test
	public void testRemoveSchedule() {		
		sheduleSlots.get(353).addGroup(gr1);
		
		RoomSchedule roomSchedule = new RoomSchedule(6, room1);
		for (ScheduleSlot ss: sheduleSlots){
			if(ss.getRoom().equals(room1)){
				roomSchedule.addSchedulerSlot(ss);
			}
		}
		university.addSchedule(roomSchedule);		
		university.addSchedule(schedule);
		university.removeSchedule(schedule);
		
		assertEquals(null, university.getSchedule(schedule));				
		assertEquals(roomSchedule, university.getSchedule(roomSchedule));
		assertEquals(sheduleSlots.get(353), ((RoomSchedule)university.getSchedule(roomSchedule))
				.getScheduleSlot(sheduleSlots.get(353)));		
	}	
	
	@Test
	public void testGetGroup() {
		university.addGroup(gr1);
		
		assertEquals(null, university.getGroup(null));		
	}	
}
