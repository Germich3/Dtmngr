package es.germich3.configuration;

public class ConfigurationComments {

    private String key;
    private String comment;
    private int blancLinesBefore;

    private ConfigurationComments(String key, String comment, int blancLinesBefore) {
        this.key = key;
        this.comment = comment;
        this.blancLinesBefore = blancLinesBefore;
    }

    public static ConfigurationComments addComment(String key, String comment, int blancLinesBefore) {
        return new ConfigurationComments(key, comment, blancLinesBefore);
    }

    public String getKey() {
        return key;
    }

    public String getComment() {
        return comment;
    }

    public int getBlancLinesBefore() {
        return blancLinesBefore;
    }

}
