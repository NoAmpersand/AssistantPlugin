package assistant;

import ApiManager.ContactManager;
import ApiRequest.ContactRequest;
import Instanciation.ContactCreation;
import Response.ContactResponse;
import Services.Service;
import Settings.Constants;
import Understanding.*;
import View.AssistantInput;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.services.people.v1.PeopleService;
import com.google.api.services.people.v1.model.Address;
import com.google.api.services.people.v1.model.Name;
import com.google.api.services.people.v1.model.SearchResponse;
import com.google.api.services.people.v1.model.SearchResult;

import Object.Contact;
import edu.stanford.nlp.pipeline.StanfordCoreNLP;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.sql.SQLOutput;


import java.io.IOException;
import java.security.GeneralSecurityException;

import java.util.List;
import java.util.Scanner;
import Object.Request;


public class Main {
    public static void main(String[] args) throws IOException, GeneralSecurityException, InterruptedException {
        ContactManager contactManager = new ContactManager();
        ContactRequest contactRequest = new ContactRequest();
        ContactCreation contactCreation = new ContactCreation();
        Service service = new Service();
        ContactResponse response = new ContactResponse();
        final NetHttpTransport HTTP_TRANSPORT = GoogleNetHttpTransport.newTrustedTransport();
        PeopleService people =
                new PeopleService.Builder(HTTP_TRANSPORT, Constants.JSON_FACTORY, contactManager.getCredentials(HTTP_TRANSPORT))
                        .setApplicationName(Constants.APPLICATION_NAME)
                        .build();

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
            //System.out.println(requestHandler.understandingRequest(request));
            response.getResponse(requestHandler.understandingRequest(request), request,people);
        } while(!text.equals("stop"));
    }
/*
        if (contactRequest.findContactByName("naruto", people).getResults() != null) {
            for (SearchResult result : contactRequest.findContactByName("naruto", people).getResults()) {
                Contact malik = contactCreation.createContact(result.getPerson());
                System.out.println(malik.getName());
                System.out.println(malik.getEmailAddress());
                System.out.println(malik.getAddress());
                System.out.println(malik.getBirthday());
                System.out.println(malik.getPhoneNumber());
                System.out.println(malik.getAge());
            }
        }


                //System.out.println(malik.getAge());
                //System.out.println(malik.getBirthday());
                //System.out.println(result.getPerson().getBirthdays().get(0).getDate().getYear());

            }
        } else {
            System.out.println("aucun resultat");
        }


        //System.out.println(contactRequest.findInhabitants("Paris", service));


        //contactRequest.findInhabitants("Paris", service);


        for(SearchResult result :  contactRequest.findContactByName("jiraya",service).getResults()){
            System.out.println(result.getPerson().getBirthdays());
        }


        contactRequest.findAgeRange(service, 40);


        for(Contact contact : contactRequest.findAgeRange(service, 40)){
            System.out.println(contact.getName());
        }

 */
    }

