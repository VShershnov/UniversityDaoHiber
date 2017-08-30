package main.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Абстрактный класс предоставляющий базовую реализацию CRUD операций с использованием JDBC.
 *
 * @param <T>  тип объекта персистенции
 * @param <PK> тип первичного ключа
 */
public abstract class AbstractJDBCDao<T extends Identified <PK>, PK extends Integer>{

	private DaoFactory daoFactory;

	private final  Logger log = LogManager.getLogger(this.getClass().getPackage().getName());
	
    /**
     * Возвращает sql запрос для получения всех записей.
     * <p/>
     * SELECT * FROM [Table]
     */
    public abstract String getSelectQuery();
    
    /**
     * Возвращает sql запрос для вставки новой записи в базу данных.
     * <p/>
     * INSERT INTO [Table] ([column, column, ...]) VALUES (?, ?, ...);
     */
    public abstract String getCreateQuery();
    
    /**
     * Возвращает sql запрос для обновления записи.
     * <p/>
     * UPDATE [Table] SET [column = ?, column = ?, ...] WHERE id = ?;
     */
    public abstract String getUpdateQuery();
    
    /**
     * Возвращает sql запрос для удаления записи из базы данных.
     * <p/>
     * DELETE FROM [Table] WHERE id= ?;
     */
    public abstract String getDeleteQuery();

    
    /**
     * Разбирает ResultSet и возвращает список объектов соответствующих содержимому ResultSet.
     * @throws PersistException 
     */
    protected abstract List<T> parseResultSet(ResultSet rs) throws PersistException;
    
    /**
     * Устанавливает аргументы insert запроса в соответствии со значением полей объекта object.
     */
    protected abstract void prepareStatementForInsert(PreparedStatement statement, T object) throws PersistException;

    /**
     * Устанавливает аргументы update запроса в соответствии со значением полей объекта object.
     */
    protected abstract void prepareStatementForUpdate(PreparedStatement statement, T object) throws PersistException;

    
    public T getByPK(int key) throws PersistException {    	
        List<T> list;
        String sql = getSelectQuery();
        sql += " WHERE id = ?";
        
        log.info("Get object from DB by key "+ key);
        
        log.debug("Open connection & Create prepared statement");
        try (Connection connection = daoFactory.getConnection();
        	PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, key);
            
            log.debug("Get result set");
            ResultSet rs = statement.executeQuery();
            
            log.debug("Parse result set");
            list = parseResultSet(rs);
            
        log.debug("statement&connection closed");   
        } catch (Exception e) {
        	log.debug("statement&connection closed");
        	log.error("Cannot find Object by id ", e);
            throw new PersistException(e);
        }
        
        if (list == null || list.size() == 0) {
        	log.info("returned object = null");
            return null;
        }
        if (list.size() > 1) {
        	String s = "Received more than one record.";
        	log.error(s);
            throw new PersistException(s);
        }
        return list.iterator().next();
    }
    
    public List<T> getAll() throws PersistException {
        List<T> list;
        String sql = getSelectQuery();
        
        log.info("Get all objects from DB");
        
        log.debug("Open connection & Create prepared statement & Get result set");
        try (Connection connection = daoFactory.getConnection();
        	PreparedStatement statement = connection.prepareStatement(sql);
        	ResultSet rs = statement.executeQuery()) {
        	
        	log.debug("Parse result set");
            list = parseResultSet(rs);
            
            log.debug("resultset & statement & connection closed");   
        } catch (Exception e) {
        	log.debug("resultset & statement & connection closed");  
        	log.error("Cannot return any Object ", e);
            throw new PersistException(e);
        }
        return list;
    }    
   
    public T persist(T object) throws PersistException {
        T persistInstance;
        
        // Добавляем запись
        String sql = getCreateQuery();
        
        log.info("Create Object " + object.toString());
        
        log.debug("Open connection");
        try (Connection connection = daoFactory.getConnection()) {
        	log.debug("Create prepared statement");
        	PreparedStatement statement = connection.prepareStatement(sql);
        	prepareStatementForInsert(statement, object);
        	
        	log.debug("Get count of inserted result set");
	        int count = statement.executeUpdate();
	        
	        if (count != 1){
	        	String s = "On persist modify more then 1 record: ";
	        	log.error(s + count);
	        	throw new PersistException(s + count);
	        }	        	
	         
	        log.debug("statement closed");
	        statement.close();
	        
	        // Получаем только что вставленную запись
	        sql = getSelectQuery() + " WHERE id = last_insert_rowid();";
        
	        log.debug("Create prepared statement");
	        statement = connection.prepareStatement(sql);
	        log.debug("Get result set");
	        ResultSet rs = statement.executeQuery();            
            List<T> list = parseResultSet(rs);
            if ((list == null) || (list.size() != 1)){
            	String s = "Exception on findByPK new persist data.";
            	log.error(s);
                throw new PersistException(s);
            }
            persistInstance = list.iterator().next();
        
        log.debug("connection closed");
        } catch (Exception e) {
        	log.debug("connection closed");
        	log.error("Cannot insert Object ", e);
            throw new PersistException(e);            
        }
        return persistInstance;
    }
   
    public void delete(T object) throws PersistException {
        String sql = getDeleteQuery();
        
        log.info("Delete Object " + object.toString());
        
        log.debug("Open connection & Create prepared statement");
        try (Connection connection = daoFactory.getConnection();
        	PreparedStatement statement = connection.prepareStatement(sql)) {            
            
        	statement.setObject(1, object.getId());
        	
        	log.debug("Get count of deleted result set");
            int count = statement.executeUpdate();
           	if (count != 1){
            	String s = "On delete modify more then 1 record: ";
        		log.error(s);
                throw new PersistException(s + count);
           	}
        
        log.debug("statement & connection closed");
        } catch (Exception e) {
        	log.debug("statement & connection closed");
        	log.error("Cannot delete Object " + object.toString(), e);
            throw new PersistException(e);
        }
    }
    
   
    public void update(T object) throws PersistException {
        String sql = getUpdateQuery();
        
        log.info("Update Object " + object.toString());
        
        log.debug("Open connection & Create prepared statement");
        try (Connection connection = daoFactory.getConnection();
        	PreparedStatement statement = connection.prepareStatement(sql);) {
	            prepareStatementForUpdate(statement, object); // заполнение аргументов запроса оставим на совесть потомков
	            
	            log.debug("Get count of updates result set");
	            int count = statement.executeUpdate();
	            if (count != 1){
	            	String s = "On update modify more then 1 record: ";
	        		log.error(s);
	                throw new PersistException(s + count);
	            }
	    
	    log.debug("statement & connection closed");
        } catch (Exception e) {
        	log.debug("statement & connection closed");
        	log.error("Cannot update Object " + object.toString(), e);
            throw new PersistException(e);
        }
    }
    
    public AbstractJDBCDao(DaoFactory daoFactory) {
        this.daoFactory = daoFactory;
    }
}
