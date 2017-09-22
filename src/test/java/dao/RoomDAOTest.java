package test.java.dao;

import java.util.List;
import main.java.dao.DaoFactory;
import main.java.dao.PersistException;
import main.java.dao.SqLiteRoomDao;
import main.java.university.Room;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;



public class RoomDAOTest {
	
	private DaoFactory daoFactory;
	
	private SqLiteRoomDao dao;
	
	private Room room;
	
	@Before
	public void setUpBefore() throws PersistException{
		try {   
			daoFactory = new DaoFactory();
	        dao = daoFactory.getRoomDao();
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
			room = dao.create(40, "Lviv");
	    } catch (Exception e) {
            throw new PersistException(e);
        }
	    
		Assert.assertNotNull(room);
	    Assert.assertNotNull(room.getId());
	}

	
	@Test
	public void testGetByPK() throws PersistException {
		 int id = dao.create(43, "Lviv").getId();
	     room = dao.getByPK(id);
	        Assert.assertNotNull(room);
	}

	@Test
	public void testGetAll() throws PersistException{
		
	    List<Room> list;
	    try {
	    	list = dao.getAll();
	    } catch (Exception e) {
            throw new PersistException(e);
        }
	    Assert.assertNotNull(list);
	    Assert.assertTrue(list.size() > 0);	
	}

	@Test
	public void testDelete() throws PersistException {
		try {
			room = dao.create(30, "Rivne");	    

	        List<Room> list = dao.getAll();
	        Assert.assertNotNull(list);

	        int oldSize = list.size();
	        Assert.assertTrue(oldSize > 0);

	        dao.delete(room);

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
		Room r = new Room(4, 15, "Chernivtsi");
		try {
			dao.update(r);
	    } catch (Exception e) {
            throw new PersistException(e);
        }
	    
		Assert.assertTrue("not the same room", r.equals(dao.getByPK(r.getId())));
	}

}
