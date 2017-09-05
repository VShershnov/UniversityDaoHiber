package test.dao;

import java.util.List;

import main.dao.DaoFactory;
import main.dao.PersistException;
import main.dao.SqLiteCourseDao;
import main.university.Course;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class CourseDAOTest {

private DaoFactory daoFactory;
	
	private SqLiteCourseDao dao;
	
	private Course course;
	
	@Before
	public void setUpBefore() throws PersistException{
		try {   
			daoFactory = new DaoFactory();
	        dao = daoFactory.getCourseDao();
	    } catch (Exception e) {
	    	System.err.println("Could not setup logger configuration: " + e.toString());
            throw new PersistException(e);
        }		
	}
	
	@After
	public void tearDown(){
		daoFactory = null;
		dao = null;		
	}

	@Test
	public void testCreate() throws PersistException {
		try {
			course = dao.create("Java", 40);
	    } catch (Exception e) {
            throw new PersistException(e);
        }
	    
		Assert.assertNotNull(course);
	    Assert.assertNotNull(course.getId());
	}

	
	@Test
	public void testGetByPK() throws PersistException {
		 int id = dao.create("JavaEE", 10).getId();
		 course = dao.getByPK(id);
	        Assert.assertNotNull(course);
	}

	@Test
	public void testGetAll() throws PersistException{
		
	    List<Course> list;
	    try {
	    	list = dao.getAll();
	    } catch (Exception e) {
            throw new PersistException(e);
        }
	    Assert.assertNotNull(list);
	    Assert.assertTrue(list.size() > 0);	
	}
	
	@Test
	public void testgetInstanceallDepencedObj1() throws PersistException {
		course = new Course(1,"JavaEE", 10);
		
		List<Integer> list = dao.getInstanceallDepencedObj1(course);

		Assert.assertNotNull(list);
	    Assert.assertTrue(list.size() > 0);
	}

	@Test
	public void testDelete() throws PersistException {
		try {
			course = dao.create("JavaEE", 10);	    

	        List<Course> list = dao.getAll();
	        Assert.assertNotNull(list);

	        int oldSize = list.size();
	        Assert.assertTrue(oldSize > 0);

	        dao.delete(course);

	        list = dao.getAll();
	        Assert.assertNotNull(list);

	        int newSize = list.size();
	        Assert.assertEquals(1, oldSize - newSize);
	        
		} catch (Exception e) {
            throw new PersistException(e);
        }
	}

	@Test
	public void testUpdate() throws PersistException {
		Course c = new Course(4, "Math", 12);
		try {
			dao.update(c);
	    } catch (Exception e) {
            throw new PersistException(e);
        }
	    course = dao.getByPK(c.getId());
		Assert.assertTrue("not the same course", c.equals(course));
	}
}
