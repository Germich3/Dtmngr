package es.germich3.examples;

import es.germich3.configuration.ConfigurationComments;
import es.germich3.configuration.ConfigurationTemplate;

import java.util.Arrays;
import java.util.List;

public class ExampleConfigParent implements ConfigurationTemplate {

    public int exampleInt;
    public String exampleString = "this is a String";
    public ExampleConfigChild child = new ExampleConfigChild();

    @Override
    public List<ConfigurationComments> getComments() {
        return Arrays.asList(
                ConfigurationComments.addComment("exampleInt", "This a generic comment", 0),
                ConfigurationComments.addComment("child.value", "This is a child property", 1)
        );
    }

}
