package assistant;

import ApiManager.CalendarManager;
import ApiManager.ContactManager;
import Response.CalendarResponse;
import Response.ContactResponse;
import Settings.Constants;
import Understanding.*;
import View.AssistantInput;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.services.calendar.Calendar;
import com.google.api.services.people.v1.PeopleService;


import edu.stanford.nlp.pipeline.StanfordCoreNLP;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.text.ParseException;

import Object.Request;


public class Main {
    public static void main(String[] args) throws IOException, GeneralSecurityException, InterruptedException, ParseException {
        ContactManager contactManager = new ContactManager();
        CalendarManager calendarManager = new CalendarManager();
        ContactResponse contactResponse = new ContactResponse();
        CalendarResponse calendarResponse = new CalendarResponse();
        AssistantInput assistant = new AssistantInput();
        final NetHttpTransport HTTP_TRANSPORT = GoogleNetHttpTransport.newTrustedTransport();
        PeopleService people =
                new PeopleService.Builder(HTTP_TRANSPORT, Constants.JSON_FACTORY, contactManager.getCredentials(HTTP_TRANSPORT))
                        .setApplicationName(Constants.APPLICATION_NAME)
                        .build();
        Calendar service =
                new Calendar.Builder(HTTP_TRANSPORT, Constants.JSON_FACTORY, calendarManager.getCredentials(HTTP_TRANSPORT))
                        .setApplicationName(Constants.APPLICATION_NAME)
                        .build();


        PluginFinder pf = new PluginFinder();
        RequestHandler requestHandler;
        StanfordCoreNLP stanfordCoreNLP = Pipeline.getPipelineApplication();
        String text;
        do {
            Request request = new Request();
            assistant.talk("Que souhaitez vous faire ?");
            text = assistant.askUserRequest();
            String plugin = pf.understandingPlugin(text);
            if (plugin.equals("contact")) {
                requestHandler = new ContactRequestHandler();
                requestHandler.requestParser(request, text, stanfordCoreNLP);
                contactResponse.getResponse(requestHandler.understandingRequest(request), request, people);
            } else {
                requestHandler = new CalendarRequestHandler();
                requestHandler.requestParser(request, text, stanfordCoreNLP);
                calendarResponse.getResponse(requestHandler.understandingRequest(request), request, service);
            }

        } while (!text.equals("stop"));
    }

}

