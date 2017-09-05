package main.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import main.university.Course;
import main.university.Group;
import main.university.person.Professor;

public class SqLiteCourseDao extends AbstractJDBCDao<Course, Integer>{

	private final  Logger log = LogManager.getLogger(this.getClass().getPackage().getName());
	
	public SqLiteCourseDao(DaoFactory daoFactory) {
		super(daoFactory);		
	}

	@Override
	public String getSelectQuery() {
		return "SELECT id, name, duration FROM Courses";
	}

	/**
	 * select * from professors 
	 * where id in (
	 *		select  professor_id from PROFESSORS_COURSES
	 *			where course_id = ?)
	 */
	public String getSelectDependedObj1Query() {
		return "SELECT professor_id FROM PROFESSORS_COURSES WHERE course_id = ?";
	}

	
	
	@Override
	public String getSelectDependedObj2Query() {
		return "SELECT group_id FROM GROUPS_COURSES WHERE course_id = ?";
	}

	@Override
	public String getCreateQuery() {
		return "INSERT INTO Courses (name, duration) \n" +
                "VALUES (?, ?);";
	}

	@Override
	public String getUpdateQuery() {
		return "UPDATE Courses SET name = ?, duration = ? WHERE id= ?;";
	}

	@Override
	public String getDeleteQuery() {
		return "DELETE FROM Courses WHERE id= ?;";
	}

	public Course create(String name, Integer duration) throws PersistException {
		log.info("Creating new course " + name + " , duration=" + duration);
		Course c = new Course(null, name, duration);
        return persist(c);
	}
	
	
/*
	public void getAllCourseProfessors (Course course){
		log.info("Get All Professors from course" + course.getId() + course.getName() 
				+ " , duration=" + course.getDuration());
	
		List<Integer> list;
        String sql = getSelectQuery();
        sql += " WHERE id = ?";
	}
	
	public void getAllCourseGroups (Course course){
		
	}	
	
	public void AddCourseProfessor (Course course, Professor prof){
		log.info("Add  Professor " + prof.toString() + " to course" + course.getId() 
				+ course.getName() + " , duration=" + course.getDuration());
	}
	
	public void AddCourseGroup (Group group){
		
	}
	
	public void removeCourseProfessor (Professor prof){
		
	}
	
	public void removeCourseGroup (Group group){
		
	}
*/
	
	@Override
	protected List<Course> parseResultSet(ResultSet rs) throws PersistException {
		List<Course> result = new ArrayList<Course>();
		log.debug("Parse Result Set from DB to Object's List");
		try {
            while (rs.next()) {
            	if(log.isEnabled(Level.TRACE))
            		log.trace("Parse row " + rs.getInt("id") + " to Course Object");
            	Course course = new Course();
            	course.setId(rs.getInt("id"));
            	course.setDuration(rs.getInt("duration"));
            	course.setName(rs.getString("name")); 
                result.add(course);
            }
        } catch (Exception e) {
        	log.error("Cannot parse Object ", e);
            throw new PersistException(e);
        }
        return result;
	}

	@Override
	protected List<Integer> parseDependenceResultSet(ResultSet rs)
			throws PersistException {
		List<Integer> result = new ArrayList<Integer>();
		log.debug("Parse Result Set from DB to Depended Object ids List");
		try {
            while (rs.next()) {
            	if(log.isEnabled(Level.TRACE))
            		log.trace("Parse row " + rs.getInt("id") + " to Depended Object id");            	
                result.add(rs.getInt("professor_id"));
            }
        } catch (Exception e) {
        	log.error("Cannot parse Object ", e);
            throw new PersistException(e);
        }
        return result;
	}

	@Override
	protected void prepareStatementForInsert(PreparedStatement statement,
			Course object) throws PersistException {
		try {           
			log.debug("Prepare Statement for insert to DB");
			statement.setInt(2, object.getDuration());
            statement.setString(1, object.getName());
        } catch (Exception e) {
        	log.error("Cannot create Statement for insert ", e);
        	throw new PersistException(e);
        }		
	}

	@Override
	protected void prepareStatementForUpdate(PreparedStatement statement,
			Course object) throws PersistException {
		try {
			log.debug("Prepare Statement for update to DB");
			statement.setInt(2, object.getDuration());
            statement.setString(1, object.getName());
            statement.setInt(3, object.getId());
        } catch (Exception e) {
        	log.error("Cannot create Statement for update ", e);
        	throw new PersistException(e);
        }		
	}
}
