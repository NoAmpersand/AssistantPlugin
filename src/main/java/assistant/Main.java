package assistant;

import ApiManager.ContactManager;
import ApiRequest.ContactRequest;
import Instanciation.ContactCreation;
import Response.ContactResponse;
import Services.Service;
import Settings.Constants;
import Understanding.*;
import View.Assistant;
import View.AssistantInput;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.services.people.v1.PeopleService;


import edu.stanford.nlp.pipeline.StanfordCoreNLP;

import java.io.IOException;
import java.security.GeneralSecurityException;

import java.util.Scanner;
import Object.Request;


public class Main {
    public static void main(String[] args) throws IOException, GeneralSecurityException, InterruptedException {
        ContactManager contactManager = new ContactManager();
        ContactResponse response = new ContactResponse();
        AssistantInput assistant = new AssistantInput();
        final NetHttpTransport HTTP_TRANSPORT = GoogleNetHttpTransport.newTrustedTransport();
        PeopleService people =
                new PeopleService.Builder(HTTP_TRANSPORT, Constants.JSON_FACTORY, contactManager.getCredentials(HTTP_TRANSPORT))
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
            if(plugin.equals("contact")) {
                requestHandler = new ContactRequestHandler();
            }else{
                requestHandler = new CalendarRequestHandler();
            }
            requestHandler.requestParser(request, text, stanfordCoreNLP);
            response.getResponse(requestHandler.understandingRequest(request), request,people);
        } while(!text.equals("stop"));
    }

    }

