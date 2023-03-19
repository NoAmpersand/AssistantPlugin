package Understanding;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PluginFinder {
    private static final String REGEX_CALENDAR = "\\b(programme|programe|program|journee|journée|journé|semaine|rdv|rendez)\\b";

    public String understandingPlugin(String text){
        Pattern pattern = Pattern.compile(REGEX_CALENDAR);
        Matcher matcher = pattern.matcher(text);
        if (matcher.find()) {
            return "calendar";
        } else{
            return "contact";
        }
    }
}
