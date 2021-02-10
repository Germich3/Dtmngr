package es.germich3.h2;

import java.io.Closeable;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.DbUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.ResultSetHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.h2.tools.RunScript;

import es.germich3.utils.Utils;

public class H2Manager implements Closeable {

	private String url;
	private Connection connection;
	private QueryRunner runner;
	
	public H2Manager(String path, String fileName, URL resource) throws IOException, SQLException, URISyntaxException {
		String filePath = path + "\\" + fileName;
		boolean exists = new File(Utils.addH2Extension(filePath)).exists();
		this.url = "jdbc:h2:" + filePath + ";TRACE_LEVEL_FILE=0";
		this.connection = DriverManager.getConnection(this.url, "sa", "");
		this.runner = new QueryRunner();
		initializeIfNeeded(filePath, exists, resource);
	}
	
	public H2Manager(String path, String fileName) throws IOException, SQLException, URISyntaxException {
		this(path, fileName, null);
	}
	
	public H2Manager(String path, URL resource) throws IOException, SQLException, URISyntaxException {
		this(path, "h2", resource);
	}
	
	public H2Manager(String path) throws IOException, SQLException, URISyntaxException {
		this(path, "h2", null);
	}
	
	private void initializeIfNeeded(String filePath, boolean init, URL resource) throws IOException, SQLException, URISyntaxException {
		try {
			if (!init && resource != null) {
				loadScriptSQL(resource, false);
			}
		}
		catch (SQLException | URISyntaxException e) {
			close();
			Files.delete(Paths.get(RepositoryUtils.addH2Extension(filePath)));
			throw e;
		}
	}
	
	@Override
	public void close() {
		DbUtils.closeQuietly(this.connection);
	}
	
	public void loadScriptSQL(URL resource, boolean continueOnError) throws SQLException, URISyntaxException {
		RunScript.execute(this.url, "sa", "", Paths.get(resource.toURI()).toFile().toString(), StandardCharsets.UTF_8, continueOnError);
	}
	
	public int update(String sql, Object... parameters) throws SQLException {
		return this.runner.execute(this.connection, sql, parameters);
	}
	
	public <T> List<T> query(Class<T> beanClass, String sql, Object... parameters) throws SQLException {
		ResultSetHandler<List<T>> handler = new BeanListHandler<>(beanClass);
		return this.runner.query(this.connection, sql, handler, parameters);
	}
	
}
