package es.germich3.csv;

import java.io.File;
import java.nio.file.Path;
import java.util.List;

import com.fasterxml.jackson.databind.MappingIterator;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;

import es.germich3.exceptions.DataStorageException;
import es.germich3.utils.Utils;

public class CsvManager {

	private static final CsvMapper CSV_MAPPER = new CsvMapper();
	
	private char columnSeparator;
	private String arrayElementSeparator;
	
	public CsvManager(char columnSeparator, String arrayElementSeparator) {
		this.columnSeparator = columnSeparator;
		this.arrayElementSeparator = arrayElementSeparator;
	}
	
	private CsvManager() {
		this(',', ";");
	}
	
	public <T> void save(Path path, String fileName, List<T> list) throws DataStorageException {
		try {
			if (Utils.notNullnotEmpty(list)) {
				CsvSchema schema = CSV_MAPPER.typedSchemaFor(list.get(0).getClass())
											 .withHeader()
											 .withArrayElementSeparator(this.arrayElementSeparator)
											 .withColumnSeparator(this.columnSeparator);
				CSV_MAPPER.writer(schema).writeValues(new File(path.toFile(), fileName + ".csv")).writeAll(list);
			}
		} 
		catch (Exception e) {
            throw new DataStorageException("Could not deserialize", e);
		}
	}
	
	public <T> List<T> load(Path path, String fileName, Class<T> clazz) throws DataStorageException {
		try {
			CsvSchema schema = CSV_MAPPER.typedSchemaFor(clazz)
										 .withHeader()
										 .withArrayElementSeparator(this.arrayElementSeparator)
										 .withColumnSeparator(this.columnSeparator);
			MappingIterator<T> it = CSV_MAPPER.reader(schema).forType(clazz).readValues(new File(path.toFile(), fileName + ".csv"));
			return it.readAll();
		} 
		catch (Exception e) {
            throw new DataStorageException("Could not serialize", e);
		}
	}
	
	public static <T> List<T> loadWithDefaultDelimiters(Path path, String fileName, Class<T> clazz) throws DataStorageException {
		return new CsvManager().load(path, fileName, clazz);
	}
	
	public static <T> void saveWithDefaultDelimiters(Path path, String fileName, List<T> list) throws DataStorageException {
		new CsvManager().save(path, fileName, list);
	}
	
}
