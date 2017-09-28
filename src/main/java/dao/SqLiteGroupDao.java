package main.java.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import main.java.university.Group;

public class SqLiteGroupDao extends AbstractJDBCDao<Group, Integer>{
	
	@Override
	public String getSelectQuery() {
		return "SELECT id, name FROM \"Groups\"";
	}
	
	/**
	 * ¬озвращает sql запрос дл€ получени€ всех id св€занного обьекта из таблицы св€зей ForeignKey.
	 * <p/>
	 * SELECT dependentObj_id FROM [FK_Table] WHERE Instance_id = ?;
	 *  
	 */
	@Override
	public String getSelectDependentObjQuery(Class<?> K1, Class<?> T){
		return "SELECT " + K1.getSimpleName() + "_id FROM " + T.getSimpleName() + "s_" 
						+ K1.getSimpleName() + "s WHERE " + T.getSimpleName() + "_id = ?";
	}
	
	/**
	 * ¬озвращает sql запрос дл€ добавлени€ записи о id св€занного обьекта в таблицу св€зей ForeignKey.
	 * <p/>
	 * INSERT INTO PROFESSORS_COURSES (professor_id, course_id) VALUES (?, ?);
	 *  
	 */
	@Override
	public String getInsertDependentObjQuery(Class<?> K1, Class<?> T) {		
		return "INSERT INTO " + T.getSimpleName() + "s_" 
				+ K1.getSimpleName() + "s (" + K1.getSimpleName() + "_id, " + T.getSimpleName() + "_id) VALUES (?, ?);";
	}

	/**
	 * ¬озвращает sql запрос дл€ добавлени€ записи о id св€занного обьекта в таблицу св€зей ForeignKey.
	 * <p/>
	 * INSERT INTO PROFESSORS_COURSES (professor_id, course_id) VALUES (?, ?);
	 *  
	 */
	@Override
	public String getDeleteDependentObjQuery(Class<?> K1, Class<?> T) {		
		return "DELETE FROM " + T.getSimpleName() + "s_"  + K1.getSimpleName() + "s WHERE " 
				+ K1.getSimpleName() + "_id=? AND " + T.getSimpleName() + "_id=? ;";
	}
	
	@Override
	public String getCreateQuery() {
		return "INSERT INTO \"Groups\" (name) \n" +
                "VALUES (?);";
	}	
	
	@Override
	public String getUpdateQuery() {
		 return "UPDATE \"Groups\" SET name = ? WHERE id= ?;";
	}

	@Override
	public List<String> getDeleteQuery() {
		List<String> sql = new ArrayList<>();		
		sql.add("DELETE FROM Groups_Students WHERE group_id = ?;");
		sql.add("DELETE FROM Groups_Courses WHERE group_id = ?;");		
		sql.add("DELETE FROM \"Groups\" WHERE id= ?;");
		return sql;
	}
	
	public Group create(String name) throws PersistException{
		Group g = new Group(null, name);
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
