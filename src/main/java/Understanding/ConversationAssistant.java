package Understanding;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.Scanner;
import Object.Request;
import edu.stanford.nlp.pipeline.StanfordCoreNLP;

public class ConversationAssistant {
    public static void main(String[] args) throws GeneralSecurityException, IOException {
        Scanner sc = new Scanner(System.in);
        PluginFinder pf = new PluginFinder();
        RequestHandler requestHandler;
        StanfordCoreNLP stanfordCoreNLP = Pipeline.getPipelineApplication();
        String text;
        do {
            Request request = new Request();
            text = sc.nextLine();
            String plugin = pf.understandingPlugin(text);
            if(plugin.equals("contact")) {
                requestHandler = new ContactRequestHandler();
            }else{
                requestHandler = new CalendarRequestHandler();
            }
            requestHandler.requestParser(request, text, stanfordCoreNLP);
            System.out.println(requestHandler.understandingRequest(request));
        } while(!text.equals("stop"));
    }
}
