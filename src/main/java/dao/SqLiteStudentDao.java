package main.java.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import main.java.university.Group;
import main.java.university.person.Student;

public class SqLiteStudentDao extends AbstractJDBCDao<Student, Integer>{


	public SqLiteStudentDao(DaoFactory daoFactory) {
		super(daoFactory);		
	}

	@Override
	public String getSelectQuery() {
		return "SELECT id, fullName, group_id FROM Student ";
	}

	@Override
	public String getCreateQuery() {
		return "INSERT INTO Student (fullName, group_id) \n" +
                "VALUES (?, ?);";
	}

	@Override
	public String getUpdateQuery() {
		return "UPDATE Student \n" +
                "SET fullName = ?, group_id = ? \n" +
                "WHERE id = ?;";
	}

	@Override
	public List<String> getDeleteQuery() {
		List<String> sql = new ArrayList<>();		
		sql.add("DELETE FROM Student WHERE id= ?;");
		return sql;
	}

	public Student create() throws PersistException {
		Student s = new Student();
        return persist(s);
	}	

	@Override
	protected List<Student> parseResultSet(ResultSet rs) throws PersistException{
		List<Student> result = new ArrayList<Student>();

		try {
            while (rs.next()) {
                Student student = new Student();
                student.setId(rs.getInt("id"));
                student.setFullName(rs.getString("fullName"));               
                student.setGroup(new Group(rs.getInt("group_id"), ""));//????????????????????????????????
                result.add(student);
            }
        } catch (Exception e) {
            throw new PersistException(e);
        }
        return result;
	}

	@Override
	protected void prepareStatementForInsert(PreparedStatement statement, Student object) throws PersistException {
        try {           
            statement.setString(1, object.getFullName());
            statement.setInt(2, object.getGroup().getId());
        } catch (Exception e) {
            throw new PersistException(e);
        }		
	}

	@Override
	protected void prepareStatementForUpdate(PreparedStatement statement,
			Student object) throws PersistException {

        try {
            statement.setString(1, object.getFullName());
            statement.setInt(2, object.getGroup().getId());
            statement.setInt(3, object.getId());
        } catch (Exception e) {
            throw new PersistException(e);
        }		
	}	
}
