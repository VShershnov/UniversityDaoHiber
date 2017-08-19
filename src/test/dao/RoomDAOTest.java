package test.dao;

import static org.junit.Assert.*;

import java.util.List;

import main.dao.DaoFactory;
import main.dao.PersistException;
import main.dao.SqLiteRoomDao;
import main.university.Room;

import org.junit.Assert;
import org.junit.Test;

public class RoomDAOTest {

	@Test
	public void testCreate() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetByPK() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetAll() throws PersistException{
		DaoFactory daoFactory = new DaoFactory();
	    List<Room> list;
	    try {
	        SqLiteRoomDao dao = daoFactory.getRoomDao();
	        list = dao.getAll();
	    } catch (Exception e) {
            throw new PersistException(e);
        }
	    Assert.assertNotNull(list);
	    Assert.assertTrue(list.size() > 0);	
	}

	@Test
	public void testPersist() {
		fail("Not yet implemented");
	}

	@Test
	public void testDelete() {
		fail("Not yet implemented");
	}

	@Test
	public void testUpdate() {
		fail("Not yet implemented");
	}

}
