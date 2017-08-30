package main.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;

import main.university.Room;

public class SqLiteCourseDao extends AbstractJDBCDao<Course, Integer>{

	public SqLiteCourseDao(DaoFactory daoFactory) {
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

	@Override
	protected List<Course> parseResultSet(ResultSet rs) throws PersistException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected void prepareStatementForInsert(PreparedStatement statement,
			Course object) throws PersistException {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void prepareStatementForUpdate(PreparedStatement statement,
			Course object) throws PersistException {
		// TODO Auto-generated method stub
		
	}

}
