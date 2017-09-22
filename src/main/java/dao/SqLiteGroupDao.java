package main.java.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import main.java.university.Group;

public class SqLiteGroupDao extends AbstractJDBCDao<Group, Integer>{
	
	@Override
	public String getSelectQuery() {
		return "SELECT id, name FROM 'Groups'";
	}

	@Override
	public String getCreateQuery() {
		return "INSERT INTO 'Groups' (name) \n" +
                "VALUES (?);";
	}

	@Override
	public String getUpdateQuery() {
		 return "UPDATE 'Groups' SET name = ? WHERE id= ?;";
	}

	@Override
	public List<String> getDeleteQuery() {
		List<String> sql = new ArrayList<>();		
		sql.add("DELETE FROM 'Groups' WHERE id= ?;");
		return sql;		
	}
	
	public Group create() throws PersistException{
		Group g = new Group();
        return persist(g);
	}

	public SqLiteGroupDao(DaoFactory daoFactory) {
		super(daoFactory);
	}

	
	@Override
	protected List<Group> parseResultSet(ResultSet rs) throws PersistException {
		List<Group> result = new ArrayList<Group>();
        try {
            while (rs.next()) {
                Group group = new Group();
                group.setId(rs.getInt("id"));
                group.setName(rs.getString("name"));               
                result.add(group);
            }
        } catch (Exception e) {
            throw new PersistException(e);
        }
        return result;
	}

	@Override
	protected void prepareStatementForInsert(PreparedStatement statement,
			Group object) throws PersistException {
		try {
            statement.setString(1, object.getName());
        } catch (Exception e) {
            throw new PersistException(e);
        }		
	}

	@Override
	protected void prepareStatementForUpdate(PreparedStatement statement,
			Group object) throws PersistException {
		try {
			statement.setString(1, object.getName());            
            statement.setInt(2, object.getId());
        } catch (Exception e) {
            throw new PersistException(e);
        }		
	}	
}
