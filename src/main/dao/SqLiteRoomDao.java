package main.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;

import main.university.Room;

public class SqLiteRoomDao extends AbstractJDBCDao<Room, Integer>{

	public SqLiteRoomDao(DaoFactory daoFactory) {
		super(daoFactory);
	}

	@Override
	public String getSelectQuery() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getCreateQuery() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getUpdateQuery() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getDeleteQuery() {
		// TODO Auto-generated method stub
		return null;
	}

	public Room create() throws PersistException {
		Room r = new Room();
        return persist(r);
	}
	
	@Override
	protected List<Room> parseResultSet(ResultSet rs) throws PersistException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected void prepareStatementForInsert(PreparedStatement statement,
			Room object) throws PersistException {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void prepareStatementForUpdate(PreparedStatement statement,
			Room object) throws PersistException {
		// TODO Auto-generated method stub
		
	}

}
