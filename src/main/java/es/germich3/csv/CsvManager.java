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
	
	private CsvManager(char columnSeparator, String arrayElementSeparator) {
		this.columnSeparator = columnSeparator;
		this.arrayElementSeparator = arrayElementSeparator;
	}
	
	public static CsvManager withDefaultSeparators() {
		return new CsvManager(',', ";");
	}
	
	public static CsvManager withSeparators(char columnSeparator, String arrayElementSeparator) {
		return new CsvManager(columnSeparator, arrayElementSeparator);
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
	
}
