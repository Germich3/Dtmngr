package es.germich3;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

import es.germich3.configuration.ConfigurationManager;
import es.germich3.csv.CsvManager;
import es.germich3.examples.ExampleConfigParent;
import es.germich3.examples.ExampleData;
import es.germich3.examples.ExampleQuidRepository;
import es.germich3.h2.H2Manager;
import es.germich3.json.JsonManager;

public class ExampleTest {

    private static void test1() throws Exception {

        //FIXME: Some valid dir
        Path path = Paths.get("some dir");

        ConfigurationManager.saveConfiguration(path, new ExampleConfigParent());

        ExampleConfigParent config = ConfigurationManager.loadConfiguration(path, ExampleConfigParent.class);

        System.out.println("exampleInt = " + config.exampleInt);
        System.out.println("exampleString = " + config.exampleString);
        System.out.println("child.value = " + config.child.value);

    }

    private static void test2() throws Exception {

        //FIXME: Some valid dir
        Path path = Paths.get("Some dir");

        ExampleData home = new ExampleData();
        home.setAlias("home");
        home.setX(720);
        home.setY(60);
        home.setZ(-200);
        ExampleData other = new ExampleData();
        other.setAlias("other");
        other.setX(280);
        other.setY(75);
        other.setZ(-800);

        JsonManager.save(path, "germich3", Arrays.asList(home, other));
        
        @SuppressWarnings("unchecked")
		Class<List<ExampleData>> clazz = (Class<List<ExampleData>>)(Object)List.class;
        
        List<ExampleData> copy = JsonManager.load(path, "germich3", clazz);
        //ExampleData[] copy = JsonManager.load(path, "germich3", ExampleData[].class);
        
        System.out.println(copy);

    }
    
    private static void test3() throws Exception {

        //FIXME: Some valid dir
        Path path = Paths.get("Some dir");

        try (H2Manager manager = new H2Manager(path.toString(), ExampleTest.class.getClassLoader().getResource("script.sql"))) {
        	
        	//manager.loadScriptSQL(ExampleTest.class.getClassLoader().getResource("script.sql"), true);
        	
        	ExampleData home = new ExampleData();
            home.setAlias("home");
            home.setX(720);
            home.setY(60);
            home.setZ(-200);
            
            ExampleData other = new ExampleData();
            other.setAlias("other");
            other.setX(280);
            other.setY(75);
            other.setZ(-800);
            
            ExampleQuidRepository exampleDataRepository = new ExampleQuidRepository(manager);
            
            System.out.println("INSERT HOME = " + exampleDataRepository.insert(home));
            System.out.println("INSERT OTHER = " + exampleDataRepository.insert(other));
            System.out.println("FIND ALL = " + exampleDataRepository.findAll());
            home.setX(300);
            System.out.println("UPDATE HOME = " + exampleDataRepository.update(home));
            System.out.println("FIND BY ALIAS = " + exampleDataRepository.findByAlias("home"));
            System.out.println("DELETE OTHER = " + exampleDataRepository.delete(other));
        	
        }
        catch (Exception e) {
			e.printStackTrace();
		}

    }
    
    private static void test4() throws Exception {

        //FIXME: Some valid dir
        Path path = Paths.get("Some dir");

        try {
        	
        	ExampleData home = new ExampleData();
            home.setAlias("home");
            home.setX(720);
            home.setY(60);
            home.setZ(-200);
            
            ExampleData other = new ExampleData();
            other.setAlias("other");
            other.setX(280);
            other.setY(75);
            other.setZ(-800);
            
            CsvManager.saveWithDefaultDelimiters(path, "germich3", Arrays.asList(home, other));
            
            List<ExampleData> result = CsvManager.loadWithDefaultDelimiters(path, "germich3", ExampleData.class);
        	
            System.out.println(result);
            
        }
        catch (Exception e) {
			e.printStackTrace();
		}

    }

    //Test
    public static void main(String[] args) throws Exception {
    	test1();
    	test2();
        test3();
        test4();
    }

}
