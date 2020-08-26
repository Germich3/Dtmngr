package es.germich3.configuration;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.List;

public interface ConfigurationTemplate {

    @JsonIgnore
    List<ConfigurationComments> getComments();

}
