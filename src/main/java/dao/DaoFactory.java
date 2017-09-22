package main.java.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class DaoFactory{
	
	private final  Logger log = LogManager.getLogger(this.getClass().getPackage().getName());
	
    private String url = "jdbc:sqlite:UniversityDAO.db";//URL адрес"
    private String driver = "org.sqlite.JDBC";//Имя драйвера
	
	public Connection getConnection() throws PersistException{
		Connection connection = null;
		try {
			log.trace("Create connection");
            connection = DriverManager.getConnection(url);
        } catch (SQLException e) {
        	log.error("Cannot set connection ", e);
        	throw new PersistException(e);
        }
        return  connection;		
	}

	public SqLiteGroupDao getGroupDao() throws PersistException{		
		return new SqLiteGroupDao(this);
	}

	public SqLiteStudentDao getStudentDao() throws PersistException{
		return new SqLiteStudentDao(this);
	}

	public SqLiteRoomDao getRoomDao() throws PersistException{
		return new SqLiteRoomDao(this);
	}
	
	public SqLiteCourseDao getCourseDao() throws PersistException{
		return new SqLiteCourseDao(this);
	}

	public DaoFactory(){
		try {
			log.trace("register jdbc driver");
            Class.forName(driver);//Регистрируем драйвер
        } catch (ClassNotFoundException e) {
        	log.error("Cannot find овис driver ", e);
            e.printStackTrace();
        }
    }
}
