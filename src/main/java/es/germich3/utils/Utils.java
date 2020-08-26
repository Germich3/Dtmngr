package es.germich3.utils;

import java.util.Collection;

public class Utils {

    private Utils(){}
    
    public static String getConfigFileName(Object object) {
        return object.getClass().getSimpleName().concat(".config");
    }

    public static <T> String getConfigFileName(Class<T> clazz) {
        return clazz.getSimpleName().concat(".config");
    }

    public static String addJsonExtension(String fileName) {
        return fileName.concat(".json");
    }
    
    public static String addH2Extension(String fileName) {
        return fileName.concat(".mv.db");
    }
    
    public static String addCsvExtension(String fileName) {
        return fileName.concat(".csv");
    }
    
    public static <E> boolean notNullnotEmpty(Collection<E> collection) {
    	return (collection != null) && !collection.isEmpty();
    }

}
