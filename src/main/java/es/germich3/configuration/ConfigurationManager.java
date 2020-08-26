package es.germich3.configuration;

import java.io.File;
import java.io.FileWriter;
import java.nio.file.Path;

import org.apache.commons.configuration2.MapConfiguration;
import org.apache.commons.configuration2.PropertiesConfiguration;

import com.fasterxml.jackson.dataformat.javaprop.JavaPropsMapper;

import es.germich3.exceptions.DataStorageException;
import es.germich3.utils.Utils;

public class ConfigurationManager {

    private ConfigurationManager(){}

    private static final JavaPropsMapper PROP_MAPPER = new JavaPropsMapper();
    
    public static <T extends ConfigurationTemplate> T loadConfiguration(Path path, Class<T> clazz) throws DataStorageException {
        try {
            return PROP_MAPPER.readValue(new File(path.toFile(), Utils.getConfigFileName(clazz)), clazz);
        } catch (Exception e) {
            throw new DataStorageException("Could not deserialize", e);
        }
    }

    public static void saveConfiguration(Path path, ConfigurationTemplate config) throws DataStorageException {
        try {
            MapConfiguration map = new MapConfiguration(PROP_MAPPER.writeValueAsMap(config));
            PropertiesConfiguration properties = new PropertiesConfiguration();
            properties.copy(map);
            for (ConfigurationComments comment : config.getComments()) {
                properties.getLayout().setComment(comment.getKey(), comment.getComment());
                properties.getLayout().setBlancLinesBefore(comment.getKey(), comment.getBlancLinesBefore());
            }
            try (FileWriter writer = new FileWriter(new File(path.toFile(), Utils.getConfigFileName(config)))) {
                properties.write(writer);
            }
        }
        catch (Exception e) {
            throw new DataStorageException("Could not serialize", e);
        }
    }

}
