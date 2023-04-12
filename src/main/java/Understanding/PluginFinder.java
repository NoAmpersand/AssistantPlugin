package Understanding;

import edu.stanford.nlp.pipeline.StanfordCoreNLP;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import Object.Request;

public class PluginFinder {
    private static final String REGEX_CALENDAR = "\\b(programme|programe|program|journee|journée|journé|semaine|rdv|rendez|heure|verrai)\\b";

    public String understandingPlugin(String text){
        Pattern pattern = Pattern.compile(REGEX_CALENDAR);
        Matcher matcher = pattern.matcher(text);
        if (matcher.find()) {
            return "calendar";
        } else{
            return "contact";
        }
    }

    static Scanner sc = new Scanner(System.in);
    public static void main(String[] args) {
        StanfordCoreNLP stanfordCoreNLP = Pipeline.getPipelineApplication();
        String text;
        do{
        Request request = new Request();
        RequestHandler requestHandler;
            PluginFinder p = new PluginFinder();
            text = sc.nextLine();
            String plugin = p.understandingPlugin(text);
            if (plugin.equals("contact")) {
                requestHandler = new ContactRequestHandler();
            } else {
                requestHandler = new CalendarRequestHandler();
            }
            requestHandler.requestParser(request, text, stanfordCoreNLP);
            System.out.println(requestHandler.understandingRequest(request));
        } while(!text.equals('s'));
    }
}
