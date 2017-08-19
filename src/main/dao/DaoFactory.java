package main.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DaoFactory{
		
    private String url = "jdbc:sqlite:UniversityDAO.db";//URL адрес"
    private String driver = "org.sqlite.JDBC";//Имя драйвера
	
	public Connection getConnection() throws PersistException{
		Connection connection = null;
		try {
            connection = DriverManager.getConnection(url);
        } catch (SQLException e) {
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

	public DaoFactory(){
		try {
            Class.forName(driver);//Регистрируем драйвер
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
