package test.java.university.person;

import static org.junit.Assert.*;
import main.java.university.Group;
import main.java.university.person.Student;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class StudentTests {

	private Student student;
	private Group group;
	
	@Before
	public void setUpBeforeClass(){
		group = new Group(1, "FIOT");
		student = new Student(1, "Andrey Petrov");
	}
		
	@After
	public void tearDown() throws Exception {
		student = null;
	}

	@Test
	public void testGetGroup() {
		student.setGroup(group);
		assertEquals( group, student.getGroup());		
	}

	@Test
	public void testSetGroup() {
		fail("Not yet implemented");
	}

	@Test
	public void testToString() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetId() {
		fail("Not yet implemented");
	}

	@Test
	public void testSetId() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetFullName() {
		fail("Not yet implemented");
	}

	@Test
	public void testSetFullName() {
		fail("Not yet implemented");
	}

}
