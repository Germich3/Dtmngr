package es.germich3.json;

import java.io.File;
import java.nio.file.Path;

import com.fasterxml.jackson.databind.json.JsonMapper;

import es.germich3.exceptions.DataStorageException;
import es.germich3.utils.Utils;

public class JsonManager {

    private JsonManager(){}

    private static final JsonMapper JSON_MAPPER = new JsonMapper();
    
    public static <T> T load(Path path, String fileName, Class<T> clazz) throws DataStorageException {
        try {
            return JSON_MAPPER.readValue(new File(path.toFile(), Utils.addJsonExtension(fileName)), clazz);
        } catch (Exception e) {
            throw new DataStorageException("Could not deserialize", e);
        }
    }

    public static void save(Path path, String fileName, Object obj) throws DataStorageException {
        try {
        	JSON_MAPPER.writerWithDefaultPrettyPrinter().writeValue(new File(path.toFile(), Utils.addJsonExtension(fileName)), obj);
        }
        catch (Exception e) {
            throw new DataStorageException("Could not serialize", e);
        }
    }

}
