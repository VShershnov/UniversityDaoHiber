package test.dao;

import static org.junit.Assert.*;

import java.util.List;

import main.dao.DaoFactory;
import main.dao.PersistException;
import main.dao.SqLiteGroupDao;
import main.university.Group;

import org.junit.Assert;
import org.junit.Test;

public class GroupDAOTest {

	@Test
	public void testCreate() {
		fail("Not yet implemented");
	}

	@Test
	public void testRead() {
		fail("Not yet implemented");
	}

	@Test
	public void testUpdate() {
		fail("Not yet implemented");
	}

	@Test
	public void testDelete() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetAll() throws PersistException {
		DaoFactory daoFactory = new DaoFactory();
	    List<Group> list;
	    try {
	        SqLiteGroupDao dao = daoFactory.getGroupDao();
	        list = dao.getAll();
	    } catch (Exception e) {
            throw new PersistException(e);
        }
	    
	    Assert.assertNotNull(list);
	    Assert.assertTrue(list.size() > 0);		
	}

	@Test
	public void testSqLiteGroupDao() {
		fail("Not yet implemented");
	}

}