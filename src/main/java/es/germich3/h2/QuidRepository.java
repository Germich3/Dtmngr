package es.germich3.h2;

import java.sql.SQLException;
import java.util.List;

public interface QuidRepository<T> {

	List<T> findAll() throws SQLException;
	
	boolean insert(T bean) throws SQLException;
	
	boolean update(T bean) throws SQLException;
	
	boolean delete(T bean) throws SQLException;
	
}
