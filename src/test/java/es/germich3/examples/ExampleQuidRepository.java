package es.germich3.examples;

import java.sql.SQLException;
import java.util.List;

import es.germich3.h2.H2Manager;
import es.germich3.h2.QuidRepository;

public class ExampleQuidRepository implements QuidRepository<ExampleData> {

	private H2Manager manager;
	
	public ExampleQuidRepository(H2Manager manager) {
		this.manager = manager;
	}
	
	@Override
	public List<ExampleData> findAll() throws SQLException {
		return this.manager.query(ExampleData.class, "SELECT * FROM EXAMPLEDATA");
	}

	@Override
	public boolean insert(ExampleData bean) throws SQLException {
		return this.manager.update("INSERT INTO EXAMPLEDATA (ALIAS, X, Y, Z) VALUES (?,?,?,?)", bean.getAlias(), bean.getX(), bean.getY(), bean.getZ()) > 0;
	}

	@Override
	public boolean update(ExampleData bean) throws SQLException {
		return this.manager.update("UPDATE EXAMPLEDATA SET ALIAS=?, X=?, Y=?, Z=? WHERE ALIAS=?", bean.getAlias(), bean.getX(), bean.getY(), bean.getZ(), bean.getAlias()) > 0;
	}

	@Override
	public boolean delete(ExampleData bean) throws SQLException {
		return this.manager.update("DELETE FROM EXAMPLEDATA WHERE ALIAS=?", bean.getAlias()) > 0;
	}
	
	public ExampleData findByAlias(String alias) throws SQLException {
		List<ExampleData> result = this.manager.query(ExampleData.class, "SELECT * FROM EXAMPLEDATA WHERE ALIAS=?", alias);
		return (result.size() > 0) ? result.get(0) : null;
	}

}
