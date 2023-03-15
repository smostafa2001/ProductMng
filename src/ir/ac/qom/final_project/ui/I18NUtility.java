package ir.ac.qom.final_project.ui;

import java.util.Locale;
import java.util.ResourceBundle;

public class I18NUtility {
    static ResourceBundle resourceBundle;
    static String language = "en";

    static {
        resourceBundle = ResourceBundle.getBundle("META-INF/ResourceBundle/Messages", new Locale(language));
    }

     public static String getMessage(String key){
        return resourceBundle.getString(key);
    }

    public static void setLanguage(String language){
        I18NUtility.language=language;
        resourceBundle = ResourceBundle.getBundle("META-INF/ResourceBundle/Messages", new Locale(language));
    }

    public static String getLanguage(){
        return language;
    }
}
