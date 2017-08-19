package main.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;

/**
 * ����������� ����� ��������������� ������� ���������� CRUD �������� � �������������� JDBC.
 *
 * @param <T>  ��� ������� ������������
 * @param <PK> ��� ���������� �����
 */
public abstract class AbstractJDBCDao<T extends Identified <PK>, PK extends Integer>{

	
	private DaoFactory daoFactory;

    /**
     * ���������� sql ������ ��� ��������� ���� �������.
     * <p/>
     * SELECT * FROM [Table]
     */
    public abstract String getSelectQuery();
    
    /**
     * ���������� sql ������ ��� ������� ����� ������ � ���� ������.
     * <p/>
     * INSERT INTO [Table] ([column, column, ...]) VALUES (?, ?, ...);
     */
    public abstract String getCreateQuery();
    
    /**
     * ���������� sql ������ ��� ���������� ������.
     * <p/>
     * UPDATE [Table] SET [column = ?, column = ?, ...] WHERE id = ?;
     */
    public abstract String getUpdateQuery();
    
    /**
     * ���������� sql ������ ��� �������� ������ �� ���� ������.
     * <p/>
     * DELETE FROM [Table] WHERE id= ?;
     */
    public abstract String getDeleteQuery();

    
    /**
     * ��������� ResultSet � ���������� ������ �������� ��������������� ����������� ResultSet.
     * @throws PersistException 
     */
    protected abstract List<T> parseResultSet(ResultSet rs) throws PersistException;
    
    /**
     * ������������� ��������� insert ������� � ������������ �� ��������� ����� ������� object.
     */
    protected abstract void prepareStatementForInsert(PreparedStatement statement, T object) throws PersistException;

    /**
     * ������������� ��������� update ������� � ������������ �� ��������� ����� ������� object.
     */
    protected abstract void prepareStatementForUpdate(PreparedStatement statement, T object) throws PersistException;

    
    public T getByPK(int key) throws PersistException {    	
        List<T> list;
        String sql = getSelectQuery();
        sql += " WHERE id = ?";
        try (Connection connection = daoFactory.getConnection();
        	PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, key);
            ResultSet rs = statement.executeQuery();
            list = parseResultSet(rs);
        } catch (Exception e) {
            throw new PersistException(e);
        }
        
        if (list == null || list.size() == 0) {
            return null;
        }
        if (list.size() > 1) {
            throw new PersistException("Received more than one record.");
        }
        return list.iterator().next();
    }
    
    public List<T> getAll() throws PersistException {
        List<T> list;
        String sql = getSelectQuery();
        try (Connection connection = daoFactory.getConnection();
        	PreparedStatement statement = connection.prepareStatement(sql);
        	ResultSet rs = statement.executeQuery()) {            
            list = parseResultSet(rs);
        } catch (Exception e) {
            throw new PersistException(e);
        }
        return list;
    }    
   
    public T persist(T object) throws PersistException {
        if (object.getId() != null)
            throw new PersistException("Object is already persist.");
        T persistInstance;
        // ��������� ������
        String sql = getCreateQuery();
        try (Connection connection = daoFactory.getConnection();
        	PreparedStatement statement = connection.prepareStatement(sql)) {
            prepareStatementForInsert(statement, object);
	            int count = statement.executeUpdate();
	            if (count != 1) 
	                throw new PersistException("On persist modify more then 1 record: " + count);
        } catch (Exception e) {
            throw new PersistException(e);
        }
        // �������� ������ ��� ����������� ������
        sql = getSelectQuery() + " WHERE id = last_insert_id();";
        try (Connection connection = daoFactory.getConnection();
        	PreparedStatement statement = connection.prepareStatement(sql);
        	ResultSet rs = statement.executeQuery()) {            
            List<T> list = parseResultSet(rs);
            if ((list == null) || (list.size() != 1))
                throw new PersistException("Exception on findByPK new persist data.");
            persistInstance = list.iterator().next();
        } catch (Exception e) {
            throw new PersistException(e);
        }
        return persistInstance;
    }
   
    public void delete(T object) throws PersistException {
        String sql = getDeleteQuery();
        try (Connection connection = daoFactory.getConnection();
        	PreparedStatement statement = connection.prepareStatement(sql)) {
            try {
                statement.setObject(1, object.getId());
            } catch (Exception e) {
                throw new PersistException(e);
            }
            int count = statement.executeUpdate();
            if (count != 1)
                throw new PersistException("On delete modify more then 1 record: " + count);
        } catch (Exception e) {
            throw new PersistException(e);
        }
    }
    
   
    public void update(T object) throws PersistException {
        String sql = getUpdateQuery();
        try (Connection connection = daoFactory.getConnection();
        	PreparedStatement statement = connection.prepareStatement(sql);) {
	            prepareStatementForUpdate(statement, object); // ���������� ���������� ������� ������� �� ������� ��������
	            int count = statement.executeUpdate();
	            if (count != 1)
	                throw new PersistException("On update modify more then 1 record: " + count);
        } catch (Exception e) {
            throw new PersistException(e);
        }
    }
    
    public AbstractJDBCDao(DaoFactory daoFactory) {
        this.daoFactory = daoFactory;
    }
}