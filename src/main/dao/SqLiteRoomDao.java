package main.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import main.university.Room;

public class SqLiteRoomDao extends AbstractJDBCDao<Room, Integer>{
	
	private static Logger log = Logger.getLogger(SqLiteRoomDao.class.getName());

	public SqLiteRoomDao(DaoFactory daoFactory) {
		super(daoFactory);
	}

	@Override
	public String getSelectQuery() {
		return "SELECT id, capacity, address FROM Room";
	}

	@Override
	public String getCreateQuery() {
		return "INSERT INTO Room (capacity, address) \n" +
                "VALUES (?, ?);";
	}

	@Override
	public String getUpdateQuery() {
		 return "UPDATE Room SET capacity = ?, address = ? WHERE id= ?;";
	}

	@Override
	public String getDeleteQuery() {
		 return "DELETE FROM Room WHERE id= ?;";
	}

	public Room create(Integer capacity, String address) throws PersistException {
		log.info("Creating new room with capacity=" + capacity + " , address=" + address);
		Room r = new Room(null, capacity, address);
        return persist(r);
	}
	
	@Override
	protected List<Room> parseResultSet(ResultSet rs) throws PersistException {
		List<Room> result = new ArrayList<Room>();
        try {
            while (rs.next()) {
            	Room room = new Room();
            	room.setId(rs.getInt("id"));
            	room.setCapacity(rs.getInt("capacity"));
            	room.setAddress(rs.getString("address")); 
                result.add(room);
            }
        } catch (Exception e) {
            throw new PersistException(e);
        }
        return result;
	}

	@Override
	protected void prepareStatementForInsert(PreparedStatement statement, Room object) throws PersistException {
		try {           
            statement.setInt(1, object.getCapacity());
            statement.setString(2, object.getAddress());
        } catch (Exception e) {
            throw new PersistException(e);
        }		
	}

	@Override
	protected void prepareStatementForUpdate(PreparedStatement statement,
			Room object) throws PersistException {
		try {
			statement.setInt(1, object.getCapacity());            
            statement.setString(2, object.getAddress());
            statement.setInt(3, object.getId());
        } catch (Exception e) {
            throw new PersistException(e);
        }		
	}
}
